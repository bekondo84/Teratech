
package com.basaccount.dao.impl.tiers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.tiers.TierDAOLocal;
import com.basaccount.dao.ifaces.tiers.TierDAORemote;
import com.basaccount.model.tiers.Tier;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "TierDAO")
public class TierDAOImpl
    extends AbstractGenericDAO<Tier, Long>
    implements TierDAOLocal, TierDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public TierDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<Tier> getManagedEntityClass() {
        return (Tier.class);
    }

}
