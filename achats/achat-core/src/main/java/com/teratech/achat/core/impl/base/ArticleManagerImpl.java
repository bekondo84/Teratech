
package com.teratech.achat.core.impl.base;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.teratech.achat.core.ifaces.base.ArticleManagerLocal;
import com.teratech.achat.core.ifaces.base.ArticleManagerRemote;
import com.teratech.achat.dao.ifaces.base.ArticleDAOLocal;
import com.teratech.achat.model.base.Article;

@TransactionAttribute
@Stateless(mappedName = "ArticleManager")
public class ArticleManagerImpl
    extends AbstractGenericManager<Article, Long>
    implements ArticleManagerLocal, ArticleManagerRemote
{

    @EJB(name = "ArticleDAO")
    protected ArticleDAOLocal dao;

    public ArticleManagerImpl() {
    }

    @Override
    public GenericDAO<Article, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
