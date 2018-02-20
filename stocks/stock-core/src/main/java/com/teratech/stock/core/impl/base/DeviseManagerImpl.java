
package com.teratech.stock.core.impl.base;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.teratech.stock.core.ifaces.base.DeviseManagerLocal;
import com.teratech.stock.core.ifaces.base.DeviseManagerRemote;
import com.teratech.stock.dao.ifaces.base.DeviseDAOLocal;
import com.teratech.stock.model.base.Devise;

@TransactionAttribute
@Stateless(mappedName = "DeviseManager")
public class DeviseManagerImpl
    extends AbstractGenericManager<Devise, Long>
    implements DeviseManagerLocal, DeviseManagerRemote
{

    @EJB(name = "DeviseDAO")
    protected DeviseDAOLocal dao;

    public DeviseManagerImpl() {
    }

    @Override
    public GenericDAO<Devise, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
