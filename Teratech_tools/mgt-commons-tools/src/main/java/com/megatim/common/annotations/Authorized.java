/**
 * 
 */
package com.megatim.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author BEKONDO Kangue Dieunedort
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD , ElementType.TYPE})
public @interface Authorized {

	//Liste des r�les des utilisateurs authorise a executer la resource annot�e
	 String[] values() default {} ;
}
