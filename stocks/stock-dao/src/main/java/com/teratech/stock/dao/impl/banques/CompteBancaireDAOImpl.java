
package com.teratech.stock.dao.impl.banques;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.teratech.stock.dao.ifaces.banques.CompteBancaireDAOLocal;
import com.teratech.stock.dao.ifaces.banques.CompteBancaireDAORemote;
import com.teratech.stock.model.banques.CompteBancaire;

@Stateless(mappedName = "CompteBancaireDAO")
public class CompteBancaireDAOImpl
    extends AbstractGenericDAO<CompteBancaire, Long>
    implements CompteBancaireDAOLocal, CompteBancaireDAORemote
{

    @PersistenceContext(unitName = "teratech")
    protected EntityManager em;

    public CompteBancaireDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<CompteBancaire> getManagedEntityClass() {
        return (CompteBancaire.class);
    }

}
