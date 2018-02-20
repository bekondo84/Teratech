/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.jax.rs.layer.impl;


import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.megatim.common.annotations.OrderType;
import com.megatimgroup.generic.jax.rs.layer.annot.AnnotationsProcessor;
import com.megatimgroup.generic.jax.rs.layer.ifaces.GenericService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Commercial_2
 */
public  abstract class AbstractGenericService< T , PK extends Serializable> implements GenericService<T , PK>{

    /**
     * Constructeur par defaut
     */
    public AbstractGenericService() {
        
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

    /**
     * Nom du fichier JNDI a utiliser pour 
     * localiser les Manager distant
     * @return 
     */
    public String jndiPropertiesFile(){
         return null ;
    }
    
    /**
     * Mise a jour du referentiel bancaire
     * @param entity
     * @return 
     */
    @Override
    public T save(T entity) {
        //To change body of generated methods, choose Tools | Templates.
        //Verifier que les conditions son OK
        processBeforeSave(entity);
        //Execution du service adapter
        T resultat =  getManager().save(entity);
        //Execution de la post condition
        processAfterSave(entity);
        //Retour du resultat
        return resultat;
    }

    /**
     * Mise a jour en masse des donnÃ©es
     * @param entities 
     */
    @Override
    public void save(List<T> entities) {
        //To change body of generated methods, choose Tools | Templates.
        processBeforeSave(entities);
        getManager().save(entities);
        processAfterSave(entities);
    }

    /**
     * 
     * @param id
     * @param entity
     * @return 
     */
    @Override
    public T update(PK id, T entity) {
       //To change body of generated methods, choose Tools | Templates.
//        String userid = headers.getRequestHeader("userid").get(0);
        processBeforeUpdate(entity);
        T result = getManager().update(id, entity);
        processAfterUpdate(entity);
        return result ;
    }

    /**
     * 
     * @param entities 
     */
    @Override
    public void update(Map<PK, T> entities) {
        //To change body of generated methods, choose Tools | Templates.
        processBeforeUpdate(entities);
        getManager().update(entities);
        processAfterUpdate(entities);
    }

    /**
     * 
     * @param id
     * @return 
     */
    @Override
    public T delete(PK id) {
        //To change body of generated methods, choose Tools | Templates.
        processBeforeDelete(id);
        T result = getManager().delete(id);
        processAfterDelete(id);
        return result;
        
    }

    @Override
    public  void deleteAll(@Context HttpHeaders headers){
        Gson gson =new Gson();
//         System.out.print(headers.getRequestHeader("ids"));
         List ids = gson.fromJson(headers.getRequestHeader("ids").get(0),new TypeToken<List<Long>>(){}.getType());
         if(ids!=null){
             for(Object id : ids){
                 delete((PK) id);
             }
         }
    }
    
    

    @Override
    public T find(String propertyName, PK id) {
        //To change body of generated methods, choose Tools | Templates.
        return getManager().find(propertyName, id);
    }

    @Override
    public boolean unique(String propertyName, String value){
        List result = findByUniqueProperty(propertyName,value);
         //System.out.println(AbstractGenericService.class.getCanonicalName()+" ==== "+result+" === property : "+propertyName+" === value:"+value);       
        return (result==null||result.isEmpty());
    }

    @Override
    public List<FilterPredicat> uniqueProperties(@Context HttpHeaders headers){
        List<FilterPredicat> results = new ArrayList<FilterPredicat>();
        Gson gson =new Gson();
        //Type predType = ;
        List contraints = gson.fromJson(headers.getRequestHeader("properties").get(0),new TypeToken<List<FilterPredicat>>(){}.getType());
        //System.out.println(AbstractGenericService.class.toString()+" === "+headers.getRequestHeader("properties").get(0));            
        if(contraints!=null&&!contraints.isEmpty()){
            for(Object obj : contraints){
                FilterPredicat contraint = (FilterPredicat) obj;
                if(!unique(contraint.getFieldName(), contraint.getFieldValue())){
                    results.add(contraint);
                }
            }
        }
        return results;
    }    
    
    

    @Override
    public List<T> findAll() {
         //To change body of generated methods, choose Tools | Templates.
        return getManager().findAll();
    }

    //@Override
    public List<T> findByUniqueProperty(String propertyName, Object propertyValue, Set<String> properties) {
        //To change body of generated methods, choose Tools | Templates.
        return getManager().findByUniqueProperty(propertyName, propertyValue, properties);
    }

    @Override
    public  List<T> findByUniqueProperty(String propertyName, short propertyValue){
        //To change body of generated methods, choose Tools | Templates.
        HashSet<String> properties = new HashSet<String>();
        return getManager().findByUniqueProperty(propertyName, propertyValue, properties);
    }

    @Override
    public List<T> findByUniqueProperty(String propertyName, int propertyValue){
        HashSet<String> properties = new HashSet<String>();
        return getManager().findByUniqueProperty(propertyName, propertyValue, properties);
    }

    @Override
    public List<T> findByUniqueProperty(String propertyName, String propertyValue){
        //To change body of generated methods, choose Tools | Templates.
        HashSet<String> properties = new HashSet<String>();
        return getManager().findByUniqueProperty(propertyName, propertyValue, properties);
    }

    @Override
    public List<T> findByUniqueProperty(String propertyName, long propertyValue){
        //To change body of generated methods, choose Tools | Templates.
        HashSet<String> properties = new HashSet<String>();
        return getManager().findByUniqueProperty(propertyName, propertyValue, properties);
    }
    
    

    /**
     * 
     * @param headers
     * @param firstResult
     * @param maxResult
     * @return 
     */
    @Override
    public List<T> filter(@Context HttpHeaders headers ,int firstResult, int maxResult) {
        //To change body of generated methods, choose Tools | Templates.        
        Gson gson =new Gson();
        //Type predType = ;
        List contraints = new ArrayList();
        if(headers.getRequestHeader("predicats")!=null){
            contraints = gson.fromJson(headers.getRequestHeader("predicats").get(0),new TypeToken<List<FilterPredicat>>(){}.getType());
        }        
//        System.out.println(AbstractGenericService.class.toString()+" === "+headers.getRequestHeader("predicats")+" === "+firstResult+" === "+maxResult+" == "+contraints);   
        RestrictionsContainer container = RestrictionsContainer.newInstance();  
        if(contraints!=null&&!contraints.isEmpty()){
            for(Object obj : contraints){
                FilterPredicat filter = (FilterPredicat) obj ;
                if(filter.getFieldName()!=null&&!filter.getFieldName().trim().isEmpty()
                        &&filter.getFieldValue()!=null&&!filter.getFieldValue().isEmpty()){
                    if(filter.getSearchfields()==null||filter.getSearchfields().length<=0){
                        container.addEq(filter.getFieldName(), filter.getFieldValue());
                    }else{
                          container.addEq(filter.getFieldName()+"."+filter.getSearchfields()[0], filter.getFieldValue());
//                        for(String fieldname:filter.getSearchfields()){
//                            if(fieldname!=null&&!fieldname.trim().isEmpty()){
//                                container.addEq(filter.getFieldName()+"."+fieldname, filter.getFieldValue());
//                            }//end if(fieldname!=null&&!fieldname.trim().isEmpty())
//                        }//end for(String fieldname:filter.getSearchfields())
                    }
                }//end if(filter.getFieldName()!=null&&!filter.getFieldName().trim().isEmpty()
            }//end  for(Object obj : contraints)
        }//end if(contraints!=null&&!contraints.isEmpty())
        //List result = new ArrayList();
        return getManager().filter(container.getPredicats(), null , new HashSet<String>(), firstResult, maxResult);
    }

    @Override
    public List<T> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, Map<String, Object> hints, int firstResult, int maxResult) {
        //To change body of generated methods, choose Tools | Templates.
        return getManager().filter(predicats, orders, properties, hints, firstResult, maxResult);
    }

