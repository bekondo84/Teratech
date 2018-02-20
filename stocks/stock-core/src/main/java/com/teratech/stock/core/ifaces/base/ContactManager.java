
package com.teratech.stock.core.ifaces.base;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.teratech.stock.model.base.Contact;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Tue Feb 20 00:07:32 GMT+01:00 2018
 * 
 */
public interface ContactManager
    extends GenericManager<Contact, Long>
{

    public final static String SERVICE_NAME = "ContactManager";

}
