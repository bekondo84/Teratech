/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.jax.rs.layer.annot;

import java.lang.reflect.Field;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Traitement des annotation de ce module
 * @author Commercial_2
 */
public class AnnotationsProcessor {
    
    
    /**
     * Cette fonction est responsable de tous les annotation que l'on peut 
     * rencontre dans un service
     * @param entity 
     * @param props 
     */
    public void process(Object entity ) throws NamingException, IllegalArgumentException, IllegalAccessException{
        
        if(entity==null)
                return  ;
        
        Field[] fields = entity.getClass().getDeclaredFields();
        
        for(Field field : fields){            
            ManagerAnnotationsProcessor(entity,field);
        }
    }
    
    /**
     * 
     * @param entity
     * @param field 
     */
    private void ManagerAnnotationsProcessor(Object entity , Field field) throws NamingException, IllegalArgumentException, IllegalAccessException{
        
        if(!field.isAnnotationPresent(Manager.class))
            return ;
        //Recuperation de l'annotation 
        Manager annot = field.getAnnotation(Manager.class);
        //
        if(annot.spec().equals(Spec.EJB)){
            
            Jndi jndi = new Jndi();

            String jndiName = jndi.build(annot);
            
            System.out.println("::::::::::::::::::::::::::: "+jndiName);
            
            field.setAccessible(true);

            InitialContext  ctx = new InitialContext();
       
            field.set(entity, ctx.lookup(jndiName));
        }
        
        
    }
    
    private interface JNDIBulder{
        /**
         * 
         * @param annot
         * @param jndi
         * @return 
         */
        public String build(Manager annot , String jndi);
    }
    
    
    private class JndiApp implements JNDIBulder{

        private JNDIBulder _nextBuilder  ;

        /**
         * 
         * @param _nextBuilder 
         */
        public JndiApp(JNDIBulder _nextBuilder) {
            this._nextBuilder = _nextBuilder;
        }
        
        
        @Override
        public String build(Manager annot, String jndi) {
            //To change body of generated methods, choose Tools | Templates.
            //To change body of generated methods, choose Tools | Templates.
             StringBuilder builder = new StringBuilder();
            
            //Verifier si le nom est fourni
            if(!annot.application().trim().isEmpty()){
                builder.append("java:global/");
                builder.append(annot.application());
            }else {
                builder.append("java:module");
            }
           
            return builder.toString();
        }
        
    }
    /**
     * Build the module part of JNDI name
     */
    private class JndiModule implements JNDIBulder{

         private JNDIBulder _nextBuilder  ;

         
         /**
          * 
          * @param _nextBuilder 
          */
        public JndiModule(JNDIBulder _nextBuilder) {
            this._nextBuilder = _nextBuilder;
        }
         
         
        
        @Override
        public String build(Manager annot, String jndi) {
            //To change body of generated methods, choose Tools | Templates.
             StringBuilder builder = new StringBuilder("");
            
            //Verifier si le nom est fourni
            if(!annot.module().trim().isEmpty()){
                builder.append(annot.module());
            }
           
            StringBuilder concat = new StringBuilder();
            
            concat.append(_nextBuilder.build(annot, jndi));
            
            if(!builder.toString().trim().isEmpty()){
                concat.append("/");
            }
            
            concat.append(builder.toString());
            
            return concat.toString();
        }
        
    }
    /**
     * This calls build de end of the JNID String
     */
    private class JndiName implements JNDIBulder{

        private JNDIBulder _nextBuilder  ;

        
        /**
         * 
         * @param _nextBuilder 
         */
        public JndiName(JNDIBulder _nextBuilder) {
            this._nextBuilder = _nextBuilder;
        }
        
        
        
        @Override
        public String build(Manager annot ,  String jndi) {
            //To change body of generated methods, choose Tools | Templates.
            StringBuilder builder = new StringBuilder();
            
            //Verifier si le nom est fourni
            if(!annot.name().trim().isEmpty()){
                builder.append(annot.name());
            }
            //Verifiacation de l'interface
            if(!annot.interf().equals(String.class)){
                builder.append("!"+annot.interf().getName());
            }
            
            StringBuilder concat = new StringBuilder();
            
            concat.append(_nextBuilder.build(annot, jndi));
            
            if(!builder.toString().trim().isEmpty()){
                concat.append("/");
            }
            
            concat.append(builder.toString());            
            
            return concat.toString();
        }
        
    }
    
    private class Jndi {
        
        public String build(Manager annot){
            
            JndiApp app = new JndiApp(null);
            
            JndiModule module = new JndiModule(app);
            
            JndiName name = new JndiName(module);
            
            return name.build(annot,null);
        }
    }
    
}
