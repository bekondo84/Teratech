
package com.basaccount.core.impl.tiers;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.tiers.TierManagerLocal;
import com.basaccount.core.ifaces.tiers.TierManagerRemote;
import com.basaccount.dao.ifaces.tiers.TierDAOLocal;
import com.basaccount.model.tiers.Tier;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "TierManager")
public class TierManagerImpl
    extends AbstractGenericManager<Tier, Long>
    implements TierManagerLocal, TierManagerRemote
{

    @EJB(name = "TierDAO")
    protected TierDAOLocal dao;

    public TierManagerImpl() {
    }

    @Override
    public GenericDAO<Tier, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<Tier> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<Tier> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<Tier>  result = new ArrayList<Tier>(); 
        for(Tier data : datas){
            result.add(new Tier(data));
        }
        return result;
    }

    @Override
    public List<Tier> findAll() {
        List<Tier> datas = super.findAll(); //To change body of generated methods, choose Tools | Templates.        
        List<Tier>  result = new ArrayList<Tier>(); 
        for(Tier data : datas){
            result.add(new Tier(data));
        }
        return result;
    }

    @Override
    public Tier find(String propertyName, Long entityID) {
        Tier data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        if(data.getComptesbancaire()!=null) data.getComptesbancaire().size();
        if(data.getContacts()!=null) data.getContacts().size();
        return data;
    }
    
    

}
