/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekosoftware.genericdaolayer.jpa.impl;

import com.megatim.common.annotations.OrderType;
import com.bekosoftware.genericdaolayer.dao.exceptions.GenericDAOException;
import com.bekosoftware.genericdaolayer.dao.tools.DAOUtilis;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericdaolayer.encryption.impl.BaseEntityWithEncryption;
import com.bekosoftware.genericdaolayer.encryption.impl.GenericEncryptionImpl;
import com.bekosoftware.genericdaolayer.jpa.ifaces.GenericEntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 *
 * @author Bekondo Kangue Dieunedort <bekondo_dieu@yahoo.fr>
 * @param T : type of object to manage
 */
public abstract class GenericEntityManagerImpl<T extends Object> implements GenericEntityManager<T> {

    //Class name of T
    protected Class<T> entityClass = getManagedEntityClass();

    /**
     * Save the parameter t to the data base
     *
     * @param t : object to save
     * @throws GenericDAOException if any problems
     * @return
     */
    public T save(T t) {
        //To change body of generated methods, choose Tools | Templates.
        try {
            if (t != null) {
                //Appel de la fonction de pré-traitement
                processBeforeSave(t);
                
                // Crytapge des champ annoté @Cryptage avant de save
                if(t instanceof BaseEntityWithEncryption){
                	t = GenericEncryptionImpl.CrypteBean(t);
                }
                //Sauvegarde dans le contexte de persistence
                getEntityManager().persist(t);
                //Appel de la fonction de post-persistence
                processAfterSave(t);

            } else {
                throw new GenericDAOException("genericentitymanager.save.entity.null");
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericDAOException("genericentitymanager.save.error", e);

        }
    }

    /**
     * Update the parameter t in the data base
     *
     * @param id : object ID
     * @param t : object to merge
     * @throws GenericDAOException if any problems
     * @return
     */
    public T update(Object entityId, T t) {

        try {

            if (entityId == null) {
                throw new GenericDAOException("genericEntityManager.update.entityId.null");
            } else if (t == null) {
                throw new GenericDAOException("genericentitymanager.update.entity.null");
            } else {
                //Execution de la fonction de pre-mise a jour
                processBeforeUpdate(t);
                //Verification si entity t existe en BD
                T entity = getEntityManager().find(entityClass, entityId);
                //Execution de la fonction de post-update
                processAfterUpdate(t);
                if(t instanceof BaseEntityWithEncryption){
                // Crytapge des champ annoté@ Cryptage avant de update
                 t = GenericEncryptionImpl.CrypteBean(t);
                }
                if (entity != null) {
                    entity = getEntityManager().merge(t);
                }
                //renvoyer l'objet persisté 
                return entity;
            }
        } catch (IllegalArgumentException e) {
            throw new GenericDAOException("genericentitymanager.update.illegalargumentexception", e);
        } catch (TransactionRequiredException e) {
            throw new GenericDAOException("genericentitymanager.update.transactionrequiredexception", e);
        } catch (Exception e) {
            throw new GenericDAOException("genericentitymanager.update.error", e);
        }
    }

    /**
     * Remove the parameter t in the data base
     *
     * @param entityID : id of the entity to delete
     * @throws GenericDAOException if any problems
     * @return
     */
    public T delete(Object entityID) {

        //Verification de la validite de entityID
        if (entityID == null) {
            throw new GenericDAOException("genericentitymanager.delete.entityID.null");
        }
        try {
            //chargement de l'netite en memoire
            T entity = getEntityManager().find(entityClass, entityID);
            //Execution action de pre-delete
            processBeforeDelete(entity);
            //Suppression de entity en BD
            getEntityManager().remove(entity);
            //Execution action de  post-delete
            processAfterDelete(entity);
            return entity;
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
            throw new GenericDAOException("genericentitymanager.delete.illegalargumentexception", e);
        } catch (TransactionRequiredException e) {
            throw new GenericDAOException("genericentitymanager.delete.transactionrequiredexception", e);
        } catch (Exception e) {
            throw new GenericDAOException("genericentitymanager.delete.error", e);
        }

    }

    /**
     * Find entity by Primary Key Search for a giveng primaryKey if there is an
     * entity in the database if entity is found then return it else return null
     *
     * @param entityIDName : name of the ID field in the entity
     * @param primaryKey : the value of the primary key
     * @param properties : Set proprietes à charge
     */
    public <T> T findByPrimaryKey(String entityIDName, Object primaryKey, Set<String> properties) {

        //Si le nom de l'id est null ou vide
        if (entityIDName == null || entityIDName.trim().isEmpty()) {
            throw new GenericDAOException("genericentitymanager.findbyprimarykey.entityidname.null");
        }
        if (primaryKey == null) {
            throw new GenericDAOException("genericentitymanager.findbyprimarykey.entityid.null");
        }
        //Creation du creteria builder
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        //creation d'un constructeur de requete par critere
        CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(entityClass);
        // Construction de la racine
        Root<T> root = (Root<T>) criteriaQuery.from(entityClass);
        // Select Clause
        criteriaQuery.select(root);
        // Paramètre
        ParameterExpression<Object> idParameter = criteriaBuilder.parameter(Object.class, entityIDName);

        // Condition sur l'ID
        criteriaQuery.where(criteriaBuilder.equal(root.get(entityIDName.trim()), idParameter));

        // Ajout des propriétés à charger en EAGER
        DAOUtilis.addProperties(root, criteriaQuery, properties);

        // Requete basée sur les critères
        TypedQuery<T> query = getEntityManager().createQuery(criteriaQuery);

        // Positionnement du Paramètre
        query.setParameter(entityIDName, primaryKey);
        try {

            // On retourne le résultat
            return query.getSingleResult();

        } catch (NoResultException e) {

            // On retourne null
            return null;

        } catch (NonUniqueResultException e) {

            // On leve une exception
            throw new GenericDAOException("genericentitymanager.findbyprimarykey.entityidname.invalid", e);
        }

    }

    /**
     * Find entity by Primary Key Search for a giveng primaryKey if there is an
     * entity in the database if entity is found then return it else return null
     *
     * @param entityIDName : name of the ID field in the entity
     * @param primaryKey : the value of the primary key
     */
    public <T> T findByPrimaryKey(String entityIDName, Object primaryKey) {

        //Si le nom de l'id est null ou vide
        if (entityIDName == null || entityIDName.trim().isEmpty()) {
            throw new GenericDAOException("genericentitymanager.findbyprimarykey.entityidname.null");
        }
        if (primaryKey == null) {
            throw new GenericDAOException("genericentitymanager.findbyprimarykey.entityid.null");
        }
        //Creation du creteria builder
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        //creation d'un constructeur de requete par critere
        CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(entityClass);
        // Construction de la racine
        Root<T> root = (Root<T>) criteriaQuery.from(entityClass);
        // Select Clause
        criteriaQuery.select(root);
        // Paramètre
        ParameterExpression<Object> idParameter = criteriaBuilder.parameter(Object.class, entityIDName);

        // Condition sur l'ID
        criteriaQuery.where(criteriaBuilder.equal(root.get(entityIDName.trim()), idParameter));

        // Ajout des propriétés à charger en EAGER
        //addProperties(root, criteriaQuery, properties);

        // Requete basée sur les critères
        TypedQuery<T> query = getEntityManager().createQuery(criteriaQuery);

        // Positionnement du Paramètre
        query.setParameter(entityIDName, primaryKey);
        try {

            // On retourne le résultat
            return query.getSingleResult();

        } catch (NoResultException e) {

            // On retourne null
            return null;

        } catch (NonUniqueResultException e) {

            // On leve une exception
            throw new GenericDAOException("genericentitymanager.findbyprimarykey.entityidname.invalid", e);
        } catch (Exception e) {
            // On leve une exception
            throw new GenericDAOException("genericentitymanager.findbyprimarykey.error", e);
        }
    }

    /**
     * Find entity by Primary Key Search for a giveng primaryKey if there is an
     * entity in the database if entity is found then return it else return null
     *
     * @param entityIDName : name of the ID field in the entity
     * @param primaryKey : the value of the primary key
     */
    public List<T> findByProperty(String propertyName, Object propertyValue) {
    	System.out.println("GenericEntityManagerImpl.findByProperty() propertyName"+propertyName);
    	System.out.println("GenericEntityManagerImpl.findByProperty() propertyValue"+propertyValue.toString());
        //propertyName non null ou non vide
        if (propertyName == null || propertyName.trim().isEmpty()) {
            throw new GenericDAOException("genericentitymanager.findbyproperty.propertyname.null");
        }
        //verification de propertyValue
        if (propertyValue == null) {
            throw new GenericDAOException("genericentitymanager.findbyproperty.propertyvalue.null");
        }
        //Tous les paramètres sont OK
        //creation de criteriaBulder
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        //creation d'un constructeur de requete 
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(entityClass);
        //Construction de la racine
        Root<T> root = criteriaQuery.from(entityClass);
        //Select clause
        criteriaQuery.select(root);
        //Parametres de la requete
        ParameterExpression<Object> idParameters = criteriaBuilder.parameter(Object.class, propertyName);
        //condition sur l'ID
        criteriaQuery.where(criteriaBuilder.equal(root.get(propertyName.trim()), idParameters));
        //requête base sur les critères
        TypedQuery<T> query = getEntityManager().createQuery(criteriaQuery);
        //positionnement du paramètre
        query.setParameter(propertyName, propertyValue);

        try {
            //on retourn le resultat
            return query.getResultList();
        } catch (NoResultException e) {

            // On retourne null
            return new ArrayList<T>();

        } catch (Exception e) {
            // On leve une exception
            throw new GenericDAOException("genericentitymanager.findbyprproperty.error", e);
        }
    }

    /**
     * Find entity by Primary Key Search for a giveng primaryKey if there is an
     * entity in the database if entity is found then return it else return null
     *
     * @param entityIDName : name of the ID field in the entity
     * @param primaryKey : the value of the primary key
     */
    public List<T> findByProperty(String propertyName, Object propertyValue, Set<String> properties) {
     	System.out.println("GenericEntityManagerImpl.findByProperty() propertyName"+propertyName);
    	System.out.println("GenericEntityManagerImpl.findByProperty() propertyValue"+propertyValue);
 

        //propertyName non null ou non vide
        if (propertyName == null || propertyName.trim().isEmpty()) {
            throw new GenericDAOException("genericentitymanager.findbyproperty.propertyname.null");
        }
        //verification de propertyValue
        if (propertyValue == null) {
            throw new GenericDAOException("genericentitymanager.findbyproperty.propertyvalue.null");
        }
        //Tous les paramètres sont OK
        //creation de criteriaBulder
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        //creation d'un constructeur de requete 
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(entityClass);
        //Construction de la racine
        Root<T> root = criteriaQuery.from(entityClass);
        //Select clause
        criteriaQuery.select(root);
        //Parametres de la requete
        ParameterExpression<Object> idParameters = criteriaBuilder.parameter(Object.class, propertyName);
        //condition sur l'ID
        criteriaQuery.where(criteriaBuilder.equal(root.get(propertyName.trim()), idParameters));
        // Ajout des propriétés à charger en EAGER
        DAOUtilis.addProperties(root, criteriaQuery, properties);
        //requête base sur les critères
        TypedQuery<T> query = getEntityManager().createQuery(criteriaQuery);
        //positionnement du paramètre
        query.setParameter(propertyName, propertyValue);

        try {
            //on retourn le resultat
            return query.getResultList();
        } catch (NoResultException e) {

            // On retourne null
            return new ArrayList<T>();

        } catch (Exception e) {
            // On leve une exception
            throw new GenericDAOException("genericentitymanager.findbyprproperty.error", e);
        }
    }

    /**
     * methode de filtre des entites d'une classe en fonction des prédicats de
     * filtres
     *
     * @param predicats : List des prédicats (condition de selection)
     * @param orders : Map ordres de tri des resultats
     * @param properties : Set ensemble des proprietes à charges
     * @param firstResult : int index du premier resultat
     * @param maxResult : int nbre maximal de données à charges par defaut -1 :
     * charge tous les données
     *
     */
    public List<T> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult ) {
        // Criteria Builder
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

        // Requete de criteres
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);

