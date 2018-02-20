
package com.teratech.stock.dao.impl.base;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.teratech.stock.dao.ifaces.base.FamilleArticleDAOLocal;
import com.teratech.stock.dao.ifaces.base.FamilleArticleDAORemote;
import com.teratech.stock.model.base.FamilleArticle;

@Stateless(mappedName = "FamilleArticleDAO")
public class FamilleArticleDAOImpl
    extends AbstractGenericDAO<FamilleArticle, Long>
    implements FamilleArticleDAOLocal, FamilleArticleDAORemote
{

    @PersistenceContext(unitName = "teratech")
    protected EntityManager em;

    public FamilleArticleDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<FamilleArticle> getManagedEntityClass() {
        return (FamilleArticle.class);
    }

}
