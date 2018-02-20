/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Commercial_2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Exclude {
    
    //Liste des classes
    Class<?>[] classes() ;
    
    //Liste des champs
    String[] champs();
    
    //Connecteur OR ou AND
    Connector connector();
    
    //Type de la données
    Class<?> type() default String.class;
    
    //Valeur 
    String value();
    
}
