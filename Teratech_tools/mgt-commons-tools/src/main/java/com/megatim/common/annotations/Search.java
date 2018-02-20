package com.megatim.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * 
 * @author Bekondo Kangue Dieunedort <bekondo_dieu@yahoo.fr / tel : 6 95 42 78 74>
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface Search {

	/**
	 * Liste des valeurs des criteres de recherches
	 * @return
	 */
	SearchType[] value()  default {SearchType.EQUAL};
	
	/**
	 * Nom qualifié du champs
	 */
	String field() default "";
	/**
	 * Critere de tri de la liste
	 */
	OrderType orderBy() default OrderType.NONE ;
	/**
	 * Position dans la liste des critere d'ordre
	 * 0 sigifie aucun critere d'ordre
	 */
	short rang() default 100;
        /**
         * 
         * @return 
         */
        Class<?> type() default String.class;
}
