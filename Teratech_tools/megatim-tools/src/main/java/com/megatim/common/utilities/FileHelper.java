/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class FileHelper {
    
    /**
     * 
     * @param sourcePath
     * @param ciblePath 
     */
    public static void moveTo(String sourcePath , String ciblePath) throws FileNotFoundException, IOException {
       
        File source = new File(sourcePath);
        
        File cible = new File(ciblePath);
        
        cible.createNewFile();
        
        FileReader filereader = new FileReader(source);
        
        //BufferedReader reader = new BufferedReader(filereader);
        
        FileWriter writer = new FileWriter(cible);
        
        boolean stop = false ;
        
        int data = filereader.read();
        
        while(data!=-1){           
            
            writer.write(data);
            
            data = filereader.read();
        }
        
        filereader.close();
        
        writer.close();
          
        //Suppression du fichier teùporaire
        source.delete();
        
    }
    
    /**
     * Mehtode permettant d'ecrire dans un fichier
     * @param pathFile
     * @param texte 
     */
    public static void writeToFile(String pathFile, List<String> lignes) {
        
        //On declare
        String texte = "";
        
        //On parcourt
        for(int i=0;i<lignes.size();i++){
            
            //On ajoute avec un retour en ligne 
            texte = texte.concat(lignes.get(i)+"\n");
        }
        
        //On ecrit dans le fichier
        writeToFile(pathFile, texte);
    }
    
    /**
     * Mehtode permettant d'ecrire dans un fichier
     * @param pathFile
     * @param texte 
     */
    public static void writeToFile(String pathFile, String texte) {
        try {
            //Initialisation
            File file = new File(pathFile);
            FileWriter ffw = new FileWriter(file);
           
            //Creation du fichier
            file.createNewFile();
            
            //On ecrit dans le fichier
            ffw.write(texte);
           
            //On ferme le fichier
            ffw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Cette methode lie un fichier
     * @param filePath
     * @return
     * @throws FileNotFoundException 
     */
    public static List<String> readToFile(String filePath) throws FileNotFoundException, IOException{
        
        //Declaration
        BufferedReader entree;
        List<String> contenuFichier;
        String ligne = null;
        
        //Affectation
        entree = new BufferedReader (new FileReader (filePath));
        contenuFichier = new ArrayList<String>();
        
        //
        while (true){ 
            
            //On lie une ligne dans le fichier
            ligne = entree.readLine();
            
            //Traitement si la ligne est non null
            if (ligne != null){
                
                //On recupere la ligne
                contenuFichier.add(ligne);
            }else{
                
                //On sort de la boucle
                break ;
            }
        }
        
        //On retour la liste
        return contenuFichier;
    }
    
    /**
     * Methode permettant d'écrire dans un fichier properties
     * @param pathPropertiesfile
     * @param prop 
     */
    public static void writeToFileProperties(String pathPropertiesfile, Properties prop){
        OutputStream output = null;

        try {
            output = new FileOutputStream(pathPropertiesfile);

            // save properties to project root folder
            prop.store(output, null);

        } catch (final IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Permet de generer des nomre aleatorement
     * @return 
     */
    public static String generatedValue(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String nombre = format.format(new Date()) + String.valueOf((int)(Math.random() * 1000000000));
        return nombre;
    }
}
