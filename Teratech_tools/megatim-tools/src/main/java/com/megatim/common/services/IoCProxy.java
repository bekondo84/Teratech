package com.megatim.common.services;



import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.common.annotations.Authorized;
import com.megatim.common.annotations.Transaction;
import com.megatim.common.decorators.GenericManagerDecorator;
import com.megatim.common.decorators.SecurityManagerDecorator;
import com.megatim.common.decorators.TransactionManagerDecorator;

/**
 * Classe responsable de l'implementation du proxy
 * Ce proxy decore les methodes des nos elements dao et manager afin d'integrer des aspects tels
 * que la securite , les transactions et tous les besions d'ordre transversal
 * cette classe renvoie un objet proxy  qui permet au composant de fournir tous les services
 * transversaux attendu 
 * @author Bekondo Kangue Dieunedort <bekondo_dieu@yahoo.fr / tel:695427874>
 *
 */
public class IoCProxy {

	/**
	 * instance de l'object a decorer
	 */
	private static Object _instance ;
	
	
	
	/**
	 * 
	 */
	//private Tran
	/**
	 * Constructeur par defaut
	 */
	public IoCProxy(Object instance) {
		// TODO Auto-generated constructor stub
		this._instance = instance ;
	}
	
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	public static Object instance(Object object){		
		
		Object proxy =object ;
		
		if(object==null) return null ;
		
                GenericManagerDecorator security= null ;
                
		GenericManagerDecorator trans= transactionManager(object); ;
                
                if(trans==null)
                           security = securityValidator(object,object);
                else {
                    proxy = trans;
                    security = securityValidator(object,trans);
                }
                      
                
		if(security !=null)
			          proxy = security ;
		
		return proxy ;
		
	}

	/**
	 * Traitement des annotations @Allow
	 */
	private static  GenericManagerDecorator securityValidator(Object _manager,Object _decor) throws SecurityException{
		
		//test de non nullit�
		if(_manager==null)
			            return null ;
                //System.out.println("SecurityManagerDecorator.save(Object entity) :::::::::::::::::::::: "+_manager+":::::"+_manager.getClass().isAnnotationPresent(Authorized.class));
		//verification de l'existance de l'annotation @Authorized
                if(!_manager.getClass().isAnnotationPresent(Authorized.class))
                                                            return null;
                //La classe est annot� @Authorized
		Authorized annot = _manager.getClass().getAnnotation(Authorized.class);
                
                String[] roles = annot.values();
                
                SecurityManagerDecorator decorator = new SecurityManagerDecorator((GenericManager)_decor,roles);
	        
                 return decorator;
        }
	
    private static GenericManagerDecorator transactionManager(Object _manager){
    	
    	//Teste de non nullit�
    	if(_manager==null)
    		             return null ;
    	
    	//verification de l'existance de l'annotation @Transaction
    	if(!_manager.getClass().isAnnotationPresent(Transaction.class))
    		                                                return null;
    	//Extenciation d'un decorateur de transaction
    	TransactionManagerDecorator decor = new TransactionManagerDecorator((GenericManager) _manager);
    	
    	return decor ;
    }
	 
}
