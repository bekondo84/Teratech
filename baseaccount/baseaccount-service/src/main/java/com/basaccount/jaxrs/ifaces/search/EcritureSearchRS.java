/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.jaxrs.ifaces.search;

import com.basaccount.model.operations.EcritureComptable;
import com.basaccount.model.search.EcritureSearch;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Commercial_2
 */
public interface EcritureSearchRS {
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("meta")
    public MetaData getMetaData();
    
    /**
     * 
     * @param param
     * @return 
     */
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("somethings")
    public List<EcritureComptable> somethings(EcritureSearch param);
    
    @PUT
    @Produces({"application/pdf"})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("pdf")
    public Response somethingspdf(EcritureSearch param);
}
