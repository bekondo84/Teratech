/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kerem.core;

import com.core.base.BaseElement;
import com.core.base.State;
import com.megatim.common.annotations.Predicate;
import com.megatim.common.annotations.TableFooter;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaArray;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaColumn;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaGroup;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Commercial_2
 */
public class MetaDataUtil {
    
    private static  Map<String,MetaData>  sharedCache = new HashMap<String,MetaData>();
    /**
     * 
     * @return 
     */
    public static Map<String,MetaData> shareCache(){
          return sharedCache;   
    }
    
    /**
     * 
     */
    public static void resetShareCache(){
        sharedCache.clear();
    }
    
    /**
     * 
     * @param key
     * @param meta 
     */
    public static void put(String key , MetaData meta){
        sharedCache.put(key, meta);
    }
    
    /**
     * 
     * @param key
     * @return 
     */
    public static boolean containKey(String key){
        return sharedCache.containsKey(key);
    }
    
    /**
     * 
     * @param meta
     * @param fields
     * @return 
     */
    public static MetaData getMetaData(MetaData meta , List<com.kerem.genarated.Field> fields){
        if(meta==null) return null;
        MetaData metaData = new MetaData();
        metaData.setEditTitle(meta.getEditTitle());
        metaData.setListTitle(meta.getListTitle());
        metaData.setEntityName(meta.getEntityName());
        metaData.setModuleName(meta.getModuleName());
        metaData.setCreateonfield(meta.isCreateonfield()); 
        metaData.setDesablecreate(meta.isDesablecreate());
        metaData.setActivefilelink(meta.isActivefilelink());
        metaData.setStates(meta.getStates());
        metaData.setActivatefollower(meta.isActivatefollower());
        metaData.setSearchfields(meta.getSearchfields());
//        metaData.setCustomfooter(meta.isCustomfooter());
        metaData.setFooterScript(meta.getFooterScript());
        HashMap<String,com.kerem.genarated.Field> map = new HashMap<String,com.kerem.genarated.Field>();
        for(com.kerem.genarated.Field field : fields){
            map.put(field.getName(), field);
        }
        //Copie des columns
        if(meta.getColumns()!=null){
            for(MetaColumn col:meta.getColumns()){
                if(map.containsKey(col.getFieldName())){
                    metaData.getColumns().add(col);
                }
            }//end for(MetaColumn col:meta.getColumns())
        }//end if(meta.getColumns()!=null)
        //Traitement des groupes
        if(meta.getGroups()!=null){
            for(MetaGroup grp : meta.getGroups()){
                if(grp.getColumns()!=null){
                     for(MetaColumn col:grp.getColumns()){
                        if(map.containsKey(col.getFieldName())){
                            metaData.getColumns().add(col);
                        }
                    }//end for(MetaColumn col:meta.getColumns())
                }
            }//end for(MetaGroup grp : meta.getGroups())
        }//end if(meta.getGroups()!=null){
        return metaData;
    }
    /**
     * 
     * @param obj
     * @param shareCache
     * @return 
     * @throws java.lang.InstantiationException 
     * @throws java.lang.IllegalAccessException 
     */
    public static MetaData getMetaData(Object obj,Map<String , MetaData> shareCache,List<String> exclures) throws InstantiationException, IllegalAccessException{
        List metaDoublons = new ArrayList();
        //Si on a deja traite cete entite
//        if(shareCache.containsKey(obj.getClass().toString())){
////            System.out.println("public static MetaData getMetaData(Object obj,Map<Class<?> , MetaData> shareCache) ========= "+obj.getClass().toString());
//            return shareCache.get(obj.getClass().toString());
//        }
        MetaData metaData = new MetaData();
        metaData.setEditTitle(((BaseElement)obj).getEditTitle());
        metaData.setListTitle(((BaseElement)obj).getListTitle());
        metaData.setEntityName(obj.getClass().getSimpleName());
        metaData.setModuleName(((BaseElement)obj).getModuleName());
        metaData.setCreateonfield(((BaseElement)obj).isCreateonfield()); 
        metaData.setDesablecreate(((BaseElement)obj).isDesablecreate());
        metaData.setActivefilelink(((BaseElement)obj).isActivefilelien());
        metaData.setFooterScript(((BaseElement)obj).getFooterScript());
        metaData.setActivatefollower(((BaseElement)obj).isActivatefollower());
        metaData.setSearchfields(((BaseElement)obj).searchFields());
        //Creation des etates
        List<com.megatimgroup.generic.jax.rs.layer.impl.State> states = new ArrayList<>();
        for(State state : ((BaseElement)obj).getStates()){
            states.add(new com.megatimgroup.generic.jax.rs.layer.impl.State(state.getCode(), state.getIntitule()));
        }//end for(State state : ((BaseElement)obj).getStates())
        metaData.setStates(states);
        
        //Mise a jour ShareClass
//        shareCache.put(obj.getClass().toString(), metaData);
         //System.out.println(MetaDataUtil.class.toString()+" ===================== "+shareCache.keySet().size()+" ==== "+obj.getClass().toString());
        
        //Liste des champs disponible
        List<Field> fields = new ArrayList<Field>();
        Field[] fields_0 = obj.getClass().getSuperclass().getDeclaredFields();
        for(Field f : fields_0){
            fields.add(f);
        }//end for(Field f : fields_0){
        Field[] fields_1 =  obj.getClass().getDeclaredFields();
        for(Field f : fields_1){
            fields.add(f);
        }//end for(Field f : fields_1)
        //Traitement des donnees
        Map<String , List<Field>> groups = new HashMap<String , List<Field>>();
        List<Field> columns = new ArrayList<Field>();
        
        for(Field field : fields){
            //On verifie que le champs est annote
            Predicate annot = field.getAnnotation(Predicate.class);      
            if(annot!=null){                
                if(!annot.group()){
                    columns.add(field);
                }else if(!annot.groupName().trim().isEmpty()){
                    if(groups.get(annot.groupName())==null){
                        groups.put(annot.groupName(), new ArrayList<Field>());
                    }
                    groups.get(annot.groupName()).add(field);
                }//end if(!annot.group()){        
            }//end if(annot!=null){
        } //end for(Field field : fields){       
                       
        //Costruction des champs
        if(!columns.isEmpty()){
            for(Field field : columns){
                Predicate annot = field.getAnnotation(Predicate.class); 
                TableFooter annot2 = field.getAnnotation(TableFooter.class);                    
                if(field.getType().equals(String.class)){   
                       if(annot.target().equals("combobox")){
                          MetaColumn column = new MetaColumn(annot.target(), field.getName(), annot.label(),annot.search(), null, null);
                          column.setValue(annot.values());column.setUnique(annot.unique());column.setUpdatable(annot.updatable());column.setEditable(annot.editable());
                          column.setOptional(annot.optional());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());//column.setPattern(annot.pattern());
                          column.setHide(annot.hide());column.setCustomfooter(annot.customfooter());
                          metaData.getColumns().add(column);        
                       }else{
                           MetaColumn column = new MetaColumn(annot.target(), field.getName(), annot.label(),annot.search(), null, null);
                          column.setOptional(annot.optional());column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                          column.setUnique(annot.unique());column.setUpdatable(annot.updatable());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());
                          column.setHide(annot.hide());column.setEditable(annot.editable());column.setCustomfooter(annot.customfooter());
                          metaData.getColumns().add(column);        
                       }                               
                }else if(field.getType().equals(Date.class)){
                          MetaColumn column = new MetaColumn(annot.target(), field.getName(), annot.label(),annot.search(), null, null);
                          column.setValue(annot.values());column.setUnique(annot.unique());column.setUpdatable(annot.updatable());
                          column.setOptional(annot.optional());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());//column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                          column.setHide(annot.hide());column.setEditable(annot.editable());column.setCustomfooter(annot.customfooter());
                          metaData.getColumns().add(column);
                }else if(field.getType().equals(Double.class)||annot.type().equals(Float.class)||annot.type().equals(Short.class)
                        ||annot.type().equals(BigDecimal.class)||annot.type().equals(Integer.class)||
                        annot.type().equals(Long.class)){
                    MetaColumn column = new MetaColumn("number", field.getName(), annot.label(),annot.search(), null, null);
                    column.setOptional(annot.optional());column.setUnique(annot.unique());column.setUpdatable(annot.updatable());//column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                    column.setHide(annot.hide());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());
                    column.setEditable(annot.editable());column.setCustomfooter(annot.customfooter());
                    metaData.getColumns().add(column);
                }else if(field.getType().equals(Boolean.class)){
                    MetaColumn column = new MetaColumn("boolean", field.getName(), annot.label(),annot.search(), null, null);
                    column.setOptional(annot.optional());column.setUpdatable(annot.updatable());//column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                    column.setHide(annot.hide());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());
                    column.setEditable(annot.editable());column.setCustomfooter(annot.customfooter());
                    metaData.getColumns().add(column);
                }else if(field.getType().equals(List.class)){
                    MetaData meta = null;
//                    boolean doulons = false ;
//                    if(shareCache.containsKey(annot.type().getClass().toString())){
//                        meta = shareCache.get(annot.type().getClass().toString());
//                        exclures.add(annot.type().getClass().toString());
//                        doulons = true;
//                    }//end //end 
////                    meta = getMetaData(annot.type().newInstance(),shareCache,exclures);
                    
                    /**if(!shareCache.containsKey(annot.type().getClass().toString()))**/{
                    if(field.isAnnotationPresent(ManyToMany.class)){
                        MetaColumn column = new MetaColumn("array", field.getName(), annot.label(),annot.search(),"many-to-many", meta);
                        if(annot.target()=="many-to-many-list"){
                            column = new MetaColumn("array", field.getName(), annot.label(),annot.search(),"many-to-many-list", meta);
                        }                        
                        column.setHide(annot.hide());column.setEditable(annot.editable());column.setUpdatable(annot.updatable()); 
                        String[] searchfields = annot.searchfields().split(",");
                        column.setSearchfields(searchfields);column.setCustomfooter(annot.customfooter());
                        if(column.isCustomfooter()&&annot2!=null){
                            column.setFooterScript(annot2.value());
                        }//end if(column.isCustomfooter()&&annot2!=null)
                        StringBuilder keybuilder = new StringBuilder(obj.getClass().toString());
                        keybuilder.append(annot.type().toString());keybuilder.append(field.getName());                      
                        if(!exclures.contains(keybuilder.toString())){                           
                            metaData.getColumns().add(column);
                        }else{
                            continue;
                        }
                        /*if(shareCache.containsKey(annot.type().toString()))*/{
                            exclures.add(keybuilder.toString());
                            //doulons = true;
                        }//end //end                         
                        column.setMetaData(getMetaData(annot.type().newInstance(),shareCache,exclures));
                        column.setSequence(annot.sequence());
                        column.setColsequence(annot.colsequence()); 
                    }else if(field.isAnnotationPresent(OneToMany.class)){
                        //System.out.println("===================================================="+annot.type().newInstance());
                        MetaColumn column = new MetaColumn("array", field.getName(), annot.label(),annot.search(),"one-to-many", null);
                        column.setHide(annot.hide());column.setEditable(annot.editable());column.setUpdatable(annot.updatable());
                        column.setCustomfooter(annot.customfooter());column.setSequence(annot.sequence());
                        String[] searchfields = annot.searchfields().split(",");
                        column.setSearchfields(searchfields);column.setColsequence(annot.colsequence());
                        if(column.isCustomfooter()&&annot2!=null){
                            column.setFooterScript(annot2.value());
                        }//end if(column.isCustomfooter()&&annot2!=null)
                        StringBuilder keybuilder = new StringBuilder(obj.getClass().toString());
                        keybuilder.append(annot.type().toString());keybuilder.append(field.getName());                            
                        if(!exclures.contains(keybuilder.toString())){                           
                            metaData.getColumns().add(column);
                        }else{
                            continue;
                        }
                        /*if(shareCache.containsKey(annot.type().toString()))*/{
                             exclures.add(keybuilder.toString());
                            //doulons = true;
                        }//end //end                         
                        column.setMetaData(getMetaData(annot.type().newInstance(),shareCache,exclures));
                    }
                    }//end if(!shareCache.containsKey(annot.type().getClass().toString()))
                }else{
                    /*if(field.isAnnotationPresent(ManyToOne.class))*/{
                        MetaData meta = null;
                        boolean doublons = false ;
//                        if(shareCache.containsKey(annot.type().getClass().toString())){
//                            meta = shareCache.get(annot.type().getClass().toString());
//                            doublons = true;
//                        }else{
//                           meta = getMetaData(annot.type().newInstance(),shareCache);
//                        }//end 
                     /**if(!shareCache.containsKey(field.getType().getClass().toString()))**/{
                        MetaColumn column = new MetaColumn("object", field.getName(), annot.label(),annot.search(), "many-to-one", meta);
                        column.setOptional(annot.optional());column.setUpdatable(annot.updatable());//column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                        column.setHide(annot.hide());column.setEditable(annot.editable());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());
                        //metaData.getColumns().add(column);
                        String[] searchfields = annot.searchfields().split(",");
                        column.setSearchfields(searchfields);column.setCustomfooter(annot.customfooter());
//                        System.out.println(MetaDataUtil.class.toString()+" ==== "+annot.type().toString()+" ==== "+field.getName());
                        StringBuilder keybuilder = new StringBuilder(obj.getClass().toString());
                        keybuilder.append(annot.type().toString());keybuilder.append(field.getName());                            
                        if(!exclures.contains(keybuilder.toString())){                           
                            metaData.getColumns().add(column);
                            /*if(shareCache.containsKey(annot.type().toString()))*/{
                                 exclures.add(keybuilder.toString());
                                //doulons = true;
                            }//end //end                       
                            column.setMetaData(getMetaData(annot.type().newInstance(),shareCache,exclures));
                        }//end if(!exclures.contains(keybuilder.toString()))                      
                     }//end if(!shareCache.containsKey(annot.type().getClass().toString())){ 
                  }
                }//end if(field.isAnnotationPresent(ManyToOne.class)){
            }
        }//End if(columns
        //Traitement des groups
        if(!groups.isEmpty()){
            //Pour chaque groupe faire
            for(String key : groups.keySet()){
                //Traitement des columns
                if(!groups.get(key).isEmpty()){
                      Predicate annot = groups.get(key).get(0).getAnnotation(Predicate.class);
                      MetaGroup metaGroup = new MetaGroup(annot.groupName(),annot.groupLabel(), null);
                      metaData.getGroups().add(metaGroup);
                      for(Field field : groups.get(key)){
                        annot = field.getAnnotation(Predicate.class);
                        TableFooter annot2 = field.getAnnotation(TableFooter.class);                    
                        metaGroup.setSequence(annot.sequence());
                        if(field.getType().equals(String.class)){   
                               if(annot.target().equals("combobox")){
                                  MetaColumn column = new MetaColumn(annot.target(), field.getName(), annot.label(),annot.search(), annot.target(), null);
                                  column.setValue(annot.values());column.setUpdatable(annot.updatable());
                                  column.setEditable(annot.editable());column.setOptional(annot.optional());//column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                                  column.setHide(annot.hide());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());
                                  column.setCustomfooter(annot.customfooter());metaGroup.getColumns().add(column);        
                               }else{
                                   MetaColumn column = new MetaColumn(annot.target(), field.getName(), annot.label(),annot.search(), null, null);
                                   column.setOptional(annot.optional());column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                                   column.setUpdatable(annot.updatable());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());
                                   column.setHide(annot.hide());column.setEditable(annot.editable());column.setCustomfooter(annot.customfooter());metaGroup.getColumns().add(column);        
                               }                               
                        }else if(field.getType().equals(Date.class)){
                                MetaColumn column = new MetaColumn(annot.target(), field.getName(), annot.label(),annot.search(), null, null);
                                column.setValue(annot.values());column.setUnique(annot.unique());column.setUpdatable(annot.updatable());
                                column.setOptional(annot.optional());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());//column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                                column.setHide(annot.hide());column.setEditable(annot.editable());column.setCustomfooter(annot.customfooter());
                                metaGroup.getColumns().add(column); 
                      }else if(field.getType().equals(Double.class)||annot.type().equals(Float.class)||annot.type().equals(Short.class)
                                ||annot.type().equals(BigDecimal.class)||annot.type().equals(Integer.class)||
                                annot.type().equals(Long.class)){
                            MetaColumn column = new MetaColumn("number", field.getName(), annot.label(),annot.search(), null, null);
                            column.setOptional(annot.optional());column.setUpdatable(annot.updatable());//column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                            column.setHide(annot.hide());column.setEditable(annot.editable());column.setSequence(annot.sequence());
                            column.setCustomfooter(annot.customfooter());column.setColsequence(annot.colsequence());metaGroup.getColumns().add(column);
                        }else if(field.getType().equals(Boolean.class)){
                            MetaColumn column = new MetaColumn("boolean", field.getName(), annot.label(),annot.search(), null, null);
                            column.setUpdatable(annot.updatable());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());
                            column.setHide(annot.hide());column.setEditable(annot.editable());column.setCustomfooter(annot.customfooter());metaGroup.getColumns().add(column);
                        }else if(field.getType().equals(List.class)){
                                MetaData meta = null;
//                                boolean doublons = false;
//                                if(shareCache.containsKey(annot.type().getClass().toString())){
//                                    meta = shareCache.get(annot.type().getClass().toString());
//                                    doublons = true;
//                                }else{
//                                   meta = getMetaData(annot.type().newInstance(),shareCache);
//                                }//end 
                            /**if(!shareCache.containsKey(annot.type().getClass().toString()))**/{
                                if(field.isAnnotationPresent(ManyToMany.class)){
                                     if(annot.target().equalsIgnoreCase("many-to-many-list")){//many-to-many-list
                                         MetaArray metaArray = new MetaArray("array", field.getName(), annot.groupLabel(),annot.search(),annot.target(), meta);
                                         metaArray.setUpdatable(annot.updatable());metaArray.setCustomfooter(annot.customfooter());
                                         String[] searchfields = annot.searchfields().split(",");
                                         metaArray.setSearchfields(searchfields);
                                         if(metaArray.isCustomfooter()&&annot2!=null){
                                            metaArray.setFooterScript(annot2.value());
                                         }//end if(column.isCustomfooter()&&annot2!=null)
                                        StringBuilder keybuilder = new StringBuilder(obj.getClass().toString());
                                        keybuilder.append(annot.type().toString());keybuilder.append(field.getName());                            
                                        if(!exclures.contains(keybuilder.toString())){                           
                                            metaGroup.setMetaArray(metaArray);
                                            /*if(shareCache.containsKey(annot.type().toString()))*/{
                                                 exclures.add(keybuilder.toString());
                                                //doulons = true;
                                            }//end //end                                         
                                            metaArray.setMetaData(getMetaData(annot.type().newInstance(),shareCache,exclures));
                                        }//end if(!exclures.contains(annot.type().toString())){ 
                                        
                                        //metaGroup.setMetaArray(metaArray);
                                     }else{
                                         MetaColumn column = new MetaColumn("array", field.getName(), annot.label(),annot.search(),"many-to-many", meta);                           
                                         column.setUpdatable(annot.updatable());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());
                                         column.setHide(annot.hide());column.setEditable(annot.editable());
                                         column.setCustomfooter(annot.customfooter());
                                         String[] searchfields = annot.searchfields().split(",");
                                         column.setSearchfields(searchfields);
                                         if(column.isCustomfooter()&&annot2!=null){
                                            column.setFooterScript(annot2.value());
                                         }//end if(column.isCustomfooter()&&annot2!=null)
                                         StringBuilder keybuilder = new StringBuilder(obj.getClass().toString());
                                         keybuilder.append(annot.type().toString());keybuilder.append(field.getName());   
                                         if(!exclures.contains(keybuilder.toString())){                           
                                            metaGroup.getColumns().add(column); 
                                            /*if(shareCache.containsKey(annot.type().toString()))*/{                                                                          
                                                 exclures.add(keybuilder.toString());
                                               //doulons = true;
                                             }//end //end                       
                                             column.setMetaData(getMetaData(annot.type().newInstance(),shareCache,exclures));
                                          }//end if(!exclures.contains(annot.type().toString())){                                          
                                    }
                                }else if(field.isAnnotationPresent(OneToMany.class)){                                    
                                    MetaArray metaArray = new MetaArray("array", field.getName(), annot.groupLabel(),annot.search(),annot.target(),meta);
                                    metaArray.setUpdatable(annot.updatable());metaArray.setCustomfooter(annot.customfooter());
                                    String[] searchfields = annot.searchfields().split(",");
                                    metaArray.setSearchfields(searchfields);
                                    if(metaArray.isCustomfooter()&&annot2!=null){
                                        metaArray.setFooterScript(annot2.value());
                                    }//end if(column.isCustomfooter()&&annot2!=null)
                                    StringBuilder keybuilder = new StringBuilder(obj.getClass().toString());
                                    keybuilder.append(annot.type().toString());keybuilder.append(field.getName());  
                                    if(!exclures.contains(keybuilder.toString())){                           
                                            metaGroup.setMetaArray(metaArray);
                                            /*if(shareCache.containsKey(annot.type().toString()))*/{                                                                           
                                                 exclures.add(keybuilder.toString());
                                                //doulons = true;
                                            }//end //end 
//                                            System.out.println(MetaDataUtil.class.toString()+" ==== "+annot.type().toString()+" ==== "+field.getName());
                                            metaArray.setMetaData(getMetaData(annot.type().newInstance(),shareCache,exclures));
                                    }//end if(!exclures.contains(annot.type().toString())){
                                    //metaGroup.setMetaArray(metaArray); 
                                }//end if(!shareCache.containsKey(annot.type().getClass().toString()))
                            }//end if(!shareCache.containsKey(annot.type().getClass().toString()))
                        }else{
                            /*if(field.isAnnotationPresent(ManyToOne.class))*/{
//                            System.out.println(MetaDataUtil.class.toString()+" ==== "+annot.type().toString()+" ==== "+field.getName());
                                    MetaData meta = null;                                
                                /*if(!shareCache.containsKey(field.getType().getClass().toString()))*/{
                                    MetaColumn column = new MetaColumn("object", field.getName(), annot.label(),annot.search(), "many-to-one", meta);
                                    column.setEditable(annot.editable());column.setOptional(annot.optional());column.setUpdatable(annot.updatable());//column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                                    column.setHide(annot.hide());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());
                                    column.setCustomfooter(annot.customfooter());//metaGroup.getColumns().add(column);
                                    String[] searchfields = annot.searchfields().split(",");
                                    column.setSearchfields(searchfields);
                                    StringBuilder keybuilder = new StringBuilder(obj.getClass().toString());    
                                    keybuilder.append(annot.type().toString());keybuilder.append(field.getName());                                    
                                    if(!exclures.contains(keybuilder.toString())){                           
                                        metaGroup.getColumns().add(column); 
                                        /*if(shareCache.containsKey(annot.type().toString()))*/{
                                             exclures.add(keybuilder.toString());
                                           //doulons = true;
                                        }//end //end                       
                                        column.setMetaData(getMetaData(annot.type().newInstance(),shareCache,exclures));
                                    }else{
                                    }//end if(shareCache.containsKey(annot.type().toString())){
                                }//end if(!shareCache.containsKey(annot.type().getClass().toString()))
                          }//end if(field.isAnnotationPresent(ManyToOne.class)){
                        }
                      }//end for(Field field : groups.get(key))
                }
            }
        }
        
        return metaData;
    }
    
    
    public static MetaData getMetaDataSave(Object obj,Map<String , MetaData> shareCache,List<String> exclures) throws InstantiationException, IllegalAccessException{
        List metaDoublons = new ArrayList();
        //Si on a deja traite cete entite
//        if(shareCache.containsKey(obj.getClass().toString())){
////            System.out.println("public static MetaData getMetaData(Object obj,Map<Class<?> , MetaData> shareCache) ========= "+obj.getClass().toString());
//            return shareCache.get(obj.getClass().toString());
//        }
        MetaData metaData = new MetaData();
        metaData.setEditTitle(((BaseElement)obj).getEditTitle());
        metaData.setListTitle(((BaseElement)obj).getListTitle());
        metaData.setEntityName(obj.getClass().getSimpleName());
        metaData.setModuleName(((BaseElement)obj).getModuleName());
        metaData.setCreateonfield(((BaseElement)obj).isCreateonfield()); 
        metaData.setDesablecreate(((BaseElement)obj).isDesablecreate());
        metaData.setActivefilelink(((BaseElement)obj).isActivefilelien());
        metaData.setFooterScript(((BaseElement)obj).getFooterScript());
        metaData.setActivatefollower(((BaseElement)obj).isActivatefollower());
        metaData.setSearchfields(((BaseElement)obj).searchFields());
        //Creation des etates
        List<com.megatimgroup.generic.jax.rs.layer.impl.State> states = new ArrayList<>();
        for(State state : ((BaseElement)obj).getStates()){
            states.add(new com.megatimgroup.generic.jax.rs.layer.impl.State(state.getCode(), state.getIntitule()));
        }//end for(State state : ((BaseElement)obj).getStates())
        metaData.setStates(states);
        
        //Mise a jour ShareClass
        shareCache.put(obj.getClass().toString(), metaData);
         //System.out.println(MetaDataUtil.class.toString()+" ===================== "+shareCache.keySet().size()+" ==== "+obj.getClass().toString());
        
        //Liste des champs disponible
        List<Field> fields = new ArrayList<Field>();
        Field[] fields_0 = obj.getClass().getSuperclass().getDeclaredFields();
        for(Field f : fields_0){
            fields.add(f);
        }
        Field[] fields_1 =  obj.getClass().getDeclaredFields();
        for(Field f : fields_1){
            fields.add(f);
        }
        //Traitement des donnees
        Map<String , List<Field>> groups = new HashMap<String , List<Field>>();
        List<Field> columns = new ArrayList<Field>();
        
        for(Field field : fields){
            //On verifie que le champs est annote
            Predicate annot = field.getAnnotation(Predicate.class);      
            if(annot!=null){                
                if(!annot.group()){
                    columns.add(field);
                }else if(!annot.groupName().trim().isEmpty()){
                    if(groups.get(annot.groupName())==null){
                        groups.put(annot.groupName(), new ArrayList<Field>());
                    }
                    groups.get(annot.groupName()).add(field);
                }        
            }
        } //end for(Field field : fields){       
                       
        //Costruction des champs
        if(!columns.isEmpty()){
            for(Field field : columns){
                Predicate annot = field.getAnnotation(Predicate.class); 
                TableFooter annot2 = field.getAnnotation(TableFooter.class);                    
                if(field.getType().equals(String.class)){   
                       if(annot.target().equals("combobox")){
                          MetaColumn column = new MetaColumn(annot.target(), field.getName(), annot.label(),annot.search(), null, null);
                          column.setValue(annot.values());column.setUnique(annot.unique());column.setUpdatable(annot.updatable());column.setEditable(annot.editable());
                          column.setOptional(annot.optional());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());//column.setPattern(annot.pattern());
                          column.setHide(annot.hide());column.setCustomfooter(annot.customfooter());
                          metaData.getColumns().add(column);        
                       }else{
                           MetaColumn column = new MetaColumn(annot.target(), field.getName(), annot.label(),annot.search(), null, null);
                          column.setOptional(annot.optional());column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                          column.setUnique(annot.unique());column.setUpdatable(annot.updatable());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());
                          column.setHide(annot.hide());column.setEditable(annot.editable());column.setCustomfooter(annot.customfooter());
                          metaData.getColumns().add(column);        
                       }                               
                }else if(field.getType().equals(Date.class)){
                          MetaColumn column = new MetaColumn(annot.target(), field.getName(), annot.label(),annot.search(), null, null);
                          column.setValue(annot.values());column.setUnique(annot.unique());column.setUpdatable(annot.updatable());
                          column.setOptional(annot.optional());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());//column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                          column.setHide(annot.hide());column.setEditable(annot.editable());column.setCustomfooter(annot.customfooter());
                          metaData.getColumns().add(column);
                }else if(field.getType().equals(Double.class)||annot.type().equals(Float.class)||annot.type().equals(Short.class)
                        ||annot.type().equals(BigDecimal.class)||annot.type().equals(Integer.class)||
                        annot.type().equals(Long.class)){
                    MetaColumn column = new MetaColumn("number", field.getName(), annot.label(),annot.search(), null, null);
                    column.setOptional(annot.optional());column.setUnique(annot.unique());column.setUpdatable(annot.updatable());//column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                    column.setHide(annot.hide());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());
                    column.setEditable(annot.editable());column.setCustomfooter(annot.customfooter());
                    metaData.getColumns().add(column);
                }else if(field.getType().equals(Boolean.class)){
                    MetaColumn column = new MetaColumn("boolean", field.getName(), annot.label(),annot.search(), null, null);
                    column.setOptional(annot.optional());column.setUpdatable(annot.updatable());//column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                    column.setHide(annot.hide());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());
                    column.setEditable(annot.editable());column.setCustomfooter(annot.customfooter());
                    metaData.getColumns().add(column);
                }else if(field.getType().equals(List.class)){
                    MetaData meta = null;
//                    boolean doulons = false ;
//                    if(shareCache.containsKey(annot.type().getClass().toString())){
//                        meta = shareCache.get(annot.type().getClass().toString());
//                        exclures.add(annot.type().getClass().toString());
//                        doulons = true;
//                    }//end //end 
////                    meta = getMetaData(annot.type().newInstance(),shareCache,exclures);
                    
                    /**if(!shareCache.containsKey(annot.type().getClass().toString()))**/{
                    if(field.isAnnotationPresent(ManyToMany.class)){
                        MetaColumn column = new MetaColumn("array", field.getName(), annot.label(),annot.search(),"many-to-many", meta);
                        if(annot.target()=="many-to-many-list"){
                            column = new MetaColumn("array", field.getName(), annot.label(),annot.search(),"many-to-many-list", meta);
                        }                        
                        column.setHide(annot.hide());column.setEditable(annot.editable());column.setUpdatable(annot.updatable()); 
                        String[] searchfields = annot.searchfields().split(",");
                        column.setSearchfields(searchfields);column.setCustomfooter(annot.customfooter());
                        if(column.isCustomfooter()&&annot2!=null){
                            column.setFooterScript(annot2.value());
                        }//end if(column.isCustomfooter()&&annot2!=null)
                        if(!exclures.contains(annot.type().toString())){                           
                            metaData.getColumns().add(column);
                        }else{
                            continue;
                        }
                        if(shareCache.containsKey(annot.type().toString())){
                            exclures.add(annot.type().toString());
                            //doulons = true;
                        }//end //end                         
                        column.setMetaData(getMetaData(annot.type().newInstance(),shareCache,exclures));
                        column.setSequence(annot.sequence());
                        column.setColsequence(annot.colsequence()); 
                    }else if(field.isAnnotationPresent(OneToMany.class)){
                        //System.out.println("===================================================="+annot.type().newInstance());
                        MetaColumn column = new MetaColumn("array", field.getName(), annot.label(),annot.search(),"one-to-many", null);
                        column.setHide(annot.hide());column.setEditable(annot.editable());column.setUpdatable(annot.updatable());
                        column.setCustomfooter(annot.customfooter());column.setSequence(annot.sequence());
                        String[] searchfields = annot.searchfields().split(",");
                        column.setSearchfields(searchfields);column.setColsequence(annot.colsequence());
                        if(column.isCustomfooter()&&annot2!=null){
                            column.setFooterScript(annot2.value());
                        }//end if(column.isCustomfooter()&&annot2!=null)
                        if(!exclures.contains(annot.type().toString())){                           
                            metaData.getColumns().add(column);
                        }else{
                            continue;
                        }
                        if(shareCache.containsKey(annot.type().toString())){
                            exclures.add(annot.type().toString());
                            //doulons = true;
                        }//end //end                         
                        column.setMetaData(getMetaData(annot.type().newInstance(),shareCache,exclures));
                    }
                    }//end if(!shareCache.containsKey(annot.type().getClass().toString()))
                }else{
                    /*if(field.isAnnotationPresent(ManyToOne.class))*/{
                        MetaData meta = null;
                        boolean doublons = false ;
//                        if(shareCache.containsKey(annot.type().getClass().toString())){
//                            meta = shareCache.get(annot.type().getClass().toString());
//                            doublons = true;
//                        }else{
//                           meta = getMetaData(annot.type().newInstance(),shareCache);
//                        }//end 
                     /**if(!shareCache.containsKey(field.getType().getClass().toString()))**/{
                        MetaColumn column = new MetaColumn("object", field.getName(), annot.label(),annot.search(), "many-to-one", meta);
                        column.setOptional(annot.optional());column.setUpdatable(annot.updatable());//column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                        column.setHide(annot.hide());column.setEditable(annot.editable());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());
                        //metaData.getColumns().add(column);
                        String[] searchfields = annot.searchfields().split(",");
                        column.setSearchfields(searchfields);column.setCustomfooter(annot.customfooter());
//                        System.out.println(MetaDataUtil.class.toString()+" ==== "+annot.type().toString()+" ==== "+field.getName());
                        if(!exclures.contains(annot.type().toString())){                           
                            metaData.getColumns().add(column);
                            if(shareCache.containsKey(annot.type().toString())){
                                exclures.add(annot.type().toString());
                                //doulons = true;
                            }//end //end                       
                            column.setMetaData(getMetaData(annot.type().newInstance(),shareCache,exclures));
                        }                      
                     }//end if(!shareCache.containsKey(annot.type().getClass().toString())){ 
                  }
                }//end if(field.isAnnotationPresent(ManyToOne.class)){
            }
        }//End if(columns
        //Traitement des groups
        if(!groups.isEmpty()){
            //Pour chaque groupe faire
            for(String key : groups.keySet()){
                //Traitement des columns
                if(!groups.get(key).isEmpty()){
                      Predicate annot = groups.get(key).get(0).getAnnotation(Predicate.class);
                      MetaGroup metaGroup = new MetaGroup(annot.groupName(),annot.groupLabel(), null);
                      metaData.getGroups().add(metaGroup);
                      for(Field field : groups.get(key)){
                        annot = field.getAnnotation(Predicate.class);
                        TableFooter annot2 = field.getAnnotation(TableFooter.class);                    
                        metaGroup.setSequence(annot.sequence());
                        if(field.getType().equals(String.class)){   
                               if(annot.target().equals("combobox")){
                                  MetaColumn column = new MetaColumn(annot.target(), field.getName(), annot.label(),annot.search(), annot.target(), null);
                                  column.setValue(annot.values());column.setUpdatable(annot.updatable());
                                  column.setEditable(annot.editable());column.setOptional(annot.optional());//column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                                  column.setHide(annot.hide());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());
                                  column.setCustomfooter(annot.customfooter());metaGroup.getColumns().add(column);        
                               }else{
                                   MetaColumn column = new MetaColumn(annot.target(), field.getName(), annot.label(),annot.search(), null, null);
                                   column.setOptional(annot.optional());column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                                   column.setUpdatable(annot.updatable());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());
                                   column.setHide(annot.hide());column.setEditable(annot.editable());column.setCustomfooter(annot.customfooter());metaGroup.getColumns().add(column);        
                               }                               
                        }else if(field.getType().equals(Date.class)){
                                MetaColumn column = new MetaColumn(annot.target(), field.getName(), annot.label(),annot.search(), null, null);
                                column.setValue(annot.values());column.setUnique(annot.unique());column.setUpdatable(annot.updatable());
                                column.setOptional(annot.optional());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());//column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                                column.setHide(annot.hide());column.setEditable(annot.editable());column.setCustomfooter(annot.customfooter());
                                metaGroup.getColumns().add(column); 
                      }else if(field.getType().equals(Double.class)||annot.type().equals(Float.class)||annot.type().equals(Short.class)
                                ||annot.type().equals(BigDecimal.class)||annot.type().equals(Integer.class)||
                                annot.type().equals(Long.class)){
                            MetaColumn column = new MetaColumn("number", field.getName(), annot.label(),annot.search(), null, null);
                            column.setOptional(annot.optional());column.setUpdatable(annot.updatable());//column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                            column.setHide(annot.hide());column.setEditable(annot.editable());column.setSequence(annot.sequence());
                            column.setCustomfooter(annot.customfooter());column.setColsequence(annot.colsequence());metaGroup.getColumns().add(column);
                        }else if(field.getType().equals(Boolean.class)){
                            MetaColumn column = new MetaColumn("boolean", field.getName(), annot.label(),annot.search(), null, null);
                            column.setUpdatable(annot.updatable());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());
                            column.setHide(annot.hide());column.setEditable(annot.editable());column.setCustomfooter(annot.customfooter());metaGroup.getColumns().add(column);
                        }else if(field.getType().equals(List.class)){
                                MetaData meta = null;
//                                boolean doublons = false;
//                                if(shareCache.containsKey(annot.type().getClass().toString())){
//                                    meta = shareCache.get(annot.type().getClass().toString());
//                                    doublons = true;
//                                }else{
//                                   meta = getMetaData(annot.type().newInstance(),shareCache);
//                                }//end 
                            /**if(!shareCache.containsKey(annot.type().getClass().toString()))**/{
                                if(field.isAnnotationPresent(ManyToMany.class)){
                                     if(annot.target().equalsIgnoreCase("many-to-many-list")){//many-to-many-list
                                         MetaArray metaArray = new MetaArray("array", field.getName(), annot.groupLabel(),annot.search(),annot.target(), meta);
                                         metaArray.setUpdatable(annot.updatable());metaArray.setCustomfooter(annot.customfooter());
                                         String[] searchfields = annot.searchfields().split(",");
                                         metaArray.setSearchfields(searchfields);
                                         if(metaArray.isCustomfooter()&&annot2!=null){
                                            metaArray.setFooterScript(annot2.value());
                                         }//end if(column.isCustomfooter()&&annot2!=null)
                                        if(!exclures.contains(annot.type().toString())){                           
                                            metaGroup.setMetaArray(metaArray);
                                            if(shareCache.containsKey(annot.type().toString())){
                                                exclures.add(annot.type().toString());
                                                //doulons = true;
                                            }//end //end                                         
                                            metaArray.setMetaData(getMetaData(annot.type().newInstance(),shareCache,exclures));
                                        }//end if(!exclures.contains(annot.type().toString())){ 
                                        
                                        //metaGroup.setMetaArray(metaArray);
                                     }else{
                                         MetaColumn column = new MetaColumn("array", field.getName(), annot.label(),annot.search(),"many-to-many", meta);                           
                                         column.setUpdatable(annot.updatable());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());
                                         column.setHide(annot.hide());column.setEditable(annot.editable());
                                         column.setCustomfooter(annot.customfooter());
                                         String[] searchfields = annot.searchfields().split(",");
                                         column.setSearchfields(searchfields);
                                         if(column.isCustomfooter()&&annot2!=null){
                                            column.setFooterScript(annot2.value());
                                         }//end if(column.isCustomfooter()&&annot2!=null)
                                         if(!exclures.contains(annot.type().toString())){                           
                                            metaGroup.getColumns().add(column); 
                                            if(shareCache.containsKey(annot.type().toString())){
                                                exclures.add(annot.type().toString());
                                               //doulons = true;
                                             }//end //end                       
                                             column.setMetaData(getMetaData(annot.type().newInstance(),shareCache,exclures));
                                          }//end if(!exclures.contains(annot.type().toString())){                                          
                                    }
                                }else if(field.isAnnotationPresent(OneToMany.class)){                                    
                                    MetaArray metaArray = new MetaArray("array", field.getName(), annot.groupLabel(),annot.search(),annot.target(),meta);
                                    metaArray.setUpdatable(annot.updatable());metaArray.setCustomfooter(annot.customfooter());
                                    String[] searchfields = annot.searchfields().split(",");
                                    metaArray.setSearchfields(searchfields);
                                    if(metaArray.isCustomfooter()&&annot2!=null){
                                        metaArray.setFooterScript(annot2.value());
                                    }//end if(column.isCustomfooter()&&annot2!=null)
                                    if(!exclures.contains(annot.type().toString())){                           
                                            metaGroup.setMetaArray(metaArray);
                                            if(shareCache.containsKey(annot.type().toString())){
                                                exclures.add(annot.type().toString());
                                                //doulons = true;
                                            }//end //end 
//                                            System.out.println(MetaDataUtil.class.toString()+" ==== "+annot.type().toString()+" ==== "+field.getName());
                                            metaArray.setMetaData(getMetaData(annot.type().newInstance(),shareCache,exclures));
                                    }//end if(!exclures.contains(annot.type().toString())){
                                    //metaGroup.setMetaArray(metaArray); 
                                }//end if(!shareCache.containsKey(annot.type().getClass().toString()))
                            }//end if(!shareCache.containsKey(annot.type().getClass().toString()))
                        }else{
                            /*if(field.isAnnotationPresent(ManyToOne.class))*/{
//                            System.out.println(MetaDataUtil.class.toString()+" ==== "+annot.type().toString()+" ==== "+field.getName());
                                    MetaData meta = null;                                
                                /*if(!shareCache.containsKey(field.getType().getClass().toString()))*/{
                                    MetaColumn column = new MetaColumn("object", field.getName(), annot.label(),annot.search(), "many-to-one", meta);
                                    column.setEditable(annot.editable());column.setOptional(annot.optional());column.setUpdatable(annot.updatable());//column.setMin(annot.min());column.setMax(annot.max());column.setPattern(annot.pattern());
                                    column.setHide(annot.hide());column.setSequence(annot.sequence());column.setColsequence(annot.colsequence());
                                    column.setCustomfooter(annot.customfooter());//metaGroup.getColumns().add(column);
                                    String[] searchfields = annot.searchfields().split(",");
                                    column.setSearchfields(searchfields);
                                    if(!exclures.contains(annot.type().toString())){                           
                                        metaGroup.getColumns().add(column); 
                                        if(shareCache.containsKey(annot.type().toString())){
                                            exclures.add(annot.type().toString());
                                           //doulons = true;
                                        }//end //end                       
                                        column.setMetaData(getMetaData(annot.type().newInstance(),shareCache,exclures));
                                    }else{
                                    }//end if(shareCache.containsKey(annot.type().toString())){
                                }//end if(!shareCache.containsKey(annot.type().getClass().toString()))
                          }//end if(field.isAnnotationPresent(ManyToOne.class)){
                        }
                      }//end for(Field field : groups.get(key))
                }
            }
        }
        
        return metaData;
    }
}
