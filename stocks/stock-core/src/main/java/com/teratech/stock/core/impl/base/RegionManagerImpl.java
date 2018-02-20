
package com.teratech.stock.core.impl.base;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.teratech.stock.core.ifaces.base.RegionManagerLocal;
import com.teratech.stock.core.ifaces.base.RegionManagerRemote;
import com.teratech.stock.dao.ifaces.base.RegionDAOLocal;
import com.teratech.stock.model.base.Region;

@TransactionAttribute
@Stateless(mappedName = "RegionManager")
public class RegionManagerImpl
    extends AbstractGenericManager<Region, Long>
    implements RegionManagerLocal, RegionManagerRemote
{

    @EJB(name = "RegionDAO")
    protected RegionDAOLocal dao;

    public RegionManagerImpl() {
    }

    @Override
    public GenericDAO<Region, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
