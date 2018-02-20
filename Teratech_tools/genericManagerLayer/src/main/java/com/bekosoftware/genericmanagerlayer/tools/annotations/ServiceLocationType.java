/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericmanagerlayer.tools.annotations;

/**
 *
 * @author MGT
 */
public enum ServiceLocationType {
    
    EJB_REMOTE,//Localisation à partir d'un conteneur leger
    EJB_LOCAL,//Localisation à partir d'un serveur web
    EJB_LITE //Localisation à partir des ejb lite
}
