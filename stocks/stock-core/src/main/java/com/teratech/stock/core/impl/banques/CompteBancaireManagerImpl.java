
package com.teratech.stock.core.impl.banques;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.teratech.stock.core.ifaces.banques.CompteBancaireManagerLocal;
import com.teratech.stock.core.ifaces.banques.CompteBancaireManagerRemote;
import com.teratech.stock.dao.ifaces.banques.CompteBancaireDAOLocal;
import com.teratech.stock.model.banques.CompteBancaire;

@TransactionAttribute
@Stateless(mappedName = "CompteBancaireManager")
public class CompteBancaireManagerImpl
    extends AbstractGenericManager<CompteBancaire, Long>
    implements CompteBancaireManagerLocal, CompteBancaireManagerRemote
{

    @EJB(name = "CompteBancaireDAO")
    protected CompteBancaireDAOLocal dao;

    public CompteBancaireManagerImpl() {
    }

    @Override
    public GenericDAO<CompteBancaire, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
