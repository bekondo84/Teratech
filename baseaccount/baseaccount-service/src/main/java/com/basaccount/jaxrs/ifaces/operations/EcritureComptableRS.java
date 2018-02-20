
package com.basaccount.jaxrs.ifaces.operations;

import com.basaccount.model.operations.EcritureComptable;
import com.megatimgroup.generic.jax.rs.layer.ifaces.GenericService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 * Interface du service JAX-RS

 * @since Sat Dec 23 09:07:30 WAT 2017
 * 
 */
public interface EcritureComptableRS
    extends GenericService<EcritureComptable, Long>
{
   @GET
   @Produces({MediaType.APPLICATION_JSON})
   @Path("analytique")
   public List<EcritureComptable> getEcritures();
}
