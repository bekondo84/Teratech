/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericdaolayer.dao.constants;

/**
 * Un predicat represent une condition dans une requete 
 * @author Bekondo Kangue Dieunedort <bekondo_dieu@yahoo.fr>
 */
public enum PredicatType {
    
    EQ , //Egalite de valeur
    NOTEQ, //Non Egalite de valeur
    GE, //Plus grand ou egale à
    GT, //Plus grand que
    LT,//Plus petit  que
    LE,//Plus petit ou Egale
    LIKE, //est comme
    NOTLIKE,
    FALSE,
    TRUE,
    NOTNULL,
    NULL ;
}
