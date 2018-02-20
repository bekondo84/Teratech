/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.utilities;

import java.util.ResourceBundle;

/**
 *
 * @author DEV_4
 */
public class ResourcesBundle {
    
     /**
     * Gestionnaire de message
     */
    private static ResourceBundle bundle =null ;
    
    private static String fileName = "com/megatim/tools/messages";   

    private  ResourcesBundle() {
         //MessagesBundle.fileName = fielName;
        if(bundle==null)
            bundle = ResourceBundle.getBundle(fileName);
    }  
    
    
    
     /**
     * 
     * @param fileName 
     */
    public static void setFileName(String fileName) {
        ResourcesBundle.fileName = fileName;
    }

    /**
     * 
     * @param bundle 
     */
    public static void setBundle(ResourceBundle bundle) {
         ResourcesBundle.bundle = bundle;
    }

     
    
    
    /**
     * Renvoi le message correspondant a une cle
     * @param key
     * @return 
     */
    public static String getResource(String key){
       
      try{  
          //System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::  "+key+"  :::: "+bundle);
        if(bundle== null)
                new  ResourcesBundle();  
        //System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::  "+key+"  :::: "+bundle.getString(key));
        return bundle.getString(key);        
        
      }catch(Exception ex){
          return key ;
      }
    }
    
    public static synchronized  ResourceBundle getInstace(){
        
        if(bundle== null)
                new  ResourcesBundle();  
        return bundle;
    }
    
     public static synchronized  ResourceBundle getInstace(String fileName){
        
          ResourcesBundle.fileName = fileName;
         
        if(bundle== null)
                new  ResourcesBundle();  
        return bundle;
    }
}
