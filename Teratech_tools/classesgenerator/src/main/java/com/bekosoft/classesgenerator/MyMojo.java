package com.bekosoft.classesgenerator;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.megatim.common.clients.ActionDetail;
import com.megatim.common.clients.ActionGroup;
import com.megatim.common.clients.CommonsUtilities;
import com.megatim.common.jaxb.entities.Element;
import com.megatim.common.jaxb.entities.Elements;
import com.megatim.common.jaxb.entities.Exclures;
import com.megatim.common.jaxb.entities.Groupes;
import com.megatim.common.jaxb.entities.JaxBModelFileReader;
import com.megatim.common.jaxb.entities.Principalscreen;
import com.megatim.common.jaxb.entities.Relations;
import com.megatim.common.utilities.SourceGenerator;
import com.sun.codemodel.JClassAlreadyExistsException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Goal which touches a timestamp file.
 *
 * @goal generate
 *
 * @phase process-classes
 */
@Mojo(name = "sourcegenerator")
public class MyMojo
        extends AbstractMojo {

    /**
     * Location of the file.
     *
     * @parameter expression="${project.build.directory}"
     * @required
     */
    @Parameter
    private File outputDirectory;

    /**
     * Location of the file.
     *
     * @parameter default-value="${project.basedir}"
     */
    private File outputTestDirectory;
    /**
     * Location of the file.
     *
     * @parameter
     * @required
     */
    @Parameter(property = "sourcegenerator.layer", defaultValue = "dao")
    private String layer;

    /**
     * ejb | jax-rest | none Location of the file.
     *
     * @parameter
     *
     */
    @Parameter(property = "sourcegenerator.layer", defaultValue = "dao")
    private String specification;

    /**
     * Location of the file.
     *
     * @parameter
     *
     */
    @Parameter(property = "sourcegenerator.sublayer", defaultValue = "interfaces")
    private String sublayer;

    /**
     * Location of output Directory Main Class.
     *
     * @parameter
     */
    @Parameter
    private String outputDirectoryMainClass;

    /**
     * main Class Name.
     *
     * @parameter
     */
    @Parameter
    private String mainClassName;

    /**
     * package Main Class.
     *
     * @parameter
     */
    @Parameter
    private String packageMainClass;

    /**
     * Location of the file.
     *
     * @parameter expression="${project}"
     * @required
     */
    private MavenProject project;

    /**
     * The classpath elements of the project.
     *
     * @parameter expression="${project.runtimeClasspathElements}"
     * @required
     * @readonly
     */
    private List<String> classpathElements;

    /**
     * Location of the file.
     *
     * @parameter
     *
     */
    private File xmlfile;
    /**
     * Location of the file.
     *
     * @parameter
     */
    private File xmlframe;

    /**
     * Location of the file.
     *
     * @parameter
     *
     */
    private String persistanceunitname;

    /**
     * Location of the file.
     *
     * @parameter
     *
     */
    private String session;
    
    /**
     * Location of the file.
     *
     * @parameter
     *
     */
    private String application;
    
    /**
     * Location of the file.
     *
     * @parameter
     *
     */
    private String module;

    /**
     * Location of the file.
     *
     * @parameter
     * @defaultValue=false
     */
    private Boolean deseable;

    private List<ActionGroup> groupes = new ArrayList<ActionGroup>();

    /**
     *
     * @return @throws MalformedURLException
     */
    private ClassLoader getProjectClassLoader()
            throws MalformedURLException {
        List<String> classPath = new ArrayList<String>();
        classPath.addAll(classpathElements);
        classPath.add(project.getBuild().getOutputDirectory());
        getLog().info(classPath.toString());
        URL[] urls = new URL[classPath.size()];
        int i = 0;
        for (String entry : classPath) {
            getLog().info("use classPath entry " + entry);
            urls[i] = new File(entry).toURI().toURL();
            i++; // Important
        }
        return new URLClassLoader(urls);
    }

    /**
     *
     * @param modelClass
     * @param idClass
     * @param idName
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void classGenerator(Class<?> modelClass, Class<?> idClass, String idName, boolean test) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        if (layer == null || sublayer == null || xmlfile == null) {
            return;
        }

        if (deseable != null && deseable) {
            return;
        }

        if (layer.equalsIgnoreCase("dao")) {

            if (sublayer.equalsIgnoreCase("interface")) {
                SourceGenerator.buildDAOInterface(outputDirectory, modelClass, idClass);
            } else if (sublayer.equalsIgnoreCase("implementation")) {
                SourceGenerator.buildDAOImplementationClass(outputDirectory, modelClass, idClass, persistanceunitname);
                if (test) {
                    if (!outputTestDirectory.exists()) {
                        outputTestDirectory.mkdirs();
                    } else {
                        SourceGenerator.buildDaoClassTest(outputTestDirectory, modelClass);
                        //SourceGenerator.buildEJBManagerClassTest(outputTestDirectory, modelClass);
                    }
                }
            }

        } else if (layer.equalsIgnoreCase("manager")) {

            if (sublayer.equalsIgnoreCase("interface")) {
                SourceGenerator.buildManagerInterface(outputDirectory, modelClass, idClass,specification);
            } else if (sublayer.equalsIgnoreCase("implementation")) {
                SourceGenerator.buildManagerImplementationClass(outputDirectory, modelClass, idClass, idName);
                if (test) {
                    if (!outputTestDirectory.exists()) {
                        outputTestDirectory.mkdirs();
                    } else {
                        //SourceGenerator.buildManagerClassTest(outputTestDirectory, modelClass);
                        SourceGenerator.buildEJBManagerClassTest(outputTestDirectory, modelClass);

                    }
                }
            }

        }
    }

    /**
     * Generateur de Web Services RestFul
     * @param modelClass
     * @param idClass
     * @throws JClassAlreadyExistsException
     * @throws IOException 
     */
    public void jax_rsGenerator(Class<?> modelClass, Class<?> idClass) throws JClassAlreadyExistsException, IOException{
        
        if (layer == null || sublayer == null || xmlfile == null) {
            return;
        }

        if (deseable != null && deseable) {
            return;
        }
        
         if (layer.equalsIgnoreCase("jax-rs")) {
            //This section concerned EJB
            if(sublayer.equalsIgnoreCase("interface")){
                SourceGenerator.buildRestInterface(outputDirectory, modelClass, idClass);
            }else if(sublayer.equalsIgnoreCase("implementation")){
                SourceGenerator.buildRestImplementationClass(outputDirectory, modelClass, idClass,application,module);
            }
        }
    }
    
    /**
     * 
     * @param modelClass
     * @param idClass
     * @param idName
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private void classGeneratorEJB(Class<?> modelClass, Class<?> idClass, String idName,boolean test) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        if (layer == null || sublayer == null || xmlfile == null) {
            return;
        }
        //Skip if this step is deseable
        if (deseable != null && deseable) {
            return;
        }

        if (layer.equalsIgnoreCase("dao")) {

            if (sublayer.equalsIgnoreCase("interface")) {
                SourceGenerator.buildDAOInterface(outputDirectory, modelClass, idClass);
                SourceGenerator.buildDAOInterfaceEJBLocal(outputDirectory, modelClass, idClass);
                SourceGenerator.buildDAOInterfaceEJBRemote(outputDirectory, modelClass, idClass);
            } else if (sublayer.equalsIgnoreCase("implementation")) {
                SourceGenerator.buildDAOImplementationClassEJBStateless(outputDirectory, modelClass, idClass, persistanceunitname);        
                
                //Generation des classes de Test
                if(test){

                    if(outputTestDirectory==null){
                        throw new IOException("Aucun repertoire de test n'est  défini");
                    }
                    SourceGenerator.buildDaoClassTest(outputTestDirectory, modelClass);
                }
            }
            
            

        } else if (layer.equalsIgnoreCase("manager")) {

            if (sublayer.equalsIgnoreCase("interface")) {
                SourceGenerator.buildManagerInterface(outputDirectory, modelClass, idClass,specification);
                SourceGenerator.buildManagerInterfaceEJBLocal(outputDirectory, modelClass, idClass);
                SourceGenerator.buildManagerInterfaceEJBRemote(outputDirectory, modelClass, idClass);
            } else if (sublayer.equalsIgnoreCase("implementation")) {

                if (session == null || session.equalsIgnoreCase("stateless")) {
                    SourceGenerator.buildManagerImplementationClassStateless(outputDirectory, modelClass, idClass, idName);
                } else if (session.equalsIgnoreCase("stateful")) {
                    SourceGenerator.buildManagerImplementationClassStateless(outputDirectory, modelClass, idClass, idName);
                }
                
                  //Generation des classes de Test
                    if(test){

                        if(outputTestDirectory==null){
                            throw new IOException("Aucun repertoire de test n'est  défini");
                        }
                        SourceGenerator.buildManagerClassTest(outputTestDirectory, modelClass,specification,persistanceunitname);
                    }
            }
            
           

        }
    }

    /**
     *
     * @param modelClass
     * @param idClass
     * @param idName
     * @param internal
     * @throws JClassAlreadyExistsException
     * @throws IOException
     */
    private void ihmGenerator(Class<?> modelClass,Class<?> modelClassV,Class<?> modelClassR
            , Class<?> idClass, String idName,String prefix, boolean internal, boolean reporting, Groupes groupes, Exclures exclures, Relations relation) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        if (layer == null || sublayer == null || xmlfile == null) {
            return;
        }

        if (deseable != null && deseable) {
            return;
        }

        if (layer.equalsIgnoreCase("swing")) {

            if (sublayer.equalsIgnoreCase("none") || sublayer == null) {
                if(modelClassV==null&&modelClassR==null){
                    SourceGenerator.buildEditDialog(outputDirectory, modelClass, idClass, idName, internal);
                    SourceGenerator.buildEditEditPanel(outputDirectory, modelClass, idClass, idName, groupes, exclures, relation);
                    SourceGenerator.buildCriteriaPanel(outputDirectory, modelClass, idName, exclures);
                    SourceGenerator.buildInternalFrame(outputDirectory, modelClass, idClass, idName, reporting);
                    SourceGenerator.buildModel(outputDirectory, modelClass, idClass, exclures, relation);                
                    //SourceGenerator.buildInternalFrame(outputDirectory, modelClass, idClass, idName, reporting);                   
                }else {
                   if(modelClassV.equals(modelClassR)){
                       SourceGenerator.buildTransfertModel(outputDirectory, modelClass, idClass, exclures, relation);
                       SourceGenerator.buildInternalFrame(outputDirectory, modelClass, modelClassV, idClass, idName,prefix, reporting); 
                       SourceGenerator.buildTransfertDialog(outputDirectory, modelClassV, idClass, idName, false);
                   }else{
                       //System.out.println("ihmGenerator(Class<?> modelClass,Class<?> modelClassV,Class<?> modelClassR ::::::::::::::::::  "+modelClassV+" :::: "+modelClassR);
                   
                       SourceGenerator.buildInternalFrame(outputDirectory, modelClass, modelClassV, modelClassR,idClass, idName, prefix, reporting);
                   }
                }     
                
            } else if (sublayer.equalsIgnoreCase("remote")) {
                ;
            }

        }
    }

    /**
     * 
     * @param modelClass
     * @param modelClassV
     * @param modelClassR
     * @param idClass
     * @param idName
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private void jsf2_2Generator(Class<?> modelClass, Class<?> idClass, String idName) throws JClassAlreadyExistsException, IOException, ClassNotFoundException{
        
         if (layer == null || sublayer == null || xmlfile == null) {
            return;
        }

        if (deseable != null && deseable) {
            return;
        }
        
        if(layer.equalsIgnoreCase("jsf")){
            //Construction de la couche ManagedBean
            SourceGenerator.buildJSFManagedBean(outputDirectory, modelClass, idClass, idName);
        }

    }
    /**
     * Generation des fichiers messages.properties et resources.properties
     * @param elements
     * @param loader
     * @throws IOException 
     */
    private void propertiesFilesGenerator(Elements elements,ClassLoader loader) throws IOException {

        if (deseable != null && deseable) {
            return;
        }
        
        for (Element elt : elements.getElement()) {
            
            //if(!elt.getType().equalsIgnoreCase("com.gepa.model.budget.AccreditationBudgetaire") && !elt.getType().equalsIgnoreCase("com.gepa.model.referentiels.NewClass")){
            try {
                Class<?> classModel = loader.loadClass(elt.getType());
                
                //Titre au niveau de la Boite d'edition   
                SourceGenerator.getMessages().put(classModel.getSimpleName().toLowerCase() + ".edit", SourceGenerator.getTitleDialogForEntity(classModel).toUpperCase());
                
                //Titre au niveau fenetre liste
                SourceGenerator.getMessages().put(classModel.getSimpleName().toLowerCase() + ".list", SourceGenerator.getTitleListeForEntity(classModel).toUpperCase());
                
                //Titre rapport
                //messages.put(modelClass.getSimpleName().toLowerCase() + ".title.report", "");
                //messages.put(modelClass.getSimpleName().toLowerCase() + ".footer.report", "");
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MyMojo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        //}
        SourceGenerator.createMessagesAndResources(outputDirectory);
    }

    /**
     * 
     * @param classModel
     * @param name
     * @param groupes
     * @param mainClassName
     * @param packageMainClass
     * @param outputDirectoryMainClass
     * @throws JClassAlreadyExistsException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private void principalScreenGenerator(Class<?> classModel, String name, List<ActionGroup> groupes, String mainClassName, String packageMainClass, String outputDirectoryMainClass)
            throws JClassAlreadyExistsException, IOException, ClassNotFoundException {

        if (deseable != null && deseable) {
            return;
        }
        SourceGenerator.buildPrincipalScreen(outputDirectory, classModel, name, groupes, mainClassName, packageMainClass, outputDirectoryMainClass);
    }

    /**
     *
     * @param className
     * @return
     */
    private Class<?> classConverter(String className) {

        if (className.equalsIgnoreCase("string")) {
            return String.class;
        } else if (className.equalsIgnoreCase("int") || className.equalsIgnoreCase("integer")) {
            return Integer.class;
        } else if (className.equalsIgnoreCase("long")) {
            return Long.class;
        } else {
            return String.class;
        }

    }

    /**
     *
     * @throws MojoExecutionException
     */
    @SuppressWarnings("empty-statement")
    public void execute()
            throws MojoExecutionException {
        try {
            ClassLoader loader = getProjectClassLoader();

            Elements elements = new Elements();

            if (xmlfile != null) {
                elements = JaxBModelFileReader.readConfigFromFile(xmlfile);
            }

            Principalscreen screen = null;

            if (xmlframe != null) {
                screen = JaxBModelFileReader.readScreenFromFile(xmlframe);
                groupes = CommonsUtilities.convert(screen);
            }

            for (Element elt : elements.getElement()) {

                //Sauter l'etape si desactive
                if (elt.isDeseable()) {
                    continue;
                }

                Class<?> classModel = loader.loadClass(elt.getType());
                Class<?> classModelV = null;
                Class<?> classModelR = null;
                if(elt.getValidateType()!=null&&!elt.getValidateType().trim().isEmpty()){
                    classModelV = loader.loadClass(elt.getValidateType());
                }
                if(elt.getRejectType()!=null&&!elt.getRejectType().trim().isEmpty()){
                    classModelR = loader.loadClass(elt.getRejectType());
                }
                //getLog().info("IT work ooo ::::::::::::::::::::::::::::::::::::  Repertoire Cible "+classModelR+" ::::: couche : "+classModelV);

                if (specification == null || specification.equalsIgnoreCase("none")) {
                    if(classModelR==null||classModelV==null){
                        classGenerator(classModel, classConverter(elt.getIdclass()), elt.getIdname(), elt.isTest());
                    }
                    ihmGenerator(classModel,classModelV,classModelR, classConverter(elt.getIdclass()), elt.getIdname(),elt.getPrefix(), elt.isInternal(), elt.isReporting(), elt.getGroupes(), elt.getExclures(), elt.getRelations());
                } else if (specification.equalsIgnoreCase("ejb")) {
                    classGeneratorEJB(classModel, classConverter(elt.getIdclass()), elt.getIdname(),elt.isTest());
                } else if (specification.equalsIgnoreCase("jax-rs")){//Generation des web services selon la specification 
                    jax_rsGenerator(classModel, classConverter(elt.getIdclass()));
                } else if (specification.equalsIgnoreCase("jax-ws")) {//Generation des web services selon la specification 
                    ;
                }else if(specification.equalsIgnoreCase("jsf")){//Generation des Managed beans et des 
                    jsf2_2Generator(classModel, classConverter(elt.getIdclass()), elt.getIdname());
                }

            }

            if (groupes != null && !groupes.isEmpty()) {
                Class<?> classModel = null;
                for (ActionGroup group : groupes) {

                    for (ActionDetail action : group.getActions()) {
                      if(!action.isSeparator()&&!action.isLiteral()){  
                        classModel = loader.loadClass(action.getViewClass());
                        action.setClassType(classModel);
                      }
                    }

                    for (ActionGroup groupe : group.getGroupes()) {
                        for (ActionDetail action : groupe.getActions()) {
                          if(!action.isSeparator()&&!action.isLiteral()) {
                            classModel = loader.loadClass(action.getViewClass());
                            action.setClassType(classModel);
                          }
                        }
                    }
                }

                //Generation de la classe principal
                if (classModel != null) {
                    System.out.println("HIHIIHIHH");
                    principalScreenGenerator(classModel, screen.getName(), groupes, mainClassName, packageMainClass, outputDirectoryMainClass);
                }
            }
            //Gestion des resources
            if (layer.equalsIgnoreCase("other")) {
                propertiesFilesGenerator(elements,loader);
            }
        } catch (Exception ex) {
            Logger.getLogger(MyMojo.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println(ex.getMessage());
        }

    }
}