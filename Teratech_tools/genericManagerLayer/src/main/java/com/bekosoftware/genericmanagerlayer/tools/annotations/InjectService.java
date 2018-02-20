/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericmanagerlayer.tools.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *Injecte les services au 
 * Localise un service à partir de son nom jndi
 * @author BEKONDO Kangue Dieunedort <bekondo_dieu@yahoo.fr>
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectService {
   
    
	/**
	 * Indique la reférence jndi de localisation du composant
	 * @return
	 */
	public String name() default "";
        /**
         * Systeme d'injection permet de determiner si l'on doit utiliser les EJB 3.x ou les EJB Lite 
         * ou toute autre technoligie
         */
        public ServiceLocationType system() default ServiceLocationType.EJB_REMOTE;
}
