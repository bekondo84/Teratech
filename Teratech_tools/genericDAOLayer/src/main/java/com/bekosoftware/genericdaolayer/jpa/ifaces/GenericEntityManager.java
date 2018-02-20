/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericdaolayer.jpa.ifaces;

import com.megatim.common.annotations.OrderType;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;


/**
 *
 * @author Bekondo Kangue Dieunedort <bekondo_dieu@yahoo.fr>
 * @param T : type of object to manage
 */
public interface GenericEntityManager<T extends Object> {
    
   /**
     * Save the parameter t to the data base
     * @param t : object to save
     * @throws GenericDAOException if any problems
     * @return 
     */
    public T save(T t);
     /**
      * Update the parameter t in the data base
      * @param id : object ID
      * @param t : object to merge
      * @throws GenericDAOException if any problems
      * @return 
      */
    public T update(Object entityId ,T t);
    
    /**
     * Remove the parameter t in the data base
     * @param  entityID : id of the entity to delete
     * @throws GenericDAOException if any problems
     * @return 
     */
    public T delete(Object entityID);
    
    /**
     * Find entity by Primary Key 
     * Search for a giveng primaryKey if there is an entity in the database
     * if entity is found then return it else return null 
     * @param entityIDName : name of the ID field in the entity
     * @param  primaryKey : the value of the primary key
     */
    public <T> T findByPrimaryKey(String entityIDName,Object primaryKey,Set<String> properties);
    
    /**
     * Find entity by Primary Key 
     * Search for a giveng primaryKey if there is an entity in the database
     * if entity is found then return it else return null 
     * @param entityIDName : name of the ID field in the entity
     * @param  primaryKey : the value of the primary key
     */
    public <T> T findByPrimaryKey(String entityIDName,Object primaryKey);
    
    /**
     * Find entity by Primary Key 
     * Search for a giveng primaryKey if there is an entity in the database
     * if entity is found then return it else return null 
     * @param entityIDName : name of the ID field in the entity
     * @param  primaryKey : the value of the primary key
     */
    public List<T> findByProperty(String propertyName,Object propertyValue);
    
    /**
     * Find entity by Primary Key 
     * Search for a giveng primaryKey if there is an entity in the database
     * if entity is found then return it else return null 
     * @param entityIDName : name of the ID field in the entity
     * @param  primaryKey : the value of the primary key
     */
    public List<T> findByProperty(String propertyName,Object propertyValue,Set<String> properties);
    
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
    public List<T> filter(List<Predicat> predicats , Map<String,OrderType> orders , Set<String> properties ,Map<String , Object> hints , int firstResult , int maxResult);
    
    
    /**
     * Methode d'execution d'une requete de comptage des elements d'une requete
     * @param criteriaQuery
     * @param parameters
     * @return
     */
    public  long count(List<Predicat> predicats) ;
    
    /**
     * Remove the given entity from the persistence context causing
     * a manage entity to become detached
     * 
     */
    
    /**
    * Méthode d'execution d'une requete de critere
    * @param <Q>	Parametre de type de la racine de l'entite
    * @param criteriaQuery	Requete de critere
    * @param parameters	Map des parametres
    * @return	Resultat de la requete
    */
   public <Q> List<Q> executeCriteria(CriteriaQuery<Q> criteriaQuery, Map<String, Object> parameters);
   
   /**
    * 
    * @param criteriaQuery
    * @param parameters
    * @return 
    */
   public  List<T> executeCriteria(String criteriaQuery, Map<String, Object> parameters);
    
    public void clear();
    
    /**
     * Synchronized le context de persistence avec la base de données sous *jacente
     */
    public void flush();
    
    /**
     * Return the entitymanager 
     * @return 
     */
    public EntityManager getEntityManager();
    /**
     * return la class de l'entite geré par la DAO
     * @return classe de l'entite gere par la DAO
     */
    public Class<T> getManagedEntityClass();
    
    /**
     * Methode execute avant la sauvegarde en BD
     */
    public void processBeforeSave(T entity);
    /**
     * Methode execute apres la sauvegarde en BD
     */
    public void processAfterSave(T entity) ;
     /**
     * Methode execute avant la mise à jour en BD
     */
    public void processBeforeUpdate(T entity) ;
    /**
     * Methode execute apres la mise à jour en BD
     */
    public void processAfterUpdate(T entity) ;
     /**
     * Methode execute avant la sauvegarde en BD
     */
    public void processBeforeDelete(T entity) ;
    /**
     * Methode execute avant la sauvegarde en BD
     */
    public void processAfterDelete(T entity) ;
}
