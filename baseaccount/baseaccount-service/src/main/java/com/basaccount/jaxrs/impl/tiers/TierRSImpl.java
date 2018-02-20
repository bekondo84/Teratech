
package com.basaccount.jaxrs.impl.tiers;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.tiers.TierManagerRemote;
import com.basaccount.jaxrs.ifaces.tiers.TierRS;
import com.basaccount.model.tiers.Tier;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.core.menus.MenuAction;
import com.core.menus.MenuActionManagerRemote;
import com.google.gson.Gson;
import com.kerem.core.CommonTools;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Fri Dec 01 14:04:41 WAT 2017
 * 
 */
@Path("/tier")
public class TierRSImpl
    extends AbstractGenericService<Tier, Long>
    implements TierRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "TierManagerImpl", interf = TierManagerRemote.class)
    protected TierManagerRemote manager;
    
    @Manager(application = "kerencore", name = "MenuActionManagerImpl", interf = MenuActionManagerRemote.class)
    protected MenuActionManagerRemote actionmanager;
    

    public TierRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<Tier, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(@Context HttpHeaders headers) {
        try {
            Gson gson = new Gson();
            MenuAction action = null;
            if(headers.getRequestHeader("action")!=null){
                action = gson.fromJson(headers.getRequestHeader("action").get(0),MenuAction.class);
            }
            if(action==null||action.getId()<=0){
                return MetaDataUtil.getMetaData(new Tier(),new HashMap<String, MetaData>(),new ArrayList<String>());
            }else{
                action = actionmanager.find("id", action.getId());
                return CommonTools.xmlViewParser(Tier.class, action.getFormView(), action.getTreeView());
            }
            
        }catch (Exception ex) {          
           throw new WebApplicationException(ex);
        }
    }
    
    

}
