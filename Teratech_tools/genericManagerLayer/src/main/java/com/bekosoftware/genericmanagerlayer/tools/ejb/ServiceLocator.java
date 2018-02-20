/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericmanagerlayer.tools.ejb;

import com.bekosoftware.genericmanagerlayer.exceptions.ServiceLocationException;
import com.bekosoftware.genericmanagerlayer.tools.annotations.ServiceLocationType;
import com.megatimgroup.mgt.commons.tools.PropertiesFileHelper;
import java.io.File;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *Utilitaire pour la localisation des service JNDI pour EJB 3.0 
 * et pour EJB Lite
 * @author BEKONDO Kangue Dieunedort <bekondo_dieu@yahoo.fr>
 */
public class ServiceLocator {
    
    /**
     * Systeme de localisation
     */
    public static ServiceLocationType system =ServiceLocationType.EJB_LITE;
    /**
     * Context pour la localisation
     */
    protected static InitialContext ctx;
    
    private static String dataSource ;
    
    /**
     * Instance du conteneur leger
     */
    //protected static EJBC
    
    
    /**
     * Initialisation de context JNDI à partir du conteneur enbarqué 
     * pour les EJB LITE
     * @throws Exception 
     */
    public static synchronized void jndi_InitContextWithPropertiesFile() {
        
        try{
            if(ctx == null) {
                //Chargement du fichier properties
                Properties properties = null ;
                // Initialisation du contexte
                ctx = new InitialContext();
            }
        }catch (NamingException e) {
	    
            throw new ServiceLocationException("servicelocator.jndi_initcontext.naming", e);
        }catch(Exception e) {

                throw new ServiceLocationException("servicelocator.jndi_initcontext.error", e);
        }
        
    }
     /**
     * Initialisation de context JNDI à partir du fichier properties
     * situe dans le repertoire des fichier
     * 4
     * 
     * @throws Exception 
     */
    public static synchronized void jndi_InitContext() {
        
        try{
            if(ctx == null) {

                // Initialisation du contexte
                ctx = new InitialContext();
            }
        }catch (NamingException e) {
	    
            throw new ServiceLocationException("servicelocator.jndi_initcontext.naming", e);
        }catch(Exception e) {

               
        }
        
    }
    
     /**
     * Initialisation de context embedded EJB
     * situe dans le repertoire des fichier
     * 4
     * 
     * @throws Exception 
     */
    
    public static synchronized void emb_InitContext() {
        
        try{
            Properties properties = new Properties();
		
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.openejb.client.LocalInitialContextFactory");
            
            properties.put(dataSource, "new://Resource?type=DataSource");
            properties.put(dataSource+".JdbcDriver", "org.apache.derby.jdbc.EmbeddedDriver");
            //properties.put(dataSource+".hibernate.dialect", "org.hibernate.dialect.DerbyDialect");  //"org.hibernate.dialect.DerbyDialect"  "com.mysema.query.jpa.support.ExtendedDerbyDialect"      
            properties.put(dataSource+".JdbcUrl", "jdbc:derby:memory:JdbcOne;create=true");
            properties.put(dataSource+".JtaManaged", "true");
            //properties.put(dataSource+".openjpa.jdbc.DBDictionary", "derby(NextSequenceQuery=VALUES NEXT VALUE FOR {0})");
            //properties.put(dataSource+".openjpa.Log", "File=/tmp/org.apache.openjpa.log,DefaultLevel=WARN, Runtime=INFO, Tool=INFO, SQL=TRACE");
             //<property name="openjpa.jdbc.DBDictionary" value="derby(NextSequenceQuery=VALUES NEXT VALUE FOR {0})"/>
            //<property name="openjpa.Log" value="File=/tmp/org.apache.openjpa.log,DefaultLevel=WARN, Runtime=INFO, Tool=INFO, SQL=TRACE"/>
            if(ctx == null) {
                // Initialisation du contexte
                ctx = new InitialContext(properties);
            }
        }catch(Exception e) {

               e.printStackTrace();
        }
        
    }
   
    /**
    ** Initialisation du context jndi
    * @throws Exception 
    * 
    */
    public static synchronized void initContext(){
        
        if(system==ServiceLocationType.EJB_LITE)
                      emb_InitContext();
        else if(system==ServiceLocationType.EJB_LOCAL)
                    jndi_InitContext();
        else if(system==ServiceLocationType.EJB_REMOTE)
                   jndi_InitContextWithPropertiesFile();
            
    }
    
