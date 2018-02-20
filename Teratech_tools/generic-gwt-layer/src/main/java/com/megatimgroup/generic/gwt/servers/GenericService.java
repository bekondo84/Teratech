/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.servers;

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
public interface GenericService {   
    
     
    public void save(String entityName , Object t);

    public void save(String entityName , List list);

    public void update(String entityName , Serializable pk, Object t);
   
    public void delete(String entityName , Serializable pk);

    public Object find(String entityName , String string, Serializable pk);

    public List findAll(String entityName);

    public List findByUniqueProperty(String entityName , String string, Object o, Set<String> set);

    public List filter(String entityName , List<FilterRule> list, Map<String, EnumOrderType> map, Set<String> set, int i, int i1);

    public List filter(String entityName , List<FilterRule> list, Map<String, EnumOrderType> map, Set<String> set, Map<String, Object> map1, int i, int i1);

    public Long count(String entityName , List<FilterRule> list);

    public void clean(String entityName );

    public void flush(String entityName);

    public void processBeforeSave(Object t);

    public void processAfterSave(Object t);

    public void processBeforeSave(List list);

    public void processAfterSave(List list);

    public void processBeforeUpdate(Object t);

    public void processAfterUpdate(Object t);

    public void processBeforeDelete(Serializable id);

    public void processAfterDelete(Serializable id);
}
