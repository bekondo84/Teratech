/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author user
 */
public class PropertiesFileHelper {
    
    public static Properties loadPropertiesFile(String path) {
        
        InputStream input = null ;
        
        Properties props = null;
           
        try {
             
        	props = new Properties();
        	
            input = new FileInputStream(path);
            
            props.load(input);
            return props;
        } catch (IOException ex) {
            //ex.printStackTrace();
            //return null;
        }finally{
            if(input!=null){
                try{
                    input.close();
                }catch(final Exception ex){
                    //ex.printStackTrace();
                }
            }
        }
        return props;
    }
    
    
}
