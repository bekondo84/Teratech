
package com.core.securites;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.core.menus.MenuAction;
import com.core.menus.MenuActionDAOLocal;
import com.core.menus.MenuGroupActions;
import com.core.menus.MenuGroupActionsDAOLocal;
import com.core.menus.MenuModule;
import com.core.menus.MenuModuleDAOLocal;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "GroupeManager")
public class GroupeManagerImpl
    extends AbstractGenericManager<Groupe, Long>
    implements GroupeManagerLocal, GroupeManagerRemote
{

    @EJB(name = "GroupeDAO")
    protected GroupeDAOLocal dao;

    @EJB(name = "MenuModuleDAO")
    protected MenuModuleDAOLocal moduledao;
    
    @EJB(name = "MenuGroupActionsDAO")
    protected MenuGroupActionsDAOLocal menudao;
    
    @EJB(name = "MenuActionDAO")
    protected MenuActionDAOLocal menuitemdao;
    
    public GroupeManagerImpl() {
    }

    @Override
    public GenericDAO<Groupe, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public Groupe find(String propertyName, Long entityID) {
         //To change body of generated methods, choose Tools | Templates.
        Groupe data =  super.find(propertyName, entityID);
        Groupe result = new Groupe(data);
        result.setModule(new MenuModule(data.getModule()));
        //Si aucun droits n'est cree initiliaisation des droits
        if(data.getDroits()==null||data.getDroits().isEmpty()){
            List<GroupeDetail> droits = new ArrayList<GroupeDetail>();
            //Chargement des menu concernant le module
            RestrictionsContainer container = RestrictionsContainer.newInstance();
            container.addEq("module.id", data.getModule().getId());
            List<MenuGroupActions> menus = menudao.filter(container.getPredicats(), null, new HashSet<String>(), 0, -1);
            if(menus!=null){
                for(MenuGroupActions menu : menus){
                    //Chargement des actions specifiques a un menus
                    container = RestrictionsContainer.newInstance();
                    container.addEq("menu.id", menu.getId());
                    List<MenuAction> actions = menuitemdao.filter(container.getPredicats(), null, new HashSet<String>(), 0, -1);
                    if(actions!=null){
                        for(MenuAction act : actions){
                            droits.add(new GroupeDetail(new MenuAction(act), "0"));
                        }
                    }
                }
            }
            result.setModule(new MenuModule(data.getModule()));
            result.setDroits(droits);
        }else{
            List<GroupeDetail> droits = new ArrayList<GroupeDetail>();
            for(GroupeDetail detail : data.getDroits()){
                GroupeDetail d = new GroupeDetail(detail);
                d.setMenuAction(new MenuAction(detail.getMenuAction()));
                droits.add(d);
            }
            result.setDroits(droits);
        }
        return result ;
        
    }

    @Override
    public List<Groupe> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<Groupe> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
         List<Groupe> resultats = new ArrayList<Groupe>();
        if(datas!=null){
            for(Groupe gr : datas){
                Groupe g = new Groupe(gr);
                g.setModule(new MenuModule(gr.getModule()));
                resultats.add(g);
            }
        }
        return resultats;
    }

    @Override
    public List<Groupe> findAll() {
        List<Groupe> datas = super.findAll(); //To change body of generated methods, choose Tools | Templates.
        List<Groupe> resultats = new ArrayList<Groupe>();
        if(datas!=null){
            for(Groupe gr : datas){
                Groupe g = new Groupe(gr);
                g.setModule(new MenuModule(gr.getModule()));
                resultats.add(g);
            }
        }
        return resultats;
    }

    @Override
    public Groupe delete(Long id) {
        //To change body of generated methods, choose Tools | Templates.
        Groupe data =  super.delete(id);
        Groupe result = new Groupe(data);
        return result; 
    }
    
    

}
