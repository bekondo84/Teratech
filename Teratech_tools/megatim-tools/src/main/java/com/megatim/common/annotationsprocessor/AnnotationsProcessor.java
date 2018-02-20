/**
 *
 */
package com.megatim.common.annotationsprocessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.megatim.common.annotations.Inject;
import com.megatim.common.annotations.PersistenceManager;
import com.megatim.common.services.IocContext;
import com.megatim.common.utilities.PropertiesFileHelper;
import com.megatim.security.manager.connection.SecurityCenter;

import java.io.File;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import javax.persistence.Entity;

import javax.persistence.EntityManager;
import javax.persistence.Id;
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

    /**
     *
     */
    public AnnotationsProcessor() {
        // TODO Auto-generated constructor stub
    }

    public static void processAnnotations(Object _instance) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

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

            //Verification si le champs est annot� @EntityManager
            if (!field.isAnnotationPresent(PersistenceManager.class)) {
                continue; 
            }
            //@EntityManager trouve il faut le traiter
            PersistenceManager annot = field.getAnnotation(PersistenceManager.class);
            Properties props = null;
            try {
                props = PropertiesFileHelper.loadPropertiesFile(System.getProperty("user.dir") + File.separator + "ds.properties");
                //Procedure de decryptage
                Set keys = props.keySet();
                Iterator iter = keys.iterator();
                SecurityCenter.isActive();
                while(iter.hasNext()){
                    String key = (String) iter.next();                 
                    if(SecurityCenter.include(key)){
                        String value = SecurityCenter.decrypte(props.getProperty(key));
                        props.put(key, value);
                    }
                }
                //System.out.println("AnnotationsProcessor.entityManagerAnnotation(Object _instance) :::::::::::::::::: "+props+"  :::: "+System.getProperty("user.dir"));
            } catch (Exception ex) {;
            }
            //Instanciation du factory de persistence
            if (factory == null) { System.out.println("AnnotationsProcessor.entityManagerAnnotation(Object _instance) :::::::::::::::::: "+props+"  :::: "+factory);

                if (props != null) {
                    factory = Persistence.createEntityManagerFactory(annot.unitName(), props);
                     System.out.println("AnnotationsProcessor.entityManagerAnnotation(Object _instance) :::::::::::::::::: "+props+"  :::: "+factory);
                } else {
                     factory = Persistence.createEntityManagerFactory(annot.unitName());
                }
            }
                
            //cas ou l'instanciation echoue
            if (factory == null) ; //lancer exception et arret traitement

            //factory.getCache().evictAll();
            
            //Recuperation d'une instance de manager de persistence
            if (manager == null||!manager.isOpen()) {
               
                if(props!=null){ System.out.println(":::::::::::::::::::::::::::::::: "+props);
                    manager = factory.createEntityManager(props);
                } else {
                    manager = factory.createEntityManager();
                }              
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
}
