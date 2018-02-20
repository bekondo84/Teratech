
package com.teratech.gmao.core.ifaces.base;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.teratech.gmao.model.base.Article;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Mon Feb 19 10:11:42 GMT+01:00 2018
 * 
 */
public interface ArticleManager
    extends GenericManager<Article, Long>
{

    public final static String SERVICE_NAME = "ArticleManager";

}
