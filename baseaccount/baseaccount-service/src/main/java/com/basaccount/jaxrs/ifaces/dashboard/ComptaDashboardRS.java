/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.jaxrs.ifaces.dashboard;

import com.core.dashboard.DashboardContainer;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Commercial_2
 */
public interface ComptaDashboardRS {
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("solde/{id}")
    public DashboardContainer dashboard(@PathParam("id")Long templateID);
    
}
