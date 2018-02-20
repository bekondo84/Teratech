/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericmanagerlayer.exceptions;

/**
 *
 * @author BEKONDO Kangue Dieuendort <bekondo_dieu@yahoo.fr>
 * Gestionnaire des exceptions niveau Manager
 */
public class GenericManagerException extends RuntimeException{

    
    public GenericManagerException() {
    }

    public GenericManagerException(String message) {
        super(message);
    }

    public GenericManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericManagerException(Throwable cause) {
        super(cause);
    }

    public GenericManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      //  super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
    
}
