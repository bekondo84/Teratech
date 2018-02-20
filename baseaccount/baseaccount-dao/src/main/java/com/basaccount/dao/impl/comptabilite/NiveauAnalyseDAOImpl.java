
package com.basaccount.dao.impl.comptabilite;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.comptabilite.NiveauAnalyseDAOLocal;
import com.basaccount.dao.ifaces.comptabilite.NiveauAnalyseDAORemote;
import com.basaccount.model.comptabilite.NiveauAnalyse;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "NiveauAnalyseDAO")
public class NiveauAnalyseDAOImpl
    extends AbstractGenericDAO<NiveauAnalyse, Long>
    implements NiveauAnalyseDAOLocal, NiveauAnalyseDAORemote
{

    @PersistenceContext(unitName = "keren")
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
