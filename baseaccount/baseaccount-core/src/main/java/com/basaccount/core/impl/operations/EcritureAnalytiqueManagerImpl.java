
package com.basaccount.core.impl.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.operations.EcritureAnalytiqueManagerLocal;
import com.basaccount.core.ifaces.operations.EcritureAnalytiqueManagerRemote;
import com.basaccount.dao.ifaces.operations.EcritureAnalytiqueDAOLocal;
import com.basaccount.model.operations.EcritureAnalytique;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

@TransactionAttribute
@Stateless(mappedName = "EcritureAnalytiqueManager")
public class EcritureAnalytiqueManagerImpl
    extends AbstractGenericManager<EcritureAnalytique, Long>
    implements EcritureAnalytiqueManagerLocal, EcritureAnalytiqueManagerRemote
{

    @EJB(name = "EcritureAnalytiqueDAO")
    protected EcritureAnalytiqueDAOLocal dao;

    public EcritureAnalytiqueManagerImpl() {
    }

    @Override
    public GenericDAO<EcritureAnalytique, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
