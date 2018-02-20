
package com.teratech.stock.core.ifaces.base;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.teratech.stock.model.base.Article;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Mon Feb 19 16:55:37 GMT+01:00 2018
 * 
 */
public interface ArticleManager
    extends GenericManager<Article, Long>
{

    public final static String SERVICE_NAME = "ArticleManager";

}
