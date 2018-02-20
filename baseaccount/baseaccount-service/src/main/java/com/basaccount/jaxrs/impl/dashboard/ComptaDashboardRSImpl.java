/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.jaxrs.impl.dashboard;

import com.basaccount.jaxrs.ifaces.dashboard.ComptaDashboardRSLocal;
import com.basaccount.jaxrs.ifaces.dashboard.ComptaDashboardRSRemote;
import com.basaccount.model.dashboard.ComptaDashboard;
import com.core.dashboard.DashboardContainer;
import com.core.views.DashboardRecord;
import com.core.views.DashboardRecordManagerRemote;
import com.kerem.core.DashboardUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.AnnotationsProcessor;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.ws.rs.Path;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Commercial_2
 */
@Path("/comptadashboard")
public class ComptaDashboardRSImpl implements ComptaDashboardRSLocal,ComptaDashboardRSRemote{

    @Manager(application = "kerencore", name = "DashboardRecordManagerImpl", interf = DashboardRecordManagerRemote.class)
    protected DashboardRecordManagerRemote dashboardmanager;
    
    
    public ComptaDashboardRSImpl() {
          AnnotationsProcessor processor = new AnnotationsProcessor();
        try {
            processor.process(this);
        } catch (NamingException ex) {
            Logger.getLogger(AbstractGenericService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(AbstractGenericService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AbstractGenericService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    @Override
    public DashboardContainer dashboard(Long templateID) {
        try {
            //To change body of generated methods, choose Tools | Templates.
            DashboardRecord dashboard = dashboardmanager.find("id", templateID);
            if(dashboard==null){
                return null;
            }
            return DashboardUtil.dashboardBuilder(new ComptaDashboard(), dashboard);
        } catch (JAXBException ex) {
            Logger.getLogger(ComptaDashboardRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ComptaDashboardRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ComptaDashboardRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
}