    /**
    * Localisation d'un service distant à partir du nom jndi
     * @param serviceName
    * @return
    */
    public synchronized static Object lookup(String serviceName){
                       
		try {

                       /**
			 * Initialisation du context JDNI
			 */
			initContext();

			/**
			 * Localisation du composant distant à partir du nom de fichier 
			 */
			Object lookupObject = null ;                         
                         lookupObject =  ctx.lookup(buildJndiServiceName(serviceName));
                         //System.out.println("**********************************************************************************  "+buildJndiServiceName(serviceName));
			
			return lookupObject;

		} catch (Exception e) {

			/**
			 *  Logger exception warning
			 */
			//logger.warn("ServiceLocator.lookup(serviceName)", e);

		}

		return null;
    }
    /**
    * Localisation d'un service distant à partir du nom jndi 
    * @return
    */
    public synchronized static Object lookup(String moduleName , String serviceName){
        
                /**
		 *  Logger method trace debug
		 */
		//logger.trace("ServiceLocator.lookup(serviceName)");

		

		try {

			/**
			 * Initialisation du context JDNI
			 */
			initContext();

			/**
			 * Localisation du composant distant à partir du nom de fichier 
			 */
			Object lookupObject = null ;
                          
                          if(lookupObject!=null)
                                  ctx.lookup(buildJndiServiceName(moduleName , serviceName));

			/**
			 * Logger sur le nom du fichier
			 */
			//logger.trace(serviceName +  "=" + lookupObject);

			/**
			 * Retourne la valeur
			 */
			return lookupObject;

		} catch (Exception e) {

			/**
			 *  Logger exception warning
			 */
			//logger.warn("ServiceLocator.lookup(serviceName)", e);

		}

		return null;
    }
    /**
    * Localisation d'un service distant à partir du nom jndi 
    * @return
    */
    public synchronized static Object lookup(String appName , String moduleName , String serviceName){
        
                /**
		 *  Logger method trace debug
		 */
		//logger.trace("ServiceLocator.lookup(serviceName)");

		

		try {

			/**
			 * Initialisation du context JDNI
			 */
			initContext();

			/**
			 * Localisation du composant distant à partir du nom de fichier 
			 */
			Object lookupObject = null ;
                          
                          if(lookupObject!=null)
                                  ctx.lookup(buildJndiServiceName(appName , moduleName , serviceName));

			/**
			 * Logger sur le nom du fichier
			 */
			//logger.trace(serviceName +  "=" + lookupObject);

			/**
			 * Retourne la valeur
			 */
			return lookupObject;

		} catch (Exception e) {

			/**
			 *  Logger exception warning
			 */
			//logger.warn("ServiceLocator.lookup(serviceName)", e);

		}

		return null;
    }
        /**
	 * Construit le nom jndi du service à partir du nom du serivce
	 * @param serviceName
	 * @return
	 */
	public static String buildJndiServiceName(String serviceName) {

		/**
		 *  Logger method trace debug
		 */
		//logger.trace("ServiceLocator.buildJndiServiceName(serviceName)");

		return serviceName;

	}
        
        /**
	 * Construit le nom jndi du service à partir du nom du serivce
	 * @param serviceName
	 * @return
	 */
	public static String buildJndiServiceName(String moduleName , String serviceName) {

		/**
		 *  Logger method trace debug
		 */
		//logger.trace("ServiceLocator.buildJndiServiceName(serviceName)");

		return "java:global /"+serviceName+" / "+moduleName;

	}
         /**
	 * Construit le nom jndi du service à partir du nom du serivce
	 * @param serviceName
	 * @return
	 */
	public static String buildJndiServiceName(String appName , String moduleName , String serviceName) {

		/**
		 *  Logger method trace debug
		 */
		//logger.trace("ServiceLocator.buildJndiServiceName(serviceName)");

		return "java:global/"+appName+"/"+serviceName+"/ "+moduleName;

	}

        /**
         * 
         * @param dataSource 
         */
        public static void setDataSource(String dataSource) {
            ServiceLocator.dataSource = dataSource;
        }
        
        
}
