
package com.basaccount.dao.impl.banques;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.banques.BanqueDAOLocal;
import com.basaccount.dao.ifaces.banques.BanqueDAORemote;
import com.basaccount.model.banques.Banque;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "BanqueDAO")
public class BanqueDAOImpl
    extends AbstractGenericDAO<Banque, Long>
    implements BanqueDAOLocal, BanqueDAORemote
{

    @PersistenceContext(unitName = "keren")
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
