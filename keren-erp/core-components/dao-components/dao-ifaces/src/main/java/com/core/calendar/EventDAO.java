
package com.core.calendar;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Sat Nov 18 09:29:25 WAT 2017
 * 
 */
public interface EventDAO
    extends GenericDAO<Event, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "EventDAO";

}
