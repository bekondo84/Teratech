
package com.teratech.stock.core.impl.base;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.teratech.stock.core.ifaces.base.UniteGestionManagerLocal;
import com.teratech.stock.core.ifaces.base.UniteGestionManagerRemote;
import com.teratech.stock.dao.ifaces.base.UniteGestionDAOLocal;
import com.teratech.stock.model.base.UniteGestion;

@TransactionAttribute
@Stateless(mappedName = "UniteGestionManager")
public class UniteGestionManagerImpl
    extends AbstractGenericManager<UniteGestion, Long>
    implements UniteGestionManagerLocal, UniteGestionManagerRemote
{

    @EJB(name = "UniteGestionDAO")
    protected UniteGestionDAOLocal dao;

    public UniteGestionManagerImpl() {
    }

    @Override
    public GenericDAO<UniteGestion, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
