/**
 * 
 */
package com.megatimgroup.generic.jsf.mbean.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatimgroup.generic.jsf.layer.mbean.processor.TypeOperation;
import com.megatimgroup.generic.jsf.layer.mbean.processor.ValidateAndFillBeans;
import com.megatimgroup.generic.jsf.mbean.pagination.PaginationStep;
import com.megatimgroup.generic.jsf.mbean.pagination.PaginationStepIterator;
import javax.naming.NamingException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * @author DEV_4
 * @param <T>
 * @param <PK>
 *
 */
public abstract class AbstractGenericBean<T extends Object , PK extends Serializable> {

	/**
	 * Current 
	 */
	protected T currentObject ;
	
	protected Object managedBean = null ;
        
	protected TypeOperation typeOperation = TypeOperation.NEW;
	
	private PaginationStep pagination =null;
	//private JPanel middlePanel =null;
        List<T> searchsResult = null;
	
	/**
	 * 
	 */
	public AbstractGenericBean() {
		// TODO Auto-generated constructor stub
	}

	protected void initialise(Object obj) throws IllegalArgumentException, IllegalAccessException, NamingException{
		
		managedBean = obj ;
		
		//loader = new InjectManagerLoader(managedBean);
		
		//loader.lookup();
	}
	/**
	 * Recupere les valeurs et construit des objects
	 * @throws IllegalAccessException 
	 */
	public void collecteCurrentObjectData() throws IllegalAccessException{
		
		if(managedBean==null)
			       return ;
		//Validation et transfert du contenu
		if(ValidateAndFillBeans.validateBean(managedBean)){
			currentObject = ValidateAndFillBeans.fillBean(managedBean, currentObject);
		}
		
	}
	
	public void save() throws IllegalAccessException{
		
		//Initialisation des champs du currentOject
		collecteCurrentObjectData();
		//Verification si il ya une entite
		if(currentObject==null||getPrimaryKey(currentObject)==null)
			return ;
		//Verification de l'existance d'un manager
		if(getManager()==null)
			     return ;
		//Verification de la pr�condition		
		boolean precondition = beforeSave();
		
		if(!precondition)
			           return ;
		//Sauvegarde de currentObject 
		if(typeOperation==TypeOperation.NEW){
			 
			getManager().save(currentObject);
		}else if(typeOperation==TypeOperation.UPDATE){
			getManager().update(getPrimaryKey(currentObject), currentObject);
		}
		//Validation de la post condition
		afterSave();
	}
	
	/**
	 * Reinitialisation des champs
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public void reset() throws IllegalArgumentException, IllegalAccessException{
		
		if(managedBean!=null)
			ValidateAndFillBeans.cleanBeanField(managedBean);
	}
	/**
	 * 
	 * @return
	 */
	public T getCurrentObject() {
		return currentObject;
	}
	
	
	/**
	 * 
	 * @param currentObject
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public void setCurrentObject(T currentObject) throws IllegalArgumentException, IllegalAccessException {
		this.currentObject = currentObject;
		
		boolean status = beforeSetCurrentObject();
		
		if(!status)
			    return ;
		
		if(managedBean==null)
			                  return ;
		
		if(currentObject!=null&&managedBean!=null){
			ValidateAndFillBeans.fillViewBean(currentObject, managedBean);
		}
		
		if(typeOperation==TypeOperation.UPDATE){
			ValidateAndFillBeans.updatableAnnotationProcessor(managedBean);
		}else if(typeOperation==TypeOperation.VIEW){
			ValidateAndFillBeans.disableAllFields(managedBean);
		}
		
		//Action apres mise a jour
		afterSetCurrentObject();
	}
	
	/**
	 * 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public void search() throws IllegalArgumentException, IllegalAccessException{
		
		//Non nullit� des criteres
		if(managedBean==null) return ;
		//non nulitte du manager
		if(getManager()==null) return ;
		//Validite des valeurs des crit�res
		if(!ValidateAndFillBeans.validateBean(managedBean))
			                   return  ;
		//Constructeur de contenaur de pr�dicats
		RestrictionsContainer container = ValidateAndFillBeans.buildSearchCriteria(managedBean);
		//
		//List<T> resultats = new ArrayList<T>();
		
		if(getPagination()==null){
			searchsResult = getManager().filter(container.getPredicats(), ValidateAndFillBeans.getOrdersCriteria(), null, 0 , -1);
		}else {
			searchsResult = getManager().filter(container.getPredicats(), ValidateAndFillBeans.getOrdersCriteria(), null, 0 , -1);
		}
		//Raffraichissement des donn�es
		//refresh(resultats);
	}
	
	/**
	 * 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public void nextStep() throws IllegalArgumentException, IllegalAccessException{
		
		
		//Initialisation de la pagination si ce n'est pas le cas
		if(pagination==null){
			pagination = getPagination();
			pagination.setSize(count());
		}
		if(pagination==null) return ;
		
		//Recuperation de l'iterateur
		PaginationStepIterator iterator = getPagination().iterator();
		//Verification de la possibilite d'avancer
		if(!iterator.hasNext())  return ;
		//Recuperer l'etape suivante
		pagination = iterator.next();
		//recherche des donn�es
		search();
	}
	
	/**
	 * 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public void previousStep() throws IllegalArgumentException, IllegalAccessException{
		
		//Initialisation de la pagination
		if(pagination==null){
			pagination = getPagination();
			pagination.setSize(count());
		}
		if(pagination==null)
			        return ;
		//Recuperation de l'iterateur
		PaginationStepIterator iterator = getPagination().iterator();
		//Verification de la possibilite d'avancer
		if(!iterator.hasPrevious())  return ;
		//Recuperer l'etape suivante
		pagination = iterator.previous();
		//recherche des donn�es
		search();
	}
	
	/**
	 * 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public void firstStep() throws IllegalArgumentException, IllegalAccessException{
		
		//Initialisation de la pagination
		if(pagination==null){
			pagination = getPagination();
			pagination.setSize(count());
		}
		if(pagination==null)
			        return ;
		//Recuperation de l'iterateur
		PaginationStepIterator iterator = getPagination().iterator();
		
		while(iterator.hasPrevious()){
			//Recuperer l'etape suivante
			pagination = iterator.previous();
		}
		
		//recherche des donn�es
		search();
	}
	
	/**
	 * 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public void lastStep() throws IllegalArgumentException, IllegalAccessException{
		//Initialisation de la pagination
		if(pagination==null){
			pagination = getPagination();
			pagination.setSize(count());
		}
		if(pagination==null)
			        return ;
		//Recuperation de l'iterateur
		PaginationStepIterator iterator = getPagination().iterator();
		
		while(iterator.hasNext()){
			//Recuperer l'etape suivante
			pagination = iterator.next();
		}
		
		//recherche des donn�es
		search();
	}
	/**
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public long count() throws IllegalArgumentException, IllegalAccessException{
		
		//Non nullit� des criteres
		if(managedBean==null) return 0;
		//non nulitte du manager
		if(getManager()==null) return 0;
		//Validite des valeurs des crit�res
		if(!ValidateAndFillBeans.validateBean(managedBean))
			                   return  0;
		//Constructeur de contenaur de pr�dicats
		RestrictionsContainer container = ValidateAndFillBeans.buildSearchCriteria(managedBean);
		//
		List<T> resultats = new ArrayList<T>();
		
		if(getPagination()==null){
			resultats = getManager().filter(container.getPredicats(), ValidateAndFillBeans.getOrdersCriteria(), null, 0 , -1);
		}else {
			resultats = getManager().filter(container.getPredicats(), ValidateAndFillBeans.getOrdersCriteria(), null, 0 , -1);
		}
		
		return resultats.size();
	}

	/**
	 * Suppression des donn�es du SI
	 */
	public void delete(){
		
		if(getSelectedObjects()==null)
			                       return ;
		
		boolean status = beforeDelete();
		
		if(!status)
			     return ;
		
		List<T> objects = getSelectedObjects();
		
		for(T object : objects){
			getManager().delete(getPrimaryKey(object));
		}
		
		afterDelete();
	}	
	
