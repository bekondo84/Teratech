
package com.basaccount.core.impl.comptabilite;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.comptabilite.CompteAnalytiqueManagerLocal;
import com.basaccount.core.ifaces.comptabilite.CompteAnalytiqueManagerRemote;
import com.basaccount.dao.ifaces.comptabilite.CompteAnalytiqueDAOLocal;
import com.basaccount.model.comptabilite.CompteAnalytique;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

@TransactionAttribute
@Stateless(mappedName = "CompteAnalytiqueManager")
public class CompteAnalytiqueManagerImpl
    extends AbstractGenericManager<CompteAnalytique, Long>
    implements CompteAnalytiqueManagerLocal, CompteAnalytiqueManagerRemote
{

    @EJB(name = "CompteAnalytiqueDAO")
    protected CompteAnalytiqueDAOLocal dao;

    public CompteAnalytiqueManagerImpl() {
    }

    @Override
    public GenericDAO<CompteAnalytique, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
