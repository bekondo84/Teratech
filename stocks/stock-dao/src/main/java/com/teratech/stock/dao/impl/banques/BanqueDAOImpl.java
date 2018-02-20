
package com.teratech.stock.dao.impl.banques;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.teratech.stock.dao.ifaces.banques.BanqueDAOLocal;
import com.teratech.stock.dao.ifaces.banques.BanqueDAORemote;
import com.teratech.stock.model.banques.Banque;

@Stateless(mappedName = "BanqueDAO")
public class BanqueDAOImpl
    extends AbstractGenericDAO<Banque, Long>
    implements BanqueDAOLocal, BanqueDAORemote
{

    @PersistenceContext(unitName = "teratech")
    protected EntityManager em;

    public BanqueDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<Banque> getManagedEntityClass() {
        return (Banque.class);
    }

}
