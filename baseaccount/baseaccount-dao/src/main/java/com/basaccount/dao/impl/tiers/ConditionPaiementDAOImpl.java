
package com.basaccount.dao.impl.tiers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.tiers.ConditionPaiementDAOLocal;
import com.basaccount.dao.ifaces.tiers.ConditionPaiementDAORemote;
import com.basaccount.model.tiers.ConditionPaiement;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "ConditionPaiementDAO")
public class ConditionPaiementDAOImpl
    extends AbstractGenericDAO<ConditionPaiement, Long>
    implements ConditionPaiementDAOLocal, ConditionPaiementDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public ConditionPaiementDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<ConditionPaiement> getManagedEntityClass() {
        return (ConditionPaiement.class);
    }

}
