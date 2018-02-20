/**
 *
 */
package com.bekosoftware.genericdaolayer.contexts;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.megatim.common.annotations.Inject;
import com.megatim.common.annotations.PersistenceManager;


import java.io.File;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import javax.persistence.Entity;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.swing.JComponent;

/**
 * Classe responsable du traitement des annotations
 *
 * @author Bekondo Kangue Dieunedort <bekondo_dieu@yahoo.fr / tel:695427874>
 *
 */
public class AnnotationsProcessor {

    private static IocContext context = new IocContext();

    private static EntityManagerFactory factory = null;

    private static EntityManager manager = null;
    
    private static boolean test =false ;

    /**
     *
     */
    public AnnotationsProcessor() {
        // TODO Auto-generated constructor stub
    }

    public static void processAnnotations(Object _instance) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        AnnotationsProcessor.test = test ;
        //traitement des annotation @EntityManager
        entityManagerAnnotation(_instance);

        //traitement de l'annotation @Inject
        injectProcessor(_instance);

    }

    /**
     * Classe responsable du triatement des annotations @EntityManager
     *
     * @param _instance
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    private static void entityManagerAnnotation(Object _instance) throws IllegalArgumentException, IllegalAccessException {

            //Instanciation du factory de persistence
        //EntityManagerFactory factory = null;
        //Recuperation d'une instance de manager de persistence
        //javax.persistence.EntityManager manager = null ;
        //teste de non nullit� 
        if (_instance == null) {
            return;
        }
        //recuperation liste des champs de _instance
        Field[] fields = _instance.getClass().getDeclaredFields();

        //Traitement sequentiel des annotations
        for (Field field : fields) {

            Annotation[] annotations = field.getAnnotations();

            String unitName = null ;
            //Verification si le champs est annot� @EntityManager
            if (field.isAnnotationPresent(PersistenceManager.class)) {
                PersistenceManager annot = field.getAnnotation(PersistenceManager.class);
                unitName = annot.unitName();
            }else if(field.isAnnotationPresent(PersistenceContext.class)){
                PersistenceContext annot = field.getAnnotation(PersistenceContext.class);
                unitName = annot.unitName();
            }
            
            if(test){
                StringBuilder build = new StringBuilder(unitName);
                build.append("_test");
                unitName = build.toString();
            }
                
            //@EntityManager trouve il faut le traiter
            //PersistenceManager annot = field.getAnnotation(PersistenceManager.class);
            //Properties props = null;
           
            //Instanciation du factory de persistence
            if (factory == null) {

                factory = Persistence.createEntityManagerFactory(unitName);
                
            }
                        //System.out.println("AnnotationsProcessor.entityManagerAnnotation(Object _instance) :::::::::::::::::: "+props+"  :::: "+factory);
            //cas ou l'instanciation echoue
            if (factory == null) ; //lancer exception et arret traitement

            //factory.getCache().evictAll();
            
            //Recuperation d'une instance de manager de persistence
            if (manager == null||!manager.isOpen()) {
                manager = factory.createEntityManager();
               
            }

            //Cas ou l'optention du manager echoue
            if (manager == null) ;  //Lancer une exception et mettre stop au traitement
             //manager.setProperty("javax.persistence.cache.retrieveMode", "BYPASS");
            //Modification de l'accessibilt� du champs
            field.setAccessible(Boolean.TRUE);
            //initialisation du champs avec la valeur du manager
            field.set(_instance, manager);
        }

    }

    /**
     * Fonction responsable du traitement des champs annot� @Inject
     *
     * @param _instance
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    private static void injectProcessor(Object _instance) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        //teste de non nullit�
        if (_instance == null) {
            return;
        }
        //recuperation liste des champs de _instance
        Field[] fields = _instance.getClass().getDeclaredFields();
        //Traitement des champs
        for (Field field : fields) {

            //teste de presence de l'annotation @Inject
            if (!field.isAnnotationPresent(Inject.class)) {
                continue;
            }
            //Lecture de l'annotation
            Inject annotation = field.getAnnotation(Inject.class);
            //creation d'une instance de la classe de mon champs field
            Object instance_ = context.lookup(annotation.value());
            //Traitement des annotations du nouvel object
            AnnotationsProcessor.processAnnotations(instance_);
            //activer la modification des champs
            field.setAccessible(Boolean.TRUE);
            //Mise a jour de mon champs field
            field.set(_instance, instance_);
        }

    }

    public static String getPrimaryKeyName(Class value) {

        if (value == null) {
            return null;
        }
        if (!value.isAnnotationPresent(Entity.class)) {
            return null;
        }

        Field[] declaredFields = ValidateAndFillBeans.getObjectDeclaredFields(value);

        for (Field field : declaredFields) {

            if (field.isAnnotationPresent(Id.class)) {
                return field.getName();
            }
        }

        Class superclass = value.getSuperclass();

        if (superclass != null && !superclass.equals(JComponent.class)) {

            declaredFields = ValidateAndFillBeans.getObjectDeclaredFields(superclass);

            for (Field field : declaredFields) {
                if (field.getClass().isAnnotationPresent(Id.class)) {
                    return field.getName();
                }
            }
        }

        return null;
    }

    public static Class getPrimaryKeyType(Class<?> value) {

        if (value == null) {
            return null;
        }
        if (!value.getClass().isAnnotationPresent(Entity.class)) {
            return null;
        }

        Field[] declaredFields = ValidateAndFillBeans.getObjectDeclaredFields(value.getClass());
            //System.out.println("LLLLLLLLLLLLL::::::::::::::::::::::::::::::::::::::::::::::::::::: "+declaredFields+" ::::::: "+value+" ::::: "+value.getClass().getDeclaredAnnotations().length);

        for (Field field : declaredFields) {
            Annotation[] annots = field.getClass().getDeclaredAnnotations();
                //System.out.println("XXXXXXXXXXXXXXX::::::::::::::::::::::::::::::::::::::::::::::::::::: "+field.getName()+" ::::: "+annots+" ::::::::::: "+field.getAnnotations().length);                

            if (field.getClass().isAnnotationPresent(Id.class)) {
                return field.getType();
            }

        }

        Class superclass = value.getSuperclass();

        if (superclass != null && !superclass.equals(JComponent.class)) {

            declaredFields = ValidateAndFillBeans.getObjectDeclaredFields(superclass);

            for (Field field : declaredFields) {
                if (field.getClass().isAnnotationPresent(Id.class)) {
                    return field.getType();
                }
            }
        }
        return null;
    }

    /**
     * 
     * @param test 
     */
    public static void setTest(boolean test) {
        AnnotationsProcessor.test = test;
    }
    
    
}
