
package com.teratech.stock.core.impl.banques;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.teratech.stock.core.ifaces.banques.BanqueManagerLocal;
import com.teratech.stock.core.ifaces.banques.BanqueManagerRemote;
import com.teratech.stock.dao.ifaces.banques.BanqueDAOLocal;
import com.teratech.stock.model.banques.Banque;

@TransactionAttribute
@Stateless(mappedName = "BanqueManager")
public class BanqueManagerImpl
    extends AbstractGenericManager<Banque, Long>
    implements BanqueManagerLocal, BanqueManagerRemote
{

    @EJB(name = "BanqueDAO")
    protected BanqueDAOLocal dao;

    public BanqueManagerImpl() {
    }

    @Override
    public GenericDAO<Banque, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
