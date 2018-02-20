
package com.teratech.achat.dao.ifaces.base;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.teratech.achat.model.base.Article;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Mon Feb 19 13:07:57 GMT+01:00 2018
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
