/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.megatim.common.ressources;

import java.io.File;

/**
 *
 * @author divers
 */
public class PathRessource {
    
    /**
     * Tous les chemins absolus des ressources concernant l'editique
     */
    public static String ROOT_DIRECTORY = System.getProperty("user.dir");
    public static String ROOT_DIRECTORY_REPPORT = System.getProperty("user.dir")+File.separator+"reporting/"; 
    public static String ROOT_DIRECTORY_REPPORT_LISTES = PathRessource.ROOT_DIRECTORY_REPPORT+"listes/";
    public static String ROOT_DIRECTORY_REPPORT_DETAILS = PathRessource.ROOT_DIRECTORY_REPPORT+"details/";
    public static String ROOT_DIRECTORY_REPPORT_TEMPLATES = PathRessource.ROOT_DIRECTORY_REPPORT+"templates/";
}
