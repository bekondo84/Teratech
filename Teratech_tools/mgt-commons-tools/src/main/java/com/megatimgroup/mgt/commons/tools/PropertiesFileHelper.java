/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.mgt.commons.tools;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import com.sun.org.apache.xml.internal.utils.DOMBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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
