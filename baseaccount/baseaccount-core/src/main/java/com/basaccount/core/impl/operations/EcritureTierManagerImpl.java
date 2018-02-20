
package com.basaccount.core.impl.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.operations.EcritureTierManagerLocal;
import com.basaccount.core.ifaces.operations.EcritureTierManagerRemote;
import com.basaccount.dao.ifaces.operations.EcritureTierDAOLocal;
import com.basaccount.model.operations.EcritureTier;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "EcritureTierManager")
public class EcritureTierManagerImpl
    extends AbstractGenericManager<EcritureTier, Long>
    implements EcritureTierManagerLocal, EcritureTierManagerRemote
{

    @EJB(name = "EcritureTierDAO")
    protected EcritureTierDAOLocal dao;

    public EcritureTierManagerImpl() {
    }

    @Override
    public GenericDAO<EcritureTier, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<EcritureTier> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<EcritureTier> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<EcritureTier> result = new ArrayList<EcritureTier>();
        for(EcritureTier data:datas){
            result.add(new EcritureTier(data));
        }
        return result;
    }

    @Override
    public List<EcritureTier> findAll() {
        List<EcritureTier> datas = super.findAll(); //To change body of generated methods, choose Tools | Templates.
        List<EcritureTier> result = new ArrayList<EcritureTier>();
        for(EcritureTier data:datas){
            result.add(new EcritureTier(data));
        }
        return result;
    }

    @Override
    public EcritureTier find(String propertyName, Long entityID) {
        EcritureTier data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        EcritureTier result = new EcritureTier(data);
        return result;
    }
    
    

}
