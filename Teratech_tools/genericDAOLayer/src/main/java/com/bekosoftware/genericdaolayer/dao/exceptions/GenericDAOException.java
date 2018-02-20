/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericdaolayer.dao.exceptions;

/**
 *
 * @author Bekondo Kangue Dieuendort <bekondo_dieu@yahoo.fr>
 * Classe representant les exception de base de la couche DAO
 */

public class GenericDAOException extends RuntimeException{
    
    /**
     * Constructeur par défaut
     */
    public GenericDAOException() {
        
        super();
    }

    /**
     * Constructeur avec initialisation du message de l'erreur
     * @param message 
     */
    public GenericDAOException(String message) {
        super(message);
    }

    public GenericDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructeur avec initialisation des paramètres
     * @param cause 
     */
    public GenericDAOException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructeur avec initialisation des paramètres
     * @param message 
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace 
     
    public GenericDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }*/
    
    
}
