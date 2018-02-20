
package com.teratech.stock.dao.impl.base;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.teratech.stock.dao.ifaces.base.UniteAchatDAOLocal;
import com.teratech.stock.dao.ifaces.base.UniteAchatDAORemote;
import com.teratech.stock.model.base.UniteAchat;

@Stateless(mappedName = "UniteAchatDAO")
public class UniteAchatDAOImpl
    extends AbstractGenericDAO<UniteAchat, Long>
    implements UniteAchatDAOLocal, UniteAchatDAORemote
{

    @PersistenceContext(unitName = "teratech")
    protected EntityManager em;

    public UniteAchatDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<UniteAchat> getManagedEntityClass() {
        return (UniteAchat.class);
    }

}
