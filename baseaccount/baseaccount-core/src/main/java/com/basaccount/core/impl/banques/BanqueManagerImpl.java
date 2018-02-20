
package com.basaccount.core.impl.banques;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.banques.BanqueManagerLocal;
import com.basaccount.core.ifaces.banques.BanqueManagerRemote;
import com.basaccount.dao.ifaces.banques.BanqueDAOLocal;
import com.basaccount.model.banques.Banque;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

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
