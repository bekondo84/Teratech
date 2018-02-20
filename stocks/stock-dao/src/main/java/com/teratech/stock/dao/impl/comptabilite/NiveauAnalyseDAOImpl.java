
package com.teratech.stock.dao.impl.comptabilite;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.teratech.stock.dao.ifaces.comptabilite.NiveauAnalyseDAOLocal;
import com.teratech.stock.dao.ifaces.comptabilite.NiveauAnalyseDAORemote;
import com.teratech.stock.model.comptabilite.NiveauAnalyse;

@Stateless(mappedName = "NiveauAnalyseDAO")
public class NiveauAnalyseDAOImpl
    extends AbstractGenericDAO<NiveauAnalyse, Long>
    implements NiveauAnalyseDAOLocal, NiveauAnalyseDAORemote
{

    @PersistenceContext(unitName = "teratech")
    protected EntityManager em;

    public NiveauAnalyseDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<NiveauAnalyse> getManagedEntityClass() {
        return (NiveauAnalyse.class);
    }

}
