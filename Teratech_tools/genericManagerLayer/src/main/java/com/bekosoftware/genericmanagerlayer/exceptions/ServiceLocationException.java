/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericmanagerlayer.exceptions;

/**
 *
 * @author Bekondo Kangue Dieuendort <bekondo_dieu@yahoo.fr>
 * Classe representant les exception pour la fonction de localisation
 */
public class ServiceLocationException extends RuntimeException{

    
    public ServiceLocationException() {
    }

    public ServiceLocationException(String message) {
        super(message);
    }

    public ServiceLocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceLocationException(Throwable cause) {
        super(cause);
    }

    public ServiceLocationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        //super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
}
