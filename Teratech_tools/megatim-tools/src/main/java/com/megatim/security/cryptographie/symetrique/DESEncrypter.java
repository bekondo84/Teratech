
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.security.cryptographie.symetrique;

import com.megatim.security.cryptographie.ifaces.SecurityInterface;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *This class implement the encrytion algorithm for cryptin password a
 * @author BEKONDO KANGUE DIEUNEDORT <bekondo_dieu@yahoo.fr  tel : 695 42 7874>
 * @since  20/O4/2016
 */
public class DESEncrypter implements SecurityInterface{
    
	/**
	 * 
	 */
	private static final String DES_ALG = "DESede" ;
	
	private String stringKey ="akatuky";
	/**
	 * Reference sur l'instance unique de l'encrypteur
	 */
    private static DESEncrypter _instance = null;
    
    /**
     * Le cypher d'encryption
     */
    private static Cipher encryptCipher = null ;
    /**
     * Le cypher de decryption
     */
    private static Cipher decryptCipher = null ;
    /**
     * Cle secret
     */
    private static SecretKey passwordKey =null;
    
    
    
    /**
     * Constructeur par defaut
     * @throws NoSuchAlgorithmException 
     * @throws NoSuchPaddingException 
     * @throws InvalidKeyException 
     */
    private DESEncrypter() {
        
	    try{	
	    	
	    	//Initialisation du cipher d'encryption
	    	encryptCipher = Cipher.getInstance(DES_ALG);
	    	encryptCipher.init(Cipher.ENCRYPT_MODE, passwordKey);
	    	//Initialisation du ciphr de decryption
	    	decryptCipher =  Cipher.getInstance(DES_ALG);
	    	decryptCipher.init(Cipher.DECRYPT_MODE, passwordKey);
	    }catch(Exception ex){
	    	 throw new RuntimeException("Erreur lors de l'initialisation de l'Encrypteur", ex);
	    }
    }
    
    public synchronized static DESEncrypter getInstance(){
        
        //Si instance est null 
        if(passwordKey==null){
            try {
               //  System.out.println("DESEncrypter.getInstance() :::::::::::::: "+DESEncrypter.class);
                 InputStream url = DESEncrypter.class.getResourceAsStream("/com/megatim/keys/keystore.data");
                 System.out.println("DESEncrypter.getInstance() :::::::::::::: "+url.toString());
                 //File file = new File(url.toURI());
                 //System.out.println(file.getAbsoluteFile()+" ::::: "+file.exists());
                loadKey(url); 
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
         _instance = new DESEncrypter();
         
        return _instance;
    }
    
    /**
     * Generation de la sous cle
     * @return
     * @throws NoSuchAlgorithmException 
     */
    public synchronized static SecretKey generateKey() throws NoSuchAlgorithmException{
        
        //Generation de la cle DES base sur le mot de passe
        KeyGenerator keyGenerator= KeyGenerator.getInstance(DES_ALG) ;
        //Definition de la taille de la cle compri entre 112 ou 168
        keyGenerator.init(168);
        //passwordKey = keyGenerator.generateKey();
        
        return keyGenerator.generateKey();
    }
    
    /**
     * 
     * @param key
     * @param file
     * @throws IOException 
     */
     public static void saveKey(SecretKey key, File file) throws IOException
     {
        byte[] encoded = key.getEncoded();
        String data = new String(Base64.encodeBase64(encoded));
        writeStringToFile(file, data);
      }
     
     
       public static SecretKey loadKey(File file) throws IOException {
           
           if(passwordKey!=null)
                           return passwordKey;
           
            String data = readFileToByteArray(file);
            char[] hex = data.toCharArray();
            byte[] encoded;

            encoded = Base64.decodeBase64(data.getBytes());       
            SecretKey key = new SecretKeySpec(encoded, DES_ALG);
            passwordKey = key;
            return key;
    }

       public static SecretKey loadKey(InputStream file) throws IOException {
           
           if(passwordKey!=null)
                           return passwordKey;
           
            String data = readFileToByteArray(file);
            char[] hex = data.toCharArray();
            byte[] encoded;

            encoded = Base64.decodeBase64(data.getBytes());       
            SecretKey key = new SecretKeySpec(encoded, DES_ALG);
            passwordKey = key;
            return key;
    }

     /**
      * 
      * @param file
      * @param data 
      */
     private static  void  writeStringToFile(File file, String data) throws IOException{
         
         FileWriter fw = new FileWriter(file.getAbsoluteFile());
         BufferedWriter bw = new BufferedWriter(fw);
         bw.write(data);
         bw.close();
     }
     
     /**
      * 
      * @param file 
      */
     private static String readFileToByteArray(File file) throws FileNotFoundException, IOException{
         
         BufferedReader br = null;
         
         String sCurrentLine;

         br = new BufferedReader(new FileReader(file));

        sCurrentLine = br.readLine();
        
        return sCurrentLine;
     }
      private static String readFileToByteArray(InputStream file) throws FileNotFoundException, IOException{
         
         BufferedReader br = null;
         
         String sCurrentLine;

          InputStreamReader streamReader = new InputStreamReader(file);
          
         br = new BufferedReader(streamReader);

        sCurrentLine = br.readLine();
        
        return sCurrentLine;
     }
    /**
     * Fonction d'encryption du text
     * @param text
     * @return
     */
    public synchronized String encryptText(String text){
    	
        try {
        	//Optention de byte
			byte[] plainTextByte = text.getBytes("UTF8");
			//Encodage
			byte[] encryptedBytes = encryptCipher.doFinal(plainTextByte);
			
			//Retourner la chaine encrypte
			return new String(Base64.encodeBase64(encryptedBytes));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Erreur lors de l'encryptage de la chaîne", e);
		}
       
    }
    
    public synchronized String decryptText(String text){
        
    	
    	try {
    		
    		//Obtention des bytes
        	byte[] encodedBytetext = Base64.decodeBase64(text.getBytes());
            
        	//Decryption
			byte[] byteText = decryptCipher.doFinal(encodedBytetext);
			
			//Retour de la chaîne
			return new String(byteText , "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erreur lors du décryptage de la chaîne", e);
		} 
    }

    @Override
    public boolean include(String key) {
       
       //Determination du champ 
        if(key.equalsIgnoreCase("javax.persistence.jdbc.password")) {
            return true;
        }else if(key.equalsIgnoreCase("javax.persistence.jdbc.user")){
            return true;               
        }else{
            return false;
        }
    }
    
   
}
