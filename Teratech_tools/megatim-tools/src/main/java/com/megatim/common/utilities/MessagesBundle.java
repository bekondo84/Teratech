/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.utilities;

import java.util.ResourceBundle;


/**
 *
 * @author DEV_4
 */
public class MessagesBundle {
    
    /**
     * Gestionnaire de message
     */
    private static ResourceBundle bundle =null ;
    
    private static String fileName = "com/megatim/tools/messages";   
    
   
    
    /**
     * Constructeur par defaut
     */
    private MessagesBundle(){
        //MessagesBundle.fileName = fielName;
        if(bundle==null)
            bundle = ResourceBundle.getBundle(fileName);
        //System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::  "+fileName+"  :::: "+bundle);
        
    }

    /**
     * 
     * @param fileName 
     */
    public static void setFileName(String fileName) {
        MessagesBundle.fileName = fileName;
    }

    /**
     * 
     * @param bundle 
     */
    public static void setBundle(ResourceBundle bundle) {
        MessagesBundle.bundle = bundle;
    }

     
    
    
    /**
     * Renvoi le message correspondant a une cle
     * @param key
     * @return 
     */
    public static String getMessage(String key){
       
      try{  
        if(bundle== null)
                new MessagesBundle();  
        
        return bundle.getString(key);
      }catch(Exception ex){
          return key ;
      }
    }
    
    public static synchronized  ResourceBundle getInstace(){
        
        if(bundle== null)
                new MessagesBundle();  
        return bundle;
    }
    
     public static synchronized  ResourceBundle getInstace(String fileName){
        
         MessagesBundle.fileName = fileName;
         
        if(bundle== null)
                new MessagesBundle();  
        return bundle;
    }
}
