
package com.teratech.stock.dao.impl.comptabilite;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.teratech.stock.dao.ifaces.comptabilite.SectionAnalytiqueDAOLocal;
import com.teratech.stock.dao.ifaces.comptabilite.SectionAnalytiqueDAORemote;
import com.teratech.stock.model.comptabilite.SectionAnalytique;

@Stateless(mappedName = "SectionAnalytiqueDAO")
public class SectionAnalytiqueDAOImpl
    extends AbstractGenericDAO<SectionAnalytique, Long>
    implements SectionAnalytiqueDAOLocal, SectionAnalytiqueDAORemote
{

    @PersistenceContext(unitName = "teratech")
    protected EntityManager em;

    public SectionAnalytiqueDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<SectionAnalytique> getManagedEntityClass() {
        return (SectionAnalytique.class);
    }

}
