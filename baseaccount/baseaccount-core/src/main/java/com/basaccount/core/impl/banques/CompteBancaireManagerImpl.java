
package com.basaccount.core.impl.banques;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.banques.CompteBancaireManagerLocal;
import com.basaccount.core.ifaces.banques.CompteBancaireManagerRemote;
import com.basaccount.dao.ifaces.banques.CompteBancaireDAOLocal;
import com.basaccount.model.banques.CompteBancaire;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

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
