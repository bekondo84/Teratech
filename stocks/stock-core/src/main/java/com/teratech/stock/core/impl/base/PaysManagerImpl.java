
package com.teratech.stock.core.impl.base;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.teratech.stock.core.ifaces.base.PaysManagerLocal;
import com.teratech.stock.core.ifaces.base.PaysManagerRemote;
import com.teratech.stock.dao.ifaces.base.PaysDAOLocal;
import com.teratech.stock.model.base.Pays;

@TransactionAttribute
@Stateless(mappedName = "PaysManager")
public class PaysManagerImpl
    extends AbstractGenericManager<Pays, Long>
    implements PaysManagerLocal, PaysManagerRemote
{

    @EJB(name = "PaysDAO")
    protected PaysDAOLocal dao;

    public PaysManagerImpl() {
    }

    @Override
    public GenericDAO<Pays, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
