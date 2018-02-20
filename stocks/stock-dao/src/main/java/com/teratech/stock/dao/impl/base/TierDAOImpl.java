
package com.teratech.stock.dao.impl.base;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.teratech.stock.dao.ifaces.base.TierDAOLocal;
import com.teratech.stock.dao.ifaces.base.TierDAORemote;
import com.teratech.stock.model.base.Tier;

@Stateless(mappedName = "TierDAO")
public class TierDAOImpl
    extends AbstractGenericDAO<Tier, Long>
    implements TierDAOLocal, TierDAORemote
{

    @PersistenceContext(unitName = "teratech")
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
