
package com.megatim.security.core.ifaces.Utilisateur;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.security.model.Utilisateur;

/**
 * Interface étendue par les interfaces locale et remote du manager
 * @since Thu Aug 25 16:24:41 WAT 2016
 * 
 */
public interface UtilisateurManager    extends GenericManager<Utilisateur, String>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "com.megatim.security.core.impl.Utilisateur.UtilisateurManagerImpl";
    
}
