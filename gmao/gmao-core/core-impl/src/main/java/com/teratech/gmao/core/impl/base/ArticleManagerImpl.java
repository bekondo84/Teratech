
package com.teratech.gmao.core.impl.base;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.teratech.gmao.core.ifaces.base.ArticleManagerLocal;
import com.teratech.gmao.core.ifaces.base.ArticleManagerRemote;
import com.teratech.gmao.dao.ifaces.base.ArticleDAOLocal;
import com.teratech.gmao.model.base.Article;

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
