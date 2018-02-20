
package com.basaccount.dao.impl.tiers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.tiers.CiviliteDAOLocal;
import com.basaccount.dao.ifaces.tiers.CiviliteDAORemote;
import com.basaccount.model.tiers.Civilite;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "CiviliteDAO")
public class CiviliteDAOImpl
    extends AbstractGenericDAO<Civilite, Long>
    implements CiviliteDAOLocal, CiviliteDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public CiviliteDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<Civilite> getManagedEntityClass() {
        return (Civilite.class);
    }

}