        // Construction de la racine
        Root<T> root = criteriaQuery.from(entityClass);

        // On positionne l'Alias
        root.alias("ROOT");

        // Selection de la racine
        criteriaQuery.select(root);

        // Ajout des Prédicats
        if (predicats != null && !predicats.isEmpty()) {
            DAOUtilis.addPredicates(criteriaBuilder, root, criteriaQuery, predicats);
        }

        // Ajout des Odres
        if (orders != null && !orders.isEmpty()) {
            DAOUtilis.addOrders(criteriaBuilder, root, criteriaQuery, orders);
        }

        // Ajout des Modes de chargements
        if (properties != null && !properties.isEmpty()) {
            DAOUtilis.addProperties(root, criteriaQuery, properties);
        }
        
        //Hint
        
        // Construction de la requete basée sur les critères
        TypedQuery<T> query = getEntityManager().createQuery(criteriaQuery);
        
        //Hint
        
        // Traitement de l'index du premier resultat

        // Si l'index du premier element est < 0
        if (firstResult < 0) {
            query.setFirstResult(0);
        } else {
            query.setFirstResult(firstResult);
        }

        // Traitement du nombre max de resultat

        // Si le nombre max d'element est <= 0
        if (maxResult > 0) {
            query.setMaxResults(maxResult);
        }

