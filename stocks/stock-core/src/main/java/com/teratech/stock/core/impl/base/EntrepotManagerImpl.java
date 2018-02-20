
package com.teratech.stock.core.impl.base;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.teratech.stock.core.ifaces.base.EntrepotManagerLocal;
import com.teratech.stock.core.ifaces.base.EntrepotManagerRemote;
import com.teratech.stock.dao.ifaces.base.EntrepotDAOLocal;
import com.teratech.stock.model.base.Entrepot;

@TransactionAttribute
@Stateless(mappedName = "EntrepotManager")
public class EntrepotManagerImpl
    extends AbstractGenericManager<Entrepot, Long>
    implements EntrepotManagerLocal, EntrepotManagerRemote
{

    @EJB(name = "EntrepotDAO")
    protected EntrepotDAOLocal dao;

    public EntrepotManagerImpl() {
    }

    @Override
    public GenericDAO<Entrepot, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
