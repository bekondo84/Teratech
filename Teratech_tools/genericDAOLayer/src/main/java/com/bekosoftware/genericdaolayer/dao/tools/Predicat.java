/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericdaolayer.dao.tools;

import java.io.Serializable;

import com.bekosoftware.genericdaolayer.dao.constants.PredicatType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

/**
 * Un predicat represent une condition dans une requete 
 * @author Bekondo Kangue Dieunedort <bekondo_dieu@yahoo.fr>
 */
public abstract class Predicat implements Serializable{
    
   
    /**
     * Constructeur par défaut
     */
    public  Predicat() {
        
    }

  
   
    /**
	 * Methode de construction d'un Predicat JPA 2
	 * @param <Y>	Parametre de type de la valeur de la propriete
	 * @param criteriaBuilder	Constructeur de critere
	 * @param root	Racine de la requete par critere
	 * @return	Predicat JPA 2
	 */
    public abstract javax.persistence.criteria.Predicate generateJPAPredicat(CriteriaBuilder criteriaBuilder , Root<?> root );         
       
    
}