    @Override
    public RSNumber count(@Context HttpHeaders headers) {
        //To change body of generated methods, choose Tools | Templates.
         Gson gson =new Gson();
        //Type predType = ;
        List contraints = new ArrayList();
        if(headers.getRequestHeader("predicats")!=null){
            contraints = gson.fromJson(headers.getRequestHeader("predicats").get(0),new TypeToken<List<FilterPredicat>>(){}.getType());
        }
        
        RestrictionsContainer container = RestrictionsContainer.newInstance();  
        if(contraints!=null&&!contraints.isEmpty()){
            for(Object obj : contraints){
                FilterPredicat filter = (FilterPredicat) obj ;
                //System.out.println(AbstractGenericService.class.toString()+" === "+filter+" === "+firstResult+" === "+maxResult);   
                if(filter.getFieldName()!=null&&!filter.getFieldName().trim().isEmpty()
                        &&filter.getFieldValue()!=null&&!filter.getFieldValue().isEmpty()){
                    container.addEq(filter.getFieldName(), filter.getFieldValue());
                }
            }
        }
        RSNumber number = new RSNumber(getManager().count(container.getPredicats()));
        return number;
    }

    @Override
    public void clean() {
        //To change body of generated methods, choose Tools | Templates.
         getManager().clean();
    }

