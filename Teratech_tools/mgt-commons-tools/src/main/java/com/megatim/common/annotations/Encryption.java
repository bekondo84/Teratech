/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * annotation permettant d'identifier les champs � cryter
 * @author nctheunte@yahoo.fr
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Encryption {
    
	   //Type de la donn�es de la colonne
    Class<?> type() default String.class ;
    
    // champ initaile donc la valeur doit etre crypter
    String hashMappedBy() default "";
    
    // champ contenant la valeur crypter
    String mappedBy() default "";
}
