
package com.teratech.gmao.jaxrs.impl.base;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.teratech.gmao.core.ifaces.base.ArticleManagerRemote;
import com.teratech.gmao.jaxrs.ifaces.base.ArticleRS;
import com.teratech.gmao.model.base.Article;


/**
 * Classe d'implementation du Web Service JAX-RS
 * @since Mon Feb 19 10:11:42 GMT+01:00 2018
 * 
 */
@Path("/article")
public class ArticleRSImpl
    extends AbstractGenericService<Article, Long>
    implements ArticleRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "gmao", name = "ArticleManagerImpl", interf = ArticleManagerRemote.class)
    protected ArticleManagerRemote manager;

    public ArticleRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<Article, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("gmao");
    }

}