    @Override
    public void flush() {
       //To change body of generated methods, choose Tools | Templates.
        getManager().flush();
    }

    @Override
    public  MetaData getMetaData(@Context HttpHeaders headers){
        return new MetaData();
    }

    

    
    @SuppressWarnings("empty-statement")
    protected void processBeforeSave(T entity) {
        ; //To change body of generated methods, choose Tools | Templates.
    }

    
    protected void processAfterSave(T entity) {
        ; //To change body of generated methods, choose Tools | Templates.
    }

   
    protected void processBeforeSave(List entities) {
        ; //To change body of generated methods, choose Tools | Templates.
    }

    protected void processAfterSave(List entities) {
        ; //To change body of generated methods, choose Tools | Templates.
    }

    
    protected void processBeforeUpdate(T entity) {
        ; //To change body of generated methods, choose Tools | Templates.
    }

    
    protected void processAfterUpdate(T entity) {
        ; //To change body of generated methods, choose Tools | Templates.
    }

    
    protected void processBeforeUpdate(Map entity) {
        ; //To change body of generated methods, choose Tools | Templates.
    }

    
    protected void processAfterUpdate(Map entity) {
        ; //To change body of generated methods, choose Tools | Templates.
    }

    
    protected void processBeforeDelete(Object entity) {
       ; //To change body of generated methods, choose Tools | Templates.
    }

    
    protected void processAfterDelete(Object entity) {
        ; //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Return  PDF file
     * @param file
     * @return
     * @throws FileNotFoundException 
     */
    public Response getPdf(File file) throws FileNotFoundException{
        FileInputStream fileInputStream = new FileInputStream(file);
        Response.ResponseBuilder responseBuilder = Response.ok((Object)fileInputStream);
        responseBuilder.type("application/pdf");
        responseBuilder.header("Content-Disposition", "filename="+file.getName()+".pdf");
        return responseBuilder.build();
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    public Response getImage(File file) throws IOException{
        BufferedImage image = ImageIO.read(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imageData = baos.toByteArray();
        Response.ResponseBuilder responseBuilder = Response.ok(imageData);
        return responseBuilder.build();
    }
    
    /**
     * Build jasper PDF from 
     * @param reportFile:fichier template  .jasper
     * @param fileName:"fichier de sortie
     * @param parametres:"parametres fichiers jasper
     * @param data:données 
     * @throws JRException 
     */
    public void jasperPdfBuilder(File reportFile,String fileName,Map parametres,List data) throws JRException{
        JasperReport report = null;
        JasperPrint jasperPrint = null;
        //Chargement du rapport
        report = (JasperReport) JRLoader.loadObject(reportFile);
        jasperPrint = JasperFillManager.fillReport(report,parametres, new JRBeanCollectionDataSource(data,false));
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
    }
    
    /**
     * 
     * @param temporalDir
     * @param template
     * @param parameters
     * @param datas
     * @return 
     */
    public Response buildReportFomTemplate(String temporalDir,String template , Map parameters, List datas) throws FileNotFoundException, JRException{
        Date date = new Date();
        String temporalfile = temporalDir+File.separator+String.valueOf(date.getTime())+".pdf";
        jasperPdfBuilder(new File(template), temporalfile, parameters, datas);
        //To change body of generated methods, choose Tools | Templates.
        File file = new File(temporalfile);        
        return getPdf(file);
    }
}