        public void imprimer() throws JRException{
            
            
                if(searchsResult==null||searchsResult.isEmpty())
                    return ;
                
                List<T> datas = new ArrayList<T>();
                //Aucun ligne selectionn�
                if(getSelectedObjects()==null){
                    
                    datas = searchsResult;
                }else {
                    datas = getSelectedObjects();
                }
                
                JasperPrint jasperPrint = null;
                
                jasperPrint = JasperFillManager.fillReport(getJasperFileName(), getReportParameters(), new JRBeanCollectionDataSource(datas));
                
                if(jasperPrint!=null)
                    JasperViewer.viewReport(jasperPrint, false);
            
        }
	
	/**
	 * Obtention de l'identification de l'entit�
	 * @param Object
	 * @return
	 */
	protected abstract PK getPrimaryKey(T Object);
	
	/**
	 * Classe responsable de la mise a disposition du manager
	 * @return
	 */
	protected abstract GenericManager getManager();
	
	/**
	 * Gestion de la pagination
	 * @return
	 */
	protected  PaginationStep getPagination(){
         
             return pagination;
        }
	
	/**
	 * Retour la liste des objects selectionn�s
	 * @return
	 */
	protected abstract List<T> getSelectedObjects() ;	
	
	
	/**
	 * Parametres des raport
	 * @return
	 */
	protected abstract Map getReportParameters();
	
	/**
	 * Nom du fichier
	 * @return
	 */
	protected abstract String getJasperFileName();
	
	
	
	/**
	 * 
	 * @param currentObject
	 * @return
	 */
	protected boolean updatable(T currentObject){ return true; }
	
	/**
	 * 
	 * @param currentObject
	 * @return
	 */
	protected boolean deletable(T currentObject){ return true; }
	/**
	 * 
	 * @param object
	 * @param manager
	 * @param typeOperation
	 * @param window
	 * @return
	 */
	//public abstract JInternalFrame getEditInternalFrame(T object , GenericManager manager , TypeOperation typeOperation , JFrame window);
	/**
	 * 
	 * @return
	 */
	protected boolean beforeDelete(){ return true;}
	
	/**
	 * 
	 * @return
	 */
	protected boolean afterDelete(){ return true;}
	/**
	 * Actions avant la mise a jour du currrentObject
	 * @param currentObject
	 * @return
	 */
	protected boolean beforeSetCurrentObject(){ return true;}
	
	/**
	 * Actions apres mise a jour du currentObject
	 * @return
	 */
	protected boolean afterSetCurrentObject(){ return true; }
	/**
	 * Verificateur de la pr�condition avant sauvegarde
	 * @return
	 */
	protected boolean beforeSave(){ return true ;}
	
	/**
	 * Verification postcondition avant sauvegarde
	 * @return
	 */
	public boolean afterSave(){return true ;}
}
