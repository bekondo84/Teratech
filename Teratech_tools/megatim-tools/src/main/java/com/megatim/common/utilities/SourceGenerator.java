/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.utilities;

import com.bekosoftware.genericdaolayer.contexts.AnnotationsProcessor;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.bekosoftware.genericmanagerlayer.tools.ejb.ServiceLocator;
import com.megatim.common.annotations.Champ;
import com.megatim.common.annotations.Inject;
import com.megatim.common.annotations.InjectDAO;
import com.megatim.common.annotations.PersistenceManager;
import com.megatim.common.annotations.Search;
import com.megatim.common.annotations.Transaction;
import com.megatim.common.annotationsprocessor.ValidateAndFillBeans;
import com.megatim.common.clients.AbstractEditTemplateDialog;
import com.megatim.common.clients.AbstractListTemplateFrame;
import com.megatim.common.clients.AbstractListTransfertTemplateFrame;
import com.megatim.common.clients.AbstractListValidationTemplateFrame;
import com.megatim.common.clients.AbstractTableBaseListModel;
import com.megatim.common.clients.AbstractTableSummaryModel;
import com.megatim.common.clients.AbstractTransfertTemplateDialog;
import com.megatim.common.clients.ActionDetail;
import com.megatim.common.clients.ActionGroup;
import com.megatim.common.clients.CustomComboBox;
import com.megatim.common.clients.OptionPanel;
import com.megatim.common.clients.PaginationStep;
import com.megatim.common.jaxb.entities.Exclure;
import com.megatim.common.jaxb.entities.Exclures;
import com.megatim.common.jaxb.entities.Groupe;
import com.megatim.common.jaxb.entities.Groupes;
import com.megatim.common.jaxb.entities.Relations;
import com.megatim.common.jaxb.entities.Summary;
import com.megatim.common.services.IocContext;
//import com.megatim.common.services.IocContext;
import com.megatim.model.test.CompteUser;
import com.megatim.security.ifaces.client.ClientInterfaceSecurity;
import com.megatim.security.principal.UserPrincipal;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.ifaces.GenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jsf.mbean.generic.AbstractGenericBean;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCase;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JConditional;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JStatement;
import com.sun.codemodel.JSwitch;
import com.sun.codemodel.JTryBlock;
import com.sun.codemodel.JVar;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.PersistenceContext;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.ws.rs.Path;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author DEV_4
 */
public class SourceGenerator {

    /**
     * Map de generation des fichiers
     */
    private static Properties messages = new Properties();

    //Fichier resource
    private static Properties resources = new Properties();

    public static void main(String[] args) throws Exception {
        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage("mypackage");
        JDefinedClass def = cl._class("MyClass");

        JMethod method = def.method(3, String.class, "myMethod");
        method.param(String.class, "a");
        method.param(String.class, "b");
        method.param(Integer.class, "c");
        JBlock bl = method.body();
        bl._return();
        def.field(1, Integer.class, "myInteger");
        codeModel.build(new File("C:/MyTestDir"));
        //buildDAOInterface("C:/MyTestDir", CompteUser.class);
        //buildDAOImplementationClass("C:/MyTestDir", CompteUser.class);
        //buildManagerInterface("C:/MyTestDir", CompteUser.class);
        //buildManagerImplementationClass("C:/MyTestDir", CompteUser.class);
        buildDAOInterfaceEJBLocal(new File("C:/MyTestDir"), CompteUser.class, String.class);
        buildDAOInterfaceEJBRemote(new File("C:/MyTestDir"), CompteUser.class, String.class);
        buildManagerInterfaceEJBLocal(new File("C:/MyTestDir"), CompteUser.class, String.class);
        buildManagerInterfaceEJBRemote(new File("C:/MyTestDir"), CompteUser.class, String.class);
        buildDAOImplementationClassEJBStateless(new File("C:/MyTestDir"), CompteUser.class, String.class, "gepa");
        buildDAOImplementationClassEJBStateful(new File("C:/MyTestDir"), CompteUser.class, String.class, "gepa");
        buildManagerImplementationClassStateless(new File("C:/MyTestDir"), CompteUser.class, String.class, "login");
        buildJSFManagedBean(new File("C:/MyTestDir"), CompteUser.class, String.class, "login");
        buildEditDialog(new File("C:/MyTestDir"), CompteUser.class, String.class, "login", true);
        buildInternalFrame(new File("C:/MyTestDir"), CompteUser.class, String.class, "login", true);
        //buildEditEditPanel(new File("C:/MyTestDir"), CompteUser.class, String.class, "login");
        //buildPrincipalScreen(new File("C:/MyTestDir"), CompteUser.class , " ",null);
    }

    /**
     *
     * @param modelClass
     * @param level
     * @return
     */
    private static String buildPackage(Class<?> modelClass, ModuleLevel level) {        //System.out.println("========================================================================= "+modelClass);

        String sourceName = modelClass.getPackage().getName();

        if (level == ModuleLevel.DAO_INTERFACE) {
            return sourceName.replace("model", "dao.ifaces");
        } else if (level == ModuleLevel.DAO_IMPLEMENTATION) {
            return sourceName.replace("model", "dao.impl");
        } else if (level == ModuleLevel.DAO_TEST) {
            return sourceName.replace("model", "dao.test");
        } else if (level == ModuleLevel.MANAGER_INTERFACE) {
            return sourceName.replace("model", "core.ifaces");
        } else if (level == ModuleLevel.MANAGER_IMPLEMENTATION) {
            return sourceName.replace("model", "core.impl");
        } else if (level == ModuleLevel.MANAGED_TEST) {
            return sourceName.replace("model", "core.test");
        } else if (level == ModuleLevel.MANAGED_BEAN) {
            return sourceName.replace("model", "bean");
        } else if (level == ModuleLevel.VIEW) {
            return sourceName.replace("model", "views");
        } else if (level == ModuleLevel.JAX_RS_INTERFACE) {
            return sourceName.replace("model", "jaxrs.ifaces");
        } else if (level == ModuleLevel.JAX_RS_IMPLEMENTATION) {
            return sourceName.replace("model", "jaxrs.impl");
        }else if (level == ModuleLevel.JAX_RS_TEST) {
            return sourceName.replace("model", "jaxrs.test");
        } else if (level == ModuleLevel.PRINCIPAL_VIEW) {
            String[] packageName = sourceName.split("model");
            if (packageName.length > 1) {
                return packageName[0] + "views.principal";
            }
        }
        return sourceName;

    }

