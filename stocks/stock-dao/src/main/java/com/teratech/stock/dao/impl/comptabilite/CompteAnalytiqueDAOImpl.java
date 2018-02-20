
package com.teratech.stock.dao.impl.comptabilite;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.teratech.stock.dao.ifaces.comptabilite.CompteAnalytiqueDAOLocal;
import com.teratech.stock.dao.ifaces.comptabilite.CompteAnalytiqueDAORemote;
import com.teratech.stock.model.comptabilite.CompteAnalytique;

@Stateless(mappedName = "CompteAnalytiqueDAO")
public class CompteAnalytiqueDAOImpl
    extends AbstractGenericDAO<CompteAnalytique, Long>
    implements CompteAnalytiqueDAOLocal, CompteAnalytiqueDAORemote
{

    @PersistenceContext(unitName = "teratech")
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
