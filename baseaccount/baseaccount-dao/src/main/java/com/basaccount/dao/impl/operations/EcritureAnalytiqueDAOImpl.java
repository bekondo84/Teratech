
package com.basaccount.dao.impl.operations;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.operations.EcritureAnalytiqueDAOLocal;
import com.basaccount.dao.ifaces.operations.EcritureAnalytiqueDAORemote;
import com.basaccount.model.operations.EcritureAnalytique;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "EcritureAnalytiqueDAO")
public class EcritureAnalytiqueDAOImpl
    extends AbstractGenericDAO<EcritureAnalytique, Long>
    implements EcritureAnalytiqueDAOLocal, EcritureAnalytiqueDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public EcritureAnalytiqueDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<EcritureAnalytique> getManagedEntityClass() {
        return (EcritureAnalytique.class);
    }

}
