/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.megatim.common.context;

import com.megatim.common.clients.ActionGroup;
import com.megatim.model.test.MenuComponent;
import com.megatim.security.model.Autorisation;
import com.megatim.security.model.Utilisateur;
import java.util.List;
import java.util.Map;

/**
 * Classe regroupant toute les variables statiques partagés par les autres modules
 * @author divers
 */
public class ToolsContext {
    
    //Listes des composants de la fenetre principales
    public static List<ActionGroup> LISTE_ITEMS = null;
    
    //Listes des sous menus
    public static Map LISTE_JMENU_ITEMS = null;
    
    //Listes des sous menus
    public static Map LISTE_JMENU = null;
    
    //Arbre
    public static List<MenuComponent> TREE;
    
    //Utilisateur courant
    public static Utilisateur CURRENT_USER = null;
    
    //Map des  autorisations de l'utilisateur courant
    public static Map<String , Autorisation> AUTORISATIONS_MAP = null;
    
    //On recuper une autorisation en focntion d'un actionName
    public static Autorisation getAutorisation(String actionAction){
        return AUTORISATIONS_MAP.get(actionAction);
    }
    
    //Repertoire de log
    public static String REP_LOG=null;
    
    //Une liste d'objets
    public static List OBJECTS_LIST;
}
