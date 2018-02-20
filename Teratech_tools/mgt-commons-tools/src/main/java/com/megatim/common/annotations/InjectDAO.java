/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation permettant l'injection des DAO pendant les phases de tests
 *
 * @author BEKONDO Kangue Dieunedort <bekondo_dieu@yahoo.fr / 695427874>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
@Inherited
public @interface InjectDAO {

    //Nom complet de la classe des DAO
    String ClassName() default "";

    String unitName() default "";
}
