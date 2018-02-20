
package com.basaccount.dao.impl.comptabilite;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.comptabilite.CompteAnalytiqueDAOLocal;
import com.basaccount.dao.ifaces.comptabilite.CompteAnalytiqueDAORemote;
import com.basaccount.model.comptabilite.CompteAnalytique;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "CompteAnalytiqueDAO")
public class CompteAnalytiqueDAOImpl
    extends AbstractGenericDAO<CompteAnalytique, Long>
    implements CompteAnalytiqueDAOLocal, CompteAnalytiqueDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public CompteAnalytiqueDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<CompteAnalytique> getManagedEntityClass() {
        return (CompteAnalytique.class);
    }

}
