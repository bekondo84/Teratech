/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.security.manager.connection;

import com.megatim.security.cryptographie.ifaces.SecurityInterface;

/**
 *
 * @author user
 */
public class SecurityCenter {
    
    private static SecurityInterface encryptor = null;

    /**
     * 
     * @param encryptor 
     */
    public static void setEncryptor(SecurityInterface encryptor) {
        SecurityCenter.encryptor = encryptor;
    }
    
    /**
     * 
     * @return 
     */
    public static boolean isActive(){
        
        return encryptor != null ;
    }
    
    /**
     * 
     * @param data
     * @return 
     */
    public static String encrypte(String data){
        
        if(isActive())
            return encryptor.encryptText(data);
        return data ;
    }
    
    /**
     * 
     * @param data
     * @return 
     */
    public static String decrypte(String data){
         if(isActive())
            return encryptor.decryptText(data);
        return data ;
    }
    
    /**
     * 
     * @param key
     * @return 
     */
    public static boolean include(String key){       
       if(isActive())
            return encryptor.include(key);
        return false;
    }
    
}
