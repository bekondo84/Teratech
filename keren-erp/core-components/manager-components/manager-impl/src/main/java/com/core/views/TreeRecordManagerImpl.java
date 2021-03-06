
package com.core.views;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.core.menus.MenuAction;
import com.megatim.common.annotations.OrderType;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "TreeRecordManager")
public class TreeRecordManagerImpl
    extends AbstractGenericManager<TreeRecord, Long>
    implements TreeRecordManagerLocal, TreeRecordManagerRemote
{

    @EJB(name = "TreeRecordDAO")
    protected TreeRecordDAOLocal dao;

    public TreeRecordManagerImpl() {
    }

    @Override
    public GenericDAO<TreeRecord, Long> getDao() {
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
    public List<TreeRecord> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<TreeRecord> datas =super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        for(TreeRecord rec : datas){
            if(rec.getAction()!=null){
                rec.setAction(new MenuAction(rec.getAction()));
            }            
        }
        return datas;
    }

    @Override
    public TreeRecord find(String propertyName, Long entityID) {
         //To change body of generated methods, choose Tools | Templates.
        TreeRecord data = super.find(propertyName, entityID);
        if(data.getScript()!=null){
            data.getScript().length();
        }
        if(data.getAction()!=null){
            data.setAction(new MenuAction(data.getAction()));            
        }
       
        return data;
    }

    @Override
    public TreeRecord delete(Long id) {
         //To change body of generated methods, choose Tools | Templates.
        TreeRecord data = super.delete(id);
        if(data.getScript()!=null){
            data.getScript().length();
        }
        if(data.getAction()!=null){
            data.setAction(new MenuAction(data.getAction()));            
        }
        
        return data;
    }
    

}
