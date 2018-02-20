
package com.basaccount.core.impl.tiers;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.tiers.ConditionPaiementManagerLocal;
import com.basaccount.core.ifaces.tiers.ConditionPaiementManagerRemote;
import com.basaccount.dao.ifaces.tiers.ConditionPaiementDAOLocal;
import com.basaccount.model.tiers.ConditionPaiement;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

@TransactionAttribute
@Stateless(mappedName = "ConditionPaiementManager")
public class ConditionPaiementManagerImpl
    extends AbstractGenericManager<ConditionPaiement, Long>
    implements ConditionPaiementManagerLocal, ConditionPaiementManagerRemote
{

    @EJB(name = "ConditionPaiementDAO")
    protected ConditionPaiementDAOLocal dao;

    public ConditionPaiementManagerImpl() {
    }

    @Override
    public GenericDAO<ConditionPaiement, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
