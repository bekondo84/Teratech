
package com.basaccount.dao.impl.operations;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.operations.PieceComptableDAOLocal;
import com.basaccount.dao.ifaces.operations.PieceComptableDAORemote;
import com.basaccount.model.operations.PieceComptable;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "PieceComptableDAO")
public class PieceComptableDAOImpl
    extends AbstractGenericDAO<PieceComptable, Long>
    implements PieceComptableDAOLocal, PieceComptableDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public PieceComptableDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<PieceComptable> getManagedEntityClass() {
        return (PieceComptable.class);
    }

}
