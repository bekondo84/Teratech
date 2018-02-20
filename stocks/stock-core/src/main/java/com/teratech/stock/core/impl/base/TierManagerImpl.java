
package com.teratech.stock.core.impl.base;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.teratech.stock.core.ifaces.base.TierManagerLocal;
import com.teratech.stock.core.ifaces.base.TierManagerRemote;
import com.teratech.stock.dao.ifaces.base.TierDAOLocal;
import com.teratech.stock.model.base.Tier;

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

}
