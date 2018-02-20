/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.utilities;

import com.megatim.common.annotations.InjectDAO;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;




public class DAOClassLoader {

    private static Map<String, EntityManager> managerMap;

    private static Map<String, EntityManagerFactory> factoryMap;

    public DAOClassLoader() {
		// TODO Auto-generated constructor stub

        managerMap = new HashMap<String, EntityManager>();

        factoryMap = new HashMap<String, EntityManagerFactory>();

    }

    /**
     * Classe responsable de l'initialisation des classe de test Elle injecte
     * les DAO correspondant au unite de persistence mentionné dans les
     * annotations
     *
     * @param source
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void initialise(Object source) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        if (source == null) {
            return;
        }

        //Liste des fields de la classe a initialiser
        Field[] fields = source.getClass().getDeclaredFields();

        //Debut des traitements
        for (int index = 0; index < fields.length; index++) {

            //Verification si le champs courant est annote
            if (fields[index].isAnnotationPresent(InjectDAO.class)) {

                //Recuperation de l'injection
                InjectDAO annotat = fields[index].getAnnotation(InjectDAO.class);

                //Rendre accessible le champs
                fields[index].setAccessible(true);

                //Instanciation d'une instance de la DAO
                Object dao = Class.forName(annotat.ClassName()).newInstance();

				//System.out.println("DAOClassLoader.initialase(Object source) :::::::::::  champs: "+fields[index].getName()+"  :::::: DAO Instance : "+dao);
                //On passe au champs suivant si le cham
                if (!managerMap.containsKey(annotat.unitName())) {

                    //Activitation d'un Factory
                    EntityManagerFactory factory = Persistence.createEntityManagerFactory(annotat.unitName());

                    //Sauvegarde du factory  dans le cache
                    factoryMap.put(annotat.unitName(), factory);

                    //Obtention d'un manager pour 
                    EntityManager em = factory.createEntityManager();
					//Set the transaction

                    //Sauvegarde du manager dans le cache
                    managerMap.put(annotat.unitName(), em);
                }
                //Injection de EntityManager dans la DAO
                initialiseDAO(dao, managerMap.get(annotat.unitName()));

                //Mise a jour du champs DAO
                fields[index].set(source, dao);
            }

        }
    }

    public static void close() {

        //Fermeture des emtityManager
        Collection<EntityManager> entries = managerMap.values();

        //Fermeture des manager 
        for (EntityManager em : entries) {
            em.close();
        }

        Collection<EntityManagerFactory> factories = factoryMap.values();

        //Fermeture des manager 
        for (EntityManagerFactory em : factories) {
            em.close();
        }
    }

    /**
     * Inject un entityManager dans une classe de DAO
     *
     * @param source: dao concerne
     * @param em : entity manager
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private static void initialiseDAO(Object source, EntityManager em) throws IllegalArgumentException, IllegalAccessException {

        if (source == null) {
            return;
        }

        //Liste des fields de la classe a initialiser
        Field[] fields = source.getClass().getDeclaredFields();

        //Debut des traitements
        for (int index = 0; index < fields.length; index++) {

            //Verification si le champs courant est annote
            if (fields[index].isAnnotationPresent(PersistenceContext.class)) {

                //Recuperation de l'injection
                PersistenceContext annotat = fields[index].getAnnotation(PersistenceContext.class);

                //Rendre accessible le champs
                fields[index].setAccessible(true);

                //Injection du manager dans la dao
                fields[index].set(source, em);

            }

        }
    }

}
