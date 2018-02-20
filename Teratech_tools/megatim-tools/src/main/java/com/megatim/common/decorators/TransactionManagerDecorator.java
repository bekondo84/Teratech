package com.megatim.common.decorators;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityTransaction;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

public class TransactionManagerDecorator implements GenericManagerDecorator {

	/**
	 * Manager a decorer
	 */
	private GenericManager manager ;
	
	/**
	 * Transaction
	 */
	private EntityTransaction transaction =null;
	
	/**
	 * 
	 * @param manager
	 */
	public TransactionManagerDecorator(GenericManager manager) {
		// TODO Auto-generated constructor stub
		
		this.manager = manager ;
		
		
	}

	public GenericDAO getDao() {
		// TODO Auto-generated method stub
		return manager.getDao();
	}

	public Object save(Object entity) {
		// TODO Auto-generated method stub
		Object result = null ;
		try{
			//demarrage de la transaction
			begin();
			//Execution de la methode*
			 result = manager.save(entity);			
			//Validation de la transaction
			commit();			
			return result;
		}catch(Exception ex){
                        ex.printStackTrace();			
			rollback();	
			throw new RuntimeException(ex);
		}finally{
                  clearManager();
                }
	}
        
        /**
         * 
         * @param entities 
         */
        public void save(List entities) {
           //To change body of generated methods, choose Tools | Templates.
            try{
                 //demarrage de la transaction                
                    begin();    
                    
                    manager.save(entities);
                    //Validation de la transaction
                    commit();
            }catch(Exception ex){
                        ex.printStackTrace();		
			rollback();	
			throw new RuntimeException(ex);
            }finally{

              clearManager();
            }
        }
     

	public Object update(Serializable id, Object entity) {
		// TODO Auto-generated method stub
		Object result =null ;
		
		try{
			
			//demarrage transaction
			begin();
                        //Execution
			result = manager.update(id, entity);
			
			//Validation
			commit();
			
			return result;
		}catch(Exception e){
                     e.printStackTrace();
			rollback();			
			throw new RuntimeException(e);
			
		}finally{
                  clearManager();
                }
	}

        public void update(Map entities) {
            //To change body of generated methods, choose Tools | Templates.
           
		try{
			
			//demarrage transaction
			begin();
			//Execution
			manager.update(entities);
			
			//Validation
			commit();
			
		}catch(Exception e){
                        e.printStackTrace();
			rollback();			
			throw new RuntimeException(e);
			
		}finally{
                  clearManager();
                }
        }

        
        
	public Object delete(Serializable id) {
                Object result =null ;
		
		try{
			
			//demarrage transaction
			begin();
			//Execution
			result = manager.delete(id);
			//System.out.println("TransactionManagerDecorator .delete() :::::::::::::: "+manager+"  ::::::::::: "+id);
			//Validation
			commit();
			
			return result;
		}catch(Exception e){
			rollback();			
			throw new RuntimeException(e);
			
		}finally{
                  clearManager();
                }
	}

	public Object find(String propertyName, Serializable id) {		// TODO Auto-generated method stub
	     	
                clearManager();
                
               Object object = manager.find(propertyName, id);
               
               return object;
               
	}
        
        private void refreshData(List objects){           
           /** 
            for(Object obj : objects){
                manager.getDao().getEntityManager().refresh(obj);
            }**/
        }

       
        
        private void clearManager(){
            
            if(manager.getDao()!=null&&manager.getDao().getEntityManager()!=null){ 
                   begin();
                    manager.getDao().getEntityManager().flush();
                    manager.getDao().getEntityManager().clear();
                    commit();
             }
        }
        
	public List findAll() {
		// TODO Auto-generated method stub
                //clearManager();
            
		List liste =  manager.findAll();
                 refreshData(liste);           
                return  liste ;
	}

	public List findByUniqueProperty(String propertyName, Object propertyValue,
			Set properties) {
		// TODO Auto-generated method stub
                //clearManager();            
		List liste = manager.findByUniqueProperty(propertyName, propertyValue, properties);
                
	        // refreshData(liste);           
                return  liste ;
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
		 //clearManager();
                 //manager.getDao().getEntityManager().setProperty("javax.persistence.cache.retrieveMode", "BYPASS");
                 List liste =manager.filter(predicats, orders, properties, firstResult, maxResult);
                 
                 refreshData(liste);                 
	         //System.out.println("TransactionManagerDecorator..........................................................................................."+liste);
                return  liste ;
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
	public List filter(List predicats, Map orders, Set properties,Map hints,
			int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		 //clearManager();
                 //manager.getDao().getEntityManager().setProperty("javax.persistence.cache.retrieveMode", "BYPASS");
                 List liste =manager.filter(predicats, orders, properties , hints, firstResult, maxResult);
                 
                 refreshData(liste);                 
	         //System.out.println("TransactionManagerDecorator..........................................................................................."+liste);
                return  liste ;
        }
        
	public Long count(List predicats) {
		// TODO Auto-generated method stub
		 //clearManager();
               return manager.count(predicats);
	}

	public void clean() {
		// TODO Auto-generated method stub
               manager.clean();
	}

	public void flush() {
		// TODO Auto-generated method stub
        
		manager.flush();
	}

	public void processBeforeSave(Object entity) {
		// TODO Auto-generated method stub
        manager.processBeforeSave(entity);
	}

	public void processAfterSave(Object entity) {
		// TODO Auto-generated method stub
        manager.processAfterSave(entity);
	}

	public void processBeforeUpdate(Object entity) {
		// TODO Auto-generated method stub
		manager.processBeforeUpdate(entity);

	}

	public void processAfterUpdate(Object entity) {
		// TODO Auto-generated method stub
		manager.processAfterUpdate(entity);

	}

	public void processBeforeDelete(Object entity) {
		// TODO Auto-generated method stub
        manager.processBeforeDelete(entity);
	}

	public void processAfterDelete(Object entity) {
		// TODO Auto-generated method stub
		manager.processAfterDelete(entity);

	}

	
	
	private void begin(){
		
		if(manager.getDao()!=null&&manager.getDao().getEntityManager()!=null)
                transaction = manager.getDao().getEntityManager().getTransaction();
		if(transaction!=null)
			       transaction.begin();
               
	}
	
	private void commit(){
		if(transaction!=null)
		       transaction.commit();
	}
	private void rollback(){
		if(transaction!=null)
		       transaction.rollback();
	}

    /*@Override
    protected void finalize() throws Throwable {
       
        if(manager.getDao()!=null&&manager.getDao().getEntityManager()!=null){
            manager.getDao().getEntityManager().close();
        }
    }*/

    public void processBeforeSave(List entities) {
        ; //To change body of generated methods, choose Tools | Templates.
    }

    public void processAfterSave(List entities) {
        ; //To change body of generated methods, choose Tools | Templates.
    }

    public void processBeforeUpdate(Map entity) {
        ; //To change body of generated methods, choose Tools | Templates.
    }

    public void processAfterUpdate(Map entity) {
        ; //To change body of generated methods, choose Tools | Templates.
    }

    public void setManager(EntityManager manager) {
        ;
    }

    
        
}
