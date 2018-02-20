
package com.basaccount.core.ifaces.tiers;

import com.basaccount.model.tiers.Contact;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Fri Dec 01 14:04:41 WAT 2017
 * 
 */
public interface ContactManager
    extends GenericManager<Contact, Long>
{

    public final static String SERVICE_NAME = "ContactManager";

}
