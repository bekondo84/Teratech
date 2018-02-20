
package com.teratech.stock.dao.impl.base;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.teratech.stock.dao.ifaces.base.CategorieProduitDAOLocal;
import com.teratech.stock.dao.ifaces.base.CategorieProduitDAORemote;
import com.teratech.stock.model.base.CategorieProduit;

@Stateless(mappedName = "CategorieProduitDAO")
public class CategorieProduitDAOImpl
    extends AbstractGenericDAO<CategorieProduit, Long>
    implements CategorieProduitDAOLocal, CategorieProduitDAORemote
{

    @PersistenceContext(unitName = "teratech")
    protected EntityManager em;

    public CategorieProduitDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<CategorieProduit> getManagedEntityClass() {
        return (CategorieProduit.class);
    }

}
