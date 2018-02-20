package com.megatim.common.decorators;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;

public class SecurityManagerDecorator implements GenericManagerDecorator {

        /**
	 * Manager a decorer
	 */
	private GenericManager manager ;
        
        private String[] roles =null;
        
        /**
         * 
         * @param manager 
     * @param roles 
         */
	public SecurityManagerDecorator(GenericManager manager,String[] roles) {
		// TODO Auto-generated constructor stub
            
            this.manager = manager ;
            
            this.roles = roles;
	}

	public GenericDAO getDao() {
		// TODO Auto-generated method stub
		return manager.getDao();
	}

	public Object save(Object entity) {
		// TODO Auto-generated method stub
               //System.out.println("SecurityManagerDecorator.save(Object entity) :::::::::::::::::::::: "+entity+" :::::::: "+roles+CurrentConnectedUser.isCurrentUserInRole(roles));
               //if(CurrentConnectedUser.isCurrentUserInRole(roles)){
                   return manager.save(entity);
               /*}else {
                   throw new SecurityException("insuffisant.rigth.level");
               }*/
	}

        /**
         * 
         * @param entities 
         */
        public void save(List entities) {
           //To change body of generated methods, choose Tools | Templates.
            //if(CurrentConnectedUser.isCurrentUserInRole(roles)){
                manager.save(entities);
            /*}else {
                throw new SecurityException("insuffisant.rigth.level");
            }*/
        }

	public Object update(Serializable id, Object entity) {            
		// TODO Auto-generated method stub
               //System.out.println("SecurityManagerDecorator.save(Object entity) :::::::::::::::::::::: "+entity+" :::::::: "+roles+CurrentConnectedUser.isCurrentUserInRole(roles));
		//if(CurrentConnectedUser.isCurrentUserInRole(roles)){
                   return manager.update(id,entity);
               /*}else {
                   throw new SecurityException("insuffisant.rigth.level");
               }*/
	}

        /**
         * 
         * @param entities 
         */
        public void update(Map entities) {
            //To change body of generated methods, choose Tools | Templates.
            //if(CurrentConnectedUser.isCurrentUserInRole(roles)){
                   manager.update(entities);
               /*}else {
                   throw new SecurityException("insuffisant.rigth.level");
               }*/
        }

      
        

	public Object delete(Serializable id) {
		// TODO Auto-generated method stub
		//if(CurrentConnectedUser.isCurrentUserInRole(roles)){
                   return manager.delete(id);
               /*}else {
                   throw new SecurityException("insuffisant.rigth.level");
               }*/
	}

	public Object find(String propertyName, Serializable id) {
		// TODO Auto-generated method stub
		//if(CurrentConnectedUser.isCurrentUserInRole(roles)){
                   return manager.find(propertyName,id);
              /* }else {
                   throw new SecurityException("insuffisant.rigth.level");
               }*/
	}
        
        

	public List findAll() {
		// TODO Auto-generated method stub
		//if(CurrentConnectedUser.isCurrentUserInRole(roles)){
                   return manager.findAll();
               /*}else {
                   throw new SecurityException("insuffisant.rigth.level");
               }*/
	}

	public List findByUniqueProperty(String propertyName, Object propertyValue,
			Set properties) {
		// TODO Auto-generated method stub
		//if(CurrentConnectedUser.isCurrentUserInRole(roles)){
                   return manager.findByUniqueProperty(propertyName,
                                           propertyValue,properties);
               /*}else {
                   throw new SecurityException("insuffisant.rigth.level");
               }*/
	}

        /**
         * 
         * @param predicats
         * @param orders
         * @param properties
         * @param firstResult
         * @param maxResult
         * @return 
         */
	public List filter(List predicats, Map orders, Set properties,
			int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		//if(CurrentConnectedUser.isCurrentUserInRole(roles)){
                   return manager.filter(predicats,orders,properties,firstResult,maxResult);
               /*}else {
                   throw new SecurityException("insuffisant.rigth.level");
               }*/
	}
        
        /**
         * 
         * @param predicats
         * @param orders
         * @param properties
         * @param firstResult
         * @param maxResult
         * @return 
         */
	public List filter(List predicats, Map orders, Set properties, Map hints ,
			int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		//if(CurrentConnectedUser.isCurrentUserInRole(roles)){
                   return manager.filter(predicats,orders,properties,hints,firstResult,maxResult);
               /*}else {
                   throw new SecurityException("insuffisant.rigth.level");
               }*/
	}

	public Long count(List predicats) {
		// TODO Auto-generated method stub
		//if(CurrentConnectedUser.isCurrentUserInRole(roles)){
                   return manager.count(predicats);
               /*}else {
                   throw new SecurityException("insuffisant.rigth.level");
               }*/
	}

	public void clean() {
		// TODO Auto-generated method stub
		//if(CurrentConnectedUser.isCurrentUserInRole(roles)){
                   manager.clean();
               /*}else {
                   throw new SecurityException("insuffisant.rigth.level");
               }*/
	}

	public void flush() {
		// TODO Auto-generated method stub
		//if(CurrentConnectedUser.isCurrentUserInRole(roles)){
                   manager.flush();
               /*}else {
                   throw new SecurityException("insuffisant.rigth.level");
               }*/
	}
        
        

	public void processBeforeSave(Object entity) {
		// TODO Auto-generated method stub
		
	}

	public void processAfterSave(Object entity) {
		// TODO Auto-generated method stub
		
	}

	public void processBeforeUpdate(Object entity) {
		// TODO Auto-generated method stub
		
	}

	public void processAfterUpdate(Object entity) {
		// TODO Auto-generated method stub
		
	}

	public void processBeforeDelete(Object entity) {
		// TODO Auto-generated method stub
		
	}

	public void processAfterDelete(Object entity) {
		// TODO Auto-generated method stub
		
	}

        public void processBeforeSave(List entities) {
            ; //To change body of generated methods, choose Tools | Templates.
        }

        public void processAfterSave(List entities) {
            ; //To change body of generated methods, choose Tools | Templates.
        }

        
        public void processBeforeUpdate(Map entity) {
            manager.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
        }

        public void processAfterUpdate(Map entity) {
            manager.processAfterUpdate(entity); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setManager(EntityManager manager) {

        }

    

    
}
