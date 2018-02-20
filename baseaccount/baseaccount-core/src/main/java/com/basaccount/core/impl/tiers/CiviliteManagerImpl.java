
package com.basaccount.core.impl.tiers;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.tiers.CiviliteManagerLocal;
import com.basaccount.core.ifaces.tiers.CiviliteManagerRemote;
import com.basaccount.dao.ifaces.tiers.CiviliteDAOLocal;
import com.basaccount.model.tiers.Civilite;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

@TransactionAttribute
@Stateless(mappedName = "CiviliteManager")
public class CiviliteManagerImpl
    extends AbstractGenericManager<Civilite, Long>
    implements CiviliteManagerLocal, CiviliteManagerRemote
{

    @EJB(name = "CiviliteDAO")
    protected CiviliteDAOLocal dao;

    public CiviliteManagerImpl() {
    }

    @Override
    public GenericDAO<Civilite, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
