package com.bekosoftware.genericdaolayer.contexts;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import com.megatim.common.annotations.OrderType;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.megatim.common.annotations.Search;
import com.megatim.common.annotations.SearchType;
import java.math.BigDecimal;

/**
 * Classe responsable du traitement des annotations dans les classes 
 * de Manager du Bean de JSF
 * 
 * @author Bekondo Kangue Dieunedort <bekondo_dieu@yahoo.fr / tel :695427874>
 * @since 30/04/2016 at 19:35
 *
 */
public class SearchAnnotationsProcessor {

	/**
	 * Objet dans le quel lon doit faire l'injection de dependances
	 */
	private  Object source = null;	
	
	/**
	 * Critere d'ordre de la requete
	 */
	private Map<String,OrderType> ordersCriteria = new HashMap<String,OrderType>(); 
        
        
	
	public SearchAnnotationsProcessor(Object source) {
		// TODO Auto-generated constructor stub
		
		this.source = source ;
	}
	
	
	/**
	 * Methode responsable du traitement des annotations @Search en vue de construire 
	 * la fonction de recherche
	 * 
	 * @return 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public  RestrictionsContainer executeSearchAnnotation(Object _source) throws IllegalArgumentException, IllegalAccessException{
		
                source = _source;
                    
	      //Ne rien faire si la source est null
		if(source==null) 
			             return null;
		
                //Construction du containeur de restriction
		RestrictionsContainer restrictionContainer = RestrictionsContainer.newInstance();
                
                //Initialisation des criteres d'ordres
		ordersCriteria = new HashMap<String,OrderType>();
				
		//Creation de la liste des criteres
		List<OrderByItem> orders = new ArrayList<OrderByItem>();
		
		
		//Recuperation de la liste des champs de sources
		Field[] fields = source.getClass().getDeclaredFields();		
		
		//Traitement de la liste des champs de l'objet source
		for(int i=0 ; i<fields.length;i++){
			
			//Recherche des champs annoté Search
			if(fields[i].isAnnotationPresent(Search.class)){
                            //System.out.println("RestrictionsContainer executeSearchAnnotation(Object _source) :: "+fields[i].getName());
                 
				//Rendre accessible le champ
				fields[i].setAccessible(true);
				
				//Recuperation de l'annotation
				Search  annotation = fields[i].getAnnotation(Search.class);
				
				//Recuperation de la liste des criteres de recherches
				SearchType[] criteres = annotation.value();
				
				//Traitement des criteres de recherches
				for(SearchType criteria : criteres)
					predicatBuilder(criteria ,annotation, fields[i],restrictionContainer);
				
				//Mise a jour de la liste des criteres d'ordres
				if(annotation.orderBy()!=OrderType.NONE){
					
					 String fieldName = annotation.field().trim().isEmpty() ? fields[i].getName() : annotation.field().trim();
					 
					 orders.add(new OrderByItem(fieldName, annotation.orderBy(), annotation.rang()));
				}
				
			}//end of if(fields[i].isAnnotationPresent(Search.class)){
			
		}//end for(int i=0 ; i<fields.length;i++){
		
		//trie de la liste selon le rang
		Collections.sort(orders);
		//onstruction du criteres d'ordre
		for(OrderByItem item : orders){
			ordersCriteria.put(item.fieldName, item.type);
		}
		
		return restrictionContainer;
	}

	
	/**
         * 
         * @param criteria
         * @param annot
         * @param field
         * @param container
         * @throws IllegalArgumentException
         * @throws IllegalAccessException 
         */
	private  void predicatBuilder(SearchType criteria ,Search annot,Field field, RestrictionsContainer container) throws IllegalArgumentException, IllegalAccessException{
		
		//System.out.println("predicatBuilder(SearchType criteria ,Search annot,Field field, RestrictionsContainer container) ::::::::::::::::::::::::::::::: "+criteria);
		if(criteria==SearchType.EQUAL){
			
			      String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
			      if(field.getType() == String.class){
			    	  container.addEq(fieldName,(String)field.get(source));
			      }else if(field.getType()==Character.class){
			    	  container.addEq(fieldName,(Character)field.get(source));
			      }else if(field.getType()==Date.class) {
			    	  container.addEq(fieldName,(Date)field.get(source));
			      }else if(field.getType()==Short.class){
			    	  container.addEq(fieldName,(Short)field.get(source));
			      }else if(field.getType()==Byte.class){
			    	  container.addEq(fieldName,(Byte)field.get(source));
			      }else if(field.getType()==Integer.class){
			    	  container.addEq(fieldName,(Integer)field.get(source));
			      }else if(field.getType()==Long.class){
			    	  container.addEq(fieldName,(Long)field.get(source));
			      }else if(field.getType()==Float.class ){
			    	  container.addEq(fieldName,(Float)field.get(source));
			      }else if(field.getType()==Double.class){
			    	  container.addEq(fieldName,(Double)field.get(source));
			      }else if(field.getType()==BigDecimal.class){
			    	  container.addEq(fieldName,(BigDecimal)field.get(source));
			      }else if(field.getType()==Boolean.class){
			    	  container.addEq(fieldName,(Boolean)field.get(source));
			      }else {
                                   container.addEq(fieldName,(Comparable)field.get(source));
                              }
		}else if(criteria==SearchType.GE){
			
		      String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
		      if(field.getType() == String.class){
		    	  container.addGe(fieldName,(String)field.get(source));
		      }else if(field.getType()==Character.class){
		    	  container.addGe(fieldName,(Character)field.get(source));
		      }else if(field.getType()==Date.class) {
		    	  container.addGe(fieldName,(Date)field.get(source));
		      }else if(field.getType()==Short.class){
		    	  container.addGe(fieldName,(Short)field.get(source));
		      }else if(field.getType()==Byte.class){
		    	  container.addGe(fieldName,(Byte)field.get(source));
		      }else if(field.getType()==Integer.class){
		    	  container.addGe(fieldName,(Integer)field.get(source));
		      }else if(field.getType()==Long.class){
		    	  container.addGe(fieldName,(Long)field.get(source));
		      }else if(field.getType()==Float.class ){
		    	  container.addGe(fieldName,(Float)field.get(source));
		      }else if(field.getType()==Double.class){
		    	  container.addGe(fieldName,(Double)field.get(source));
		      }else if(field.getType()==BigDecimal.class){
                          container.addGe(fieldName,(BigDecimal)field.get(source));
                      }else {
                            container.addGe(fieldName,(Comparable)field.get(source));
                       }
	  }else if(criteria==SearchType.GT){
			
	      String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      if(field.getType() == String.class){
	    	  container.addGt(fieldName,(String)field.get(source));
	      }else if(field.getType()==Character.class){
	    	  container.addGt(fieldName,(Character)field.get(source));
	      }else if(field.getType()==Date.class) {
	    	  container.addGt(fieldName,(Date)field.get(source));
	      }else if(field.getType()==Short.class){
	    	  container.addGt(fieldName,(Short)field.get(source));
	      }else if(field.getType()==Byte.class){
	    	  container.addGt(fieldName,(Byte)field.get(source));
	      }else if(field.getType()==Integer.class){
	    	  container.addGt(fieldName,(Integer)field.get(source));
	      }else if(field.getType()==Long.class){
	    	  container.addGt(fieldName,(Long)field.get(source));
	      }else if(field.getType()==Float.class ){
	    	  container.addGt(fieldName,(Float)field.get(source));
	      }else if(field.getType()==Double.class){
	    	  container.addGt(fieldName,(Double)field.get(source));
	      }else if(field.getType()==BigDecimal.class){
                container.addGt(fieldName,(BigDecimal)field.get(source));
              }else {
                    container.addGt(fieldName,(Comparable)field.get(source));
               }
      }else if(criteria==SearchType.ISEMPTY){
			
	      String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      
	      if(field.getType() == String.class){
	    	  container.addIsEmpty(fieldName,(String)field.get(source));
	      }else if(field.getType()==Collection.class){
	    	  //container.addIsEmpty(fieldName,(Collection)field.get(source));
	      }
      }else if(criteria==SearchType.ISFALSE){
			
	      String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      
	      if(field.getType() == Boolean.class){
	    	  container.addIsFalse(fieldName);
	      }
      }else if(criteria==SearchType.ISNOTEMPTY){
			
	      String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      
	      if(field.getType() == String.class){
	    	  container.addIsNotEmpty(fieldName,(String)field.get(source));
	      }
      }else if(criteria==SearchType.ISNOTNULL){
			
	      String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      
	       	  if(field.getType() == String.class){
		    	  container.addNotNull(fieldName,(String)field.get(source));
		      }else if(field.getType()==Character.class){
		    	  container.addNotNull(fieldName,(Character)field.get(source));
		      }else if(field.getType()==Date.class) {
		    	  container.addNotNull(fieldName,(Date)field.get(source));
		      }else if(field.getType()==Short.class){
		    	  container.addNotNull(fieldName,(Short)field.get(source));
		      }else if(field.getType()==Byte.class){
		    	  container.addNotNull(fieldName,(Byte)field.get(source));
		      }else if(field.getType()==Integer.class){
		    	  container.addNotNull(fieldName,(Integer)field.get(source));
		      }else if(field.getType()==Long.class){
		    	  container.addNotNull(fieldName,(Long)field.get(source));
		      }else if(field.getType()==Float.class ){
		    	  container.addNotNull(fieldName,(Float)field.get(source));
		      }else if(field.getType()==Double.class){
		    	  container.addNotNull(fieldName,(Double)field.get(source));
		      }else if(field.getType()==BigDecimal.class){
			    	  container.addNotNull(fieldName,(BigDecimal)field.get(source));
	              }else if(field.getType()==Boolean.class){
		    	  container.addNotNull(fieldName,(Boolean)field.get(source));
		      }else {
                            container.addNotNull(fieldName,(Comparable)field.get(source));
                       }
	      
      }else if(criteria==SearchType.ISNULL){
			
	      String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      
	         	  
	    	  if(field.getType() == String.class){
		    	  container.addIsNull(fieldName,(String)field.get(source));
		      }else if(field.getType()==Character.class){
		    	  container.addIsNull(fieldName,(Character)field.get(source));
		      }else if(field.getType()==Date.class) {
		    	  container.addIsNull(fieldName,(Date)field.get(source));
		      }else if(field.getType()==Short.class){
		    	  container.addIsNull(fieldName,(Short)field.get(source));
		      }else if(field.getType()==Byte.class){
		    	  container.addIsNull(fieldName,(Byte)field.get(source));
		      }else if(field.getType()==Integer.class){
		    	  container.addIsNull(fieldName,(Integer)field.get(source));
		      }else if(field.getType()==Long.class){
		    	  container.addIsNull(fieldName,(Long)field.get(source));
		      }else if(field.getType()==Float.class ){
		    	  container.addIsNull(fieldName,(Float)field.get(source));
		      }else if(field.getType()==Double.class){
		    	  container.addIsNull(fieldName,(Double)field.get(source));
		      }else if(field.getType()==Boolean.class){
		    	  container.addIsNull(fieldName,(Boolean)field.get(source));
		      }else if(field.getType()==BigDecimal.class){
                        container.addIsNull(fieldName,(BigDecimal)field.get(source));
                      }else {
                            container.addIsNull(fieldName,(Comparable)field.get(source));
                       }
	      
      }else if(criteria==SearchType.ISTRUE){
			
	      String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
          
	      if(field.getType() == Boolean.class){
	    	  container.addIsTrue(fieldName);
	      }	      
      }else if(criteria==SearchType.LE){
    	  
    	  String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      if(field.getType() == String.class){
	    	  container.addLe(fieldName,(String)field.get(source));
	      }else if(field.getType()==Character.class){
	    	  container.addLe(fieldName,(Character)field.get(source));
	      }else if(field.getType()==Date.class) {
	    	  container.addLe(fieldName,(Date)field.get(source));
	      }else if(field.getType()==Short.class){
	    	  container.addLe(fieldName,(Short)field.get(source));
	      }else if(field.getType()==Byte.class){
	    	  container.addLe(fieldName,(Byte)field.get(source));
	      }else if(field.getType()==Integer.class){
	    	  container.addLe(fieldName,(Integer)field.get(source));
	      }else if(field.getType()==Long.class){
	    	  container.addLe(fieldName,(Long)field.get(source));
	      }else if(field.getType()==Float.class ){
	    	  container.addLe(fieldName,(Float)field.get(source));
	      }else if(field.getType()==Double.class){
	    	  container.addLe(fieldName,(Double)field.get(source));
	      }else if(field.getType()==BigDecimal.class){
                container.addLe(fieldName,(BigDecimal)field.get(source));
              }else {
                    container.addLe(fieldName,(Comparable)field.get(source));
               }
      }else if(criteria==SearchType.LIKE){
    	  
          String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      
	      if(field.getType() == String.class){
	    	  container.addLike(fieldName,((String)field.get(source)));
	      }
      }else if(criteria==SearchType.LT){
    	  
    	  String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      if(field.getType() == String.class){
	    	  container.addLt(fieldName,(String)field.get(source));
	      }else if(field.getType()==Character.class){
	    	  container.addLt(fieldName,(Character)field.get(source));
	      }else if(field.getType()==Date.class) {
	    	  container.addLt(fieldName,(Date)field.get(source));
	      }else if(field.getType()==Short.class){
	    	  container.addLt(fieldName,(Short)field.get(source));
	      }else if(field.getType()==Byte.class){
	    	  container.addLt(fieldName,(Byte)field.get(source));
	      }else if(field.getType()==Integer.class){
	    	  container.addLt(fieldName,(Integer)field.get(source));
	      }else if(field.getType()==Long.class){
	    	  container.addLt(fieldName,(Long)field.get(source));
	      }else if(field.getType()==Float.class ){
	    	  container.addLt(fieldName,(Float)field.get(source));
	      }else if(field.getType()==Double.class){
	    	  container.addLt(fieldName,(Double)field.get(source));
	      }else if(field.getType()==BigDecimal.class){
                container.addLt(fieldName,(BigDecimal)field.get(source));
             }else {
                container.addLt(fieldName,(Comparable)field.get(source));
             }
      }else if(criteria==SearchType.NOTEQ){
    	  
    	  String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      if(field.getType() == String.class){
	    	  container.addNotEq(fieldName,(String)field.get(source));
	      }else if(field.getType()==Character.class){
	    	  container.addNotEq(fieldName,(Character)field.get(source));
	      }else if(field.getType()==Date.class) {
	    	  container.addNotEq(fieldName,(Date)field.get(source));
	      }else if(field.getType()==Short.class){
	    	  container.addNotEq(fieldName,(Short)field.get(source));
	      }else if(field.getType()==Byte.class){
	    	  container.addNotEq(fieldName,(Byte)field.get(source));
	      }else if(field.getType()==Integer.class){
	    	  container.addNotEq(fieldName,(Integer)field.get(source));
	      }else if(field.getType()==Long.class){
	    	  container.addNotEq(fieldName,(Long)field.get(source));
	      }else if(field.getType()==Float.class ){
	    	  container.addNotEq(fieldName,(Float)field.get(source));
	      }else if(field.getType()==Double.class){
	    	  container.addNotEq(fieldName,(Double)field.get(source));
	      }else if(field.getType()==Boolean.class){
	    	  container.addNotEq(fieldName,(Boolean)field.get(source));
	      }else if(field.getType()==BigDecimal.class){
                container.addNotEq(fieldName,(BigDecimal)field.get(source));
              }else {
                    container.addNotEq(fieldName,(Comparable)field.get(source));
               }
      }else if(criteria==SearchType.NOTLIKE){
    	  
    	  String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      
	      if(field.getType() == String.class){
	    	  container.addNotLike(fieldName,((String)field.get(source)));
	      }
      }
			
		return  ;
	}
	
	
	
	public  Map<String, OrderType> getOrdersCriteria() {
		return ordersCriteria;
	}



	private class OrderByItem implements Comparable<OrderByItem>{
		
		/**
		 * Champ sur le quel porte le critere d'ordre
		 */
		private String fieldName ;
		/**
		 * Ordre
		 */
		private OrderType type ;
		
		/**
		 * Rang dans le map
		 */
		private short rang;

		
		/**
		 * Constructeur par defaut
		
		
		public OrderByItem() {
			super();
			// TODO Auto-generated constructor stub
		}

      */


		public OrderByItem(String fieldName, OrderType type, short rang) {
			super();
			this.fieldName = fieldName;
			this.type = type;
			this.rang = rang;
		}




		public int compareTo(OrderByItem arg0) {
			// TODO Auto-generated method stub
			
			if(rang<arg0.rang)
			            return -1;
			else if(rang>arg0.rang) 
				       return 1 ;
			else return 0;
		}




		public String getFieldName() {
			return fieldName;
		}




		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}




		public OrderType getType() {
			return type;
		}




		public void setType(OrderType type) {
			this.type = type;
		}




		public short getRang() {
			return rang;
		}




		public void setRang(short rang) {
			this.rang = rang;
		}
		
		
		
		
		
	}
}
