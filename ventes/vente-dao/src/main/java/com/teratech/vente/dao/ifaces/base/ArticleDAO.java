
package com.teratech.vente.dao.ifaces.base;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.teratech.vente.model.base.Article;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Mon Feb 19 13:22:28 GMT+01:00 2018
 * 
 */
public interface ArticleDAO
    extends GenericDAO<Article, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "ArticleDAO";

}
