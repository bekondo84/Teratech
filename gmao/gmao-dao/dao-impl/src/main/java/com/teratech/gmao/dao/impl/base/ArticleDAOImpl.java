
package com.teratech.gmao.dao.impl.base;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.teratech.gmao.dao.ifaces.base.ArticleDAOLocal;
import com.teratech.gmao.dao.ifaces.base.ArticleDAORemote;
import com.teratech.gmao.model.base.Article;

@Stateless(mappedName = "ArticleDAO")
public class ArticleDAOImpl
    extends AbstractGenericDAO<Article, Long>
    implements ArticleDAOLocal, ArticleDAORemote
{

    @PersistenceContext(unitName = "gmao")
    protected EntityManager em;

    public ArticleDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<Article> getManagedEntityClass() {
        return (Article.class);
    }

}
