/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.os.utilities;

import com.developpez.adiguba.shell.Shell;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cette classe concerne tout ce qui est du domaine d'execution des scripts(.bat,.cmd,...)
 * @author Serge abega
 */
public class ScriptExecution {
   
    //Cette methode permet d'execution un script en fond
    public static void demarrerUneApplication(String applicationPath){
       File file = new File(applicationPath);
       
       if(file.exists()){
           executerCommandeBackground(applicationPath);
       }else{
           System.err.println("Le fichier suivant est introuvable : "+applicationPath);
       }
    }
    
    //Cette methode permet d'execution un script en fond
    public static void demarrerUneApplication(String params[]){
       File file = new File(params[0]);
       
       if(file.exists()){
           executerCommandeBackground(params);
       }else{
           System.err.println("Le fichier suivant est introuvable : "+params[0]);
       }
    }
    
    //Cette methode permet d'execution un script en fond
    public static void executerCommandeBackground(String commande) {
        try {
            Shell sh = new Shell();
            sh.exec(commande).consume();
        } catch (IOException ex) {
            Logger.getLogger(ScriptExecution.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //Cette methode permet d'execution un script en fond
    public static void executerCommandeBackground(String params[]) {
        try {
            Shell sh = new Shell();
            sh.exec(params).consume();
        } catch (IOException ex) {
            Logger.getLogger(ScriptExecution.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static Process executerCommande(String commande) {
        String cmd=System.getenv("SystemRoot")+"\\System32\\cmd.exe /C START "+commande;
        try {
            return Runtime.getRuntime().exec(cmd);
        } catch (IOException ex) {
           return null;
        }
         
    }
    
}
