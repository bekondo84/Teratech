/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericmanagerlayer.core.impl;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.bekosoftware.genericmanagerlayer.exceptions.GenericManagerException;
import com.megatim.common.annotations.OrderType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.persistence.EntityManager;

/**
 * Implementation abstraite des gestionnaires
 * @author <a href="mailto:bekondo_dieu@yahoo.fr">BEKONDO Kangue Dieunedort</a>
 * @since 19 Nov. 2015 - 16:18:05
 */
public abstract class AbstractGenericManager<T , PK extends Serializable>
             implements GenericManager<T , PK >{

    /**
    * Methode d'obtention de la DAO
    * @return	DAO
    */
    public abstract GenericDAO<T, PK> getDao();

    /**
     * EntityManager
     * @param manager 
     */
    public void setManager(EntityManager manager) {
        //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    /**
     * Methode d'obtention du nom de la cle primaire 
     * @return EntityIDName
     */
    public abstract String getEntityIdName();
    /**
    * Methode generique d'enregistrement d'une entite
    * @param entity	Entite a enregistrer
    * @return	Entite enregistree
    */
    public T save(T entity) {
       //Operation de pré-persistence
        this.processBeforeSave(entity);
        //Enregismetrement en base de données
        getDao().save(entity);
        //Operation de post-persistence
        processAfterSave(entity);
        //return l'object entity
        return entity;
        
    }

    /**
     * 
     * @param entities 
     */
    public  void save(List<T> entities){
        
        if(entities==null||entities.isEmpty())
             return ;
        processBeforeSave(entities);
        
        for(T entity : entities){
            
             getDao().save(entity);
        }
        
        processAfterSave(entities);
    }

    
    
    /**
    * Methode generique de mise a jour d'une entite
    * @param id	Identifiant de l'objet a mettre a jour
    * @param entity	Entite a mettre a jour
    * @return	Entite mise a jour
    */
    public T update(PK id, T entity) {
        //Operation de pré-persistence
        this.processBeforeUpdate(entity);
        //Enregismetrement en base de données
        getDao().update(id , entity);
        //Operation de post-persistence
        processAfterUpdate(entity);
        //return l'object entity
        return entity;
    }

    /**
     * 
     * @param entities 
     */
    public void update(Map<PK,T> entities){
        
        if(entities==null||entities.isEmpty())
            return ;
        Set<PK> keys = entities.keySet();
        for(PK key : keys){
            update(key,entities.get(key));
        }
    }

    
    
    /**
    * Methode generique de suppression d'une entite
    * @param id	Identifiant de l'entite a supprimer
    */
    public T delete(PK id) {
        //Chargement en memoire
        T entity = getDao().findByPrimaryKey(getEntityIdName(), id);
       if(entity!=null){   
            //Operation de pré-persistence
            this.processBeforeDelete(entity);
            //Enregismetrement en base de données
            entity = getDao().delete(id);
            //Operation de post-persistence
            processAfterDelete(entity);
            //return entity supprime
            return entity;
       }else throw new GenericManagerException("AbstractGenericManager.delete.entity_not_found");
    }

    
    /**
    * Methode de recherche d'une entite par son ID
    * @param id	ID de l'entite
    * @param properties Conteneur de Proprietes a charger immediatement
    * @return Entite recherchee
    */
    public T find(String propertyName , PK entityID) {
        //filtre des elements
       return getDao().findByPrimaryKey(propertyName, entityID);
    }

    
    /**
    * Methode de chargement de tous les objets
    * @return	Liste de tous les objets
    */
    public List<T> findAll() {
        // Filtre de tous les éléments
    	
	    return getDao().filter(new ArrayList<Predicat>(), null, null, 0, -1);
    }

    /**
     * Methode de recherche d'une entite par une propriete unique
     * @param propertyName	Nom de la propriete Unique
     * @param propertyValue	Valeur de la propriete Unique
     * @param properties	Ensemble des proprietes a charger en EAGER
     * @return	Objet recherche
     */
    public List<T> findByUniqueProperty(String propertyName, Object propertyValue, Set<String> properties) {
        
       // retourne une entité basé sur la clé métier
       return getDao().findByProperty(propertyName, propertyValue, properties );
       
    }
   
    

    
    /**
    * Methode de filtre sur la base du critere d'egalite et de conjonction des proprietes
    * @param criteriasProperties	MAP des proprietes de recheche
    * @param properties	Ensemble des proprietes a charger immediatement
    * @param firstResult Index du premoier
    * @return	Liste des entites trouvees
    */
    public <Y extends Comparable<Y>> List<T> filterOnEqualProperties(Map<String, Y> criteriasProperties, Set<String> properties, int firstResult, int maxResult) {
                // Conteneur de restrictions
		RestrictionsContainer restrictionsContainer = RestrictionsContainer.newInstance();
		
		// Si la MAP des proprietes est non vide
		if(criteriasProperties != null && !criteriasProperties.isEmpty()) {
			
			// Obtention des entrees
			Set<Entry<String, Y>> entries = criteriasProperties.entrySet();
			
			// Parcours
			for (Entry<String, Y> entry : entries) {
				
				// Predicat d'egalite
				restrictionsContainer.addEq(entry.getKey(), entry.getValue());
			}
		}
		
		// Filtre
		return getDao().filter(restrictionsContainer.getPredicats(), null, properties, firstResult, maxResult);
    }

    
    
    /**
     * 
     * @param predicats
     * @param orders
     * @param properties
     * @param firstResult
     * @param maxResult
     * @return 
     */
    public List<T> filter(List<Predicat> predicats,Map<String, OrderType> orders, Set<String> properties,int firstResult, int maxResult) {
        // TODO Auto-generated method stub
        return getDao().filter(predicats, orders, properties, firstResult, maxResult);
    }
    
      /**
     * 
     * @param predicats
     * @param orders
     * @param properties
     * @param firstResult
     * @param maxResult
     * @return 
     */
    public List<T> filter(List<Predicat> predicats,
			Map<String, OrderType> orders, Set<String> properties,Map<String,Object> hints,
			int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		return getDao().filter(predicats, orders, properties,hints, firstResult, maxResult);
	}
	
    /**
	 * Retourne le nombre d'enregistrements dans une requete
	 * @param predicats
	 * @return
	 */
	public Long count(List<Predicat> predicats) {
		// TODO Auto-generated method stub
		return getDao().count(predicats);
	}

	/**
    * Methode de nettoyage des entites
    */
    public void clean() {
       getDao().clear();
    }

     /**
      * Methode vidage du contexte de persistence en BD
      */
    public void flush() {
        getDao().flush();
    }
    
    
      /**
     * Bloc de code à executer avant l'insertion en base de données
     * @param entity
     */
    @SuppressWarnings("empty-statement")
    public void processBeforeSave(T entity) {
        ;
    }

    /**
     * Bloc de code à executer apres l'insertion en base de données
     * @param entity
     */
    @SuppressWarnings("empty-statement")
    public void processAfterSave(T entity) {
        ;
    }
    
      /**
     * Bloc de code à executer avant l'insertion en base de données
     * @param entities
     */
    @SuppressWarnings("empty-statement")
    public void processBeforeSave(List<T> entities) {
        ;
    }

    /**
     * Bloc de code à executer apres l'insertion en base de données
     * @param entities
     */
    @SuppressWarnings("empty-statement")
    public void processAfterSave(List<T> entities) {
        ;
    }

    /**
     * Bloc de code à executer avant la mise à jour en base de données
     */
    @SuppressWarnings("empty-statement")
    public void processBeforeUpdate(T entity) {
        ;
    }

    /**
     * Bloc de code à executer après la mise en base de données
     */
    @SuppressWarnings("empty-statement")
    public void processAfterUpdate(T entity) {
        ;
    }

    /**
     * Bloc de code à executer avant la suppression en base de données
     */
    @SuppressWarnings("empty-statement")
    public void processBeforeDelete(T entity) {
        ;
    }

     /**
     * Bloc de code à executer apres la suppression en base de données
     */
    @SuppressWarnings("empty-statement")
    public void processAfterDelete(T entity) {
       ;
    }

    @SuppressWarnings("empty-statement")
    public void processBeforeUpdate(Map<PK, T> entity) {
        ; //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("empty-statement")
    public void processAfterUpdate(Map<PK, T> entity) {
        ; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
