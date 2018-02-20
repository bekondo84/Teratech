
package com.core.securites;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.core.menus.ActionItem;
import com.core.menus.MenuAction;
import com.core.menus.MenuActionDAOLocal;
import com.core.menus.MenuGroupActions;
import com.core.menus.MenuGroupActionsDAOLocal;
import com.core.menus.MenuModule;
import com.core.menus.MenuModuleDAOLocal;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "UtilisateurManager")
public class UtilisateurManagerImpl
    extends AbstractGenericManager<Utilisateur, Long>
    implements UtilisateurManagerLocal, UtilisateurManagerRemote
{

    @EJB(name = "UtilisateurDAO")
    protected UtilisateurDAOLocal dao;
    
    @EJB(name = "MenuModuleDAO")
    protected MenuModuleDAOLocal moduledao;
    
    @EJB(name = "MenuGroupActionsDAO")
    protected MenuGroupActionsDAOLocal menudao;
    
    @EJB(name = "MenuActionDAO")
    protected MenuActionDAOLocal menuitemdao;
    
    @EJB(name = "GroupeDAO")
    protected GroupeDAOLocal groupedao;   
    
    

    public UtilisateurManagerImpl() {
    }

    @Override
    public GenericDAO<Utilisateur, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    /**
     * 
     * @param predicats
     * @param orders
     * @param properties
     * @param firstResult
     * @param maxResult
     * @return 
     */
    @Override
    public List<Utilisateur> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        //To change body of generated methods, choose Tools | Templates.
        List<Utilisateur> datas = super.filter(predicats, orders, properties, firstResult, maxResult); 
        List<Utilisateur> resultats = new ArrayList<Utilisateur>();
        if(datas!=null){
            for(Utilisateur u : datas){
                resultats.add(new Utilisateur(u));
            }
        }
        return resultats;
    }

    /**
     * 
     * @return 
     */
    @Override
    public List<Utilisateur> findAll() {
         //To change body of generated methods, choose Tools | Templates.
        List<Utilisateur> datas = super.findAll();
        List<Utilisateur> resultats = new ArrayList<Utilisateur>();
        if(datas!=null){
            for(Utilisateur u : datas){
                resultats.add(new Utilisateur(u));
            }
        }
        return resultats;
    }

    @Override
    public Utilisateur find(String propertyName, Long entityID) {
         //To change body of generated methods, choose Tools | Templates.
        Utilisateur user = super.find(propertyName, entityID);
        Utilisateur result = new Utilisateur(user);
        result.setSocieteCourante(user.getSocieteCourante());
        result.setSocieteAutorisees(user.getSocieteAutorisees());
        if(user.getAutorisations()!=null){
            for(Groupe grp : user.getAutorisations()){
                Groupe gr = new Groupe(grp);
                gr.setModule(new MenuModule(grp.getModule()));
                result.getAutorisations().add(gr);
            }
        }
        return result;
    }

    @Override
    public Utilisateur delete(Long id) {
        //To change body of generated methods, choose Tools | Templates.
        Utilisateur user = super.delete(id); 
        Utilisateur result = new Utilisateur(user);
        result.setSocieteCourante(user.getSocieteCourante());
        result.setSocieteAutorisees(user.getSocieteAutorisees());
        if(user.getAutorisations()!=null){
            for(Groupe grp : user.getAutorisations()){
                result.getAutorisations().add(new Groupe(grp));
            }
        }
        return result;
    }

    
    /**
     * 
     * @param utilisateur
     * @return 
     */
    @Override
    public List<MenuModule> loadUserApplications(Utilisateur utilisateur) {
        //To change body of generated methods, choose Tools | Templates.
        List<MenuModule> result = new ArrayList<MenuModule>();
       if(!utilisateur.getIntitule().equalsIgnoreCase("Administrateur")){ 
            Utilisateur user = dao.findByPrimaryKey("id", utilisateur.getId());
            if(user==null || user.getAutorisations()==null) return result;
            for(Groupe grp : user.getAutorisations()){
                Map<Long , String> groupdb = getHabilitation(grp);            
                MenuModule mod = new MenuModule(grp.getModule());
                if(mod.isHasmenu()){
                    mod.setGroups(getMenuGroupes(mod, groupdb));
                }else if(grp.getModule().getAction()!=null){
                   mod.setAction(new MenuAction(grp.getModule().getAction()));
                }
                result.add(mod);
            }//end for(Groupe grp : user.getAutorisations()){
       }else{
           //System.out.println(UtilisateurManagerImpl.class.toString()+" ================ "+utilisateur.getName());
           RestrictionsContainer container = RestrictionsContainer.newInstance();
           container.addEq("application", true);
           container.addEq("active", true);
           List<MenuModule> datas = moduledao.filter(container.getPredicats(), null, new HashSet<String>(), 0, -1);
           if(datas!=null){
               for(MenuModule data : datas){
                    MenuModule mod = new MenuModule(data);
                    if(mod.isHasmenu()){
                        mod.setGroups(getMenuGroupes(mod, new HashMap<Long ,String>()));
                    }else if(data.getAction()!=null){
                       mod.setAction(new MenuAction(data.getAction()));
                    }
                    result.add(mod);
               }
           }
       }
        return result;
    }
    
    /**
     * Chargement des 
     * @param module
     * @return 
     */
    private List<MenuGroupActions> getMenuGroupes(MenuModule module,Map<Long , String> groupdb){
        List<MenuGroupActions> result = new ArrayList<MenuGroupActions>();
        RestrictionsContainer container = RestrictionsContainer.newInstance();
        container.addEq("module.id", module.getId());
        List<MenuGroupActions> datas = menudao.filter(container.getPredicats(), null, new HashSet<String>(), 0, -1);
        if(datas==null) return result;
        for(MenuGroupActions group : datas){
            MenuGroupActions grp = new MenuGroupActions(group);            
            grp.setActions(getMenuActions(grp, groupdb));
            result.add(grp);
        }
        return result;
    }
    
   /**
    * 
    * @param group
    * @param groupdb
    * @return 
    */
    private List<MenuAction> getMenuActions(MenuGroupActions group,Map<Long , String> groupdb){
        List<MenuAction> result = new ArrayList<MenuAction>();
        RestrictionsContainer container = RestrictionsContainer.newInstance();
        container.addEq("menu.id", group.getId());
        List<MenuAction> datas = menuitemdao.filter(container.getPredicats(), null, new HashSet<String>(), 0, -1);
        if(datas==null) return result;
        for(MenuAction act:datas){
            MenuAction action = new MenuAction(act);
            for(ActionItem item : act.getActions()){
                action.getActions().add(new ActionItem(item));
            }
            if(groupdb.containsKey(action.getId())){
                if(groupdb.get(action.getId())!=null&&!groupdb.get(action.getId()).isEmpty()){
                    action.setSecuritylevel(Short.valueOf(groupdb.get(action.getId())));
                }
            }//end if(groupdb.containsKey(act.getId())){
            result.add(action);
        }//end for(MenuAction act:datas){
        return result;
    }
    
    /**
     * 
     * @param grp
     * @return 
     */
    private Map<Long , String> getHabilitation(Groupe grp){
        Map<Long , String> result = new HashMap<Long , String>();
        Groupe data = groupedao.findByPrimaryKey("id", grp.getId());
        for(GroupeDetail gd : data.getDroits()){
            MenuAction action = new MenuAction(gd.getMenuAction());
            result.put(action.getId(), gd.getHabilitation());
        }//end for(GroupeDetail gd : data.getDroits()){
        return result;
    }
  

}
