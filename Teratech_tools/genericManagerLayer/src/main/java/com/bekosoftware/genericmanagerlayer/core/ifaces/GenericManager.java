/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericmanagerlayer.core.ifaces;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.megatim.common.annotations.OrderType;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;

/**
 * Interface générique des gestionnaires
 * @author BEKONDO Kangue <bekondo_dieu@yahoo.fr>
 * @since 19 Nov 2015 à 16:04
 */
public interface GenericManager<T, PK extends Serializable>  {
    
    	/**
	 * Methode d'obtention de la DAO
	 * @return	DAO
	 */
	public GenericDAO<T, PK> getDao();
        
        /**
         * Methode permettant de set un entityManager
         * @param manager 
         */
        public void setManager(EntityManager manager);
	
	/**
	 * Methode generique d'enregistrement d'une entite
	 * @param entity	Entite a enregistrer
	 * @return	Entite enregistree
	 */
	public T save(T entity);

        /**
         * 
         * @param entities
         * @return 
         */
        public void save(List<T> entities);
	/**
	 * Methode generique de mise a jour d'une entite
	 * @param id	Identifiant de l'objet a mettre a jour
	 * @param entity	Entite a mettre a jour
	 * @return	Entite mise a jour
	 */
	public T update(PK id, T entity);
	
        /**
	 * Methode generique de mise a jour d'une entite
     * @param entities
	 */
	public void update(Map<PK,T> entities);
	/**
	 * Methode generique de suppression d'une entite
	 * @param id	Identifiant de l'entite a supprimer
	 */
	public T delete(PK id);
	
	/**
	 * Methode de recherche d'une entite par son ID
	 * @param id	ID de l'entite
	 * @param properties Conteneur de Proprietes a charger immediatement
	 * @return Entite recherchee
	 */
	public T find(String propertyName , PK id );
	
	/**
	 * Methode de chargement de tous les objets
	 * @return	Liste de tous les objets
	 */
	public List<T> findAll();
	
	
	/**
	 * Methode de recherche d'une entite par une propriete unique
	 * @param propertyName	Nom de la propriete Unique
	 * @param propertyValue	Valeur de la propriete Unique
	 * @param properties	Ensemble des proprietes a charger en EAGER
	 * @return	Objet recherche
	 */
	public List<T> findByUniqueProperty(String propertyName, Object propertyValue, Set<String>  properties);
    
    /**
     * methode de filtre des entites d'une classe en fonction des prédicats de filtres
     * @param predicats : List des prédicats (condition de selection)
     * @param orders : Map ordres de tri des resultats
     * @param properties : Set ensemble des proprietes à charges
     * @param  firstResult : int index du premier resultat
     * @param  maxResult : int nbre maximal de données à charges par defaut -1 : charge tous les données
     * 
     */
     
    public List<T> filter(List<Predicat> predicats , Map<String,OrderType> orders , Set<String> properties , int firstResult , int maxResult);
    
/**
     * methode de filtre des entites d'une classe en fonction des prédicats de filtres
     * @param predicats : List des prédicats (condition de selection)
     * @param orders : Map ordres de tri des resultats
     * @param properties : Set ensemble des proprietes à charges
     * @param hints
     * @param  firstResult : int index du premier resultat
     * @param  maxResult : int nbre maximal de données à charges par defaut -1 : charge tous les données
     * @return 
     * 
     */
     
    public List<T> filter(List<Predicat> predicats , Map<String,OrderType> orders , Set<String> properties , Map<String,Object> hints , int firstResult , int maxResult);
    
    /**
	 * Retourne le nombre d'enregistrements dans une requete
	 * @param predicats
	 * @return
	 */
    public Long count(List<Predicat> predicats );
    
    /**
	 * Methode de filtre sur la base du critere d'egalite et de conjonction des proprietes
	 * @param criteriasProperties	MAP des proprietes de recheche
	 * @param properties	Ensemble des proprietes a charger immediatement
	 * @param firstResult Index du premoier
	 * @return	Liste des entites trouvees
	 
	public <Y extends Comparable<Y>> List<T> filter(Map<String, Y> criteriasProperties, Set<String> properties, int firstResult, int maxResult);
	
	*/
    
	/**
	 * Methode de nettoyage des entites
	 */
	public void clean();
        
        /**
	 * Methode vidage du contexte de persistence en BD
	 */
	public void flush();
        
        /**
        * Methode execute avant la sauvegarde en BD
     * @param entity
        */
        public void processBeforeSave(T entity);
        /**
         * Methode execute apres la sauvegarde en BD
     * @param entity
         */
        public void processAfterSave(T entity);
          /**
        * Methode execute avant la sauvegarde en BD
     * @param entities
        */
        public void processBeforeSave(List<T> entities);
        /**
         * Methode execute apres la sauvegarde en BD
     * @param entities
         */
        public void processAfterSave(List<T> entities);
         /**
         * Methode execute avant la mise à jour en BD
     * @param entity
         */
        public void processBeforeUpdate(T entity);
        /**
         * Methode execute apres la mise à jour en BD
     * @param entity
         */
        public void processAfterUpdate(T entity);
          /**
         * Methode execute avant la mise à jour en BD
     * @param entity
         */
        public void processBeforeUpdate(Map<PK,T> entity);
        /**
         * Methode execute apres la mise à jour en BD
     * @param entity
         */
        public void processAfterUpdate(Map<PK,T> entity);
         /**
         * Methode execute avant la sauvegarde en BD
     * @param entity
         */
        public void processBeforeDelete(T entity);
        /**
         * Methode execute avant la sauvegarde en BD
     * @param entity
         */
        public void processAfterDelete(T entity);
}
