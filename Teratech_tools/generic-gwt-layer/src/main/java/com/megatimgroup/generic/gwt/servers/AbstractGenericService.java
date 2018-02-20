/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.servers;

import com.bekosoftware.genericdaolayer.dao.tools.EQ;
import com.bekosoftware.genericdaolayer.dao.tools.GE;
import com.bekosoftware.genericdaolayer.dao.tools.GT;
import com.bekosoftware.genericdaolayer.dao.tools.LE;
import com.bekosoftware.genericdaolayer.dao.tools.LIKE;
import com.bekosoftware.genericdaolayer.dao.tools.LT;
import com.bekosoftware.genericdaolayer.dao.tools.NOTEQ;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.megatimgroup.generic.gwt.commons.EnumConnector;
import com.megatimgroup.generic.gwt.commons.EnumOrderType;
import com.megatimgroup.generic.gwt.commons.FilterRule;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Commercial_2
 */
public abstract class AbstractGenericService
                    extends RemoteServiceServlet   implements GenericService{

   /**
    * Reference au Manager 
     * @param entityName
     * @param t
    * @return 
    */
//   public abstract GenericManager getManager(String entityName);
     
    @Override
    public void save(String entityName , Object t) {
        //To change body of generated methods, choose Tools | Templates.
        processBeforeSave(t);
//        getManager(entityName).save(t);
        processAfterSave(t);       
       
    }

    @Override
    public void save(String entityName ,List list) {
         //To change body of generated methods, choose Tools | Templates.
        processBeforeSave(list);
//        getManager(entityName).save(list);
        processAfterSave(list);
    }

    @Override
    public void update(String entityName , Serializable pk, Object t) {
        //To change body of generated methods, choose Tools | Templates.
        processBeforeUpdate(t);
//        getManager(entityName).update(pk, t);
        processAfterUpdate(t);
       
    }

    @Override
    public void delete(String entityName , Serializable pk) {
        //To change body of generated methods, choose Tools | Templates.
       processBeforeDelete(pk);
//        getManager(entityName).delete(pk);
        processAfterDelete(pk);        
    }

    @Override
    public Object find(String entityName , String string, Serializable pk) {
        //To change body of generated methods, choose Tools | Templates.
        return  null ; //getManager(entityName).find(string, pk);
    }

    @Override
    public List findAll(String entityName ) {
        //To change body of generated methods, choose Tools | Templates.
        return null ; //getManager(entityName).findAll();
    }

    @Override
    public List findByUniqueProperty(String entityName , String string, Object o, Set<String> set) {
        //To change body of generated methods, choose Tools | Templates.
        return null ; //getManager(entityName).findByUniqueProperty(string, o, set);
    }

    @Override
    public List filter(String entityName , List<FilterRule> list, Map<String, EnumOrderType> map, Set<String> set, int i, int i1) {
        //To change body of generated methods, choose Tools | Templates.
        RestrictionsContainer container = RestrictionsContainer.newInstance();
        for(FilterRule rule : list){
            container.add(ruleToPredicate(rule));
        }
        return null ;//getManager(entityName).filter(container.getPredicats(), null, set, i, i1);
        
    }

    @Override
    public List filter(String entityName , List<FilterRule> list, Map<String, EnumOrderType> map, Set<String> set, Map<String, Object> map1, int i, int i1) {
        //To change body of generated methods, choose Tools | Templates.
        RestrictionsContainer container = RestrictionsContainer.newInstance();
        for(FilterRule rule : list){
            container.add(ruleToPredicate(rule));
        }
        return null ; //getManager(entityName).filter(container.getPredicats(), null, set, map1, i, i1);
    }

    @Override
    public Long count(String entityName , List<FilterRule> list) {
        //To change body of generated methods, choose Tools | Templates.
        RestrictionsContainer container = RestrictionsContainer.newInstance();
        for(FilterRule rule : list){
            container.add(ruleToPredicate(rule));
        }
        return null;//getManager(entityName).count(container.getPredicats());
    }

    @Override
    public void clean(String entityName ) {
        //To change body of generated methods, choose Tools | Templates.
//        getManager(entityName).clean();
    }

    @Override
    public void flush(String entityName ) {
        //To change body of generated methods, choose Tools | Templates.
//        getManager(entityName).flush();
    }

    @Override
    public void processBeforeSave(Object t) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processAfterSave(Object t) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processBeforeSave(List list) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processAfterSave(List list) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processBeforeUpdate(Object t) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processAfterUpdate(Object t) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processBeforeDelete(Serializable key) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processAfterDelete(Serializable t) {
        //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
     * @param rule
     * @return 
     */
    private Predicat ruleToPredicate(FilterRule rule){        
        if(rule.getPredicate().equals(EnumConnector.EQUAL)){
            return new EQ(rule.getFieldName(), rule.getValue());
        }else if(rule.getPredicate().equals(EnumConnector.NOTEQUAL)){
            return new NOTEQ(rule.getFieldName(), rule.getValue());
        }else if(rule.getPredicate().equals(EnumConnector.GT)){
            return new GT(rule.getFieldName(), rule.getValue());
        }else if(rule.getPredicate().equals(EnumConnector.GE)){
            return new GE(rule.getFieldName(), rule.getValue());
        }else if(rule.getPredicate().equals(EnumConnector.LT)){
            return new LT(rule.getFieldName(), rule.getValue());
        }else if(rule.getPredicate().equals(EnumConnector.LE)){
            return new LE(rule.getFieldName(), rule.getValue());
        }else if(rule.getPredicate().equals(EnumConnector.LIKE)){
            return new LIKE(rule.getFieldName(), (String) rule.getValue());
        }
        return null;
    }
}
