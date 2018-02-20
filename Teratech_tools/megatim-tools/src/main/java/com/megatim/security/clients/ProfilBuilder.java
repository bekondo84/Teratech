/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.security.clients;

import com.megatim.common.clients.ActionDetail;
import com.megatim.common.clients.ActionGroup;
import com.megatim.common.enumeration.TreeState;
import com.megatim.model.test.MenuComponent;
import com.megatim.model.test.MenuComposite;
import com.megatim.model.test.MenuLeaf;
import com.megatim.security.enumerations.EnumStatutAutorisation;
import com.megatim.security.model.Autorisation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ProfilBuilder {
    
     private static Map cache = new HashMap();
    
     private static Map<String , Autorisation> autorisationCache = new HashMap();
     
     private static List<Autorisation> habilitations = new ArrayList<Autorisation>();
     
      /**
     * 
     * @param group
     * @return 
     */
    private static MenuComponent buildMenuGroup(ActionGroup group){
         MenuComposite menuComposite = new MenuComposite(group.getLabel().trim(), group, null);
        
         //Traitement des groupes
         for(ActionGroup gp : group.getGroupes()){
             if(gp.isSeparator())
                 continue;             
             menuComposite.getElements().add(buildMenuGroup(gp));
         }
         
         //Traitement des details
         for(ActionDetail dt : group.getActions()){
             if(dt.isSeparator())
                 continue;
             if(cache.containsKey(dt.getActionIndex()))
                 continue;
             cache.put(dt.getActionIndex(), dt.getActionName());
             MenuLeaf leaf = new MenuLeaf(dt.getActionLabel().trim(), dt, autorisationCache.get(dt.getActionName()));
             menuComposite.getElements().add(leaf);
         }
        return menuComposite;
        
    }
    
    
    /**
     * 
     * @param listeTopMenus
     * @param menus
     * @param autorisations
     * @param state
     * @return 
     */
     public static List<MenuComponent> profilTreeBuilder(List<ActionGroup> listeTopMenus, List<MenuComponent> menus, List<Autorisation> autorisations, TreeState state) {
            //Initialisation du cache des autorisationd   
            autorisationCache.clear();
            for(Autorisation aut : autorisations){
                   autorisationCache.put(aut.getNomAutorisation(), aut);
            }
         
             Iterator j = listeTopMenus.iterator();
            //Set sommetsVisites = new HashSet();
            
            while (j.hasNext()) {
              ActionGroup s = (ActionGroup) j.next();
              
              //System.out.print("Size actionGroup "+s.getName()+" LAbel "+s.getLabel()+" Size action  = "+s.getActions().size()+" ::::: Size Groupe :: "+s.getGroupes().size());
              
              //Creation d'un noeud
              MenuComposite menuComposite = null;

                //Creation d'un noeud
                menuComposite = new MenuComposite(s.getLabel().trim(), s, null);

                 //Traitement des groupes
                for(ActionGroup gp : s.getGroupes()){
                     if(gp.isSeparator())
                     continue;
                    menuComposite.getElements().add(buildMenuGroup(gp));
                }

                //Traitement des details
                for(ActionDetail dt : s.getActions()){
                    if(dt.isSeparator())
                         continue;
                    if(cache.containsKey(dt.getActionIndex()))
                     continue;
                    cache.put(dt.getActionIndex(), dt.getActionName());
                    MenuLeaf leaf = new MenuLeaf(dt.getActionLabel().trim(), dt, autorisationCache.get(dt.getActionName()));
                    menuComposite.getElements().add(leaf);
                }       

                //Ajout à l'abre
                menus.add(menuComposite);
            }
            return menus;
      }
     
     
       /**
     * 
     * @param group
     * @return 
     */
    private static void buildAutorisation(ActionGroup group){
        //Traitement des groupes
         for(ActionGroup gp : group.getGroupes()){
             if(gp.isSeparator())
                 continue;             
             buildAutorisation(gp);
         }
         
         //Traitement des details
         for(ActionDetail dt : group.getActions()){
             if(dt.isSeparator())
                 continue;
             Autorisation auth = new Autorisation(dt.getActionName(), dt.getActionLabel(), EnumStatutAutorisation.WRITE);
             if(!habilitations.contains(auth))
                              habilitations.add(auth);
         }
        return ;
        
    }
    
    
    /**
     * 
     * @param listeTopMenus
     * @param menus
     * @param autorisations
     * @param state
     * @return 
     */
     public static List<Autorisation> autorisationBuilder(List<ActionGroup> listeTopMenus) {
            //Initialisation du cache des autorisationd   
            Iterator j = listeTopMenus.iterator();
            //Set sommetsVisites = new HashSet();
            habilitations.clear();
            while (j.hasNext()) {
              ActionGroup s = (ActionGroup) j.next();
              
              //System.out.print("Size actionGroup "+s.getName()+" LAbel "+s.getLabel()+" Size action  = "+s.getActions().size()+" ::::: Size Groupe :: "+s.getGroupes().size());
              
             
                 //Traitement des groupes
                for(ActionGroup gp : s.getGroupes()){
                     if(gp.isSeparator())
                     continue;
                    buildAutorisation(gp);
                }

                //Traitement des details
                for(ActionDetail dt : s.getActions()){
                    if(dt.isSeparator())
                         continue;
                     Autorisation auth = new Autorisation(dt.getActionName(), dt.getActionLabel(), EnumStatutAutorisation.WRITE);
                     if(!habilitations.contains(auth))
                              habilitations.add(auth);
                }       

                
            }
            return habilitations;
      }
     
     public static void clearCache(){
         cache = new HashMap();
     }

  
}
