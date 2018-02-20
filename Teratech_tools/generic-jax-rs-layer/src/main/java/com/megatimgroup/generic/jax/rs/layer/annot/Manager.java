/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.jax.rs.layer.annot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Commercial_2
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Manager {
    
    /**
     * Contain the na of the service
     * @return 
     */
    public String name() ;
    
    /**
     * Full name of the resource
     * @return 
     */
    public String module() default "" ;
    
    /**
     * Nom de l'application sur le serveur
     * @return 
     */
    public String application() default "" ;
    
  
    
    /**
     * Nom pleinement qualifié de l'interface
     * @return 
     */
    public Class<?> interf() default String.class;
    
    /**
     * Determine si le manager est dans le même conteneur 
     * que le service ou non
     * @return 
     */
    public Scope scope() default Scope.LOCAL ;
   
    
    /**
     * Permet de connaitre la specification
     * @return 
     */
    public Spec spec() default Spec.EJB;
    
}
