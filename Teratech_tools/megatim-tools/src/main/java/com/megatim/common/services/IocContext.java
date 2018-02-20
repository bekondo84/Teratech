/**
 * 
 */
package com.megatim.common.services;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.common.annotationsprocessor.AnnotationsProcessor;


/**
 * Classe responsable du la mise a disposition des composants de la couche service ou dao
 * cette classe renvoie un objet proxy  qui permet au composant de fournir tous les services
 * transversaux attendu 
 * @author Bekondo Kangue Dieunedort <bekondo_dieu@yahoo.fr / tel:695427874>
 *
 */
public class IocContext {

	/**
	 * constructeur par defaut
	 */
	public IocContext() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Renvoie une classe proxy representant le composant sollicit� pour les managers
	 * dans le cas des autres classes renvoi une instance de la classe
	 * @param className: classe a instancie
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Object lookup(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		Object _instance = Class.forName(className).newInstance();
		
		//Traitement des annotations de la classe
		AnnotationsProcessor.processAnnotations(_instance);
		
		//renvoie un proxy si il s'agit d'un manager
		if(_instance instanceof GenericManager)
		           return IoCProxy.instance(_instance);
		
		//renvoie un proxy si il s'agit d'une DAO
		/*if(_instance instanceof GenericDAO)
		           return IoCProxy.instance(_instance);*/
		
		//Dans tout autre cas 
		return _instance;
	}
 
	public Object lookup(Object _instance) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		//verification clause de non nullit�
		if(_instance==null) 
			             return null;
		//Traitement des annotations de la classe
		AnnotationsProcessor.processAnnotations(_instance);
		
		//renvoie un proxy si il s'agit d'un manager
		if(_instance instanceof GenericManager)
		           return IoCProxy.instance(_instance);
		
		//renvoie un proxy si il s'agit d'une DAO
		/*if(_instance instanceof GenericDAO)
		           return IoCProxy.instance(_instance);*/
		
		//Dans tout autre cas 
		return _instance;
		
	}

}
