/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericdaolayer.dao.tools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Container permettant de construire les requettes 
 * @author : Bekondo Kangue Dieunedort <bekondo_dieu@yahoo.fr>
 */
public class RestrictionsContainer implements Serializable{
    
    //Les des prédicats constituant les conditions de choix des entités
    List<Predicat> predicats = null;

    /**
     * Constructeur par defaut
     */
    public RestrictionsContainer() {
       if(predicats==null) 
           predicats = new ArrayList<Predicat>();
    }
    
    /**
     * Bulder pour 
     * @return 
     */
    public static RestrictionsContainer newInstance(){
        
       return new RestrictionsContainer();       
    }
    
    /**
     * Ajout d'un predicat
     */
    public RestrictionsContainer add(Predicat predicat){
        newInstance();
        this.predicats.add(predicat);
        return this ;
    }
    
    /**
     * Ajout une restriction de type egale dans la requete
     */
    public <Y extends Comparable<Y>> RestrictionsContainer addEq(String property , Y value){
        
        //Creation du predicat devant contenir la valeur
        Predicat predicat = new EQ(property,value);        
        //Ajout du predicat dans la liste des prédicats
        add(predicat);
        //Renvoi de l'objet Restriction courant
        return this ;
    }
    
     /**
     * Ajout une restriction de type non egale dans la requete
     */
    public <Y extends Comparable<Y>> RestrictionsContainer addNotEq(String property , Y value){
        
        //Creation du predicat devant contenir la valeur
        Predicat predicat = new NOTEQ(property,value);        
        //Ajout du predicat dans la liste des prédicats
        add(predicat);
        //Renvoi de l'objet Restriction courant
        return this ;
    }
    
     /**
     * Ajout une restriction de type non >= dans la requete
     */
    public <Y extends Comparable<Y>> RestrictionsContainer addGe(String property , Y value){
        
        //Creation du predicat devant contenir la valeur
        Predicat predicat = new GE(property,value);        
        //Ajout du predicat dans la liste des prédicats
        add(predicat);
        //Renvoi de l'objet Restriction courant
        return this ;
    }
    
    /**
     * Ajout une restriction de type non > dans la requete
     */
    public <Y extends Comparable<Y>> RestrictionsContainer addGt(String property , Y value){
        
        //Creation du predicat devant contenir la valeur
        Predicat predicat = new GT(property,value);        
        //Ajout du predicat dans la liste des prédicats
        add(predicat);
        //Renvoi de l'objet Restriction courant
        return this ;
    }
    
    /**
     * Ajout une restriction de type non <= dans la requete
     */
    public <Y extends Comparable<Y>> RestrictionsContainer addLe(String property , Y value){
        
        //Creation du predicat devant contenir la valeur
        Predicat predicat = new LE(property,value);        
        //Ajout du predicat dans la liste des prédicats
        add(predicat);
        //Renvoi de l'objet Restriction courant
        return this ;
    }
    
    /**
     * Ajout une restriction de type non < dans la requete
     */
    public <Y extends Comparable<Y>> RestrictionsContainer addLt(String property , Y value){
        
        //Creation du predicat devant contenir la valeur
        Predicat predicat = new LT(property,value);        
        //Ajout du predicat dans la liste des prédicats
        add(predicat);
        //Renvoi de l'objet Restriction courant
        return this ;
    }
    
    /**
     * Ajout une restriction de type non LIKE dans la requete <Y extends Comparable<Y>>
     */
    public  RestrictionsContainer addLike(String property , String value){
        
        //Creation du predicat devant contenir la valeur
        Predicat predicat = new LIKE(property,value);        
        //Ajout du predicat dans la liste des prédicats
        add(predicat);
        //Renvoi de l'objet Restriction courant
        return this ;
    }
    
    /**
     * Ajout une restriction de type non NOT LIKE dans la requete <Y extends Comparable<Y>>
     */
    public  RestrictionsContainer addNotLike(String property , String value){
        
        //Creation du predicat devant contenir la valeur
        Predicat predicat = new NOTLIKE(property,value);        
        //Ajout du predicat dans la liste des prédicats
        add(predicat);
        //Renvoi de l'objet Restriction courant
        return this ;
    }
    
    /**
     * Ajout une restriction de type property ==false  <Y extends Comparable<Y>>
     */
    public  RestrictionsContainer addIsFalse(String property ){
        
        //Creation du predicat devant contenir la valeur
        Predicat predicat = new ISFALSE(property);        
        //Ajout du predicat dans la liste des prédicats
        add(predicat);
        //Renvoi de l'objet Restriction courant
        return this ;
    }
    
    /**
     * Ajout une restriction de type property ==true <Y extends Comparable<Y>>
     */
    public  RestrictionsContainer addIsTrue(String property){
        
        //Creation du predicat devant contenir la valeur
        Predicat predicat = new ISTRUE(property);        
        //Ajout du predicat dans la liste des prédicats
        add(predicat);
        //Renvoi de l'objet Restriction courant
        return this ;
    }
    
    /**
     * Ajout une restriction de type property isNull
     */
    public <Y extends Comparable<Y>> RestrictionsContainer addIsNull(String property , Y value){
        
        //Creation du predicat devant contenir la valeur
        Predicat predicat = new ISNULL(property);        
        //Ajout du predicat dans la liste des prédicats
        add(predicat);
        //Renvoi de l'objet Restriction courant
        return this ;
    }
    
    /**
     * Ajout une restriction de type property NOT Null
     */
    public <Y extends Comparable<Y>> RestrictionsContainer addNotNull(String property , Y value){
        
        //Creation du predicat devant contenir la valeur
        Predicat predicat = new ISNOTNULL(property);        
        //Ajout du predicat dans la liste des prédicats
        add(predicat);
        //Renvoi de l'objet Restriction courant
        return this ;
    }
    /**
     * Ajout de la clause ISEMPTY
     */
    public <Y extends Comparable<Y>> RestrictionsContainer addIsEmpty(String property , Y value){
        
        //Creation du predicat devant contenir la valeur
        Predicat predicat = new ISEMPTY(property);        
        //Ajout du predicat dans la liste des prédicats
        add(predicat);
        //Renvoi de l'objet Restriction courant
        return this ;
    }
    
    /**
     * Ajout de la clause NOT ISEMPTY
     */
    public <Y extends Comparable<Y>> RestrictionsContainer addIsNotEmpty(String property , Y value){
        
        //Creation du predicat devant contenir la valeur
        Predicat predicat = new ISNOTEMPTY(property);        
        //Ajout du predicat dans la liste des prédicats
        add(predicat);
        //Renvoi de l'objet Restriction courant
        return this ;
    }
    
    /**
     * Nombre de predicat
     */
    public int size(){
        newInstance();
        return predicats.size();
        
    }
    /**
     * Vide la liste des predicats
     */
    public void clear(){
        newInstance();
        this.predicats.clear();
    }

    /**
     * Getter pour les prédicats
     * @return 
     */
    public List<Predicat> getPredicats() {
        return predicats;
    }
    
    
}
