
package com.megatim.security.core.ifaces.Profil;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.security.model.Profil;


/**
 * Interface etendue par les interfaces locale et remote du manager

 * @since Sun Sep 18 21:53:32 CEST 2016
 * 
 */
public interface ProfilManager
    extends GenericManager<Profil, String>
{

    public final static String SERVICE_NAME = "ProfilManager";

}
