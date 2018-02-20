/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.security.cryptographie.ifaces;

/**
 * Interface permettant de gerer le cryptage et le decryptage
 * @author user
 */
public interface SecurityInterface {
    
    /**
     * Methode permettant de crypter
     * @param text
     * @return 
     */
    public String encryptText(String text);
    
    /**
     * Methode permettant de decrypter
     * @param text
     * @return 
     */
    public String decryptText(String text);
    
    /**
     * 
     * @param key
     * @return true si key doit être encryte false sinon
     */
    public boolean include(String key);
}