    /**
     *
     * @param repository
     * @param modelClass
     * @param idClass
     * @throws JClassAlreadyExistsException
     * @throws IOException
     */
    public static void buildDAOInterface(File repository, Class<?> modelClass, Class<?> idClass) throws JClassAlreadyExistsException, IOException {

        //Map keyMap = AnnotationsProcessor.getPrimaryKeyName(modelClass);
        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE));
        //System.out.println("HAHAHAHAHAHAHAHA ::::::::::::::::::::: "+repository+" ::::  "+modelClass+" :::: "+idClass);
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(GenericDAO.class).narrow(modelClass, idClass);
        JDefinedClass def = cl._interface(modelClass.getSimpleName() + "DAO");
        def._extends(extendsClass);
        JFieldVar field = def.field(25, String.class, "SERVICE_NAME");
        //field.
        //field.annotate(InjectService.class);

        field.init(JExpr.lit(modelClass.getSimpleName() + "DAO"));

        //Ajout du commentaire au niveau de l'interface
        def.javadoc().add("Interface etendue par les interfaces locale et remote de la DAO" + System.getProperty("line.separator"));
        def.javadoc().append("@since " + (new Date()).toString());

        //Ajout du commentaire au niveau de la variable d'instance
        field.javadoc().add("Nom du service");

        codeModel.build(repository);
    }

    /**
     *
     * @param repository
     * @param modelClass
     * @param idClass
     * @throws JClassAlreadyExistsException
     * @throws IOException
     */
    public static void buildDAOInterfaceEJBLocal(File repository, Class<?> modelClass, Class<?> idClass) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        //Map keyMap = AnnotationsProcessor.getPrimaryKeyName(modelClass);
        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE));
        ClassLoader urlCl = URLClassLoader.newInstance(new URL[]{repository.toURL()});
        //Class<?> ifacesClass = urlCl.loadClass(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE)+"."+modelClass.getSimpleName()+"DAO");
        //System.out.println("EJB Specification is too strong :::::::::::::::::::::::::::::: "+ifacesClass);
        //Defintion des parametre
        //JClass implementsClass = codeModel.ref(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE)+"."+modelClass.getSimpleName()+"DAO");
        JClass extendsClass = codeModel.ref(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE) + "." + modelClass.getSimpleName() + "DAO");
        JDefinedClass def = cl._interface(modelClass.getSimpleName() + "DAOLocal");
        def._implements(extendsClass);
        def.annotate(Local.class);
          //JFieldVar field = def.field(25, String.class, "SERVICE_NAME");
        //field.
        //field.annotate(InjectService.class);

        //field.init(JExpr.lit(modelClass.getSimpleName()+"DAO"));       
        //Ajout du commentaire au niveau de l'interface
        def.javadoc().add("Interface locale de la DAO" + System.getProperty("line.separator"));
        def.javadoc().append("@since " + (new Date()).toString());

        codeModel.build(repository);
    }

    /**
     *
     * @param repository
     * @param modelClass
     * @param idClass
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void buildDAOInterfaceEJBRemote(File repository, Class<?> modelClass, Class<?> idClass) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        //Map keyMap = AnnotationsProcessor.getPrimaryKeyName(modelClass);
        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE));
        ClassLoader urlCl = URLClassLoader.newInstance(new URL[]{repository.toURL()});
        //Class<?> ifacesClass = urlCl.loadClass(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE)+"."+modelClass.getSimpleName()+"DAO");
        //System.out.println("EJB Specification is too strong :::::::::::::::::::::::::::::: "+ifacesClass);
        //Defintion des parametre
        //JClass implementsClass = codeModel.ref(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE)+"."+modelClass.getSimpleName()+"DAO");
        JClass extendsClass = codeModel.ref(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE) + "." + modelClass.getSimpleName() + "DAO");
        JDefinedClass def = cl._interface(modelClass.getSimpleName() + "DAORemote");
        def._implements(extendsClass);
        def.annotate(Remote.class);
       //JFieldVar field = def.field(25, String.class, "SERVICE_NAME");
        //field.
        //field.annotate(InjectService.class);

        //field.init(JExpr.lit(modelClass.getSimpleName()+"DAO"));
        //Ajout du commentaire au niveau de l'interface
        def.javadoc().add("Interface remote de la DAO" + System.getProperty("line.separator"));
        def.javadoc().append("@since " + (new Date()).toString());

        codeModel.build(repository);
    }

    /**
     *
     * @param repository
     * @param modelClass
     * @throws JClassAlreadyExistsException
     * @throws IOException
     */
    public static void buildManagerInterface(File repository, Class<?> modelClass, Class<?> idClass,String specification) throws JClassAlreadyExistsException, IOException {
        //Map keyMap = AnnotationsProcessor.getPrimaryKeyName(modelClass);

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.MANAGER_INTERFACE));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(GenericManager.class).narrow(modelClass, idClass);
        JDefinedClass def = cl._interface(modelClass.getSimpleName() + "Manager");
        def._extends(extendsClass);
        JFieldVar field = def.field(25, String.class, "SERVICE_NAME");
        //field.
        //field.annotate(InjectService.class);

        if(specification==null||specification.trim().isEmpty()||specification.trim().equalsIgnoreCase("none")){
            StringBuilder builder = new StringBuilder(buildPackage(modelClass, ModuleLevel.MANAGER_IMPLEMENTATION));
            builder.append("."+modelClass.getSimpleName()+"ManagerImpl");
            field.init(JExpr.lit(builder.toString()));
        }else{
            field.init(JExpr.lit(modelClass.getSimpleName() + "Manager"));
        }

        //Ajout du commentaire au niveau de l'interface
        def.javadoc().add("Interface etendue par les interfaces locale et remote du manager" + System.getProperty("line.separator"));

        def.javadoc().append("@since " + (new Date()).toString());

        codeModel.build((repository));
    }

    /**
     *
     * @param repository
     * @param modelClass
     * @throws JClassAlreadyExistsException
     * @throws IOException
     */
    public static void buildManagerInterfaceEJBLocal(File repository, Class<?> modelClass, Class<?> idClass) throws JClassAlreadyExistsException, IOException {
        //Map keyMap = AnnotationsProcessor.getPrimaryKeyName(modelClass);

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.MANAGER_INTERFACE));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(buildPackage(modelClass, ModuleLevel.MANAGER_INTERFACE) + "." + modelClass.getSimpleName() + "Manager");
        JDefinedClass def = cl._interface(modelClass.getSimpleName() + "ManagerLocal");
        def.annotate(Local.class);
        def._implements(extendsClass);
          //JFieldVar field = def.field(25, String.class, "SERVICE_NAME");
        //field.
        //field.annotate(InjectService.class);

        //field.init(JExpr.lit(modelClass.getSimpleName()+"Manager"));    
        //Ajout du commentaire au niveau de l'interface
        def.javadoc().add("Interface locale du manager" + System.getProperty("line.separator"));
        def.javadoc().append("@since " + (new Date()).toString());

        codeModel.build((repository));
    }

  
    
    /**
     *
     * @param repository
     * @param modelClass
     * @throws JClassAlreadyExistsException
     * @throws IOException
     */
    public static void buildManagerInterfaceEJBRemote(File repository, Class<?> modelClass, Class<?> idClass) throws JClassAlreadyExistsException, IOException {
        //Map keyMap = AnnotationsProcessor.getPrimaryKeyName(modelClass);

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.MANAGER_INTERFACE));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(buildPackage(modelClass, ModuleLevel.MANAGER_INTERFACE) + "." + modelClass.getSimpleName() + "Manager");
        JDefinedClass def = cl._interface(modelClass.getSimpleName() + "ManagerRemote");
        def.annotate(Remote.class);
        def._implements(extendsClass);
       //JFieldVar field = def.field(25, String.class, "SERVICE_NAME");
        //field.
        //field.annotate(InjectService.class);

        //field.init(JExpr.lit(modelClass.getSimpleName()+"Manager")); 
        //Ajout du commentaire au niveau de l'interface
        def.javadoc().add("Interface remote du manager" + System.getProperty("line.separator"));
        def.javadoc().append("@since " + (new Date()).toString());

        codeModel.build((repository));
    }
    
      /**
     * 
     * @param repository
     * @param modelClass
     * @param idClass
     * @throws JClassAlreadyExistsException
     * @throws IOException 
     */
    public static void buildRestInterface(File repository, Class<?> modelClass, Class<?> idClass) throws JClassAlreadyExistsException, IOException {
        //Map keyMap = AnnotationsProcessor.getPrimaryKeyName(modelClass);

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.JAX_RS_INTERFACE));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(GenericService.class).narrow(modelClass,idClass);
        JDefinedClass def = cl._interface(modelClass.getSimpleName() + "RS");
        StringBuilder build = new StringBuilder("/");
        build.append(modelClass.getSimpleName().toLowerCase());
        //def.annotate(Path.class).param("value", build.toString());
        def._implements(extendsClass);
          //JFieldVar field = def.field(25, String.class, "SERVICE_NAME");
        //field.
        //field.annotate(InjectService.class);

        //field.init(JExpr.lit(modelClass.getSimpleName()+"Manager"));    
        //Ajout du commentaire au niveau de l'interface
        def.javadoc().add("Interface du service JAX-RS" + System.getProperty("line.separator"));
        def.javadoc().append("@since " + (new Date()).toString());

        codeModel.build((repository));
    }
    

     /**
     *
     * @param repository
     * @param modelClass
     * @param idClass
     * @param application
     * @param module
     * @param persistenceUnit
     * @throws JClassAlreadyExistsException
     * @throws IOException
     */
    public static void buildRestImplementationClass(File repository, Class<?> modelClass, Class<?> idClass
            ,String application , String module)
            throws JClassAlreadyExistsException, IOException {

        //Map keyMap = AnnotationsProcessor.getPrimaryKeyName(modelClass);
        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.JAX_RS_IMPLEMENTATION));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(AbstractGenericService.class).narrow(modelClass, idClass);
        JDefinedClass def = cl._class(modelClass.getSimpleName() + "RSImpl");
        StringBuilder build = new StringBuilder("/");
        build.append(modelClass.getSimpleName().toLowerCase());
        def.annotate(Path.class).param("value", build.toString());
        def._extends(extendsClass);
        JClass implementsClass = codeModel.ref(buildPackage(modelClass, ModuleLevel.JAX_RS_INTERFACE) + "." + modelClass.getSimpleName() + "RS");
        def._implements(implementsClass);
        def.constructor(1).body().block().directStatement("super();");
        JClass resultClass = codeModel.ref(GenericManager.class).narrow(modelClass,idClass);
        JMethod method = def.method(1, resultClass, "getManager");
        method.annotate(Override.class);
        JBlock bl = method.body();
        bl._return(JExpr.ref("manager"));
        //Creation de la metode getModuleName
        //JClass modResult = codeModel.ref(String.class);
        //JMethod moduleMethod = def.method(1, String.class, "getModuleName");
        //moduleMethod.body()._return(JExpr.direct(module));
        JClass paramClass = codeModel.ref(String.class);
        JMethod method2 = def.method(1, paramClass, "getModuleName");
        //method2.annotate(Override.class);
        JBlock b2 = method2.body();
        b2._return(JExpr.direct((module!=null&&!module.trim().isEmpty()) ? "\""+module+"\"":"\""+application+"\""));
        
        JClass managerIFace = codeModel.ref(buildPackage(modelClass, ModuleLevel.MANAGER_INTERFACE) + "." + modelClass.getSimpleName() + "ManagerRemote");
        
        JFieldVar field = def.field(2, managerIFace, "manager");
        JAnnotationUse annotation = null ;
        
        if(application!=null&&!application.trim().isEmpty()){
            annotation = field.annotate(Manager.class).param("application", application);
        }
        
        if(module!=null&&!module.trim().isEmpty()){
            
            if(annotation==null){
                annotation = field.annotate(Manager.class).param("module", module);
            }else{
                annotation = annotation.param("module", module);
            }
        }
        
        if(annotation==null){
            field.annotate(Manager.class).param("name",modelClass.getSimpleName() + "ManagerImpl");
        }else{
            annotation = annotation.param("name",modelClass.getSimpleName() + "ManagerImpl");
        }
        
        annotation.param("interf", managerIFace);
        //field.annotate(Manager.class.class).param("name",modelClass.getSimpleName() + "Manager");

        //field.init(JExpr.lit(modelClass.getSimpleName()+"DAO"));  
        //Ajout du commentaire au niveau de la classe
        def.javadoc().add("Classe d'implementation du Web Service JAX-RS" + System.getProperty("line.separator"));
        def.javadoc().append("@since " + (new Date()).toString());

        //Ajout du commentaire au niveau de la variable d'instance
        field.javadoc().add("On injecte un Gestionnaire d'entites");

        //Ajout du commentaire au niveau de la methode
        method.javadoc().add("Methode permettant de retourner le gestionnaire d'entites");

        //Ajout du commentaire au niveau de la methode
        //method2.javadoc().add("Methode permettant de retourner la classe de l'entite");

        codeModel.build((repository));
    }

    
    /**
     *
     * @param repository
     * @param modelClass
     * @param idClass
     * @param persistenceUnit
     * @throws JClassAlreadyExistsException
     * @throws IOException
     */
    public static void buildDAOImplementationClass(File repository, Class<?> modelClass, Class<?> idClass, String persistenceUnit)
            throws JClassAlreadyExistsException, IOException {

        //Map keyMap = AnnotationsProcessor.getPrimaryKeyName(modelClass);
        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.DAO_IMPLEMENTATION));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(AbstractGenericDAO.class).narrow(modelClass, idClass);
        JDefinedClass def = cl._class(modelClass.getSimpleName() + "DAOImpl");
        def._extends(extendsClass);
        JClass implementsClass = codeModel.ref(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE) + "." + modelClass.getSimpleName() + "DAO");
        def._implements(implementsClass);
        def.constructor(1);
        JMethod method = def.method(1, EntityManager.class, "getEntityManager");
        method.annotate(Override.class);
        JBlock bl = method.body();
        bl._return(JExpr.ref("em"));
        JClass paramClass = codeModel.ref(Class.class).narrow(modelClass);
        JMethod method2 = def.method(1, paramClass, "getManagedEntityClass");
        method2.annotate(Override.class);
        JBlock b2 = method2.body();
        b2._return(JExpr.direct(modelClass.getSimpleName() + ".class"));
        JFieldVar field = def.field(2, EntityManager.class, "em");
        //field.
        field.annotate(PersistenceManager.class).param("unitName", persistenceUnit);

        //field.init(JExpr.lit(modelClass.getSimpleName()+"DAO"));  
        //Ajout du commentaire au niveau de la classe
        def.javadoc().add("Classe d'implementation de la DAO" + System.getProperty("line.separator"));
        def.javadoc().append("@since " + (new Date()).toString());

        //Ajout du commentaire au niveau de la variable d'instance
        field.javadoc().add("On injecte un Gestionnaire d'entites");

        //Ajout du commentaire au niveau de la methode
        method.javadoc().add("Methode permettant de retourner le gestionnaire d'entites");

        //Ajout du commentaire au niveau de la methode
        method2.javadoc().add("Methode permettant de retourner la classe de l'entite");

        codeModel.build((repository));
    }

    /**
     *
     * @param repository
     * @param modelClass
     * @param idClass
     * @param persistenceUnit
     * @throws JClassAlreadyExistsException
     * @throws IOException
     */
    public static void buildDAOImplementationClassEJBStateless(File repository, Class<?> modelClass, Class<?> idClass, String persistenceUnit)
            throws JClassAlreadyExistsException, IOException {

        //Map keyMap = AnnotationsProcessor.getPrimaryKeyName(modelClass);
        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.DAO_IMPLEMENTATION));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(AbstractGenericDAO.class).narrow(modelClass, idClass);
        JDefinedClass def = cl._class(modelClass.getSimpleName() + "DAOImpl");
        def._extends(extendsClass);
        JClass implementsClass = codeModel.ref(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE) + "." + modelClass.getSimpleName() + "DAOLocal");
        JClass implementsClass2 = codeModel.ref(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE) + "." + modelClass.getSimpleName() + "DAORemote");
        def._implements(implementsClass)._implements(implementsClass2);
        def.annotate(Stateless.class)/*.param("name", modelClass.getSimpleName() + "DAO")*/.param("mappedName", modelClass.getSimpleName() + "DAO");
        def.constructor(1);
        JMethod method = def.method(1, EntityManager.class, "getEntityManager");
        method.annotate(Override.class);
        JBlock bl = method.body();
        bl._return(JExpr.ref("em"));
        JClass paramClass = codeModel.ref(Class.class).narrow(modelClass);
        JMethod method2 = def.method(1, paramClass, "getManagedEntityClass");
        method2.annotate(Override.class);
        JBlock b2 = method2.body();
        b2._return(JExpr.direct(modelClass.getSimpleName() + ".class"));
        JFieldVar field = def.field(2, EntityManager.class, "em");
        //field.
        field.annotate(PersistenceContext.class).param("unitName", persistenceUnit);

        //field.init(JExpr.lit(modelClass.getSimpleName()+"DAO"));       
        codeModel.build((repository));
    }

    /**
     *
     * @param repository
     * @param modelClass
     * @param idClass
     * @param persistenceUnit
     * @throws JClassAlreadyExistsException
     * @throws IOException
     */
    public static void buildDAOImplementationClassEJBStateful(File repository, Class<?> modelClass, Class<?> idClass, String persistenceUnit)
            throws JClassAlreadyExistsException, IOException {

        //Map keyMap = AnnotationsProcessor.getPrimaryKeyName(modelClass);
        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.DAO_IMPLEMENTATION));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(AbstractGenericDAO.class).narrow(modelClass, idClass);
        JDefinedClass def = cl._class(modelClass.getSimpleName() + "DAOImpl");
        def._extends(extendsClass);
        JClass implementsClass = codeModel.ref(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE) + "." + modelClass.getSimpleName() + "DAOLocal");
        JClass implementsClass2 = codeModel.ref(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE) + "." + modelClass.getSimpleName() + "DAORemote");
        def._implements(implementsClass)._implements(implementsClass2);
        def.annotate(Stateful.class)/*.param("name", modelClass.getSimpleName() + "DAO")*/.param("mappedName", modelClass.getSimpleName() + "DAO");
        def.constructor(1);
        JMethod method = def.method(1, EntityManager.class, "getEntityManager");
        method.annotate(Override.class);
        JBlock bl = method.body();
        bl._return(JExpr.ref("em"));
        JClass paramClass = codeModel.ref(Class.class).narrow(modelClass);
        JMethod method2 = def.method(1, paramClass, "getManagedEntityClass");
        method2.annotate(Override.class);
        JBlock b2 = method2.body();
        b2._return(JExpr.direct(modelClass.getSimpleName() + ".class"));
        JFieldVar field = def.field(2, EntityManager.class, "em");
        //field.
        field.annotate(PersistenceContext.class).param("unitName", persistenceUnit);

        //field.init(JExpr.lit(modelClass.getSimpleName()+"DAO"));       
        codeModel.build((repository));
    }

    /**
     *
     * @param repository
     * @param modelClass
     * @param idClass
     * @param idName
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void buildManagerImplementationClass(File repository, Class<?> modelClass, Class<?> idClass, String idName)
            throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.MANAGER_IMPLEMENTATION));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(AbstractGenericManager.class).narrow(modelClass, idClass);
        JDefinedClass def = cl._class(modelClass.getSimpleName() + "ManagerImpl");
        def._extends(extendsClass);
        JClass implementsClass = codeModel.ref(buildPackage(modelClass, ModuleLevel.MANAGER_INTERFACE) + "." + modelClass.getSimpleName() + "Manager");
        def._implements(implementsClass);
        def.annotate(Transaction.class);
        def.constructor(1);
        JClass paramClass = codeModel.ref(GenericDAO.class).narrow(modelClass, idClass);
        JMethod method = def.method(1, paramClass, "getDao");
        method.annotate(Override.class);
        JBlock bl = method.body();
        bl._return(JExpr.ref("dao"));
        JMethod method2 = def.method(1, String.class, "getEntityIdName");
        method2.annotate(Override.class);
        JBlock b2 = method2.body();
        //System.out.println(":::::::::::::::::::::::::::  "+modelClass.getPackage());
        b2._return(JExpr.lit(idName));
        //Class<?> classe = Class.forName(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE)+"."+modelClass.getSimpleName()+"DAO");
        JFieldVar field = def.field(2, GenericDAO.class, "dao");
        //field.
        field.annotate(Inject.class).param("value", buildPackage(modelClass, ModuleLevel.DAO_IMPLEMENTATION) + "." + modelClass.getSimpleName() + "DAOImpl");

        //field.init(JExpr.lit(modelClass.getSimpleName()+"DAO"));   
        //Ajout du commentaire au niveau de la classe
        def.javadoc().add("Classe d'implementation du manager" + System.getProperty("line.separator"));
        def.javadoc().append("@since " + (new Date()).toString());

        //Ajout du commentaire au niveau de la variable d'instance
        field.javadoc().add("On injecte la DAO");

        //Ajout du commentaire au niveau de la methode
        method.javadoc().add("Methode permettant de retourner la DAO");

        //Ajout du commentaire au niveau de la methode
        method2.javadoc().add("Methode permettant de retourner l'id de l'entite");

        codeModel.build((repository));
    }

    /**
     *
     * @param repository
     * @param modelClass
     * @param idClass
     * @param idName
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void buildManagerImplementationClassStateless(File repository, Class<?> modelClass, Class<?> idClass, String idName)
            throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.MANAGER_IMPLEMENTATION));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(AbstractGenericManager.class).narrow(modelClass, idClass);
        JDefinedClass def = cl._class(modelClass.getSimpleName() + "ManagerImpl");
        def._extends(extendsClass);
        JClass implementsClass = codeModel.ref(buildPackage(modelClass, ModuleLevel.MANAGER_INTERFACE) + "." + modelClass.getSimpleName() + "ManagerLocal");
        JClass implementsClass2 = codeModel.ref(buildPackage(modelClass, ModuleLevel.MANAGER_INTERFACE) + "." + modelClass.getSimpleName() + "ManagerRemote");
        def._implements(implementsClass)._implements(implementsClass2);
        def.annotate(TransactionAttribute.class).annotate(Stateless.class);
        def.annotate(Stateless.class)/*.param("name", modelClass.getSimpleName() + "Manager")*/.param("mappedName", modelClass.getSimpleName() + "Manager");
        def.constructor(1);
        JClass paramClass = codeModel.ref(GenericDAO.class).narrow(modelClass, idClass);
        JMethod method = def.method(1, paramClass, "getDao");
        method.annotate(Override.class);
        JBlock bl = method.body();
        bl._return(JExpr.ref("dao"));
        JMethod method2 = def.method(1, String.class, "getEntityIdName");
        method2.annotate(Override.class);
        JBlock b2 = method2.body();
        //System.out.println(":::::::::::::::::::::::::::  "+modelClass.getPackage());
        b2._return(JExpr.lit(idName));
        //Class<?> classe = Class.forName(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE)+"."+modelClass.getSimpleName()+"DAO");
        JClass implementsdaoClass = codeModel.ref(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE) + "." + modelClass.getSimpleName() + "DAOLocal");        
        JFieldVar field = def.field(2, implementsdaoClass, "dao");
        //field.
        field.annotate(EJB.class).param("name", modelClass.getSimpleName() + "DAO");

        //field.init(JExpr.lit(modelClass.getSimpleName()+"DAO"));       
        codeModel.build((repository));
    }

    /**
     *
     * @param repository
     * @param modelClass
     * @param idClass
     * @param idName
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void buildManagerImplementationClassStateful(File repository, Class<?> modelClass, Class<?> idClass, String idName)
            throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.MANAGER_IMPLEMENTATION));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(AbstractGenericManager.class).narrow(modelClass, idClass);
        JDefinedClass def = cl._class(modelClass.getSimpleName() + "ManagerImpl");
        def._extends(extendsClass);
        JClass implementsClass = codeModel.ref(buildPackage(modelClass, ModuleLevel.MANAGER_INTERFACE) + "." + modelClass.getSimpleName() + "ManagerLocal");
        JClass implementsClass2 = codeModel.ref(buildPackage(modelClass, ModuleLevel.MANAGER_INTERFACE) + "." + modelClass.getSimpleName() + "ManagerRemote");
        def._implements(implementsClass)._implements(implementsClass2);
        def.annotate(TransactionAttribute.class).annotate(Stateful.class);
        def.annotate(Stateful.class)/*.param("name", modelClass.getSimpleName() + "Manager")*/.param("mappedName", modelClass.getSimpleName() + "Manager");
        JClass paramClass = codeModel.ref(GenericDAO.class).narrow(modelClass, idClass);
        def.constructor(1);
        JMethod method = def.method(1, paramClass, "getDao");
        method.annotate(Override.class);
        JBlock bl = method.body();
        bl._return(JExpr.ref("dao"));
        JMethod method2 = def.method(1, String.class, "getEntityIdName");
        method2.annotate(Override.class);
        JBlock b2 = method2.body();
        //System.out.println(":::::::::::::::::::::::::::  "+modelClass.getPackage());
        b2._return(JExpr.lit(idName));
        //Class<?> classe = Class.forName(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE)+"."+modelClass.getSimpleName()+"DAO");
        JFieldVar field = def.field(2, GenericDAO.class, "dao");
        //field.
        field.annotate(EJB.class).param("name", modelClass.getSimpleName() + "DAO");

        //field.init(JExpr.lit(modelClass.getSimpleName()+"DAO"));       
        codeModel.build((repository));
    }

    /**
     *
     * @param repository
     * @param modelClass
     * @param idClass
     * @param idName
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void buildJSFManagedBean(File repository, Class<?> modelClass, Class<?> idClass, String idName)
            throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.MANAGED_BEAN));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(AbstractGenericBean.class).narrow(modelClass, idClass);
        JDefinedClass def = cl._class(modelClass.getSimpleName() + "Bean");
        def._extends(extendsClass);
        JMethod constructor = def.constructor(1);
        constructor._throws(IllegalArgumentException.class)._throws(IllegalAccessException.class)._throws(NamingException.class);
        constructor.body().directStatement("super();");
        constructor.body().directStatement("super.initialise(this);");
        //Class<?> classe = Class.forName(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE)+"."+modelClass.getSimpleName()+"DAO");
        JClass managerClass = codeModel.ref(buildPackage(modelClass, ModuleLevel.MANAGER_INTERFACE) + "." + modelClass.getSimpleName() + "ManagerLocal");
        JFieldVar field = def.field(4, managerClass, "manager");
        //field.
        field.annotate(EJB.class).param("name", modelClass.getSimpleName() + "Manager");
        JFieldVar field2 = def.field(4, modelClass, "currentObject");
        buildFieldGetterAndSetter(modelClass, def, idName);
        def.annotate(ManagedBean.class).param("name", modelClass.getSimpleName() + "Bean");
        def.annotate(SessionScoped.class);
        JMethod method = def.method(1, modelClass, "getCurrentObject");
        method.annotate(Override.class);
        JBlock bl = method.body();
        bl._return(JExpr.ref("currentObject"));
        JClass paramClass = codeModel.ref(GenericManager.class).narrow(modelClass, idClass);
        JMethod method2 = def.method(1, paramClass, "getManager");
        method2.annotate(Override.class);
        JBlock b2 = method2.body();
        b2._return(JExpr.ref("manager"));
        JMethod method3 = def.method(1, idClass, "getPrimaryKey");
        method3.param(modelClass, "object");
        String idValues = ("" + idName.charAt(0)).toUpperCase() + idName.substring(1);
        JBlock b3 = method3.body();//._return(JExpr.ref("currentObject.get"+idValues+"()"));
        b3.directStatement("return object.get" + idValues + "() ;");
        method3.annotate(Override.class);
        JClass typeClass = codeModel.ref(List.class).narrow(modelClass);
        JMethod method4 = def.method(1, typeClass, "getSelectedObjects");
        method4.body().directStatement("throw new UnsupportedOperationException(\"Not supported yet.\"); ");
        method4.annotate(Override.class);
        JMethod method5 = def.method(1, Map.class, "getReportParameters");
        method5.body().directStatement("throw new UnsupportedOperationException(\"Not supported yet.\"); ");
        method5.annotate(Override.class);
        JMethod method6 = def.method(1, String.class, "getJasperFileName");
        method6.body().directStatement("throw new UnsupportedOperationException(\"Not supported yet.\"); ");
        method6.annotate(Override.class);
      //System.out.println(":::::::::::::::::::::::::::  "+modelClass.getPackage());     

        //field.init(JExpr.lit(modelClass.getSimpleName()+"DAO"));       
        codeModel.build((repository));
    }

    /**
     * Construit la fenêtre XHTML
     * @param repository
     * @param modelClass
     * @param idClass
     * @param idName
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static void buildPrimeFacesView(File repository, Class<?> modelClass, Class<?> idClass, String idName)
            throws JClassAlreadyExistsException, IOException, ClassNotFoundException {
        
    }
    
    /**
     *
     * @param repository
     * @param modelClass
     * @param idClass
     * @param idName
     * @param interne
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void buildEditDialog(File repository, Class<?> modelClass, Class<?> idClass, String idName, boolean interne)
            throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.VIEW));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(AbstractEditTemplateDialog.class).narrow(modelClass, idClass);
        JDefinedClass def = cl._class(modelClass.getSimpleName() + "Dialog");
        def._extends(extendsClass);
        JClass class0 = codeModel.ref(MessagesBundle.class);
        def.field(4, class0, "bundle");
        JClass class01 = codeModel.ref(ResourcesBundle.class);
        def.field(4, class01, "resourcesbundle");
        //Constructeur par defaut
        def.constructor(1).javadoc().add("Constructeur par defaut");
        JMethod constructor = def.constructor(1);
        constructor.param(Frame.class, "parent");
        constructor.body().directStatement(" super(parent);");
        constructor.body().directStatement("interne = " + interne + " ;");
        JMethod constructor1 = def.constructor(1);
        constructor1.param(Frame.class, "parent");
        constructor1.param(String.class, "title");
        constructor1.body().directStatement(" super(parent , title);");
        constructor1.body().directStatement("interne = " + interne + " ;");
        JMethod constructor2 = def.constructor(1);
        constructor2.param(JFrame.class, "parent");
        constructor2.param(Boolean.class, "modal");
        constructor2.param(TypeOperation.class, "typeOperation");
        constructor2.body().directStatement(" super(parent , modal ,typeOperation );");
        constructor2.body().directStatement("interne = " + interne + " ;");
        JMethod method3 = def.method(1, idClass, "getPrimaryKey");
        method3.param(modelClass, "object");
        String idValues = ("" + idName.charAt(0)).toUpperCase() + idName.substring(1);
        JBlock b3 = method3.body();//._return(JExpr.ref("currentObject.get"+idValues+"()"));
        b3.directStatement("return object.get" + idValues + "() ;");
        method3.annotate(Override.class);
        JMethod method4 = def.method(1, String.class, "getActionName");
        method4.body().directStatement(" return null ; ");
        method4.annotate(Override.class);
        JMethod method5 = def.method(1, Map.class, "getReportParameters");
        method5.body().directStatement(" return null ; ");
        method5.annotate(Override.class);
        JMethod method6 = def.method(1, String.class, "getJasperFileName");
        method6.body().directStatement(" return null ; ");
        method6.annotate(Override.class);
        JMethod method = def.method(1, String.class, "getWindowTitle");
        method.annotate(Override.class);
        JBlock bl = method.body();
        bl._return(JExpr.direct("MessagesBundle.getMessage(\"" + modelClass.getSimpleName().toLowerCase() + ".edit\")"));
        messages.put(modelClass.getSimpleName().toLowerCase() + ".edit", modelClass.getSimpleName().toUpperCase());
        JMethod method2 = def.method(1, ImageIcon.class, "getImage");
        method2.annotate(Override.class);
        JBlock b2 = method2.body();
        b2.directStatement("try{");
        JClass varClass = codeModel.ref(ImageIcon.class);
        JVar var = b2.decl(varClass, "icon");
        var.init(JExpr._null());
        b2.directStatement("icon = new ImageIcon(getClass().getResource(ResourcesBundle.getResource(\"" + modelClass.getSimpleName().toLowerCase() + ".edit.image\")));");
        resources.put(modelClass.getSimpleName().toLowerCase() + ".edit.image", "");
        //JConditional cond1 = b2._if(JExpr.ref(" url==null"));
        b2._return(var);
        b2.directStatement("}catch(Exception ex){;}");
        b2._return(JExpr._null());
        JMethod method7 = def.method(1, JPanel.class, "getFiledsPanel");
        JBlock b7 = method7.body();
        JConditional cond2 = b7._if(JExpr.direct("middlePanel==null"));
        String entityname = ("" + modelClass.getSimpleName().charAt(0)).toUpperCase() + modelClass.getSimpleName().substring(1);
        cond2._then().block().directStatement("middlePanel = new " + entityname + "EditPanel() ;");
        b7._return(JExpr.direct("middlePanel"));
        //System.out.println(":::::::::::::::::::::::::::  "+modelClass.getPackage());     
        method7.annotate(Override.class);
        JMethod method10 = def.method(1, JFrame.class, "getApplicationFrame");
        method10.body().directStatement("return " + buildPackage(modelClass, ModuleLevel.PRINCIPAL_VIEW) + ".PrincipalScreen.FRAME ;");
        method10.annotate(Override.class);
        //field.init(JExpr.lit(modelClass.getSimpleName()+"DAO"));   
        JMethod method11 = def.method(1, String.class, "getWindowClassName");
        method11.body().directStatement(" return \"" + buildPackage(modelClass, ModuleLevel.VIEW) + "." + modelClass.getSimpleName() + "IFrame\" ;");
        method11.annotate(Override.class);

        //Ajout du commentaire au niveau de classe
        def.javadoc().add("Boite de dialogue d'edition " + def.name() + System.getProperty("line.separator"));
        def.javadoc().append("@since " + (new Date()).toString());

        //Ajout du commentaire au niveau de la methode
        constructor.javadoc().add("Constructeur avec parametres");
        constructor.javadoc().addParam("parent");

        //Ajout du commentaire au niveau de la methode
        constructor1.javadoc().add("Constructeur  avec parametres");
        constructor1.javadoc().addParam("parent");
        constructor1.javadoc().addParam("title");

        //Ajout du commentaire au niveau de la methode
        constructor2.javadoc().add("Constructeur  avec parametres");
        constructor2.javadoc().addParam("parent");
        constructor2.javadoc().addParam("modal");
        constructor2.javadoc().addParam("typeOperation");

        //Ajout du commentaire au niveau de la methode
        method.javadoc().add("Methode permettant de retourner le titre de la fenetre");
        method.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method2.javadoc().add("Methode permettant de retourner l'icone de la fenetre");
        method2.javadoc().addReturn().append(ImageIcon.class.getName());

        //Ajout du commentaire au niveau de la methode
        method3.javadoc().add("Methode permettant de retourner la cle primaire");
        method3.javadoc().addParam("object");
        method3.javadoc().addReturn().append(idClass.getName());

        //Ajout du commentaire au niveau de la methode
        method4.javadoc().add("Methode permettant de retourner nom de l'action");
        method4.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method5.javadoc().add("Methode permettant de retourner les parametres pour le reporting");
        method5.javadoc().addReturn().append(Map.class.getName());

        //Ajout du commentaire au niveau de la methode
        method6.javadoc().add("Methode permettant de retourner le nom du fichier Jasper");
        method6.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method7.javadoc().add("Methode permettant de retourner le panel des champs");
        method7.javadoc().addReturn().append(JPanel.class.getName());

        //Ajout du commentaire au niveau de la methode
        method10.javadoc().add("Methode permettant de retourner l'instance de la fenetre principale");
        method10.javadoc().addReturn().append(JFrame.class.getName());

        //Ajout du commentaire au niveau de la methode
        method11.javadoc().add("Methode permettant de retourner nom complet de la classe");
        method11.javadoc().addReturn().append(String.class.getName());

        codeModel.build((repository));
    }

     /**
     *
     * @param repository
     * @param modelClass
     * @param idClass
     * @param idName
     * @param interne
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void buildTransfertDialog(File repository, Class<?> modelClass, Class<?> idClass, String idName, boolean interne)
            throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.VIEW));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(AbstractTransfertTemplateDialog.class).narrow(modelClass, idClass);
        JDefinedClass def = cl._class(modelClass.getSimpleName() + "TDialog");
        def._extends(extendsClass);
        JClass class0 = codeModel.ref(MessagesBundle.class);
        def.field(4, class0, "bundle");
        JClass class01 = codeModel.ref(ResourcesBundle.class);
        def.field(4, class01, "resourcesbundle");
        //Constructeur par defaut
        JMethod constructor0 = def.constructor(1);
        class0  = codeModel.ref(List.class).narrow(modelClass);
        constructor0.param(class0, "datas");     
        constructor0.body().directStatement("super(datas);");
        constructor0.javadoc().add("Constructeur par defaut");
        JMethod constructor = def.constructor(1);
        constructor.param(Frame.class, "parent");
        class0  = codeModel.ref(List.class).narrow(modelClass);
        constructor.param(class0, "datas") ;       
        constructor.body().directStatement(" super(parent,datas);");
        constructor.body().directStatement("interne = " + interne + " ;");
        JMethod constructor1 = def.constructor(1);
        constructor1.param(Frame.class, "parent");
        constructor1.param(String.class, "title");
        class0  = codeModel.ref(List.class).narrow(modelClass);
        constructor1.param(class0, "datas") ;       
        constructor1.body().directStatement(" super(parent , title,datas);");
        constructor1.body().directStatement("interne = " + interne + " ;");
        JMethod constructor2 = def.constructor(1);
        constructor2.param(JFrame.class, "parent");
        constructor2.param(Boolean.class, "modal");
        constructor2.param(TypeOperation.class, "typeOperation");
        class0  = codeModel.ref(List.class).narrow(modelClass);
        constructor2.param(class0, "datas");        
        constructor2.body().directStatement(" super(parent , modal ,typeOperation,datas );");
        constructor2.body().directStatement("interne = " + interne + " ;");
        JMethod method3 = def.method(1, idClass, "getPrimaryKey");
        method3.param(modelClass, "object");
        String idValues = ("" + idName.charAt(0)).toUpperCase() + idName.substring(1);
        JBlock b3 = method3.body();//._return(JExpr.ref("currentObject.get"+idValues+"()"));
        b3.directStatement("return object.get" + idValues + "() ;");
        method3.annotate(Override.class);
        JMethod method4 = def.method(1, String.class, "getActionName");
        method4.body().directStatement(" return null ; ");
        method4.annotate(Override.class);
        JMethod method5 = def.method(1, Map.class, "getReportParameters");
        method5.body().directStatement(" return null ; ");
        method5.annotate(Override.class);
        JMethod method6 = def.method(1, String.class, "getJasperFileName");
        method6.body().directStatement(" return null ; ");
        method6.annotate(Override.class);
        JMethod method = def.method(1, String.class, "getWindowTitle");
        method.annotate(Override.class);
        JBlock bl = method.body();
        bl._return(JExpr.direct("MessagesBundle.getMessage(\"" + modelClass.getSimpleName().toLowerCase() + ".edit\")"));
        messages.put(modelClass.getSimpleName().toLowerCase() + ".edit", modelClass.getSimpleName().toUpperCase());
        JMethod method2 = def.method(1, ImageIcon.class, "getImage");
        method2.annotate(Override.class);
        JBlock b2 = method2.body();
        b2.directStatement("try{");
        JClass varClass = codeModel.ref(ImageIcon.class);
        JVar var = b2.decl(varClass, "icon");
        var.init(JExpr._null());
        b2.directStatement("icon = new ImageIcon(getClass().getResource(ResourcesBundle.getResource(\"" + modelClass.getSimpleName().toLowerCase() + ".edit.image\")));");
        resources.put(modelClass.getSimpleName().toLowerCase() + ".edit.image", "");
        //JConditional cond1 = b2._if(JExpr.ref(" url==null"));
        b2._return(var);
        b2.directStatement("}catch(Exception ex){;}");
        b2._return(JExpr._null());
       /* JMethod method7 = def.method(1, JPanel.class, "getFiledsPanel");
        JBlock b7 = method7.body();
        JConditional cond2 = b7._if(JExpr.direct("middlePanel==null"));
        String entityname = ("" + modelClass.getSimpleName().charAt(0)).toUpperCase() + modelClass.getSimpleName().substring(1);
        cond2._then().block().directStatement("middlePanel = new " + entityname + "EditPanel() ;");
        b7._return(JExpr.direct("middlePanel"));
        //System.out.println(":::::::::::::::::::::::::::  "+modelClass.getPackage());     
        method7.annotate(Override.class);*/
        JMethod method10 = def.method(1, JFrame.class, "getApplicationFrame");
        method10.body().directStatement("return " + buildPackage(modelClass, ModuleLevel.PRINCIPAL_VIEW) + ".PrincipalScreen.FRAME ;");
        method10.annotate(Override.class);
        //field.init(JExpr.lit(modelClass.getSimpleName()+"DAO"));   
        JMethod method11 = def.method(1, String.class, "getWindowClassName");
        method11.body().directStatement(" return \"" + buildPackage(modelClass, ModuleLevel.VIEW) + "." + modelClass.getSimpleName() + "IFrame\" ;");
        method11.annotate(Override.class);
        method11 = def.method(JMod.PUBLIC, boolean.class, "isSelected");
        method11.annotate(Override.class);
        method11.param(modelClass, "objet");
        method11.body().directStatement("return objet.isSelected();");
        //Ajout du commentaire au niveau de classe
        def.javadoc().add("Boite de dialogue d'edition " + def.name() + System.getProperty("line.separator"));
        def.javadoc().append("@since " + (new Date()).toString());

        //Ajout du commentaire au niveau de la methode
        constructor.javadoc().add("Constructeur avec parametres");
        constructor.javadoc().addParam("parent");

        //Ajout du commentaire au niveau de la methode
        constructor1.javadoc().add("Constructeur  avec parametres");
        constructor1.javadoc().addParam("parent");
        constructor1.javadoc().addParam("title");

        //Ajout du commentaire au niveau de la methode
        constructor2.javadoc().add("Constructeur  avec parametres");
        constructor2.javadoc().addParam("parent");
        constructor2.javadoc().addParam("modal");
        constructor2.javadoc().addParam("typeOperation");

        //Ajout du commentaire au niveau de la methode
        method.javadoc().add("Methode permettant de retourner le titre de la fenetre");
        method.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method2.javadoc().add("Methode permettant de retourner l'icone de la fenetre");
        method2.javadoc().addReturn().append(ImageIcon.class.getName());

        //Ajout du commentaire au niveau de la methode
        method3.javadoc().add("Methode permettant de retourner la cle primaire");
        method3.javadoc().addParam("object");
        method3.javadoc().addReturn().append(idClass.getName());

        //Ajout du commentaire au niveau de la methode
        method4.javadoc().add("Methode permettant de retourner nom de l'action");
        method4.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method5.javadoc().add("Methode permettant de retourner les parametres pour le reporting");
        method5.javadoc().addReturn().append(Map.class.getName());

        //Ajout du commentaire au niveau de la methode
        method6.javadoc().add("Methode permettant de retourner le nom du fichier Jasper");
        method6.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        //method7.javadoc().add("Methode permettant de retourner le panel des champs");
        //method7.javadoc().addReturn().append(JPanel.class.getName());

        //Ajout du commentaire au niveau de la methode
        method10.javadoc().add("Methode permettant de retourner l'instance de la fenetre principale");
        method10.javadoc().addReturn().append(JFrame.class.getName());

        //Ajout du commentaire au niveau de la methode
        method11.javadoc().add("Methode permettant de retourner nom complet de la classe");
        method11.javadoc().addReturn().append(String.class.getName());

        codeModel.build((repository));
    }
    /**
     *
     * @param repository
     * @param modelClass
     * @param idClass
     * @param idName
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void buildInternalFrame(File repository, Class<?> modelClass, Class<?> idClass, String idName, boolean reporting)
            throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.VIEW));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(AbstractListTemplateFrame.class).narrow(modelClass, idClass);
        JDefinedClass def = cl._class(modelClass.getSimpleName() + "IFrame");
        def._extends(extendsClass);
        JClass class0 = codeModel.ref(MessagesBundle.class);
        def.field(4, class0, "bundle");
        JClass class01 = codeModel.ref(ResourcesBundle.class);
        def.field(4, class01, "resourcesbundle");
        JMethod constructor = def.constructor(1);
        //Constructeur par defaut
        constructor.javadoc().add("Constructeur par defaut");
        //constructor.param(Frame.class, "parent");
        constructor.body().directStatement(" super();");
        JFieldVar field = def.field(25, String.class, "FRAME_NAME");
        field.init(JExpr.direct("\"" + buildPackage(modelClass, ModuleLevel.VIEW) + "." + modelClass.getSimpleName() + "IFrame\""));
        //Cas ou la classe est une vue (reserve seulement a l'editique
        if (reporting) {
            constructor.body().directStatement("btnew.setVisible(false);");
            constructor.body().directStatement("btupdate.setVisible(false);");
            constructor.body().directStatement("btdelete.setVisible(false);");
        }
        /*JFieldVar field3 = def.field(4, PaginationStep.class, "pagination");
         field3.init(JExpr._null());*/
        JMethod method3 = def.method(1, idClass, "getPrimaryKey");
        method3.param(modelClass, "object");
        String idValues = ("" + idName.charAt(0)).toUpperCase() + idName.substring(1);
        JBlock b3 = method3.body();//._return(JExpr.ref("currentObject.get"+idValues+"()"));
        b3.directStatement("return object.get" + idValues + "() ;");
        method3.annotate(Override.class);
        JMethod method4 = def.method(1, String.class, "getActionName");
        method4.body().directStatement(" return \"" + modelClass.getSimpleName().toLowerCase() + "Action\" ; ");
        method4.annotate(Override.class);
        JMethod method5 = def.method(1, Map.class, "getReportParameters");
        JClass class5 = codeModel.ref(Map.class);
        JClass class51 = codeModel.ref(HashMap.class);
        JVar var5 = method5.body().decl(class5, "parametres");
        var5.init(JExpr._new(class51));
        method5.body().directStatement("parametres.put(\"titreRapport\", MessagesBundle.getMessage(\"" + modelClass.getSimpleName().toLowerCase() + ".title.report\")) ;");
        method5.body().directStatement("parametres.put(\"piedRapport\", MessagesBundle.getMessage(\"" + modelClass.getSimpleName().toLowerCase() + ".footer.report\")) ;");
        method5.body().directStatement("parametres.put(\"nombreLignesRapport\", getSelectedObjects().size()) ;");
        method5.body().directStatement("return parametres ; ");
        messages.put(modelClass.getSimpleName().toLowerCase() + ".title.report", "");
        messages.put(modelClass.getSimpleName().toLowerCase() + ".footer.report", "");
        method5.annotate(Override.class);
        JMethod method6 = def.method(1, String.class, "getJasperFileName");
        JConditional cond6 = method6.body()._if(JExpr.direct("getSelectedObjects().size()==0 || getSelectedObjects().isEmpty()"));
        cond6._then()._return(JExpr._null());
        JConditional cond61 = cond6._elseif(JExpr.direct("getSelectedObjects().size()==1"));
        cond61._then()._return(JExpr.direct("ResourcesBundle.getResource(\"" + modelClass.getSimpleName().toLowerCase() + ".detail.report\")"));
        cond61._else()._return(JExpr.direct("ResourcesBundle.getResource(\"" + modelClass.getSimpleName().toLowerCase() + ".list.report\")"));
        resources.put(modelClass.getSimpleName().toLowerCase() + ".detail.report", "");
        resources.put(modelClass.getSimpleName().toLowerCase() + ".list.report", "");
        //method6.body().directStatement("ResourcesBundle.getResource(\""+modelClass.getSimpleName().toLowerCase()+".list\")");
        method6.annotate(Override.class);
        JMethod method = def.method(1, String.class, "getWindowTitle");
        method.annotate(Override.class);
        JBlock bl = method.body();
        bl._return(JExpr.direct("MessagesBundle.getMessage(\"" + modelClass.getSimpleName().toLowerCase() + ".list\")"));
        messages.put(modelClass.getSimpleName().toLowerCase() + ".list", modelClass.getSimpleName().toUpperCase());
        JMethod method2 = def.method(1, ImageIcon.class, "getImage");
        method2.annotate(Override.class);
        JBlock b2 = method2.body();
        b2.directStatement("try{");
        JClass varClass = codeModel.ref(ImageIcon.class);
        JVar var = b2.decl(varClass, "icon");
        var.init(JExpr._null());
        b2.directStatement("icon = new ImageIcon(getClass().getResource(ResourcesBundle.getResource(\"" + modelClass.getSimpleName().toLowerCase() + ".list.image\")));");
        resources.put(modelClass.getSimpleName().toLowerCase() + ".list.image", "");
        //JConditional cond1 = b2._if(JExpr.ref("url==null"));
        b2._return(var);
        b2.directStatement("} catch(Exception ex ){;}");
        b2._return(JExpr._null());
        JMethod method7 = def.method(1, JPanel.class, "getCriteriaPanel");
        JBlock b7 = method7.body();
        JConditional cond2 = b7._if(JExpr.direct("criteriaPanel==null"));
        String entityname = ("" + modelClass.getSimpleName().charAt(0)).toUpperCase() + modelClass.getSimpleName().substring(1);
        cond2._then().block().directStatement("criteriaPanel = new " + entityname + "CriteriaPanel();");
        b7._return(JExpr.direct("criteriaPanel"));
        //System.out.println(":::::::::::::::::::::::::::  "+modelClass.getPackage());     
        method7.annotate(Override.class);
        JClass class7 = codeModel.ref(GenericManager.class).narrow(modelClass, idClass);
        JMethod method8 = def.method(1, class7, "getManager");
        method8._throws(Exception.class);
        JClass iocClass = codeModel.ref(IocContext.class);
        JVar field2 = method8.body().decl(iocClass, "context");
        field2.init(JExpr._new(iocClass));
        method8.body().directStatement("return (GenericManager)context.lookup(\"" + buildPackage(modelClass, ModuleLevel.MANAGER_IMPLEMENTATION) + "." + modelClass.getSimpleName() + "ManagerImpl\");");
        method8.annotate(Override.class);
        JMethod method9 = def.method(1, AbstractTableBaseListModel.class, "getTableModel");
        method9.body()._if(JExpr.direct("model==null"))._then().directStatement(" model = new " + modelClass.getSimpleName() + "Model();");
        method9.body()._return(JExpr.direct("model"));
        method9.annotate(Override.class);
        JMethod method10 = def.method(1, String.class, "getWindowClassName");
        method10.body().directStatement("return \"" + buildPackage(modelClass, ModuleLevel.VIEW) + "." + modelClass.getSimpleName() + "IFrame\" ;");
        method10.annotate(Override.class);
        JMethod method11 = def.method(1, JDialog.class, "getEditDialog");
        method11.annotate(Override.class);
        method11._throws(Exception.class);
        method11.param(modelClass, "object");
        method11.param(GenericManager.class, "manager");
        method11.param(TypeOperation.class, "typeOperation");
        method11.param(JFrame.class, "window");
        JClass class11 = codeModel.ref(modelClass);
        method11.body()._if(JExpr.direct("object==null"))._then().directStatement(" object = new " + modelClass.getSimpleName() + "();");
        method11.body().directStatement(modelClass.getSimpleName() + "Dialog  dialog = " + "new " + modelClass.getSimpleName() + "Dialog(getApplicationFrame() ,true, typeOperation) ;");
        method11.body().directStatement(" dialog.setCurrentObject(object);");
        method11.body().directStatement(" dialog.setManager(manager);");
        method11.body()._return(JExpr.direct("dialog"));
        JMethod method12 = def.method(1, JFrame.class, "getApplicationFrame");
        method12.body().directStatement("return " + buildPackage(modelClass, ModuleLevel.PRINCIPAL_VIEW) + ".PrincipalScreen.FRAME ;");
        method12.annotate(Override.class);
        JMethod method13 = def.method(1, PaginationStep.class, "getPagination");
        method13.annotate(Override.class);
        JBlock b13 = method13.body();
        b13.directStatement("pagination =  new PaginationStep(20);");
        b13.directStatement("return pagination ; ");
        // field.init(JExpr.lit(modelClass.getSim+pleName()+"DAO"));   

        //Ajout du commentaire au niveau de classe
        def.javadoc().add("Fenetre interne " + def.name() + System.getProperty("line.separator"));
        def.javadoc().append("@since " + (new Date()).toString());

        //Ajout du commentaire au niveau de la methode
        method.javadoc().add("Methode permettant de retourner le titre de la fenetre");
        method.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method2.javadoc().add("Methode permettant de retourner l'icone de la fenetre");
        method2.javadoc().addReturn().append(ImageIcon.class.getName());

        //Ajout du commentaire au niveau de la methode
        method3.javadoc().add("Methode permettant de retourner la cle primaire");
        method3.javadoc().addParam("object");
        method3.javadoc().addReturn().append(idClass.getName());

        //Ajout du commentaire au niveau de la methode
        method4.javadoc().add("Methode permettant de retourner nom de l'action");
        method4.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method5.javadoc().add("Methode permettant de retourner les parametres pour le reporting");
        method5.javadoc().addReturn().append(Map.class.getName());

        //Ajout du commentaire au niveau de la methode
        method6.javadoc().add("Methode permettant de retourner le nom du fichier Jasper");
        method6.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method7.javadoc().add("Methode permettant de retourner le panel des champs");
        method7.javadoc().addReturn().append(JPanel.class.getName());

        //Ajout du commentaire au niveau de la methode
        method8.javadoc().add("Methode permettant de retourner le manager");
        method8.javadoc().addReturn().append(GenericManager.class.getName());

        //Ajout du commentaire au niveau de la methode
        method9.javadoc().add("Methode permettant de retourner le modele de tableau");
        method9.javadoc().addReturn().append(AbstractTableBaseListModel.class.getName());

        //Ajout du commentaire au niveau de la methode
        method10.javadoc().add("Methode permettant de retourner le nom complet de la classe");
        method10.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method11.javadoc().add("Methode permettant de retourner nom complet de la classe");
        method11.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method12.javadoc().add("Methode permettant de retourner l'instance de la fenetre principale");
        method12.javadoc().addReturn().append(JFrame.class.getName());

        //Ajout du commentaire au niveau de la methode
        method13.javadoc().add("Methode permettant de retourner une instance de la pagination");
        method13.javadoc().addReturn().append(PaginationStep.class.getName());

        codeModel.build((repository));
    }

      /**
     *
     * @param repository
     * @param modelClass:Class sourcce
     * @param modelClassV:Class validite
     * @param modelClassR:Class rejet
     * @param idClass
     * @param idName
     * @param reporting
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void buildInternalFrame(File repository, Class<?> modelClass
              ,Class<?> modelClassV,Class<?> modelClassR,Class<?> idClass, String idName,String prefix, boolean reporting)

            throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClassV, ModuleLevel.VIEW));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(AbstractListValidationTemplateFrame.class).narrow(modelClass,modelClassV,modelClassR, idClass);
        JDefinedClass def = cl._class(modelClassV.getSimpleName() +(prefix==null ? "":prefix.trim())+ "IFrame");
        def._extends(extendsClass);
        JClass class0 = codeModel.ref(MessagesBundle.class);
        def.field(4, class0, "bundle");
        JClass class01 = codeModel.ref(ResourcesBundle.class);
        def.field(4, class01, "resourcesbundle");
        JMethod constructor = def.constructor(1);
        //Constructeur par defaut
        constructor.javadoc().add("Constructeur par defaut");
        //constructor.param(Frame.class, "parent");
        constructor.body().directStatement(" super();");
        JFieldVar field = def.field(25, String.class, "FRAME_NAME");
        field.init(JExpr.direct("\"" + buildPackage(modelClass, ModuleLevel.VIEW) + "." + modelClass.getSimpleName() + "IFrame\""));

        //Cas ou la classe est une vue (reserve seulement a l'editique
        if (reporting) {
            constructor.body().directStatement("btnew.setVisible(false);");
            constructor.body().directStatement("btupdate.setVisible(false);");
            constructor.body().directStatement("btdelete.setVisible(false);");
        }
        if(modelClassV.equals(modelClassR)){
            constructor.body().directStatement("btrejeter.setVisible(false);");
        }
        /*JFieldVar field3 = def.field(4, PaginationStep.class, "pagination");
         field3.init(JExpr._null());*/
        JMethod method3 = def.method(1, idClass, "getPrimaryKey");
        method3.param(modelClass, "object");
        String idValues = ("" + idName.charAt(0)).toUpperCase() + idName.substring(1);
        JBlock b3 = method3.body();//._return(JExpr.ref("currentObject.get"+idValues+"()"));
        b3.directStatement("return object.get" + idValues + "() ;");
        method3.annotate(Override.class);
        JMethod method4 = def.method(1, String.class, "getActionName");
        method4.body().directStatement(" return \"" + modelClass.getSimpleName().toLowerCase() + "Action\" ; ");
        method4.annotate(Override.class);
        JMethod method5 = def.method(1, Map.class, "getReportParameters");
        JClass class5 = codeModel.ref(Map.class);
        JClass class51 = codeModel.ref(HashMap.class);
        JVar var5 = method5.body().decl(class5, "parametres");
        var5.init(JExpr._new(class51));
        method5.body().directStatement("parametres.put(\"titreRapport\", MessagesBundle.getMessage(\"" + modelClass.getSimpleName().toLowerCase() + ".title.report\")) ;");
        method5.body().directStatement("parametres.put(\"piedRapport\", MessagesBundle.getMessage(\"" + modelClass.getSimpleName().toLowerCase() + ".footer.report\")) ;");
        method5.body().directStatement("parametres.put(\"nombreLignesRapport\", getSelectedObjects().size()) ;");
        method5.body().directStatement("return parametres ; ");
        messages.put(modelClass.getSimpleName().toLowerCase() + ".title.report", "");
        messages.put(modelClass.getSimpleName().toLowerCase() + ".footer.report", "");
        method5.annotate(Override.class);
        JMethod method6 = def.method(1, String.class, "getJasperFileName");
        JConditional cond6 = method6.body()._if(JExpr.direct("getSelectedObjects().size()==0 || getSelectedObjects().isEmpty()"));
        cond6._then()._return(JExpr._null());
        JConditional cond61 = cond6._elseif(JExpr.direct("getSelectedObjects().size()==1"));
        cond61._then()._return(JExpr.direct("ResourcesBundle.getResource(\"" + modelClass.getSimpleName().toLowerCase() + ".detail.report\")"));
        cond61._else()._return(JExpr.direct("ResourcesBundle.getResource(\"" + modelClass.getSimpleName().toLowerCase() + ".list.report\")"));
        resources.put(modelClass.getSimpleName().toLowerCase() + ".detail.report", "");
        resources.put(modelClass.getSimpleName().toLowerCase() + ".list.report", "");
        //method6.body().directStatement("ResourcesBundle.getResource(\""+modelClass.getSimpleName().toLowerCase()+".list\")");
        method6.annotate(Override.class);
        JMethod method = def.method(1, String.class, "getWindowTitle");
        method.annotate(Override.class);
        JBlock bl = method.body();
        bl._return(JExpr.direct("MessagesBundle.getMessage(\"" + modelClass.getSimpleName().toLowerCase() + ".list\")"));
        messages.put(modelClass.getSimpleName().toLowerCase() + ".list", modelClass.getSimpleName().toUpperCase());
        JMethod method2 = def.method(1, ImageIcon.class, "getImage");
        method2.annotate(Override.class);
        JBlock b2 = method2.body();
        b2.directStatement("try{");
        JClass varClass = codeModel.ref(ImageIcon.class);
        JVar var = b2.decl(varClass, "icon");
        var.init(JExpr._null());
        b2.directStatement("icon = new ImageIcon(getClass().getResource(ResourcesBundle.getResource(\"" + modelClass.getSimpleName().toLowerCase() + ".list.image\")));");
        resources.put(modelClass.getSimpleName().toLowerCase() + ".list.image", "");
        //JConditional cond1 = b2._if(JExpr.ref("url==null"));
        b2._return(var);
        b2.directStatement("} catch(Exception ex ){;}");
        b2._return(JExpr._null());
        JMethod method7 = def.method(1, JPanel.class, "getCriteriaPanel");
        JBlock b7 = method7.body();
        JConditional cond2 = b7._if(JExpr.direct("criteriaPanel==null"));
        String entityname = ("" + modelClass.getSimpleName().charAt(0)).toUpperCase() + modelClass.getSimpleName().substring(1);
        cond2._then().block().directStatement("criteriaPanel = new " + entityname + "CriteriaPanel();");
        b7._return(JExpr.direct("criteriaPanel"));
        //System.out.println(":::::::::::::::::::::::::::  "+modelClass.getPackage());     
        method7.annotate(Override.class);
        JClass class7 = codeModel.ref(GenericManager.class).narrow(modelClass, idClass);
        JMethod method8 = def.method(1, class7, "getManager");
        method8._throws(Exception.class);
        JClass iocClass = codeModel.ref(IocContext.class);
        JVar field2 = method8.body().decl(iocClass, "context");
        field2.init(JExpr._new(iocClass));
        method8.body().directStatement("return (GenericManager)context.lookup(\"" + buildPackage(modelClass, ModuleLevel.MANAGER_IMPLEMENTATION) + "." + modelClass.getSimpleName() + "ManagerImpl\");");
        //getValideManager
        class7 = codeModel.ref(GenericManager.class).narrow(modelClassV, idClass);
        JMethod method14 = def.method(1, class7, "getValideManager");
        method14.annotate(Override.class);
        method14._throws(Exception.class);
        iocClass = codeModel.ref(IocContext.class);
        field2 = method14.body().decl(iocClass, "context");
        field2.init(JExpr._new(iocClass));
        method14.body().directStatement("return (GenericManager)context.lookup(\"" + buildPackage(modelClassV, ModuleLevel.MANAGER_IMPLEMENTATION) + "." + modelClassV.getSimpleName() + "ManagerImpl\");");
        //getRejetManager()
        class7 = codeModel.ref(GenericManager.class).narrow(modelClassR, idClass);
        JMethod method15 = def.method(1, class7, "getRejetManager");
        method15.annotate(Override.class);
        method15._throws(Exception.class);
        iocClass = codeModel.ref(IocContext.class);
        field2 = method15.body().decl(iocClass, "context");
        field2.init(JExpr._new(iocClass));
        method15.body().directStatement("return (GenericManager)context.lookup(\"" + buildPackage(modelClassR, ModuleLevel.MANAGER_IMPLEMENTATION) + "." + modelClassR.getSimpleName() + "ManagerImpl\");");        
        method8.annotate(Override.class);
        JMethod method9 = def.method(1, AbstractTableBaseListModel.class, "getTableModel");
        method9.body()._if(JExpr.direct("model==null"))._then().directStatement(" model = new " + modelClass.getSimpleName() + "Model();");
        method9.body()._return(JExpr.direct("model"));
        method9.annotate(Override.class);
        JMethod method10 = def.method(1, String.class, "getWindowClassName");
        method10.body().directStatement("return \"" + buildPackage(modelClass, ModuleLevel.VIEW) + "." + modelClass.getSimpleName() + "IFrame\" ;");
        method10.annotate(Override.class);
        method15 = def.method(JMod.PUBLIC, modelClassV, "validateWapper");
        method15.annotate(Override.class);
        method15.param(modelClass, "object");
        method15.body().directStatement("return new "+modelClassV.getSimpleName()+"(object);");
        method15 = def.method(JMod.PUBLIC, modelClassR, "rejectWapper");
        method15.annotate(Override.class);
        method15.param(modelClass, "object");
        method15.body().directStatement("return new "+modelClassR.getSimpleName()+"(object);");
        ////JDialog getValideEditDialog
        JMethod method11 = def.method(1, JDialog.class, "getEditDialog");
        method11.annotate(Override.class);
        method11._throws(Exception.class);
        method11.param(modelClass, "object");
        method11.param(GenericManager.class, "manager");
        method11.param(TypeOperation.class, "typeOperation");
        method11.param(JFrame.class, "window");
        JClass class11 = codeModel.ref(modelClass);
        method11.body()._if(JExpr.direct("object==null"))._then().directStatement(" object = new " + modelClass.getSimpleName() + "();");
        method11.body().directStatement(modelClass.getSimpleName() + "Dialog  dialog = " + "new " + modelClass.getSimpleName() + "Dialog(getApplicationFrame() ,true, typeOperation) ;");
        method11.body().directStatement(" dialog.setCurrentObject(object);");
        method11.body().directStatement(" dialog.setManager(manager);");
        method11.body()._return(JExpr.direct("dialog"));
        //JDialog getValideEditDialog
        method11 = def.method(1, JDialog.class, "getValideEditDialog");
        method11.annotate(Override.class);
        method11._throws(Exception.class);
        method11.param(modelClassV, "object");
        method11.param(GenericManager.class, "manager");
        method11.param(TypeOperation.class, "typeOperation");
        method11.param(JFrame.class, "window");
        class11 = codeModel.ref(modelClassV);
        method11.body()._if(JExpr.direct("object==null"))._then().directStatement(" object = new " + modelClassV.getSimpleName() + "();");
        method11.body().directStatement(modelClassV.getSimpleName() + "Dialog  dialog = " + "new " + modelClassV.getSimpleName() + "Dialog(getApplicationFrame() ,true, typeOperation) ;");
        method11.body().directStatement(" dialog.setCurrentObject(object);");
        method11.body().directStatement(" dialog.setManager(manager);");
        method11.body()._return(JExpr.direct("dialog"));
         //JDialog JDialog getRejetEditDialog
        method11 = def.method(1, JDialog.class, "getRejetEditDialog");
        method11.annotate(Override.class);
        method11._throws(Exception.class);
        method11.param(modelClassR, "object");
        method11.param(GenericManager.class, "manager");
        method11.param(TypeOperation.class, "typeOperation");
        method11.param(JFrame.class, "window");
        class11 = codeModel.ref(modelClassV);
        method11.body()._if(JExpr.direct("object==null"))._then().directStatement(" object = new " + modelClassR.getSimpleName() + "();");
        method11.body().directStatement(modelClassR.getSimpleName() + "Dialog  dialog = " + "new " + modelClassR.getSimpleName() + "Dialog(getApplicationFrame() ,true, typeOperation) ;");
        method11.body().directStatement(" dialog.setCurrentObject(object);");
        method11.body().directStatement(" dialog.setManager(manager);");
        method11.body()._return(JExpr.direct("dialog"));
        JMethod method12 = def.method(1, JFrame.class, "getApplicationFrame");
        method12.body().directStatement("return " + buildPackage(modelClass, ModuleLevel.PRINCIPAL_VIEW) + ".PrincipalScreen.FRAME ;");
        method12.annotate(Override.class);
        JMethod method13 = def.method(1, PaginationStep.class, "getPagination");
        method13.annotate(Override.class);
        JBlock b13 = method13.body();
        b13.directStatement("pagination =  new PaginationStep(20);");
        b13.directStatement("return pagination ; ");
        // field.init(JExpr.lit(modelClass.getSim+pleName()+"DAO"));   
        //Ajout du commentaire au niveau de classe
        def.javadoc().add("Fenetre interne " + def.name() + System.getProperty("line.separator"));
        def.javadoc().append("@since " + (new Date()).toString());

        //Ajout du commentaire au niveau de la methode
        method.javadoc().add("Methode permettant de retourner le titre de la fenetre");
        method.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method2.javadoc().add("Methode permettant de retourner l'icone de la fenetre");
        method2.javadoc().addReturn().append(ImageIcon.class.getName());

        //Ajout du commentaire au niveau de la methode
        method3.javadoc().add("Methode permettant de retourner la cle primaire");
        method3.javadoc().addParam("object");
        method3.javadoc().addReturn().append(idClass.getName());

        //Ajout du commentaire au niveau de la methode
        method4.javadoc().add("Methode permettant de retourner nom de l'action");
        method4.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method5.javadoc().add("Methode permettant de retourner les parametres pour le reporting");
        method5.javadoc().addReturn().append(Map.class.getName());

        //Ajout du commentaire au niveau de la methode
        method6.javadoc().add("Methode permettant de retourner le nom du fichier Jasper");
        method6.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method7.javadoc().add("Methode permettant de retourner le panel des champs");
        method7.javadoc().addReturn().append(JPanel.class.getName());

        //Ajout du commentaire au niveau de la methode
        method8.javadoc().add("Methode permettant de retourner le manager");
        method8.javadoc().addReturn().append(GenericManager.class.getName());

        //Ajout du commentaire au niveau de la methode
        method9.javadoc().add("Methode permettant de retourner le modele de tableau");
        method9.javadoc().addReturn().append(AbstractTableBaseListModel.class.getName());

        //Ajout du commentaire au niveau de la methode
        method10.javadoc().add("Methode permettant de retourner le nom complet de la classe");
        method10.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method11.javadoc().add("Methode permettant de retourner nom complet de la classe");
        method11.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method12.javadoc().add("Methode permettant de retourner l'instance de la fenetre principale");
        method12.javadoc().addReturn().append(JFrame.class.getName());

        //Ajout du commentaire au niveau de la methode
        method13.javadoc().add("Methode permettant de retourner une instance de la pagination");
        method13.javadoc().addReturn().append(PaginationStep.class.getName());

        codeModel.build((repository));
    }
    
    /**
     *
     * @param repository
     * @param modelClass:Class sourcce
     * @param modelClassV:Class validite
     * @param modelClassR:Class rejet
     * @param idClass
     * @param idName
     * @param reporting
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void buildInternalFrame(File repository, Class<?> modelClass
              ,Class<?> modelClassV,Class<?> idClass, String idName,String prefix, boolean reporting)

            throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClassV, ModuleLevel.VIEW));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(AbstractListTransfertTemplateFrame.class).narrow(modelClass,modelClassV, idClass);
        JDefinedClass def = cl._class(modelClassV.getSimpleName() +(prefix==null ? "":prefix.trim())+"IFrame");
        def._extends(extendsClass);
        JClass class0 = codeModel.ref(MessagesBundle.class);
        def.field(4, class0, "bundle");
        JClass class01 = codeModel.ref(ResourcesBundle.class);
        def.field(4, class01, "resourcesbundle");
        JMethod constructor = def.constructor(1);
        //Constructeur par defaut
        constructor.javadoc().add("Constructeur par defaut");
        //constructor.param(Frame.class, "parent");
        constructor.body().directStatement(" super();");
        JFieldVar field = def.field(25, String.class, "FRAME_NAME");
        field.init(JExpr.direct("\"" + buildPackage(modelClass, ModuleLevel.VIEW) + "." + modelClass.getSimpleName() + "IFrame\""));

        //Cas ou la classe est une vue (reserve seulement a l'editique
        if (reporting) {
            constructor.body().directStatement("btnew.setVisible(false);");
            constructor.body().directStatement("btupdate.setVisible(false);");
            constructor.body().directStatement("btdelete.setVisible(false);");
        }
        /*JFieldVar field3 = def.field(4, PaginationStep.class, "pagination");
         field3.init(JExpr._null());*/
        JMethod method3 = def.method(1, idClass, "getPrimaryKey");
        method3.param(modelClass, "object");
        String idValues = ("" + idName.charAt(0)).toUpperCase() + idName.substring(1);
        JBlock b3 = method3.body();//._return(JExpr.ref("currentObject.get"+idValues+"()"));
        b3.directStatement("return object.get" + idValues + "() ;");
        method3.annotate(Override.class);
        JMethod method4 = def.method(1, String.class, "getActionName");
        method4.body().directStatement(" return \"" + modelClass.getSimpleName().toLowerCase() + "Action\" ; ");
        method4.annotate(Override.class);
        JMethod method5 = def.method(1, Map.class, "getReportParameters");
        JClass class5 = codeModel.ref(Map.class);
        JClass class51 = codeModel.ref(HashMap.class);
        JVar var5 = method5.body().decl(class5, "parametres");
        var5.init(JExpr._new(class51));
        method5.body().directStatement("parametres.put(\"titreRapport\", MessagesBundle.getMessage(\"" + modelClass.getSimpleName().toLowerCase() + ".title.report\")) ;");
        method5.body().directStatement("parametres.put(\"piedRapport\", MessagesBundle.getMessage(\"" + modelClass.getSimpleName().toLowerCase() + ".footer.report\")) ;");
        method5.body().directStatement("parametres.put(\"nombreLignesRapport\", getSelectedObjects().size()) ;");
        method5.body().directStatement("return parametres ; ");
        messages.put(modelClass.getSimpleName().toLowerCase() + ".title.report", "");
        messages.put(modelClass.getSimpleName().toLowerCase() + ".footer.report", "");
        method5.annotate(Override.class);
        JMethod method6 = def.method(1, String.class, "getJasperFileName");
        JConditional cond6 = method6.body()._if(JExpr.direct("getSelectedObjects().size()==0 || getSelectedObjects().isEmpty()"));
        cond6._then()._return(JExpr._null());
        JConditional cond61 = cond6._elseif(JExpr.direct("getSelectedObjects().size()==1"));
        cond61._then()._return(JExpr.direct("ResourcesBundle.getResource(\"" + modelClass.getSimpleName().toLowerCase() + ".detail.report\")"));
        cond61._else()._return(JExpr.direct("ResourcesBundle.getResource(\"" + modelClass.getSimpleName().toLowerCase() + ".list.report\")"));
        resources.put(modelClass.getSimpleName().toLowerCase() + ".detail.report", "");
        resources.put(modelClass.getSimpleName().toLowerCase() + ".list.report", "");
        //method6.body().directStatement("ResourcesBundle.getResource(\""+modelClass.getSimpleName().toLowerCase()+".list\")");
        method6.annotate(Override.class);
        JMethod method = def.method(1, String.class, "getWindowTitle");
        method.annotate(Override.class);
        JBlock bl = method.body();
        bl._return(JExpr.direct("MessagesBundle.getMessage(\"" + modelClass.getSimpleName().toLowerCase() + ".list\")"));
        messages.put(modelClass.getSimpleName().toLowerCase() + ".list", modelClass.getSimpleName().toUpperCase());
        JMethod method2 = def.method(1, ImageIcon.class, "getImage");
        method2.annotate(Override.class);
        JBlock b2 = method2.body();
        b2.directStatement("try{");
        JClass varClass = codeModel.ref(ImageIcon.class);
        JVar var = b2.decl(varClass, "icon");
        var.init(JExpr._null());
        b2.directStatement("icon = new ImageIcon(getClass().getResource(ResourcesBundle.getResource(\"" + modelClass.getSimpleName().toLowerCase() + ".list.image\")));");
        resources.put(modelClass.getSimpleName().toLowerCase() + ".list.image", "");
        //JConditional cond1 = b2._if(JExpr.ref("url==null"));
        b2._return(var);
        b2.directStatement("} catch(Exception ex ){;}");
        b2._return(JExpr._null());
        JMethod method7 = def.method(1, JPanel.class, "getCriteriaPanel");
        JBlock b7 = method7.body();
        JConditional cond2 = b7._if(JExpr.direct("criteriaPanel==null"));
        String entityname = ("" + modelClass.getSimpleName().charAt(0)).toUpperCase() + modelClass.getSimpleName().substring(1);
        cond2._then().block().directStatement("criteriaPanel = new " + entityname + "CriteriaPanel();");
        b7._return(JExpr.direct("criteriaPanel"));
        //System.out.println(":::::::::::::::::::::::::::  "+modelClass.getPackage());     
        method7.annotate(Override.class);
        JClass class7 = codeModel.ref(GenericManager.class).narrow(modelClass, idClass);
        JMethod method8 = def.method(1, class7, "getManager");
        method8._throws(Exception.class);
        JClass iocClass = codeModel.ref(IocContext.class);
        JVar field2 = method8.body().decl(iocClass, "context");
        field2.init(JExpr._new(iocClass));
        method8.body().directStatement("return (GenericManager)context.lookup(\"" + buildPackage(modelClass, ModuleLevel.MANAGER_IMPLEMENTATION) + "." + modelClass.getSimpleName() + "ManagerImpl\");");
        //getValideManager
        class7 = codeModel.ref(GenericManager.class).narrow(modelClassV, idClass);
        JMethod method14 = def.method(1, class7, "getValideManager");
        method14.annotate(Override.class);
        method14._throws(Exception.class);
        iocClass = codeModel.ref(IocContext.class);
        field2 = method14.body().decl(iocClass, "context");
        field2.init(JExpr._new(iocClass));
        method14.body().directStatement("return (GenericManager)context.lookup(\"" + buildPackage(modelClassV, ModuleLevel.MANAGER_IMPLEMENTATION) + "." + modelClassV.getSimpleName() + "ManagerImpl\");");
        //getRejetManager()
        JMethod method15 = null;
        method8.annotate(Override.class);
        JMethod method9 = def.method(1, AbstractTableBaseListModel.class, "getTableModel");
        method9.body()._if(JExpr.direct("model==null"))._then().directStatement(" model = new " + modelClass.getSimpleName() + "TModel();");
        method9.body()._return(JExpr.direct("model"));
        method9.annotate(Override.class);
        method9 = def.method(1, AbstractTableBaseListModel.class, "getValidateTableModel");
        method9.body()._if(JExpr.direct("modelValidation==null"))._then().directStatement(" modelValidation = new " + modelClassV.getSimpleName() + "Model();");
        method9.body()._return(JExpr.direct("modelValidation"));
        method9.annotate(Override.class);
        JMethod method10 = def.method(1, String.class, "getWindowClassName");
        method10.body().directStatement("return \"" + buildPackage(modelClass, ModuleLevel.VIEW) + "." + modelClass.getSimpleName() + "IFrame\" ;");
        method10.annotate(Override.class);
        method15 = def.method(JMod.PUBLIC, modelClassV, "validateWapper");
        method15.annotate(Override.class);
        method15.param(modelClass, "object");
        method15.body().directStatement("return new "+modelClassV.getSimpleName()+"(object);");
        ////JDialog getValideEditDialog
        JMethod method11 = def.method(1, JDialog.class, "getEditDialog");
        method11.annotate(Override.class);
        method11._throws(Exception.class);
        method11.param(modelClass, "object");
        method11.param(GenericManager.class, "manager");
        method11.param(TypeOperation.class, "typeOperation");
        method11.param(JFrame.class, "window");
        JClass class11 = codeModel.ref(modelClass);
        method11.body()._if(JExpr.direct("object==null"))._then().directStatement(" object = new " + modelClass.getSimpleName() + "();");
        method11.body().directStatement(modelClass.getSimpleName() + "Dialog  dialog = " + "new " + modelClass.getSimpleName() + "Dialog(getApplicationFrame() ,true, typeOperation) ;");
        method11.body().directStatement(" dialog.setCurrentObject(object);");
        method11.body().directStatement(" dialog.setManager(manager);");
        method11.body()._return(JExpr.direct("dialog"));
        //JDialog getValideEditDialog
        method11 = def.method(1, JDialog.class, "getValideEditDialog");
        method11.annotate(Override.class);
        method11._throws(Exception.class);
        method11.param(modelClassV, "object");
        method11.param(GenericManager.class, "manager");
        method11.param(TypeOperation.class, "typeOperation");
        method11.param(JFrame.class, "window");
        class11 = codeModel.ref(modelClassV);
        method11.body()._if(JExpr.direct("object==null"))._then().directStatement(" object = new " + modelClassV.getSimpleName() + "();");
        method11.body().directStatement(modelClassV.getSimpleName() + "TDialog  dialog = " + "new " + modelClassV.getSimpleName() + "TDialog(getApplicationFrame() ,true, typeOperation,translate(getSelectedRows())) ;");
        method11.body().directStatement(" dialog.setCurrentObject(object);");
        method11.body().directStatement(" dialog.setManager(manager);");
        method11.body().directStatement("dialog.setModel(getValidateTableModel());");
        method11.body()._return(JExpr.direct("dialog"));
        JMethod method12 = def.method(1, JFrame.class, "getApplicationFrame");
        method12.body().directStatement("return " + buildPackage(modelClass, ModuleLevel.PRINCIPAL_VIEW) + ".PrincipalScreen.FRAME ;");
        method12.annotate(Override.class);
        JMethod method13 = def.method(1, PaginationStep.class, "getPagination");
        method13.annotate(Override.class);
        JBlock b13 = method13.body();
        b13.directStatement("pagination =  new PaginationStep(20);");
        b13.directStatement("return pagination ; ");
        method13 = def.method(JMod.PUBLIC, boolean.class, "isSelected");
        method13.annotate(Override.class);
        method13.param(modelClass, "objet");
        method13.body().directStatement("return objet.isSelected();");
        // field.init(JExpr.lit(modelClass.getSim+pleName()+"DAO"));   
        //Ajout du commentaire au niveau de classe
        def.javadoc().add("Fenetre interne " + def.name() + System.getProperty("line.separator"));
        def.javadoc().append("@since " + (new Date()).toString());

        //Ajout du commentaire au niveau de la methode
        method.javadoc().add("Methode permettant de retourner le titre de la fenetre");
        method.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method2.javadoc().add("Methode permettant de retourner l'icone de la fenetre");
        method2.javadoc().addReturn().append(ImageIcon.class.getName());

        //Ajout du commentaire au niveau de la methode
        method3.javadoc().add("Methode permettant de retourner la cle primaire");
        method3.javadoc().addParam("object");
        method3.javadoc().addReturn().append(idClass.getName());

        //Ajout du commentaire au niveau de la methode
        method4.javadoc().add("Methode permettant de retourner nom de l'action");
        method4.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method5.javadoc().add("Methode permettant de retourner les parametres pour le reporting");
        method5.javadoc().addReturn().append(Map.class.getName());

        //Ajout du commentaire au niveau de la methode
        method6.javadoc().add("Methode permettant de retourner le nom du fichier Jasper");
        method6.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method7.javadoc().add("Methode permettant de retourner le panel des champs");
        method7.javadoc().addReturn().append(JPanel.class.getName());

        //Ajout du commentaire au niveau de la methode
        method8.javadoc().add("Methode permettant de retourner le manager");
        method8.javadoc().addReturn().append(GenericManager.class.getName());

        //Ajout du commentaire au niveau de la methode
        method9.javadoc().add("Methode permettant de retourner le modele de tableau");
        method9.javadoc().addReturn().append(AbstractTableBaseListModel.class.getName());

        //Ajout du commentaire au niveau de la methode
        method10.javadoc().add("Methode permettant de retourner le nom complet de la classe");
        method10.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method11.javadoc().add("Methode permettant de retourner nom complet de la classe");
        method11.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la methode
        method12.javadoc().add("Methode permettant de retourner l'instance de la fenetre principale");
        method12.javadoc().addReturn().append(JFrame.class.getName());

        //Ajout du commentaire au niveau de la methode
        method13.javadoc().add("Methode permettant de retourner une instance de la pagination");
        method13.javadoc().addReturn().append(PaginationStep.class.getName());
        //SourceGenerator.buildTransfertModel(repository, modelClass, idClass, null, null);
        codeModel.build((repository));
    }
    /**
     *
     * @param repository
     * @param modelClass
     * @param idClass
     * @param idName
     * @param groupes
     * @param properties
     * @param relation
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void buildEditEditPanel(File repository, Class<?> modelClass, Class<?> idClass, String idName, Groupes groupes, Exclures properties, Relations relation)
            throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.VIEW));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(JPanel.class);
        JDefinedClass def = cl._class(modelClass.getSimpleName() + "EditPanel");
        def._extends(extendsClass);
        //Constructeur par defaut     
        JClass class0 = codeModel.ref(MessagesBundle.class);
        def.field(4, class0, "bundle");
        JClass class01 = codeModel.ref(MessagesBundle.class);
        def.field(4, class01, "resourcebundle");
        def.constructor(1).body().directStatement("initComponents() ; ");
        JFieldVar field = def.field(25, String.class, "FRAME_NAME");
        field.init(JExpr.direct("\"" + buildPackage(modelClass, ModuleLevel.VIEW) + "." + modelClass.getSimpleName() + "IFrame\""));
        JFieldVar field2 = def.field(4, IocContext.class, "context");
        JClass iocClass = codeModel.ref(IocContext.class);
        field2.init(JExpr._new(iocClass));
        buildPanelFieldFromModel(modelClass, def, codeModel, idName, groupes, properties);
        JMethod method3 = def.method(4, Void.TYPE, "initComponents");
        String idValues = ("" + idName.charAt(0)).toUpperCase() + idName.substring(1);
        JBlock b3 = method3.body();//._return(JExpr.ref("currentObject.get"+idValues+"()"));        
        //method3.annotate(SuppressWarnings.class);       
        buildInitComponentsInitialisation(repository, modelClass, b3, groupes, properties, relation);
        JClass class3 = codeModel.ref(GroupLayout.class);
        JVar var30 = b3.decl(class3, "layout").init(JExpr._new(class3).arg(JExpr.direct("this")));
        b3.directStatement("this.setLayout(layout);");
        b3.directStatement("layout.setAutoCreateGaps(true);");
        b3.directStatement("layout.setAutoCreateContainerGaps(true);");
        buildhorizontalGroup(modelClass, b3, codeModel, groupes, properties,WindowType.EDIT);
        buildFieldManager(modelClass, codeModel, def);

        //Ajout du commentaire au niveau de classe
        def.javadoc().add("Panel d'edition " + def.name() + System.getProperty("line.separator"));
        def.javadoc().append("@since " + (new Date()).toString());

        //Ajout du commentaire au niveau de la methode
        method3.javadoc().add("Methode permettant d'initialiser les composants");

        codeModel.build((repository));
    }

    /**
     *
     * @param repository
     * @param modelClass
     * @param idName
     * @param properties
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void buildCriteriaPanel(File repository, Class<?> modelClass, String idName, Exclures properties)
            throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.VIEW));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(JPanel.class);
        JDefinedClass def = cl._class(modelClass.getSimpleName() + "CriteriaPanel");
        def._extends(extendsClass);
        //Constructeur par defaut     
        JClass class0 = codeModel.ref(MessagesBundle.class);
        def.field(4, class0, "bundle");
        JClass class01 = codeModel.ref(MessagesBundle.class);
        def.field(4, class01, "resourcebundle");
        def.constructor(1).body().directStatement("initComponents() ; ");
        JFieldVar field = def.field(25, String.class, "FRAME_NAME");
        field.init(JExpr.direct("\"" + buildPackage(modelClass, ModuleLevel.VIEW) + "." + modelClass.getSimpleName() + "IFrame\""));
        JFieldVar field2 = def.field(4, IocContext.class, "context");
        JClass iocClass = codeModel.ref(IocContext.class);
        field2.init(JExpr._new(iocClass));
        buildPanelFieldCriteria(modelClass, def, codeModel, idName, properties);
        JMethod method3 = def.method(4, Void.TYPE, "initComponents");
        String idValues = ("" + idName.charAt(0)).toUpperCase() + idName.substring(1);
        JBlock b3 = method3.body();//._return(JExpr.ref("currentObject.get"+idValues+"()"));        
        //method3.annotate(SuppressWarnings.class);       
        buildInitCriteria(repository, modelClass, b3, properties);
        JClass class3 = codeModel.ref(GroupLayout.class);
        JVar var30 = b3.decl(class3, "layout").init(JExpr._new(class3).arg(JExpr.direct("this")));
        b3.directStatement("this.setLayout(layout);");
        b3.directStatement("layout.setAutoCreateGaps(true);");
        b3.directStatement("layout.setAutoCreateContainerGaps(true);");
        buildhorizontalGroupCriteria(modelClass, b3, codeModel, properties);
        buildFieldManager(modelClass, codeModel, def);

        //Ajout du commentaire au niveau de classe
        def.javadoc().add("Panel de recherche " + def.name() + System.getProperty("line.separator"));
        def.javadoc().append("@since " + (new Date()).toString());

        //Ajout du commentaire au niveau de la methode
        method3.javadoc().add("Methode permettant d'initialiser les composants");

        codeModel.build((repository));
    }

    
    /**
     *
     * @param repository
     * @param modelClass
     * @param title
     * @param groupes
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void buildPrincipalScreen(File repository, Class modelClass, String title, List<ActionGroup> groupes, String mainClassName, String packageMainClass, String outputDirectoryMainClass)
            throws JClassAlreadyExistsException, IOException, ClassNotFoundException {
        //System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: "+groupes);
        String PrincipalScreenName = "PrincipalScreen";
        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.PRINCIPAL_VIEW));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(PrincipalFrame.class);
        JClass implmentsClass = codeModel.ref(ClientInterfaceSecurity.class);
        JDefinedClass def = cl._class(PrincipalScreenName);
        def._extends(extendsClass);
        def._implements(implmentsClass);
        //Declaration de variable
        //codeModel._package(MessagesBundle.class);
        def.field(JMod.PRIVATE, codeModel.ref(MessagesBundle.class), "var1");
        def.field(JMod.PRIVATE, codeModel.ref(ResourcesBundle.class), "var2");
        JClass class_05 = codeModel.ref(ResourceBundle.class);
        def.field(JMod.PRIVATE, class_05, "bundle");
        JClass class_09 = codeModel.ref(ResourceBundle.class);
        def.field(JMod.PRIVATE, class_09, "resourcesbundle");
        JClass class0 = codeModel.ref(List.class).narrow(ActionGroup.class);
        JClass classx = codeModel.ref(Map.class);
        JVar jvar0 = def.field(1, class0, "options");
        JVar jvar1 = def.field(1, classx, "componentsList");
        JMethod method0 = def.constructor(1);
        method0.body().directStatement(" super() ; ");
        method0.body().directStatement("bundle = ResourceBundle.getBundle(\"messages\");");
        method0.body().directStatement("MessagesBundle.setBundle(bundle);");
        method0.body().directStatement("resourcesbundle = ResourceBundle.getBundle(\"resources\");");
        method0.body().directStatement("ResourcesBundle.setBundle(resourcesbundle);");
        method0.body().directStatement(" this.setTitle(MessagesBundle.getMessage(\"" + title + "\")) ; ");
        
        messages.put(title, title);
        method0.body().assign(jvar0, JExpr._new(codeModel.ref(ArrayList.class).narrow(ActionGroup.class)));
        method0.body().assign(jvar1, JExpr._new(codeModel.ref(HashMap.class)));
        method0.body().directStatement("initComponentsList();");
        JClass class01 = codeModel.ref(ActionGroup.class);
        JVar jvar01 = method0.body().decl(class01, "group");
        JClass class02 = codeModel.ref(ActionDetail.class);
        JVar jvar02 = method0.body().decl(class02, "element");
        JVar jvar04 = method0.body().decl(class01, "elementgroup");
        JClass class03 = codeModel.ref(OptionPanel.class);
        JVar jvar03 = def.field(1, class03, "optionsPanel");
        for (ActionGroup group : groupes) {
            method0.body().assign(jvar01, JExpr._new(codeModel.ref(ActionGroup.class)));
            method0.body().directStatement("group.setName(\"" + group.getName() + "\");");
            method0.body().directStatement("group.setLabel(\"" + fillString(group.getLabel()) + "\");");
            method0.body().directStatement("group.setIndependent(" + group.isIndependent() + ");");
            method0.body().directStatement("group.setVmenu(" + group.isVmenu() + ");");
            for (ActionDetail detail : group.getActions()) {
                // method0.body().assign(jvar02, JExpr._new(codeModel.ref(ActionDetail.class)));
                method0.body().assign(jvar02, JExpr._new(codeModel.ref(ActionDetail.class)));
                method0.body().directStatement("element.setActionName(\"" + detail.getActionName() + "\");");
                method0.body().directStatement("element.setViewClass(\"" + detail.getViewClass() + "\");");
                method0.body().directStatement("element.setActionLabel(\"" + detail.getActionLabel() + "\");");
                method0.body().directStatement("element.setActionIndex(" + detail.getActionIndex() + ");");
                method0.body().directStatement("element.setSeparator("+detail.isSeparator()+");");
                method0.body().directStatement("group.getActions().add(element);");
            }

            for (ActionGroup groupe : group.getGroupes()) {
                method0.body().assign(jvar04, JExpr._new(codeModel.ref(ActionGroup.class)));
                method0.body().directStatement("elementgroup.setName(\"" + groupe.getName() + "\");");
                method0.body().directStatement("elementgroup.setLabel(\"" + fillString(groupe.getLabel()) + "\");");
                method0.body().directStatement("elementgroup.setIndependent(" + groupe.isIndependent() + ");");
                for (ActionDetail detail : groupe.getActions()) {
                    // method0.body().assign(jvar02, JExpr._new(codeModel.ref(ActionDetail.class)));
                    method0.body().assign(jvar02, JExpr._new(codeModel.ref(ActionDetail.class)));
                    method0.body().directStatement("element.setActionName(\"" + detail.getActionName() + "\");");
                    method0.body().directStatement("element.setViewClass(\"" + detail.getViewClass() + "\");");
                    method0.body().directStatement("element.setActionLabel(\"" + detail.getActionLabel() + "\");");
                    method0.body().directStatement("element.setActionIndex(" + detail.getActionIndex() + ");");
                     method0.body().directStatement("element.setSeparator("+detail.isSeparator()+");");
                    method0.body().directStatement("elementgroup.getActions().add(element);");
                }
                method0.body().directStatement("group.getGroupes().add(elementgroup);");
            }
            method0.body().directStatement("options.add(group);");
        }
        method0.body().directStatement("super.buildViewComponents();");
        method0.javadoc().add("Constructeur par defaut");
        //Method
        JMethod method_1 = def.method(1, JPanel.class, "buildOptionPanel");
        //method_1.param(Map.class, "options");
        method_1.annotate(Override.class);
        method_1.body().directStatement("optionsPanel = new OptionPanel(options);");
        method_1.body().directStatement("return optionsPanel ; ");
        //Method
        JMethod method_2 = def.method(1, void.class, "buildMenuBar");
        //method_2.param(Map.class, "items");
        method_2.annotate(Override.class);
        //Method
        JMethod method_y = def.method(1, void.class, "launchClientFrame");
        method_y.param(UserPrincipal.class, "currentUserConnected");
        method_y.body().directStatement("Start();");
         //Method
        JMethod method_yx = def.method(1, void.class, "Start");
        method_yx.body().directStatement("try {");
        method_yx.body().directStatement("for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {");
        method_yx.body().directStatement("if (\"Nimbus\".equals(info.getName())) {");
        method_yx.body().directStatement("javax.swing.UIManager.setLookAndFeel(info.getClassName());");
        method_yx.body().directStatement("break;");
        method_yx.body().directStatement("}}");
        method_yx.body().directStatement("}catch (Exception ex) {");
        method_yx.body().directStatement("java.util.logging.Logger.getLogger(PrincipalScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);");
        method_yx.body().directStatement("}");
        method_yx.body().directStatement("java.awt.EventQueue.invokeLater(new Runnable() {");
        method_yx.body().directStatement("public void run() {");
        method_yx.body().directStatement("PrincipalScreen princial = new PrincipalScreen();");
        method_yx.body().directStatement("java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();");
        method_yx.body().directStatement("//princial.setLocation(dim.width/2 - princial.getWidth()/2, dim.height/2 - princial.getHeight()/2);");
        method_yx.body().directStatement("princial.setSize(dim.width - 20, dim.height - 30);");
        method_yx.body().directStatement("princial.setVisible(true);");
        method_yx.body().directStatement("}");
        method_yx.body().directStatement("});");
        
        //Methode d'intialisation des composants pour la gestion des droits
        JMethod method_x = def.method(1, void.class, "initComponentsList");
        method_x.body().directStatement("Map listeMenus = new HashMap();");
        method_x.body().directStatement("Map listeMenuItems = new HashMap();");
        method_x.body().directStatement("Map listeButons = new HashMap();");
        method_x.body().directStatement("componentsList.put(\"listeMenus\", listeMenus);");
        method_x.body().directStatement("componentsList.put(\"listeMenuItems\", listeMenuItems);");
        method_x.body().directStatement("componentsList.put(\"listeButons\", listeButons);");
        //Generation du contenu de la methode de construction de la bar de menu
        int index = 1;
        JClass class20 = codeModel.ref(KeyStroke.class);
        JVar var20 = def.field(JMod.PRIVATE, class20, "keyStoke");
        JClass class201 = codeModel.ref(KeyEvent.class);
        JVar var201 = def.field(JMod.PRIVATE, class201, "keyEvent");
        for (ActionGroup group : groupes) {
            JClass class1 = codeModel.ref(JMenu.class);
            String menuName = group.getName();
            JVar var1 = def.field(JMod.PRIVATE, class1, menuName);
            method_2.body().assign(var1, JExpr._new(class1));
            method_2.body().directStatement(menuName + ".setText( MessagesBundle.getMessage(\"" + group.getLabel() + "\"));");
            messages.put(group.getLabel(), group.getLabel());
            method_2.body().directStatement("menubar.add(" + menuName + " );");
            //Pour la gestion des droits
            method_2.body().directStatement("((Map)componentsList.get(\"listeMenus\")).put(\""+menuName+"\", "+menuName+");");
            //Gestion des sous menu
            for (ActionGroup groupe : group.getGroupes()) {
                String submenuName = groupe.getName();
                var1 = def.field(JMod.PRIVATE, class1, submenuName);
                method_2.body().assign(var1, JExpr._new(class1));
                method_2.body().directStatement(submenuName + ".setText( MessagesBundle.getMessage(\"" + groupe.getLabel() + "\"));");
                method_2.body().directStatement(menuName + ".add(" + submenuName + " );");
                //Pour la gestion des droits
                method_2.body().directStatement("((Map)componentsList.get(\"listeMenus\")).put(\""+submenuName+"\", "+submenuName+");");
                messages.put(groupe.getLabel(), groupe.getLabel());
                //Gestion des menus details Items
                for (ActionDetail action : groupe.getActions()) {
                  if(!action.isSeparator()){  
                    JClass class2 = codeModel.ref(JMenuItem.class);
                    JVar var2 = def.field(JMod.PRIVATE, class2, action.getActionName() + "item"); 
                    method_2.body().assign(var2, JExpr._new(class2));
                    
                    //Pour la gestion des droits
                    method_2.body().directStatement("((Map)componentsList.get(\"listeMenuItems\")).put(\""+action.getActionName()+"\", "+action.getActionName() + "item);");
                    
                    method_2.body().directStatement(action.getActionName() + "item.setText( MessagesBundle.getMessage(\"" + action.getActionLabel() + "\"));");
                    messages.put(action.getActionLabel(), action.getActionLabel());
                    method_2.body().directStatement(submenuName + ".add( " + action.getActionName() + "item);");
                    method_2.body().directStatement(action.getActionName() + "item.setMnemonic('" + action.getMnemonic() + "');");
                    method_2.body().directStatement(action.getActionName() + "item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_" +action.getMnemonic() + " , KeyEvent.CTRL_MASK));");
                    method_2.body().directStatement(action.getActionName() + "item.addActionListener(new java.awt.event.ActionListener() {");
                    method_2.body().directStatement("  public void actionPerformed(java.awt.event.ActionEvent evt) {" + action.getActionName() + "Action(); } });");
                  }else {
                     method_2.body().directStatement(submenuName + ".addSeparator();");
                  }
                }//end for(ActionDetail action:groupe.getActions()){
            }
            //Gestion des menus details Items
            for (ActionDetail action : group.getActions()) {
                System.out.println("SourceGenerator.buildPrincipalScreen()::::::::   "+action.getActionLabel()+"::::::::: "+action.getActionName()+" ::::: "+action.isSeparator()+"  ::: "+action.isLiteral());
              if(!action.isSeparator()){ 
                JClass class2 = codeModel.ref(JMenuItem.class);
                JVar var2 = def.field(JMod.PRIVATE, class2, action.getActionName() + "item");
                method_2.body().assign(var2, JExpr._new(class2));
                messages.put(action.getActionLabel(), action.getActionLabel());
                method_2.body().directStatement(action.getActionName() + "item.setText( MessagesBundle.getMessage(\"" + action.getActionLabel() + "\"));");
                method_2.body().directStatement(menuName + ".add( " + action.getActionName() + "item);");
                method_2.body().directStatement(action.getActionName() + "item.setMnemonic('" + action.getMnemonic() + "');");
                method_2.body().directStatement(action.getActionName() + "item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_" + action.getMnemonic() + " , KeyEvent.CTRL_MASK));");
                method_2.body().directStatement(action.getActionName() + "item.addActionListener(new java.awt.event.ActionListener() {");
                method_2.body().directStatement("  public void actionPerformed(java.awt.event.ActionEvent evt) {" + action.getActionName() + "Action(); } });");
              }else {
                  method_2.body().directStatement(menuName+".addSeparator();");
              }
            }
            index++;
        }
        codeModel.build((repository));
        JMethod method_3 = def.method(1, void.class, "accept");
        JVar indexVar = method_3.param(int.class, "index");
        method_3.annotate(Override.class);
        JSwitch switchVar = method_3.body()._switch(indexVar);
        for (ActionGroup group : groupes) {
            for (ActionDetail action : group.getActions()) {
                if(action.isSeparator())
                     continue ;
                
                JCase jcase = switchVar._case(JExpr.lit(action.getActionIndex()));
                jcase.body().directStatement(action.getActionName() + "Action() ;");
                jcase.body().directStatement("break ;");
            }
            for (ActionGroup groupe : group.getGroupes()) {
                for (ActionDetail action : groupe.getActions()) {
                    if(action.isSeparator())
                           continue ;
                    JCase jcase = switchVar._case(JExpr.lit(action.getActionIndex()));
                    jcase.body().directStatement(action.getActionName() + "Action() ;");
                    jcase.body().directStatement("break ;");
                }
            }
        }

        switchVar._default().body()._return();
        JMethod method = null;
        for (ActionGroup group : groupes) {
            for (ActionGroup groupe : group.getGroupes()) {
                for (ActionDetail action : groupe.getActions()) {
                    if(action.isSeparator())
                        continue ;
                      method = def.method(2, void.class, action.getActionName() + "Action");
                      if(action.isDialog()){ //showDialog(String frameName,Class<?> clazz)
                        method.body().directStatement("showDialog(\""+action.getViewClass()+"\" , "+action.getClassType()+");");
                      }else if(!action.isLiteral()){
                          method.body().directStatement("showInsideDesktopPane(\"" + buildPackage(action.getClassType(), ModuleLevel.VIEW) + "." + action.getClassType().getSimpleName()+(action.getPrefix().isEmpty() ? "":action.getPrefix().trim())+"IFrame\");");
                      }else{
                          method.body().directStatement("showInsideDesktopPane(\""+action.getViewClass()+"\");");
                      }

                }
            }
            for (ActionDetail action : group.getActions()) {
                if(action.isSeparator())
                        continue ;
                method = def.method(2, void.class, action.getActionName() + "Action");
                if(action.isDialog()){ //showDialog(String frameName,Class<?> clazz)
                   method.body().directStatement("showDialog(\""+action.getViewClass()+"\" , "+action.getClassType()+");");
                }else if(!action.isLiteral()){
                    method.body().directStatement("showInsideDesktopPane(\"" + buildPackage(action.getClassType(), ModuleLevel.VIEW) + "." + action.getClassType().getSimpleName()+(action.getPrefix().isEmpty() ? "":action.getPrefix().trim())+"IFrame\");");
                }else {
                    method.body().directStatement("showInsideDesktopPane(\""+action.getViewClass()+"\");");
                }

            }
        }

        //Ajout du commentaire au niveau de la classe
        def.javadoc().add("Classe representant la fenetre principale de la l'application");

        //Ajout du commentaire au niveau de la classe
        method_1.javadoc().add("Methode permettant de construire les options du panel");
        method_1.javadoc().addReturn().append(JPanel.class.getName());

        //Ajout du commentaire au niveau de la classe
        method_2.javadoc().add("Methode permettant de construire la barre des menus");
        method_2.javadoc().addReturn().append(void.class.getName());

        //Ajout du commentaire au niveau de la classe
        method_3.javadoc().add("Methode permettant de lier chaque index a une action");
        method_3.javadoc().addReturn().append(void.class.getName());

        //Creer methode main        
        if(packageMainClass!=null&&!packageMainClass.trim().isEmpty()&&mainClassName!=null&&!mainClassName.trim().isEmpty()){
            buildMain(packageMainClass, mainClassName, PrincipalScreenName, def._package().name(), repository.getAbsolutePath());
        }

        codeModel.build((repository));
    }

    /**
     * Methode permettant de creer la methode main
     *
     * @param packageName
     * @param className
     * @param principamScreen
     * @param principalPacckageName
     * @param outdirectory
     * @throws JClassAlreadyExistsException
     * @throws IOException
     */
    public static void buildMain(String packageName, String className, String principamScreen, String principalPacckageName, String outdirectory) throws JClassAlreadyExistsException, IOException {
        //Declaring an instance of a JCodeModel Class to be used for class generation
        JCodeModel jCodeModel = new JCodeModel();

        //testin that the name of the package in which to generate the class in is not null
        if (!packageName.trim().isEmpty()) {

            JPackage jPackage = jCodeModel._package(packageName);

            if (!className.trim().isEmpty()) {
                JDefinedClass definedClass = jPackage._class(className);

          //      System.out.println(definedClass.getPackage());
                //adding java doc comments for the class
                definedClass.javadoc().add("Generated Class: " + className);
                //definedClass.javadoc().addAuthor().add("ESA");

                //defining a class default Constructor
                JMethod constructor = definedClass.constructor(1);

                //Creating the main method
                JMethod main = definedClass.method(17, jCodeModel.VOID, "main");
                main.param(String[].class, "args");

                main._throws(Exception.class);

                main.body().directStatement("for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()){ ");
                main.body()._if(JExpr.direct("\"Nimbus\".equals(info.getName())"))._then().directStatement("javax.swing.UIManager.setLookAndFeel(info.getClassName()); \n\t\t break;");
                main.body().directStatement("}");

                main.body().directStatement("java.awt.EventQueue.invokeLater(new Runnable() {");
                main.body().directStatement("public void run() {");

                //testing the class pass is not null
                if (principamScreen != null) {

                    main.body().decl(JMod.NONE, jCodeModel.ref(principalPacckageName + "." + principamScreen), "principal", JExpr._new(jCodeModel.ref(principalPacckageName + "." + principamScreen)));

                    //   main.body().directStatement("Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); \n\t principal.setSize(dim.width-20, dim.height-30);");
                    main.body().decl(jCodeModel._ref(Dimension.class), "dim", JExpr.direct("java.awt.Toolkit.getDefaultToolkit().getScreenSize()"));

                    main.body().directStatement("principal.setSize(dim.width-20, dim.height-30);");

                    main.body().directStatement("principal.setVisible(true);");

//        
                    main.body().directStatement("}\n\t});");

                    //creating Class in the outputdirectory
                    if (!outdirectory.isEmpty()) {
                        //building the class with its content
                        jCodeModel.build(new File(outdirectory));
                        //end of outputdirectory
                    }

                }

            }

        }
    }

    /**
     *
     * @param repository
     * @param field
     * @param modelClass
     * @param relation
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void buildTablePanel(File repository, Field field, Class<?> modelClass, Relations relation)
            throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.VIEW));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(AbstractTablePanel.class)
                .narrow(modelClass);
        JDefinedClass def = cl._class(modelClass.getSimpleName() + "TablePanel");
        def._extends(extendsClass);
        JClass class0 = codeModel.ref(IocContext.class);
        JFieldVar field0 = def.field(4, class0, "context");

        //Constructeur par defaut  
        //def.constructor(1).direct("/** Constructeur par defaut **/");
        JMethod method0 = def.constructor(1);
        method0.body().directStatement("super() ; ");
        method0.body().directStatement(" context = new IocContext();");
        //Activition des buttons
        oneToMany(method0, field, relation);
        manyToMany(method0, field, relation);
        //}
        JMethod method11 = def.method(1, JDialog.class, "getEditDialog");
        method11.annotate(Override.class);
        method11.param(modelClass, "object");
        method11.param(GenericManager.class, "manager");
        method11.param(AbstractTableBaseListModel.class, "model");
        method11.param(TypeOperation.class, "typeOperation");
        method11.param(JFrame.class, "window");
        JClass class11 = codeModel.ref(modelClass);
        method11._throws(Exception.class);
        method11.body()._if(JExpr.direct("object==null"))._then().directStatement(" object = new " + modelClass.getSimpleName() + "();");
        method11.body().directStatement(buildPackage(modelClass, ModuleLevel.VIEW) + "." + modelClass.getSimpleName() + "Dialog  dialog = " + "new " + buildPackage(modelClass, ModuleLevel.VIEW) + "." + modelClass.getSimpleName() + "Dialog(getApplicationFrame() , true , typeOperation) ;");
        method11.body().directStatement(" dialog.setCurrentObject(object);");
        method11.body().directStatement(" dialog.setManager(manager);");
        method11.body().directStatement(" dialog.setModel(model);");
        method11.body()._return(JExpr.direct("dialog"));
        JClass class8 = codeModel.ref(GenericManager.class);
        JMethod method8 = def.method(1, class8, "getManager");
        method8._throws(Exception.class);
        method8.body().directStatement("return (GenericManager)context.lookup(\"" + buildPackage(modelClass, ModuleLevel.MANAGER_IMPLEMENTATION) + "." + modelClass.getSimpleName() + "ManagerImpl\");");
        method8.annotate(Override.class);
        method8.javadoc().add("/**  **/");
        JMethod method10 = def.method(1, JFrame.class, "getApplicationFrame");
        method10.body().directStatement("return " + buildPackage(modelClass, ModuleLevel.PRINCIPAL_VIEW) + ".PrincipalScreen.FRAME ;");
        if (isSummarise(field, relation)) {

            for (com.megatim.common.jaxb.entities.OneToMany rel : relation.getOneToMany()) {
                if (field.getName().equalsIgnoreCase(rel.getName())) {
                    buildSummaryModel(repository, modelClass, field.getType(), rel.getSummary());
                    method0.body().directStatement("this.enabledSubTotalTable(Boolean.TRUE);");
                    method0.body().directStatement("this.setSummaryModel(new " + buildPackage(modelClass, ModuleLevel.VIEW) + "." + modelClass.getSimpleName() + "SummaryModel()) ;");
                }
            }
        }
        codeModel.build((repository));

    }

    private static void manyToMany(JMethod method, Field field, Relations relations) {

    }

    /**
     *
     * @param method
     * @param relations
     */
    private static void oneToMany(JMethod method0, Field field, Relations relations) {

        if (relations == null) {
            return;
        }
        for (com.megatim.common.jaxb.entities.OneToMany rel : relations.getOneToMany()) {

            if (rel.getName() != null && rel.getName().trim().equalsIgnoreCase(field.getName())) {

                String cascade = rel.getCascade();
                if (cascade.equalsIgnoreCase("all")) {
                    //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: "+cascade+"   :::::::::::::: field Name = "+field.getName());
                    method0.body().directStatement("enableAddButton(true);");
                    method0.body().directStatement("enableEditButton(true);");
                    method0.body().directStatement("enableDeleteButton(true);");
                    //System.out.println("END !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: "+cascade+"   :::::::::::::: field Name = "+field.getName());
                } else if (cascade.equalsIgnoreCase("persist")) {
                    method0.body().directStatement("enableAddButton(true);");
                    method0.body().directStatement("enableEditButton(true);");
                } else if (cascade.equalsIgnoreCase("merge")) {
                    method0.body().directStatement("enableEditButton(true);");
                } else if (cascade.equalsIgnoreCase("remove")) {
                    method0.body().directStatement("enableDeleteButton(true);");
                }
                //Gestion des model de totalisateur
                if (rel.getSummary() != null) {

                }
                return;
            } //end if(rel.getName()!=null&&rel.getName().trim().equalsIgnoreCase(field.getName())){ 
        }
    }

    /**
     *
     * @param repository
     * @param modelClass
     * @param idClass
     * @param properties
     * @param relation
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void buildModel(File repository, Class<?> modelClass, Class<?> idClass, Exclures properties, Relations relation)
            throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.VIEW));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(AbstractTableBaseListModel.class)
                .narrow(modelClass);
        JDefinedClass def = cl._class(modelClass.getSimpleName() + "Model");
        def._extends(extendsClass);
        //Constructeur par defaut  
        //def.constructor(1).direct("/** Constructeur par defaut **/");
        JClass class0 = codeModel.ref(MessagesBundle.class);
        def.field(2, class0, "bundle");
        JMethod method0 = def.constructor(1);
        method0.body().directStatement("super() ; ");
        JMethod method11 = def.method(1, String.class, "getColumnName");
        method11.annotate(Override.class);
        method11.param(int.class, "columnIndex");
        JSwitch switchBlock = method11.body()._switch(JExpr.direct("columnIndex"));
        //Construction du 
        buildgetColumnNameBody(modelClass, switchBlock, properties,WindowType.MODEL);
         //JClass class11 = codeModel.ref(modelClass); 
        JClass class8 = codeModel.ref(Object.class);
        JMethod method8 = def.method(1, class8, "getColoumnValue");
        method8.param(modelClass, "data");
        method8.param(int.class, "columnIndex");
        method8.annotate(Override.class);
        method8.javadoc().add("/**  **/");
        buildgetColoumnValueBody(modelClass, method8.body()._switch(JExpr.direct("columnIndex")), properties,WindowType.MODEL);
        JClass class10 = codeModel.ref(Class.class);
        JMethod method10 = def.method(1, class10, "getColumnClass");
        method10.param(int.class, "columnIndex");
        method10.annotate(Override.class);
        //Generation du code de getColumnClass
        switchBlock = method10.body()._switch(JExpr.direct("columnIndex"));
        buildgetColumnClassBody(modelClass, switchBlock, properties,WindowType.MODEL);
        //method10.body()._return(JExpr.direct("String.class"));
        JMethod method12 = def.method(1, int.class, "getColumnCount");
        Field[] fields = ValidateAndFillBeans.getObjectDeclaredFields(modelClass);
        method12.body()._return(JExpr.lit(countFieldSize(modelClass, properties,WindowType.MODEL)));
        method12.annotate(Override.class);

        //Ajout du commentaire au niveau de classe
        def.javadoc().add("Modele de tableau " + def.name() + System.getProperty("line.separator"));
        def.javadoc().append("@since " + (new Date()).toString());

        //Ajout du commentaire au niveau de la classe
        method11.javadoc().add("Methode permettant de retourner le nom de la colonne");
        method11.javadoc().addParam("columnIndex");
        method11.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la classe
        method8.javadoc().add("Methode permettant de retourner la valeur de la colonne");
        method8.javadoc().addParam("data");
        method8.javadoc().addParam("columnIndex");
        method8.javadoc().addReturn().append(void.class.getName());

        //Ajout du commentaire au niveau de la classe
        method10.javadoc().add("Methode permettant de retourner la classe de la colonne");
        method10.javadoc().addParam("columnIndex");
        method10.javadoc().addReturn().append(class10.fullName());

        //Ajout du commentaire au niveau de la classe
        method12.javadoc().add("Methode permettant de retourner le nombre de colonnes");
        method12.javadoc().addReturn().append(int.class.getName());

        codeModel.build((repository));

        
    }
 /**
     *
     * @param repository
     * @param modelClass
     * @param idClass
     * @param properties
     * @param relation
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void buildTransfertModel(File repository, Class<?> modelClass, Class<?> idClass, Exclures properties, Relations relation)
            throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.VIEW));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(AbstractTableBaseListModel.class)
                .narrow(modelClass);
        JDefinedClass def = cl._class(modelClass.getSimpleName() + "TModel");
        def._extends(extendsClass);
        //Constructeur par defaut  
        //def.constructor(1).direct("/** Constructeur par defaut **/");
        JClass class0 = codeModel.ref(MessagesBundle.class);
        def.field(2, class0, "bundle");
        JMethod method0 = def.constructor(1);
        method0.body().directStatement("super() ; ");
        JMethod method11 = def.method(1, String.class, "getColumnName");
        method11.annotate(Override.class);
        method11.param(int.class, "columnIndex");
        JSwitch switchBlock = method11.body()._switch(JExpr.direct("columnIndex"));
        //Construction du 
        buildgetColumnNameBody(modelClass, switchBlock, properties,WindowType.TRANSFERT);
         //JClass class11 = codeModel.ref(modelClass); 
        JClass class8 = codeModel.ref(Object.class);
        JMethod method8 = def.method(1, class8, "getColoumnValue");
        method8.param(modelClass, "data");
        method8.param(int.class, "columnIndex");
        method8.annotate(Override.class);
        method8.javadoc().add("/**  **/");
        buildgetColoumnValueBody(modelClass, method8.body()._switch(JExpr.direct("columnIndex"))
                , properties,WindowType.TRANSFERT);
        JClass class10 = codeModel.ref(Class.class);
        JMethod method10 = def.method(1, class10, "getColumnClass");
        method10.param(int.class, "columnIndex");
        method10.annotate(Override.class);
        //Generation du code de getColumnClass
        switchBlock = method10.body()._switch(JExpr.direct("columnIndex"));
        buildgetColumnClassBody(modelClass, switchBlock, properties,WindowType.TRANSFERT);
        //method10.body()._return(JExpr.direct("String.class"));
        JMethod method12 = def.method(1, int.class, "getColumnCount");
        Field[] fields = ValidateAndFillBeans.getObjectDeclaredFields(modelClass);
        int nbreElements = countFieldSize(modelClass, properties,WindowType.TRANSFERT);
        method12.body()._return(JExpr.lit(nbreElements));
        method12.annotate(Override.class);

        JMethod method13 = def.method(JMod.PUBLIC,void.class , "setValueAt");
        method13.annotate(Override.class);
        method13.param(Object.class, "aValue");
        method13.param(int.class, "rowIndex");
        method13.param(int.class, "columnIndex");
        method13.body()._if(JExpr.direct("columnIndex=="+(nbreElements-1)))
                ._then().directStatement("elements.get(rowIndex).setSelected((Boolean) aValue);");
        
        method13 = def.method(JMod.PUBLIC, boolean.class, "isCellEditable");
        method13.annotate(Override.class);
        method13.param(int.class, "rowIndex");
        method13.param(int.class, "columnIndex");
        method13.body()._if(JExpr.direct("columnIndex=="+(nbreElements-1)))
                ._then().directStatement("return true;");
        method13.body().directStatement(" return false ;");
        //Ajout du commentaire au niveau de classe
        def.javadoc().add("Modele de tableau " + def.name() + System.getProperty("line.separator"));
        def.javadoc().append("@since " + (new Date()).toString());

        //Ajout du commentaire au niveau de la classe
        method11.javadoc().add("Methode permettant de retourner le nom de la colonne");
        method11.javadoc().addParam("columnIndex");
        method11.javadoc().addReturn().append(String.class.getName());

        //Ajout du commentaire au niveau de la classe
        method8.javadoc().add("Methode permettant de retourner la valeur de la colonne");
        method8.javadoc().addParam("data");
        method8.javadoc().addParam("columnIndex");
        method8.javadoc().addReturn().append(void.class.getName());

        //Ajout du commentaire au niveau de la classe
        method10.javadoc().add("Methode permettant de retourner la classe de la colonne");
        method10.javadoc().addParam("columnIndex");
        method10.javadoc().addReturn().append(class10.fullName());

        //Ajout du commentaire au niveau de la classe
        method12.javadoc().add("Methode permettant de retourner le nombre de colonnes");
        method12.javadoc().addReturn().append(int.class.getName());

        codeModel.build((repository));


    }
    /**
     *
     * @param repository
     * @param modelClass
     * @param idClass
     * @param relation
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void buildSummaryModel(File repository, Class<?> modelClass, Class<?> idClass, Summary relation)
            throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        JCodeModel codeModel = new JCodeModel();
        JPackage cl = codeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.VIEW));
        //Defintion des parametre
        JClass extendsClass = codeModel.ref(AbstractTableSummaryModel.class)
                .narrow(modelClass);
        JDefinedClass def = cl._class(modelClass.getSimpleName() + "SummaryModel");
        def._extends(extendsClass);
        //Constructeur par defaut  
        JSwitch switchBlock = null; //method11.body()._switch(JExpr.direct("columnIndex"));
        //Construction du 
        //buildgetColumnNameBody(modelClass,switchBlock,properties);
        //JClass class11 = codeModel.ref(modelClass); 

        JClass class8 = codeModel.ref(Object.class);
        JMethod method8 = def.method(1, class8, "getColoumnValue");
        method8.param(modelClass, "data");
        method8.param(int.class, "columnIndex");
        method8.annotate(Override.class);
        method8.javadoc().add("/**  **/");
        JSwitch expr = method8.body()._switch(JExpr.direct("columnIndex"));
        int index = 0;
        for (com.megatim.common.jaxb.entities.Column col : relation.getColumn()) {
            expr._case(JExpr.lit(index)).body().directStatement("return \"" + col.getValue() + "\" ; ");
            index++;
        }
        expr._default().body().directStatement("return \" \" ;");
        JClass class10 = codeModel.ref(Class.class);
        JMethod method10 = def.method(1, class10, "getColumnClass");
        method10.param(int.class, "columnIndex");
        method10.annotate(Override.class);
        //Generation du code de getColumnClass
        method10.body()._return(JExpr.direct("String.class"));
        //method10.body()._return(JExpr.direct("String.class"));
        JMethod method12 = def.method(1, int.class, "getColumnCount");
        //Field[] fields = ValidateAndFillBeans.getObjectDeclaredFields(modelClass);
        method12.body()._return(JExpr.lit(relation.getColumn().size()));
        method12.annotate(Override.class);
        codeModel.build((repository));

    }

    /**
     * Renvoie truse si la colonne est une collection et est a totalise false
     * sinon
     *
     * @param field
     * @param summaries
     * @return
     */
    private static boolean isSummarise(Field field, Relations relation) {

        if (!isCollectionType(field.getType()) || relation == null) {
            return false;
        }

        if (relation.getOneToMany() == null || relation.getManyToMany() == null) {
            return false;
        }
        for (com.megatim.common.jaxb.entities.OneToMany rel : relation.getOneToMany()) {
            if (rel.getName() != null && rel.getName().equalsIgnoreCase(field.getName())) {
                return (rel.getSummary() != null);
            }
        }
        for (com.megatim.common.jaxb.entities.ManyToMany rel : relation.getManyToMany()) {
            if (rel.getName() != null && rel.getName().equalsIgnoreCase(field.getName())) {
                return (rel.getSummary() != null);
            }
        }
        return false;
    }

    /**
     *
     * @param modelClass
     * @param sw
     */
    private static void buildgetColoumnValueBody(Class<?> modelClass, JSwitch sw, Exclures properties,WindowType type) {

        Field[] fields = ValidateAndFillBeans.getObjectDeclaredFields(modelClass);//modelClass.getDeclaredFields();
        int index = 0;
        for (Field field : fields) {
            if (isFieldExclude(properties, field.getName(), type)) {
                continue;
            }
            //if(properties.)
            String value = ("" + field.getName().charAt(0)).toUpperCase() + field.getName().substring(1) + "()";
            if (!isCollectionType(field.getType()) && !field.getName().equalsIgnoreCase("serialVersionUID")) {
                if (!field.getType().equals(Boolean.class) && !field.getType().equals(boolean.class)) {
                    sw._case(JExpr.lit(index)).body()._return(JExpr.direct("data.get" + value));
                } else {
                    sw._case(JExpr.lit(index)).body()._return(JExpr.direct("data.is" + value));
                }
                index++;
            }

        }
        sw._default().body()._return(JExpr.direct("\"\""));

    }

    /**
     *
     * @param modelClass
     * @param sw
     */
    private static void buildgetColumnNameBody(Class<?> modelClass, JSwitch sw, Exclures properties,WindowType type) {

        Field[] fields = ValidateAndFillBeans.getObjectDeclaredFields(modelClass);//modelClass.getDeclaredFields();
        int index = 0;
        for (Field field : fields) {
            if (isFieldExclude(properties, field.getName(), type)) {
                continue;
            }
            if (!isCollectionType(field.getType()) && !field.getName().equalsIgnoreCase("serialVersionUID")) {
                sw._case(JExpr.lit(index)).body()._return(JExpr.direct("MessagesBundle.getMessage(\"" + modelClass.getSimpleName().toLowerCase() + "." + field.getName().toLowerCase() + "\")"));
                //messages.put(modelClass.getSimpleName().toLowerCase()+"."+field.getName().toLowerCase(), field.getName());
                index++;
            }

        }
        sw._default().body()._return(JExpr.direct("\"\""));

    }

    /**
     *
     * @param modelClass
     * @param sw
     */
    private static void buildgetColumnClassBody(Class<?> modelClass, JSwitch sw, Exclures properties,WindowType type) {

        Field[] fields = ValidateAndFillBeans.getObjectDeclaredFields(modelClass);//modelClass.getDeclaredFields();
        int index = 0;
        for (Field field : fields) {
            if (isFieldExclude(properties, field.getName(), type)) {
                continue;
            }
            if (!isCollectionType(field.getType()) && !field.getName().equalsIgnoreCase("serialVersionUID")) {
                if (!field.getType().equals(String.class)) {
                    sw._case(JExpr.lit(index)).body()._return(JExpr.direct(field.getType().getName() + ".class"));
                }
                index++;
            }

        }
        sw._default().body()._return(JExpr.direct("String.class"));

    }

    /**
     *
     * @param modelClass
     * @param sw
     */
    private static int countFieldSize(Class<?> modelClass, Exclures properties,WindowType type) {

        Field[] fields = ValidateAndFillBeans.getObjectDeclaredFields(modelClass);//modelClass.getDeclaredFields();
        int index = 0;
        for (Field field : fields) {
            if (isFieldExclude(properties, field.getName(),type)) {
                continue;
            }
            if (!isCollectionType(field.getType()) && !field.getName().equalsIgnoreCase("serialVersionUID")) {
                //sw._case(JExpr.lit(index)).body()._return(JExpr.direct("MessagesBundle.getMessage(\""+modelClass.getSimpleName().toLowerCase()+"."+field.getName().toLowerCase()+"\")"));
                index++;
            }

        }
        return index;

    }

    /**
     *
     * @param modelClass
     * @param def
     * @param idName
     */
    private static void buildFieldGetterAndSetter(Class<?> modelClass, JDefinedClass def, String idName) {

        Field[] fields = ValidateAndFillBeans.getObjectDeclaredFields(modelClass);//modelClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.getName().equalsIgnoreCase("serialVersionUID")) {
                continue;
            }
            JFieldVar fieldVar = def.field(4, field.getType(), field.getName().toLowerCase());
            JAnnotationUse annot = fieldVar.annotate(Champ.class);
            annot.param("mappedBy", field.getName()).param("type", field.getType());

            Annotation[] annotations = field.getDeclaredAnnotations();
            //System.out.println("FFFFFFFFFFFFFFFFF FFFFFFFFFFFFFFFFFFFFFFFF::::::::::::::::::::: "+field.getDeclaredAnnotations()+" :::::::: GGG ::: "+field.getAnnotations());
            for (Annotation a : annotations) {
                //System.out.println("FFFFFFFFFFFFFFFFF ::::::::::::::::::::: "+a.annotationType());
                if (a.annotationType().equals(Column.class)) {

                    Column column = (Column) a;
                    //System.out.println("FFFFFFFFFFFFFFFFF ::::::::::::::::::::: "+column);
                    annot.param("nullable", column.nullable());
                    if (column.length() > 0) {
                        annot.param("length", column.length());
                    }
                    annot.param("update", column.updatable());

                } else if (a.annotationType().equals(JoinColumn.class)) {

                    JoinColumn join = (JoinColumn) a;
                    annot.param("nullable", join.nullable());
                    annot.param("update", join.updatable());
                }
            }
            if (field.getName().equalsIgnoreCase(idName)) {
                annot.param("nullable", Boolean.FALSE);
                annot.param("update", Boolean.FALSE);
            }
            annot.param("errorMessageField", "lbm" + field.getName().toLowerCase());
            annot.param("errorMessage", modelClass.getSimpleName().toLowerCase() + "." + field.getName().toLowerCase() + ".error");
            String idValues = ("" + field.getName().charAt(0)).toUpperCase() + field.getName().substring(1);
            JMethod method = def.method(1, Void.TYPE, "set" + idValues);
            method.param(field.getType(), field.getName());
            method.body().block().directStatement("this." + field.getName().toLowerCase() + " = " + field.getName()+";");
            JMethod method2 = def.method(1, field.getType(), "get" + idValues);
            method2.body()._return(JExpr.ref(field.getName().toLowerCase()));
        }
    }

    /**
     *
     * @param modelClass
     * @param def
     * @param idName
     */
    private static void buildPanelFieldFromModel(Class<?> modelClass, JDefinedClass def, JCodeModel codeModel, String idName, Groupes groupes, Exclures properties) {

        //Creation du tabPane et des panels
        if (groupes != null && groupes.getGroupe().size() > 1) {
            JClass class0 = codeModel.ref(JTabbedPane.class);
            JFieldVar field0 = def.field(JMod.PRIVATE, class0, "tabbedPane");
            for (Groupe groupe : groupes.getGroupe()) {
                class0 = codeModel.ref(JPanel.class);
                field0 = def.field(JMod.PRIVATE, class0, groupe.getName() + "Panel");
            }
        }//end if(groupes!=null&&groupes.getGroupe().size()>1){
        Field[] fields = ValidateAndFillBeans.getObjectDeclaredFields(modelClass);//modelClass.getDeclaredFields();

        for (Field field0 : fields) {

            if (field0.getName().equalsIgnoreCase("serialVersionUID")||field0.getName().equalsIgnoreCase("selected")) {
                continue;
            }
            Field field = null;
            
            if (properties == null || properties.getExclure().isEmpty()) {
                field = field0;
            } else if (!isFieldExclude(properties, field0.getName(), WindowType.EDIT)) {
                field = field0;
            }
            //System.out.println("FFFFFFFFFFFFFFFFF ::::::::::::::::::::: "+isFieldExclude(properties, field0.getName(), WindowType.EDIT)+" ::::::::: "+field0.getName()+" ::::: "+field);
            if (field == null) {
                continue;
            }

            JFieldVar fieldVar;
            JFieldVar leftLabel = null;
            JFieldVar rigthLabel = null;
            JClass jclass;
            if (field.getType().equals(String.class)) {
                fieldVar = def.field(4, JTextField.class, field.getName().toLowerCase());
                jclass = codeModel.ref(JTextField.class);

            } else if (field.getType().equals(Long.class) || field.getType().equals(long.class) || field.getType().equals(Integer.class) || field.getType().equals(int.class) || field.getType().equals(Short.class) || field.getType().equals(short.class)) {
                fieldVar = def.field(4, JFormattedTextField.class, field.getName().toLowerCase());
                jclass = codeModel.ref(JTextField.class);
            } else if (field.getType().equals(Double.class) || field.getType().equals(Float.class) || field.getType().equals(BigDecimal.class) || field.getType().equals(double.class) || field.getType().equals(float.class)) {
                fieldVar = def.field(4, JFormattedTextField.class, field.getName().toLowerCase());
                jclass = codeModel.ref(JFormattedTextField.class);
            } else if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)) {
                fieldVar = def.field(4, JCheckBox.class, field.getName().toLowerCase());
                jclass = codeModel.ref(JCheckBox.class);
            } else if (field.getType().equals(Date.class)) {
                fieldVar = def.field(4, JDateChooser.class, field.getName().toLowerCase());
                jclass = codeModel.ref(JDateChooser.class);
            } else if (field.getType().equals(Collection.class) || field.getType().equals(List.class)
                    || field.getType().equals(Set.class) || field.getType().equals(ArrayList.class)) {
                fieldVar = def.field(4, AbstractTablePanel.class, field.getName().toLowerCase());
                jclass = codeModel.ref(AbstractTablePanel.class);
            } else {//Tout autre object java
                fieldVar = def.field(4, CustomComboBox.class, field.getName().toLowerCase());
                jclass = codeModel.ref(CustomComboBox.class);
            }
            if (fieldVar != null) {
                if (!field.getType().equals(Collection.class) && !field.getType().equals(List.class)
                        && !field.getType().equals(Set.class) && !field.getType().equals(ArrayList.class)) {
                    leftLabel = def.field(4, JLabel.class, "lb" + field.getName().toLowerCase());
                    rigthLabel = def.field(4, JLabel.class, "lbm" + field.getName().toLowerCase());
                }
                JAnnotationUse annot = fieldVar.annotate(Champ.class);
                annot.param("mappedBy", field.getName()).param("type", field.getType());
                //field.setAccessible(true);
                Annotation[] annotations = field.getDeclaredAnnotations();
                //System.out.println("FFLLFFFFFFFFFFFFF FFFFFFFFFFFFFFFFFFFFFFFF::::::::::::::::::::: "+field.getDeclaredAnnotations().length+" ::::::::::::: "+field.getName()+" :::::::::HH :::: "+field.getAnnotations().length);
                for (Annotation a : annotations) {
                    //System.out.println("FFFFFFFFFFFFFFFFF ::::::::::::::::::::: "+a.annotationType());
                    if (a.annotationType().equals(Column.class)) {

                        Column column = (Column) a;
                        //System.out.println("FFFFFFFFFFFFFFFFF ::::::::::::::::::::: "+column);
                        annot.param("nullable", column.nullable());
                        if (column.length() > 0) {
                            annot.param("length", column.length());
                        }
                        if (!column.updatable()) {
                            annot.param("update", column.updatable());
                        }
                    } else if (a.annotationType().equals(JoinColumn.class)) {

                        JoinColumn join = (JoinColumn) a;
                        if (!join.nullable()) {
                            annot.param("nullable", join.nullable());
                        }

                        if (!join.updatable()) {
                            annot.param("update", join.updatable());
                        }
                    }

                }
                if (field.getName().equalsIgnoreCase(idName)) {
                    annot.param("nullable", Boolean.FALSE);
                    annot.param("update", Boolean.FALSE);
                }
                annot.param("errorMessageField", "lbm" + field.getName().toLowerCase());
                annot.param("errorMessage", modelClass.getSimpleName().toLowerCase() + "." + field.getName().toLowerCase() + ".error");
                String idValues = ("" + field.getName().charAt(0)).toUpperCase() + field.getName().substring(1);
                JMethod method2 = def.method(1, jclass, "get" + idValues);
                method2.body()._return(JExpr.ref(field.getName().toLowerCase()));
                //Ajout du commentaire au niveau de la methode
                method2.javadoc().add("Getter");
                method2.javadoc().addReturn().append(jclass.fullName());
            }
        }
    }

    /**
     *
     * @param modelClass
     * @param def
     * @param idName
     */
    private static void buildInitComponentsInitialisation(File repository, Class<?> modelClass, JBlock block, Groupes groupes, Exclures properties, Relations relations) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        //Creation du tabPane et des panels
        if (groupes != null && groupes.getGroupe().size() > 1) {
            //JFieldVar field0 = def.field(JMod.PRIVATE, class0, "tabbedPane");
            block.directStatement("tabbedPane = new JTabbedPane() ;");
            for (Groupe groupe : groupes.getGroupe()) {
                block.directStatement(groupe.getName() + "Panel = new JPanel() ;");
            }
        }//end if(groupes!=null&&groupes.getGroupe().size()>1){

        Field[] fields = ValidateAndFillBeans.getObjectDeclaredFields(modelClass);//modelClass.getDeclaredFields();

        for (Field field0 : fields) {
            if (field0.getName().equalsIgnoreCase("serialVersionUID")||field0.getName().equalsIgnoreCase("selected")) {
                continue;
            }
            Field field = null;
            if (properties == null || properties.getExclure().isEmpty()) {
                field = field0;
            } else if (!isFieldExclude(properties, field0.getName(), WindowType.EDIT)) {
                field = field0;
            }

            if (field == null) {
                continue;
            }

            JStatement statement = null;
            if (field.getType().equals(String.class)) {
                statement = block.directStatement(field.getName().toLowerCase() + " = new JTextField() ;");
                block.directStatement(field.getName().toLowerCase() + ".setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));");
            } else if (field.getType().equals(Long.class) || field.getType().equals(long.class) || field.getType().equals(Integer.class) || field.getType().equals(int.class) || field.getType().equals(Short.class) || field.getType().equals(short.class)) {
                statement = block.directStatement(field.getName().toLowerCase() + " = new JFormattedTextField() ;");
                block.directStatement(field.getName().toLowerCase() + ".setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));");
            } else if (field.getType().equals(Double.class) || field.getType().equals(Float.class) || field.getType().equals(BigDecimal.class) || field.getType().equals(double.class) || field.getType().equals(float.class)) {
                statement = block.directStatement(field.getName().toLowerCase() + " = new JFormattedTextField() ;");
                block.directStatement(field.getName().toLowerCase() + ".setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));");
            } else if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)) {
                statement = block.directStatement(field.getName().toLowerCase() + " = new JCheckBox() ;");
                block.directStatement(field.getName().toLowerCase() + ".setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));");
            } else if (field.getType().equals(Date.class)) {
                statement = block.directStatement(field.getName().toLowerCase() + " = new JDateChooser() ;");
            } else if (field.getType().equals(Collection.class) || field.getType().equals(List.class)
                    || field.getType().equals(Set.class) || field.getType().equals(ArrayList.class)) {
                ParameterizedType type = (ParameterizedType) field.getGenericType();
                Class<?> typeClass = ((Class<?>) type.getActualTypeArguments()[0]);
                if (!isBaseType(typeClass)) {
                    buildTablePanel(repository, field, typeClass, relations);
                    statement = block.directStatement(field.getName().toLowerCase() + " = new " + buildPackage(typeClass, ModuleLevel.VIEW) + "." + typeClass.getSimpleName() + "TablePanel();");

                    block.directStatement(field.getName().toLowerCase() + ".setModel( new " + buildPackage(typeClass, ModuleLevel.VIEW) + "." + typeClass.getSimpleName() + "Model());");
                } //end if(!isBaseType(typeClass)) 
            } else {//Tout autre object java
                statement = block.directStatement(field.getName().toLowerCase() + " = new CustomComboBox() ;");
                String name = ("" + field.getName().charAt(0)).toUpperCase() + field.getName().substring(1);
                statement = block.directStatement(field.getName().toLowerCase() + ".setManager(get" + name + "Manager());");
                statement = block.directStatement(field.getName().toLowerCase() + ".loadData() ;");
            }
            if (statement != null) {
                if (!field.getType().equals(Collection.class) && !field.getType().equals(List.class)
                        && !field.getType().equals(Set.class) && !field.getType().equals(ArrayList.class)) {
                    block.directStatement("lb" + field.getName().toLowerCase() + " = new JLabel();");
                    block.directStatement("lbm" + field.getName().toLowerCase() + " = new JLabel();");
                    block.directStatement(" lb" + field.getName().toLowerCase() + ".setText(MessagesBundle.getMessage( \"" + modelClass.getSimpleName().toLowerCase() + "." + field.getName().toLowerCase() + "\"));");
                    messages.put(modelClass.getSimpleName().toLowerCase() + "." + field.getName().toLowerCase(), field.getName());

                }
            }
        }
    }

    /**
     *
     * @param modelClass
     * @param def
     * @param idName
     */
    private static void buildPanelFieldCriteria(Class<?> modelClass, JDefinedClass def, JCodeModel codeModel, String idName, Exclures properties) throws JClassAlreadyExistsException {

        Field[] fields = ValidateAndFillBeans.getObjectDeclaredFields(modelClass);//modelClass.getDeclaredFields();

        for (Field field0 : fields) {
            if (field0.getName().equalsIgnoreCase("serialVersionUID")||field0.getName().equalsIgnoreCase("selected")) {
                continue;
            }
            Field field = null;
            if (properties == null || properties.getExclure().isEmpty()) {
                field = field0;
            } else if (!isFieldExclude(properties, field0.getName(), WindowType.CRITERIA)) {
                field = field0;
            }

            if (field == null) {
                continue;
            }

            JFieldVar fieldVar = null;
            JFieldVar leftLabel = null;
            JFieldVar rigthLabel = null;
            JClass jclass = null;
            if (field.getType().equals(String.class)) {
                fieldVar = def.field(4, JTextField.class, field.getName().toLowerCase());
                jclass = codeModel.ref(JTextField.class);

            } else if (field.getType().equals(Long.class) || field.getType().equals(long.class) || field.getType().equals(Integer.class) || field.getType().equals(int.class) || field.getType().equals(Short.class) || field.getType().equals(short.class)) {
                fieldVar = def.field(4, JFormattedTextField.class, field.getName().toLowerCase());
                jclass = codeModel.ref(JTextField.class);
            } else if (field.getType().equals(Double.class) || field.getType().equals(Float.class) || field.getType().equals(BigDecimal.class) || field.getType().equals(double.class) || field.getType().equals(float.class)) {
                fieldVar = def.field(4, JFormattedTextField.class, field.getName().toLowerCase());
                jclass = codeModel.ref(JFormattedTextField.class);
            } else if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)) {
                fieldVar = def.field(4, JCheckBox.class, field.getName().toLowerCase());
                jclass = codeModel.ref(JCheckBox.class);
            } else if (field.getType().equals(Date.class)) {
                fieldVar = def.field(4, JDateChooser.class, field.getName().toLowerCase());
                jclass = codeModel.ref(JDateChooser.class);
            } else if (!isCollectionType(field.getType())) {//Tout autre object java
                fieldVar = def.field(4, CustomComboBox.class, field.getName().toLowerCase());
                jclass = codeModel.ref(CustomComboBox.class);
            }
            if (fieldVar != null) {
                if (!isCollectionType(field.getType())) {
                    leftLabel = def.field(4, JLabel.class, "lb" + field.getName().toLowerCase());
                    rigthLabel = def.field(4, JLabel.class, "lbm" + field.getName().toLowerCase());
                    JAnnotationUse annot = fieldVar.annotate(Search.class);
                    annot.param("field", field.getName()).param("type", field.getType());
                    String idValues = ("" + field.getName().charAt(0)).toUpperCase() + field.getName().substring(1);
                    JMethod method2 = def.method(1, jclass, "get" + idValues);
                    method2.body()._return(JExpr.ref(field.getName().toLowerCase()));

                    //Ajout du commentaire au niveau de la methode
                    method2.javadoc().add("Getter");
                    method2.javadoc().addReturn().append(jclass.fullName());
                }
            }
        }
    }

    /**
     *
     * @param exclures
     * @param fieldName
     * @param criteria:panel de recherche ou non
     * @return
     */
    private static boolean isFieldExclude(Exclures exclures, String fieldName, WindowType type) {
        
        if (exclures == null || exclures.getExclure() == null || exclures.getExclure().isEmpty()) {
            return false;
        }
        for (Exclure e : exclures.getExclure()) {
            
            if (e.getName().equalsIgnoreCase(fieldName)) {
                if(fieldName.equalsIgnoreCase("selected")&&type.equals(WindowType.TRANSFERT))
                    return false ;
                
                if(fieldName.equalsIgnoreCase("selected"))
                    return true ;
                
                if(e.isAll())
                       return true ;
                else if(e.isEdit()&&type.equals(WindowType.EDIT))
                    return true;
                else if(e.isSearch()&&type.equals(WindowType.CRITERIA))
                    return true ;
                else if(e.isModel()&&type.equals(WindowType.MODEL))
                    return true;
                else if(e.isModel()&&type.equals(WindowType.TRANSFERT))
                    return true;
                
                return false;
            }
        }

        return false;
    }

    /**
     *
     * @param modelClass
     * @param def
     * @param idName
     */
    private static void buildInitCriteria(File repository, Class<?> modelClass, JBlock block, Exclures properties) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        Field[] fields = ValidateAndFillBeans.getObjectDeclaredFields(modelClass);//modelClass.getDeclaredFields();

        for (Field field0 : fields) {
            if (field0.getName().equalsIgnoreCase("serialVersionUID")||field0.getName().equalsIgnoreCase("selected")) {
                continue;
            }
            Field field = null;
            if (properties == null || properties.getExclure().isEmpty()) {
                field = field0;
            } else if (!isFieldExclude(properties, field0.getName(), WindowType.CRITERIA)) {
                field = field0;
            }

            if (field == null) {
                continue;
            }
            JStatement statement = null;
            if (field.getType().equals(String.class)) {
                statement = block.directStatement(field.getName().toLowerCase() + " = new JTextField() ;");
                block.directStatement(field.getName().toLowerCase() + ".setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));");
            } else if (field.getType().equals(Long.class) || field.getType().equals(long.class) || field.getType().equals(Integer.class) || field.getType().equals(int.class) || field.getType().equals(Short.class) || field.getType().equals(short.class)) {
                statement = block.directStatement(field.getName().toLowerCase() + " = new JFormattedTextField() ;");
                block.directStatement(field.getName().toLowerCase() + ".setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));");
            } else if (field.getType().equals(Double.class) || field.getType().equals(Float.class) || field.getType().equals(BigDecimal.class) || field.getType().equals(double.class) || field.getType().equals(float.class)) {
                statement = block.directStatement(field.getName().toLowerCase() + " = new JFormattedTextField() ;");
                block.directStatement(field.getName().toLowerCase() + ".setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));");
            } else if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)) {
                statement = block.directStatement(field.getName().toLowerCase() + " = new JCheckBox() ;");
                block.directStatement(field.getName().toLowerCase() + ".setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));");
            } else if (field.getType().equals(Date.class)) {
                statement = block.directStatement(field.getName().toLowerCase() + " = new JDateChooser() ;");
            } else if (!isCollectionType(field.getType())) {//Tout autre object java
                statement = block.directStatement(field.getName().toLowerCase() + " = new CustomComboBox() ;");
                String name = ("" + field.getName().charAt(0)).toUpperCase() + field.getName().substring(1);
                statement = block.directStatement(field.getName().toLowerCase() + ".setManager(get" + name + "Manager());");
                statement = block.directStatement(field.getName().toLowerCase() + ".loadData() ;");
            }
            if (statement != null) {
                if (!isCollectionType(field.getType())) {
                    block.directStatement("lb" + field.getName().toLowerCase() + " = new JLabel();");
                    block.directStatement("lbm" + field.getName().toLowerCase() + " = new JLabel();");
                    block.directStatement(" lb" + field.getName().toLowerCase() + ".setText(MessagesBundle.getMessage( \"" + modelClass.getSimpleName().toLowerCase() + "." + field.getName().toLowerCase() + "\"));");
                    
                }
            }
        }
    }

    /**
     *
     * @param modelClass
     * @param codeModel
     * @param ref
     */
    private static void buildFieldManager(Class<?> modelClass, JCodeModel codeModel, JDefinedClass def) {

        Field[] fields = ValidateAndFillBeans.getObjectDeclaredFields(modelClass);//modelClass.getDeclaredFields();

        for (Field field : fields) {

            if (!isBaseType(field.getType()) && !isCollectionType(field.getType())) {

                String name = ("" + field.getName().charAt(0)).toUpperCase() + field.getName().substring(1);

                JMethod method = def.method(JMod.PRIVATE, GenericManager.class, "get" + name + "Manager");
                //System.out.println("----------------------------------------------------------------------------- "+field.getType());
                JTryBlock block = method.body()._try();
                block.body().directStatement(" return (GenericManager)context.lookup(\"" + buildPackage(field.getType(), ModuleLevel.MANAGER_IMPLEMENTATION) + "." + field.getType().getSimpleName() + "ManagerImpl\");");
                JClass class0 = codeModel.ref(Exception.class);
                block._catch(class0).body()._return(JExpr._null());

                //Ajout du commentaire au niveau de la methode
                method.javadoc().add("Methode permettant de renvoyer un manager");
                method.javadoc().addReturn().append(GenericManager.class.getName());
            }
        }
    }

    /**
     *
     * @param modelClass
     * @param def
     * @param idName
     */
    private static void buildhorizontalGroup(Class<?> modelClass, JBlock block, JCodeModel codeModel, Groupes groupes, Exclures properties,WindowType type) {

        if (groupes == null || groupes.getGroupe() == null || groupes.getGroupe().isEmpty()) {
            buildHorizontalAndVerticalGroupForPanel(modelClass, block, codeModel, properties,type);
        } else {
            //col3v.init(JExpr.direct("layout.createParallelGroup() "));
            JClass secol1v = codeModel.ref(GroupLayout.SequentialGroup.class);
            JClass pacol1h = codeModel.ref(GroupLayout.ParallelGroup.class);
            JVar seqL = block.decl(secol1v, "hg").init(JExpr.direct("layout.createSequentialGroup()"));
            block.directStatement("GroupLayout.ParallelGroup hgp = layout.createParallelGroup(GroupLayout.Alignment.LEADING);");
            block.directStatement("hgp.addGroup(hg);");
            block.directStatement("layout.setHorizontalGroup(hgp);");
            block.directStatement("GroupLayout.SequentialGroup sg = layout.createSequentialGroup();");
            JVar hv = block.decl(pacol1h, "hv").init(JExpr.direct("layout.createParallelGroup()"));
            block.directStatement("layout.setVerticalGroup(hv) ;");
            block.directStatement("hv.addGroup(sg);");
            for (Groupe groupe : groupes.getGroupe()) {
                buildHorizontalAndVerticalGroupForPanel(groupe.getName() + "Panel", modelClass, block, codeModel, groupe, properties,type);
                block.directStatement("tabbedPane.addTab(MessagesBundle.getMessage(\"" + groupe.getLabel() + "\") ," + groupe.getName() + "Panel);");
                messages.put(groupe.getLabel(), groupe.getLabel());
            }
            block.directStatement("hgp.addComponent(tabbedPane , javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);");
            block.directStatement("sg.addComponent(tabbedPane , javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);");

        }

    }

    /**
     *
     * @param panelName
     * @param modelClass
     * @param block
     * @param codeModel
     * @param groupe
     * @param properties
     */
    private static void buildHorizontalAndVerticalGroupForPanel(Class<?> modelClass, JBlock block, JCodeModel codeModel, Exclures properties,WindowType type) {

        Field[] fields = ValidateAndFillBeans.getObjectDeclaredFields(modelClass);//modelClass.getDeclaredFields();
        JClass pacol1h = codeModel.ref(GroupLayout.ParallelGroup.class);
        JVar col1h = block.decl(pacol1h, "col1h");
        JVar col2h = block.decl(pacol1h, "col2h");
        JVar col3h = block.decl(pacol1h, "col3h");
        //JClass GroupClass = codeModel.ref(GroupLayout.ParallelGroup.class);
        JClass secol1v = codeModel.ref(GroupLayout.SequentialGroup.class);
        JVar col1v = block.decl(pacol1h, "col1v");
        JVar col2v = block.decl(pacol1h, "col2v");
        JVar col3v = block.decl(pacol1h, "col3v");
        col1h.init(JExpr.direct("layout.createParallelGroup() "));
        //col1v.init(JExpr.direct("layout.createParallelGroup() "));
        col2h.init(JExpr.direct("layout.createParallelGroup() "));
        //col2v.init(JExpr.direct("layout.createParallelGroup() "));
        col3h.init(JExpr.direct("layout.createParallelGroup() "));
        //col3v.init(JExpr.direct("layout.createParallelGroup() "));
        JVar seqL = block.decl(secol1v, "hg").init(JExpr.direct("layout.createSequentialGroup()"));
        block.directStatement("GroupLayout.ParallelGroup hgp = layout.createParallelGroup(GroupLayout.Alignment.LEADING);");
        block.directStatement("hgp.addGroup(hg);");
        block.directStatement("layout.setHorizontalGroup(hgp);");
        JVar hv = block.decl(pacol1h, "hv").init(JExpr.direct("layout.createParallelGroup()"));
        block.directStatement("GroupLayout.SequentialGroup sg = layout.createSequentialGroup();");
        block.directStatement("layout.setVerticalGroup(hv) ;");
        block.directStatement("hv.addGroup(sg);");
        //Positionnement du 
        for (Field field0 : fields) {

            if (field0.getName().equalsIgnoreCase("serialVersionUID")||field0.getName().equalsIgnoreCase("selected")) {
                continue;
            }
            Field field = null;
            if (properties == null || properties.getExclure().isEmpty()) {
                field = field0;
            } else if (!isFieldExclude(properties, field0.getName(), type)) {
                field = field0;
            }

            if (field == null) {
                continue;
            }

            JStatement statement = null;

            if (!field.getType().equals(Collection.class) && !field.getType().equals(List.class)
                    && !field.getType().equals(Set.class) && !field.getType().equals(ArrayList.class)) {
                block.directStatement("// Positionnement des elements ");
                block.directStatement("col1h.addComponent(" + "lb" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;");
                block.directStatement("col2h.addComponent(" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;");
                block.directStatement("col3h.addComponent( lbm" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;");
                JVar var2 = block.decl(pacol1h, "col" + field.getName().toLowerCase() + "v").init(JExpr.direct("layout.createParallelGroup() "));
                block.directStatement("col" + field.getName().toLowerCase() + "v.addComponent(" + "lb" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;");
                block.directStatement("col" + field.getName().toLowerCase() + "v.addComponent(" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;");
                block.directStatement("col" + field.getName().toLowerCase() + "v.addComponent( lbm" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;");
                block.directStatement("sg.addGroup(col" + field.getName().toLowerCase() + "v) ; ");

            } else {
                block.directStatement("// Positionnement des elements ");
                block.directStatement("hgp.addComponent(" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);");
                block.directStatement("sg.addComponent(" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);");

            }
        }

        block.directStatement("hg.addGroup(col1h) ; ");
        block.directStatement("hg.addGroup(col2h) ; ");
        block.directStatement("hg.addGroup(col3h) ; ");

        /*block.directStatement("sg.addGroup(col1v) ; ");
         block.directStatement("sg.addGroup(col2v) ; ");
         block.directStatement("sg.addGroup(col3v) ;");*/
    }

    /**
     *
     * @param panelName
     * @param modelClass
     * @param block
     * @param codeModel
     * @param groupe
     * @param properties
     */
    private static void buildHorizontalAndVerticalGroupForPanel(String panelName, Class<?> modelClass, JBlock block, JCodeModel codeModel, Groupe groupe, Exclures properties,WindowType type) {

        List<Integer> fieldsIndex = indexsConverter(groupe.getIndexs());
        //System.out.println(("::::::::::::::::::::::::::::::::::::::::::::::::::::: "+fieldsIndex));
        //Initialisation du groupe panel
        JClass panelClass = codeModel.ref(GroupLayout.class);
        JVar fieldVar = block.decl(panelClass, panelName + "layout");
        fieldVar.init(JExpr.direct("new GroupLayout(" + panelName + ")"));
        block.directStatement(panelName + ".setLayout(" + panelName + "layout);");
        JClass pacol1h = codeModel.ref(GroupLayout.ParallelGroup.class);
        JVar col1h = block.decl(pacol1h, panelName + "col1h");
        JVar col2h = block.decl(pacol1h, panelName + "col2h");
        JVar col3h = block.decl(pacol1h, panelName + "col3h");
        //JClass GroupClass = codeModel.ref(GroupLayout.ParallelGroup.class);
        JClass secol1v = codeModel.ref(GroupLayout.SequentialGroup.class);
        col1h.init(JExpr.direct(panelName + "layout.createParallelGroup() "));
        //col1v.init(JExpr.direct("layout.createParallelGroup() "));
        col2h.init(JExpr.direct(panelName + "layout.createParallelGroup() "));
        //col2v.init(JExpr.direct("layout.createParallelGroup() "));
        col3h.init(JExpr.direct(panelName + "layout.createParallelGroup() "));
        //col3v.init(JExpr.direct("layout.createParallelGroup() "));
        JVar seqL = block.decl(secol1v, panelName + "hg").init(JExpr.direct(panelName + "layout.createSequentialGroup()"));
        block.directStatement("GroupLayout.ParallelGroup " + panelName + "hgp = " + panelName + "layout.createParallelGroup(GroupLayout.Alignment.LEADING);");
        block.directStatement(panelName + "hgp.addGroup(" + panelName + "hg);");
        block.directStatement(panelName + "layout.setHorizontalGroup(" + panelName + "hgp);");
        JVar hv = block.decl(pacol1h, panelName + "hv").init(JExpr.direct(panelName + "layout.createParallelGroup()"));
        block.directStatement("GroupLayout.SequentialGroup " + panelName + "sg = " + panelName + "layout.createSequentialGroup();");
        block.directStatement(panelName + "layout.setVerticalGroup(" + panelName + "hv) ;");
        block.directStatement(panelName + "hv.addGroup(" + panelName + "sg);");
        //Positionnement du 
        int index = -1;
        Field[] fields = ValidateAndFillBeans.getObjectDeclaredFields(modelClass);//modelClass.getDeclaredFields();
        for (Field field0 : fields) {

            index++;

            if (field0.getName().equalsIgnoreCase("serialVersionUID")||field0.getName().equalsIgnoreCase("selected")) {
                continue;
            }
            if (!fieldsIndex.contains(index)) {
                continue;
            }
            Field field = null;
            if (properties == null || properties.getExclure().isEmpty()) {
                field = field0;
            } else if (!isFieldExclude(properties, field0.getName(), type)) {
                field = field0;
            }

            if (field == null) {
                continue;
            }

            JStatement statement = null;

            if (!field.getType().equals(Collection.class) && !field.getType().equals(List.class)
                    && !field.getType().equals(Set.class) && !field.getType().equals(ArrayList.class)) {
                block.directStatement("// Positionnement des elements ");
                block.directStatement(panelName + "col1h.addComponent(" + "lb" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;");
                block.directStatement(panelName + "col2h.addComponent(" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;");
                block.directStatement(panelName + "col3h.addComponent( lbm" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;");
                JVar var2 = block.decl(pacol1h, panelName + "col" + field.getName().toLowerCase() + "v").init(JExpr.direct(panelName + "layout.createParallelGroup() "));
                block.directStatement(panelName + "col" + field.getName().toLowerCase() + "v.addComponent(" + "lb" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;");
                block.directStatement(panelName + "col" + field.getName().toLowerCase() + "v.addComponent(" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;");
                block.directStatement(panelName + "col" + field.getName().toLowerCase() + "v.addComponent( lbm" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;");
                block.directStatement(panelName + "sg.addGroup(" + panelName + "col" + field.getName().toLowerCase() + "v) ; ");

            } else {
                block.directStatement("// Positionnement des elements ");
                block.directStatement(panelName + "hgp.addComponent(" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);");
                block.directStatement(panelName + "sg.addComponent(" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);");

            }

        }

        block.directStatement(panelName + "hg.addGroup(" + panelName + "col1h) ; ");
        block.directStatement(panelName + "hg.addGroup(" + panelName + "col2h) ; ");
        block.directStatement(panelName + "hg.addGroup(" + panelName + "col3h) ; ");
        block.directStatement(panelName + "layout.setAutoCreateGaps(true);");
        block.directStatement(panelName + "layout.setAutoCreateContainerGaps(true);");

    }

    private static List<Integer> indexsConverter(String indexs) {

        List<Integer> indexsList = new ArrayList<Integer>();

        String[] indexsTab = indexs.split(",");

        for (String index : indexsTab) {

            if (!index.matches("(\\d)*")) {

                String[] indexes2 = index.split("-");

                int index_inf = Integer.parseInt(indexes2[0]);
                int index_sup = Integer.parseInt(indexes2[1]);
                for (int i = index_inf; i <= index_sup; i++) {
                    indexsList.add(i);
                }

            } else {
                indexsList.add(Integer.parseInt(index));
            }
        }
        return indexsList;
    }

    /**
     *
     * @param modelClass
     * @param def
     * @param idName
     */
    private static void buildhorizontalGroupCriteria(Class<?> modelClass, JBlock block, JCodeModel codeModel, Exclures properties) {

        Field[] fields = ValidateAndFillBeans.getObjectDeclaredFields(modelClass);//modelClass.getDeclaredFields();
        JClass pacol1h = codeModel.ref(GroupLayout.ParallelGroup.class);
        JVar col1h = block.decl(pacol1h, "col1h");
        JVar col2h = block.decl(pacol1h, "col2h");
        JVar col3h = block.decl(pacol1h, "col3h");
        //JClass GroupClass = codeModel.ref(GroupLayout.ParallelGroup.class);
        JClass secol1v = codeModel.ref(GroupLayout.SequentialGroup.class);
        JVar col1v = block.decl(pacol1h, "col1v");
        JVar col2v = block.decl(pacol1h, "col2v");
        JVar col3v = block.decl(pacol1h, "col3v");
        col1h.init(JExpr.direct("layout.createParallelGroup() "));
        //col1v.init(JExpr.direct("layout.createParallelGroup() "));
        col2h.init(JExpr.direct("layout.createParallelGroup() "));
        //col2v.init(JExpr.direct("layout.createParallelGroup() "));
        col3h.init(JExpr.direct("layout.createParallelGroup() "));
        //col3v.init(JExpr.direct("layout.createParallelGroup() "));
        JVar seqL = block.decl(secol1v, "hg").init(JExpr.direct("layout.createSequentialGroup()"));
        block.directStatement("GroupLayout.ParallelGroup hgp = layout.createParallelGroup(GroupLayout.Alignment.LEADING);");
        block.directStatement("hgp.addGroup(hg);");
        block.directStatement("layout.setHorizontalGroup(hgp);");
        JVar hv = block.decl(pacol1h, "hv").init(JExpr.direct("layout.createParallelGroup()"));
        block.directStatement("GroupLayout.SequentialGroup sg = layout.createSequentialGroup();");
        block.directStatement("layout.setVerticalGroup(hv) ;");
        block.directStatement("hv.addGroup(sg);");
        for (Field field0 : fields) {

            if (field0.getName().equalsIgnoreCase("serialVersionUID")||field0.getName().equalsIgnoreCase("selected")) {
                continue;
            }

            Field field = null;

            if (properties == null || properties.getExclure().isEmpty()) {
                field = field0;
            } else if (!isFieldExclude(properties, field0.getName(), WindowType.CRITERIA)) {
                field = field0;
            }

            if (field == null) {
                continue;
            }

            JStatement statement = null;
            if (!isCollectionType(field.getType())) {
                block.directStatement("// Positionnement des elements ");
                block.directStatement("col1h.addComponent(" + "lb" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;");
                block.directStatement("col2h.addComponent(" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;");
                block.directStatement("col3h.addComponent( lbm" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;");
                JVar var2 = block.decl(pacol1h, "col" + field.getName().toLowerCase() + "v").init(JExpr.direct("layout.createParallelGroup() "));
                block.directStatement("col" + field.getName().toLowerCase() + "v.addComponent(" + "lb" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;");
                block.directStatement("col" + field.getName().toLowerCase() + "v.addComponent(" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;");
                block.directStatement("col" + field.getName().toLowerCase() + "v.addComponent( lbm" + field.getName().toLowerCase() + ", javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;");
                block.directStatement("sg.addGroup(col" + field.getName().toLowerCase() + "v) ; ");

            }/*else {
             block.directStatement("// Positionnement des elements ");
             block.directStatement("hgp.addComponent("+field.getName().toLowerCase()+", javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);"); 
             block.directStatement("sg.addComponent("+field.getName().toLowerCase()+", javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);"); 
              
             }*/

        }

        block.directStatement("hg.addGroup(col1h) ; ");
        block.directStatement("hg.addGroup(col2h) ; ");
        block.directStatement("hg.addGroup(col3h) ; ");

        /*block.directStatement("sg.addGroup(col1v) ; ");
         block.directStatement("sg.addGroup(col2v) ; ");
         block.directStatement("sg.addGroup(col3v) ;");*/
    }
    
    
    /**
     * Methode retournant le title de chaque entite et l'adapte en fonction de chaque type de fenetre
     * @param repository
     * @throws java.io.FileNotFoundException
     */
    public static String getTitleDialogForEntity(Class<?> modelClass) {
       
        Method mthd = null;
        
       for(Method methode : modelClass.getDeclaredMethods()){
           
           if(methode.getName().equalsIgnoreCase("getTitleDialog")){ //Dialog 
               
               mthd = methode;
               //System.out.println("=================================== "+modelClass.getSimpleName()+"    =================================");
               try {
                   return (String)mthd.invoke(modelClass.newInstance(), null);
               } catch (Exception ex) {
                   Logger.getLogger(SourceGenerator.class.getName()).log(Level.SEVERE, null, ex);
               }
               
           }
       }
        
        return "AUCUN TITRE EDIT DIAOLOG PREVU";
    }
    
    /**
     * Methode retournant le title de chaque entite et l'adapte en fonction de chaque type de fenetre
     * @param repository
     * @throws java.io.FileNotFoundException
     */
    public static String getTitleListeForEntity(Class<?> modelClass) {
       
        Method mthd = null;
        
       for(Method methode : modelClass.getDeclaredMethods()){
           
           if(methode.getName().equalsIgnoreCase("getTitleListe")){ // Liste
               
               mthd = methode;
               //System.out.println("=================================== "+modelClass.getSimpleName()+"    =================================");
               try {
                   return (String)mthd.invoke(modelClass.newInstance(), null);
               } catch (Exception ex) {
                   Logger.getLogger(SourceGenerator.class.getName()).log(Level.SEVERE, null, ex);
               }
               
           }
       }
       
        return "AUCUN TITRE LISTE PREVU";
    }
    
    /**
     *
     * @param repository
     * @throws java.io.FileNotFoundException
     */
    public static void createMessagesAndResources(File repository) throws FileNotFoundException, IOException {

        messages.put("save", "Enregistrer");
        messages.put("save_tooltip", "Valider et enregistrer ");
        messages.put("cancel", "Quitter");
        messages.put("cancel_tooltip", "Fermer la fenetre");
        messages.put("new.title", "Nouveau");
        messages.put("new.tooltip", "Enregistrer une nouvelle ligne");
        messages.put("update.title", "Modifier");
        messages.put("update.tooltip", "Modifier la selection");
        messages.put("delete.title", "Supprimer");
        messages.put("delete.tooltip", "Supprimer la selection");
        messages.put("view.title", "Consulter");
        messages.put("view.tooltip", "Consulter les details");
        messages.put("print.title", "Imprimer");
        messages.put("print.tooltip", "Imprimer la selection");
        messages.put("cancel.title", "Quitter");
        messages.put("cancel.tooltip", "Ferme et Quitter le fenetre");
        messages.put("search.title", "Rechercher");
        messages.put("search.tooltip", "Lance la recherche");
        messages.put("clear.title", "Effacer");
        messages.put("clear.tooltip", "Reinitialisation des champs");
        messages.put("deconnexion.title", "Deconnection");
        
        //On met à jour les messages
        //getTitleForEntity(modelClass)
        
        //Section fichiers messages.properties
        File file = new File(repository, "messages.properties");
        file.createNewFile();
        FileInputStream stream = new FileInputStream(file);
        Properties prop = new Properties();
        prop.load(stream);
        prop.putAll(messages);
        FileOutputStream oStream = new FileOutputStream(file);
        prop.store(oStream, "test");
        //Section des resources.properties
        file = new File(repository, "resources.properties");
        file.createNewFile();
        stream = new FileInputStream(file);
        prop = new Properties();
        prop.load(stream);
        prop.putAll(resources);
        oStream = new FileOutputStream(file);
        prop.store(oStream, "test");
        
        
    }
    
    public static Properties getMessages(){
        return messages;
    } 

    /**
     *
     * @param classe
     * @return
     */
    private static boolean isBaseType(Class<?> classe) {

        if (classe.equals(String.class) || classe.equals(Integer.class) || classe.equals(int.class) || classe.equals(short.class)
                || classe.equals(Short.class) || classe.equals(Long.class) || classe.equals(long.class) || classe.equals(byte.class)
                || classe.equals(Byte.class) || classe.equals(Double.class) || classe.equals(double.class) || classe.equals(float.class)
                || classe.equals(Float.class) || classe.equals(BigDecimal.class) || classe.equals(Date.class) || classe.equals(java.sql.Date.class)
                || classe.equals(Boolean.class) || classe.equals(boolean.class)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isCollectionType(Class<?> classe) {

        if (classe.equals(Collection.class) || classe.equals(List.class) || classe.equals(ArrayList.class)
                || classe.equals(Set.class) || classe.equals(Map.class) || classe.equals(HashMap.class)) {
            return true;
        } else {
            return false;
        }
    }

    private static String fillString(String value) {

        if (value.length() >= 28) {
            return value;
        }
        StringBuffer buffer = new StringBuffer();
        int nbre = 28 - value.length();
        for (int i = 0; i < nbre / 2; i++) {
            buffer.append(" ");
        }
        buffer.append(value);
        for (int i = 0; i < nbre / 2; i++) {
            buffer.append(" ");
        }
        nbre = 28 - buffer.toString().length();
        for (int i = 0; i < nbre - 1; i++) {
            buffer.append(" ");
        }
        //buffer.append(".");
        return buffer.toString();

    }

    /**
     * 
     * @param outputDirectory
     * @param modelClass
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static void buildDaoClassTest(File outputDirectory, Class<?> modelClass) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {
        JCodeModel jCodeModel = new JCodeModel();

        JPackage cl = jCodeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.DAO_TEST));

        JDefinedClass classe = cl._class(modelClass.getSimpleName() + "DaoTest");

        classe.javadoc().add(classe.name());

        JFieldVar personneDao = classe.field(JMod.PRIVATE | JMod.STATIC, jCodeModel.ref(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE) + "." + modelClass.getSimpleName() + "DAO"), "dao");
        JFieldVar ioc = classe.field(JMod.PRIVATE | JMod.STATIC, com.bekosoftware.genericdaolayer.contexts.IocContext.class, "stub");
        ioc.javadoc().add("initialisation du conteneur");
        JMethod beforeClass = classe.method(JMod.PUBLIC | JMod.STATIC, jCodeModel.VOID, "initialise");
        beforeClass.annotate(BeforeClass.class);
        beforeClass._throws(Exception.class);
        beforeClass.javadoc().add("@throws java.lang.Exception");
        beforeClass.body().block().add(jCodeModel.ref(AnnotationsProcessor.class).staticInvoke("setTest").arg(JExpr.TRUE));
        beforeClass.body().assign(JExpr.ref("stub"), JExpr._new(ioc.type()));
        beforeClass.body().assign(JExpr.ref("dao"), JExpr.cast(jCodeModel.ref(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE) + "." + modelClass.getSimpleName() + "DAO"), JExpr.ref("stub").invoke("lookup").arg(jCodeModel.ref(buildPackage(modelClass, ModuleLevel.DAO_IMPLEMENTATION) + "." + modelClass.getSimpleName() + "DAOImpl").fullName())));

        JMethod afterClass = classe.method(JMod.PUBLIC | JMod.STATIC, jCodeModel.VOID, "finalise");
        afterClass.annotate(AfterClass.class);
        afterClass._throws(Exception.class);
        afterClass.javadoc().add("@throws java.lang.Exception");

        JMethod before = classe.method(JMod.PUBLIC, jCodeModel.VOID, "before");
        before.annotate(Before.class);
        before._throws(Exception.class);
        before.javadoc().add("@throws java.lang.Exception");

        JMethod after = classe.method(JMod.PUBLIC, jCodeModel.VOID, "after");
        after.annotate(After.class);
        after._throws(Exception.class);
        after.javadoc().add("@throws java.lang.Exception");

        JMethod add = classe.method(JMod.PUBLIC, jCodeModel.VOID, "test");
        add.annotate(Test.class);
        add._throws(Exception.class);
        add.javadoc().add("@throws java.lang.Exception");
//        add.body().assign(JExpr.ref("personneDao"), JExpr.cast(personneDao.type(), JExpr.ref("stub").invoke("lookup").arg(PersonneDao.SERVICE_NAME)));
        //add.body().decl(JMod.NONE, Personne.class., "personn");
        jCodeModel.build(outputDirectory);
    }

    /**
     * 
     * @param outputDirectory
     * @param modelClass
     * @param spec
     * @param unitName
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static void buildManagerClassTest(File outputDirectory, Class<?> modelClass , String spec,String unitName) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {
        JCodeModel jCodeModel = new JCodeModel();

        JPackage cl = jCodeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.MANAGED_TEST));

        JDefinedClass classe = cl._class(modelClass.getSimpleName() + "ManagerTest");

        classe.javadoc().add(classe.name());

        JFieldVar manager = null ;
        JFieldVar ioc = null;
        if(spec.equalsIgnoreCase("none")){
           manager = classe.field(JMod.PRIVATE | JMod.STATIC, GenericManager.class, "manager");
          ioc = classe.field(JMod.PRIVATE | JMod.STATIC, IocContext.class, "stub");
          ioc.javadoc().add("initialisation du conteneur");
        }else if(spec.equalsIgnoreCase("ejb")){
            manager = classe.field(JMod.PRIVATE | JMod.STATIC, jCodeModel.ref(buildPackage(modelClass, ModuleLevel.MANAGER_INTERFACE) + "." + modelClass.getSimpleName() + "Manager"), "manager");
        }
        
        JMethod beforeClass = classe.method(JMod.PUBLIC | JMod.STATIC, jCodeModel.VOID, "initialise");
        beforeClass.annotate(BeforeClass.class);
        beforeClass._throws(Exception.class);
        beforeClass.javadoc().add("@throws java.lang.Exception");
        if(ioc!=null){
            beforeClass.body().assign(JExpr.ref("stub"), JExpr._new(ioc.type()));
            beforeClass.body().assign(JExpr.ref("manager"), JExpr.cast(jCodeModel.ref(GenericManager.class.getName()), JExpr.ref("stub").invoke("lookup").arg(jCodeModel.ref(buildPackage(modelClass, ModuleLevel.MANAGER_INTERFACE) + "." + modelClass.getSimpleName() + "Manager").staticRef("SERVICE_NAME"))));
        }else{
             beforeClass.body().staticInvoke(jCodeModel.ref(ServiceLocator.class), "setDataSource").arg(unitName+"_ds");
             //JClass managerClass = jCodeModel.ref(buildPackage(modelClass, ModuleLevel.MANAGER_INTERFACE) + "." + modelClass.getSimpleName() + "Manager");
             beforeClass.body().assign(JExpr.ref("manager"), JExpr.cast(jCodeModel.ref(buildPackage(modelClass, ModuleLevel.MANAGER_INTERFACE) + "." + modelClass.getSimpleName() + "Manager"), jCodeModel.ref(ServiceLocator.class.getName()).staticInvoke("lookup").arg(jCodeModel.ref(buildPackage(modelClass, ModuleLevel.MANAGER_INTERFACE) + "." + modelClass.getSimpleName() + "Manager").staticRef("SERVICE_NAME"))));
        }
        JMethod afterClass = classe.method(JMod.PUBLIC | JMod.STATIC, jCodeModel.VOID, "finalise");
        afterClass.annotate(AfterClass.class);
        afterClass._throws(Exception.class);
        afterClass.javadoc().add("@throws java.lang.Exception");

        JMethod before = classe.method(JMod.PUBLIC, jCodeModel.VOID, "before");
        before.annotate(Before.class);
        before._throws(Exception.class);
        before.javadoc().add("@throws java.lang.Exception");

        JMethod after = classe.method(JMod.PUBLIC, jCodeModel.VOID, "after");
        after.annotate(After.class);
        after._throws(Exception.class);
        after.javadoc().add("@throws java.lang.Exception");

        JMethod add = classe.method(JMod.PUBLIC, jCodeModel.VOID, "test");
        add.annotate(Test.class);
        add._throws(Exception.class);
        add.javadoc().add("@throws java.lang.Exception");
//        add.body().assign(JExpr.ref("personneDao"), JExpr.cast(personneDao.type(), JExpr.ref("stub").invoke("lookup").arg(PersonneDao.SERVICE_NAME)));
        //add.body().decl(JMod.NONE, Personne.class., "personn");
        jCodeModel.build(outputDirectory);
    }
    
    /**
     * 
     * @param outputDirectory
     * @param modelClass
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static void buildRestfulClassTest(File outputDirectory, Class<?> modelClass) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {
        JCodeModel jCodeModel = new JCodeModel();

        JPackage cl = jCodeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.JAX_RS_TEST));

        JDefinedClass classe = cl._class(modelClass.getSimpleName() + "RSTest");
        //classe.annotate(org.apache.openejb.junit.)
        classe.javadoc().add(classe.name());

        JFieldVar personneDao = classe.field(JMod.PRIVATE | JMod.STATIC, jCodeModel.ref(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE) + "." + modelClass.getSimpleName() + "DAO"), "dao");
        JFieldVar ioc = classe.field(JMod.PRIVATE | JMod.STATIC, com.bekosoftware.genericdaolayer.contexts.IocContext.class, "stub");
        ioc.javadoc().add("initialisation du conteneur");
        JMethod beforeClass = classe.method(JMod.PUBLIC | JMod.STATIC, jCodeModel.VOID, "initialise");
        beforeClass.annotate(BeforeClass.class);
        beforeClass._throws(Exception.class);
        beforeClass.javadoc().add("@throws java.lang.Exception");
        beforeClass.body().block().add(jCodeModel.ref(AnnotationsProcessor.class).staticInvoke("setTest").arg(JExpr.TRUE));
        beforeClass.body().assign(JExpr.ref("stub"), JExpr._new(ioc.type()));
        beforeClass.body().assign(JExpr.ref("dao"), JExpr.cast(jCodeModel.ref(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE) + "." + modelClass.getSimpleName() + "DAO"), JExpr.ref("stub").invoke("lookup").arg(jCodeModel.ref(buildPackage(modelClass, ModuleLevel.DAO_IMPLEMENTATION) + "." + modelClass.getSimpleName() + "DAOImpl").fullName())));

        JMethod afterClass = classe.method(JMod.PUBLIC | JMod.STATIC, jCodeModel.VOID, "finalise");
        afterClass.annotate(AfterClass.class);
        afterClass._throws(Exception.class);
        afterClass.javadoc().add("@throws java.lang.Exception");

        JMethod before = classe.method(JMod.PUBLIC, jCodeModel.VOID, "before");
        before.annotate(Before.class);
        before._throws(Exception.class);
        before.javadoc().add("@throws java.lang.Exception");

        JMethod after = classe.method(JMod.PUBLIC, jCodeModel.VOID, "after");
        after.annotate(After.class);
        after._throws(Exception.class);
        after.javadoc().add("@throws java.lang.Exception");

        JMethod add = classe.method(JMod.PUBLIC, jCodeModel.VOID, "test");
        add.annotate(Test.class);
        add._throws(Exception.class);
        add.javadoc().add("@throws java.lang.Exception");
//        add.body().assign(JExpr.ref("personneDao"), JExpr.cast(personneDao.type(), JExpr.ref("stub").invoke("lookup").arg(PersonneDao.SERVICE_NAME)));
        //add.body().decl(JMod.NONE, Personne.class., "personn");
        jCodeModel.build(outputDirectory);
    }

    /**
     * 
     * @param outputDirectory
     * @param modelClass
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static void buildEJBManagerClassTest(File outputDirectory, Class<?> modelClass) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        JCodeModel jCodeModel = new JCodeModel();
        JPackage cl = jCodeModel.rootPackage();
        cl = cl.subPackage(buildPackage(modelClass, ModuleLevel.MANAGED_TEST));

        JDefinedClass classe = cl._class(modelClass.getSimpleName() + "ManagerTest");
        classe._extends(DAOClassLoader.class);

        classe.javadoc().add(classe.name());

        JFieldVar dao = classe.field(JMod.NONE, jCodeModel.ref(buildPackage(modelClass, ModuleLevel.DAO_INTERFACE) + "." + modelClass.getSimpleName() + "DAO"), "dao");
        dao.annotate(InjectDAO.class).param("unitName", "").param("ClassName", JExpr.direct("\"" + buildPackage(modelClass, ModuleLevel.DAO_IMPLEMENTATION) + "." + modelClass.getSimpleName() + "DAOImpl\""));

        JMethod beforeClass = classe.method(JMod.PUBLIC, jCodeModel.VOID, "initialise");
        beforeClass.annotate(BeforeClass.class);
        beforeClass._throws(Exception.class);
        beforeClass.javadoc().add("@throws java.lang.Exception");
        beforeClass.body().staticInvoke(jCodeModel.ref(DAOClassLoader.class), "initialise").arg(JExpr._this());

        //beforeClass.body().assign(JExpr.ref("manager"), JExpr.cast(jCodeModel.ref(GenericManager.class.getName()), JExpr.ref("stub").invoke("lookup").arg(jCodeModel.ref(buildPackage(modelClass, ModuleLevel.MANAGER_INTERFACE) + "." + modelClass.getSimpleName() + "Manager").staticRef("SERVICE_NAME"))));
        JMethod afterClass = classe.method(JMod.PUBLIC, jCodeModel.VOID, "finalise");
        afterClass.annotate(AfterClass.class);
        afterClass._throws(Exception.class);
        afterClass.javadoc().add("@throws java.lang.Exception");
        afterClass.body().staticInvoke(jCodeModel.ref(DAOClassLoader.class), "close");

        JMethod before = classe.method(JMod.PUBLIC, jCodeModel.VOID, "before");
        before.annotate(Before.class);
        before._throws(Exception.class);
        before.javadoc().add("@throws java.lang.Exception");

        JMethod after = classe.method(JMod.PUBLIC, jCodeModel.VOID, "after");
        after.annotate(After.class);
        after._throws(Exception.class);
        after.javadoc().add("@throws java.lang.Exception");

        JMethod add = classe.method(JMod.PUBLIC, jCodeModel.VOID, "test");
        add.annotate(Test.class);
        add._throws(Exception.class);
        add.javadoc().add("@throws java.lang.Exception");
//        add.body().assign(JExpr.ref("personneDao"), JExpr.cast(personneDao.type(), JExpr.ref("stub").invoke("lookup").arg(PersonneDao.SERVICE_NAME)));
        //add.body().decl(JMod.NONE, Personne.class., "personn");
        jCodeModel.build(outputDirectory);
    }

}