        // Execution
        List<T> results = query.getResultList();

        // On retourne le résult
        return results;
    }

    public  List<T> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, Map<String, Object> hints, int firstResult, int maxResult){
        
        // Criteria Builder
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

        // Requete de criteres
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);

        // Construction de la racine
        Root<T> root = criteriaQuery.from(entityClass);

        // On positionne l'Alias
        root.alias("ROOT");

        // Selection de la racine
        criteriaQuery.select(root);

        // Ajout des Prédicats
        if (predicats != null && !predicats.isEmpty()) {
            DAOUtilis.addPredicates(criteriaBuilder, root, criteriaQuery, predicats);
        }

        // Ajout des Odres
        if (orders != null && !orders.isEmpty()) {
            DAOUtilis.addOrders(criteriaBuilder, root, criteriaQuery, orders);
        }

        // Ajout des Modes de chargements
        if (properties != null && !properties.isEmpty()) {
            DAOUtilis.addProperties(root, criteriaQuery, properties);
        }
        
        //Hint
        
        // Construction de la requete basée sur les critères
        TypedQuery<T> query = getEntityManager().createQuery(criteriaQuery);
        
        //Hint
        
        // Traitement de l'index du premier resultat

        // Si l'index du premier element est < 0
        if (firstResult < 0) {
            query.setFirstResult(0);
        } else {
            query.setFirstResult(firstResult);
        }

        // Traitement du nombre max de resultat

        // Si le nombre max d'element est <= 0
        if (maxResult > 0) {
            query.setMaxResults(maxResult);
        }

        if(hints!=null&&!hints.isEmpty()){
            for(String key : hints.keySet()){
                query.setHint(key, hints.get(key));
            }
        }
        // Execution
        List<T> results = query.getResultList();

        // On retourne le résult
        return results;
    }

    
    
    
    /**
     * Methode d'execution d'une requete de comptage des elements d'une requete
     *
     * @param criteriaQuery
     * @param parameters
     * @return
     */
    public long count(List<Predicat> predicats) {
        // TODO Auto-generated method stub
        // Criteria Builder
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

        // Requete de criteres
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        // Construction de la racine
        Root<T> root = criteriaQuery.from(entityClass);

        // On positionne l'Alias
        root.alias("ROOT");

        // Selection de la racine
        criteriaQuery.select(criteriaBuilder.countDistinct(root));

        // Ajout des Prédicats
        if (predicats != null && !predicats.isEmpty()) {
            DAOUtilis.addPredicates(criteriaBuilder, root, criteriaQuery, predicats);
        }


        // Construction de la requete basée sur les critères
        TypedQuery<Long> query = getEntityManager().createQuery(criteriaQuery);

        // Traitement de l'index du premier resultat



        // Execution
        Long results = query.getSingleResult();

        // On retourne le résult              
        return results;
    }

    /**
     * Méthode d'execution d'une requete de critere
     *
     * @param <Q>	Parametre de type de la racine de l'entite
     * @param criteriaQuery	Requete de critere
     * @param parameters	Map des parametres
     * @return	Resultat de la requete
     */
    public <Q> List<Q> executeCriteria(CriteriaQuery<Q> criteriaQuery, Map<String, Object> parameters) {

        // Si la Requete est nulle
        if (criteriaQuery == null) {
            throw new GenericDAOException("genericentitymanager.executeCriteria.query.null");
        }

        // Requete Typee
        TypedQuery<Q> query = getEntityManager().createQuery(criteriaQuery);

        // Ajout des Parametres
        DAOUtilis.addQueryParameters(query, parameters);

        // Execution
        return query.getResultList();
    }

    /**
     * 
     * @param criteriaQuery
     * @param parameters
     * @return 
     */
    public List<T> executeCriteria(String criteriaQuery, Map<String, Object> parameters){
        if(criteriaQuery==null||criteriaQuery.trim().isEmpty()){
            throw new GenericDAOException("genericentitymanager.executeCriteria.query.null");
        }
        Query query = getEntityManager().createNamedQuery(criteriaQuery);
        
        return query.getResultList();
    }
    
    

    public void clear() {
        getEntityManager().clear();
    }

    /**
     * Flush the content of Persistence context to the database
     */
    public void flush() {
        getEntityManager().flush();
    }

    /**
     * Bloc de code à executer avant l'insertion en base de données
     */
    @SuppressWarnings("empty-statement")
    public void processBeforeSave(T entity) {
        ;
    }

    /**
     * Bloc de code à executer apres l'insertion en base de données
     */
    @SuppressWarnings("empty-statement")
    public void processAfterSave(T entity) {
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
    public void processAfterDelete(T entity) {
        ;
    }
}
