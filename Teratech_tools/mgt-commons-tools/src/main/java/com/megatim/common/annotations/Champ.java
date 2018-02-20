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
 *Annotations niveau GUI permetant l'identification des champs
 * de l'object mapp� au panel
 * @author BEKONDO Kangue Dieunedort <bekondo_dieu@yahoo.fr / tel : 6 95 42 78 74>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Champ {
    
    //nom du champs correspondant dans l'entite concern�
    String mappedBy();
    //Type de la donn�es de la colonne
    Class<?> type() default String.class ;
    /**
     *Session des attributs de traitement des chaines de carracteres 
     */
    //Long du champs valable pour les String
    int length() default -1 ;
    //Pattern
    String pattern() default "";
    //valeur mininal 
    double min() default 0 ;
    //Longeur maximal
    double max() default -1;
    
    /*
     * Nom du champs de preference de type JLabel dans lequel afficher le message
     * d'erreur valable seulement pour les composant graphique
     */
    String errorMessageField() default "";
    /*
     * Message d'erreur pas defaut
     */
    String errorMessage() default "";
    /**
     * contrainte de nullit�
     */
    boolean nullable() default true ;
    /**
     * Deternime si le champ est modifiable
     * @return 
     */
    boolean update() default true;
}
