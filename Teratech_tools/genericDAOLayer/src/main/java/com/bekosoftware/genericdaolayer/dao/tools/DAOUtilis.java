/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericdaolayer.dao.tools;

import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

/**
 *
 * @author BEKONDO KANGUE Dieunedort <bekondo_dieu@yahoo.fr>
 */
public class DAOUtilis {
    
    /**
	 * Méthode de construction d'un chemin de propriété à partir de la racine
	 * @param <Y>	Paramètre de type du chemin final
	 * @param root	Racine
	 * @param stringPath	Chemin sous forme de chaine
	 * @return	Chemin recherché sous forme Path
	 */
    public static <Y extends Comparable<Y>> Path<Y> buildPropertyPath(Root<?> root, String stringPath){
             
             // Si la racine est nulle
			if(root == null) return null;
			
			// Si la chaine est vide
			if(stringPath == null || stringPath.trim().length() == 0) return null;
			
			// Le Path à retournet
			Path<Y> path = null;
			
			// On splitte sur le séparateur de champs
			String[] hierarchicalPaths = stringPath.trim().split("\\.");
			
			// Obtention du premier chemin
			path = root.get(hierarchicalPaths[0]);
			
			// Si la taille est > 1
			if(hierarchicalPaths.length > 1) {
	
				// Parcours
				for (int i = 1; i < hierarchicalPaths.length; i++) {
					
					// Le chemin
					String unitPath = hierarchicalPaths[i];
					
					// Si le path est vide ou est une suite d'espace
					if(unitPath == null || unitPath.trim().length() == 0) continue;
					
					// Acces à la ppt
					path = path.get(unitPath.trim());
				}
			}
			
			// On retourne le Path
			return (Path<Y>) path;
    }
    
    /**
	 * Méthode de construction d'un chemin de propriété à partir de la racine
	 * @param <Y>	Paramètre de type du chemin final
	 * @param root	Racine
	 * @param stringPath	Chemin sous forme de chaine
	 * @return	Chemin recherché sous forme Path
	 */
    public static  Path<?> buildPropertyPath2(Root<?> root, String stringPath){
             
             // Si la racine est nulle
			if(root == null) return null;
			
			// Si la chaine est vide
			if(stringPath == null || stringPath.trim().length() == 0) return null;
			
			// Le Path à retournet
			Path<?> path = null;
			
			// On splitte sur le séparateur de champs
			String[] hierarchicalPaths = stringPath.trim().split("\\.");
			
			// Obtention du premier chemin
			path = root.get(hierarchicalPaths[0]);
			
			// Si la taille est > 1
			if(hierarchicalPaths.length > 1) {
	
				// Parcours
				for (int i = 1; i < hierarchicalPaths.length; i++) {
					
					// Le chemin
					String unitPath = hierarchicalPaths[i];
					
					// Si le path est vide ou est une suite d'espace
					if(unitPath == null || unitPath.trim().length() == 0) continue;
					
					// Acces à la ppt
					path = path.get(unitPath.trim());
				}
			}
			
			// On retourne le Path
			return (Path<?>) path;
    }
         
         
         /**
	 * Méthode de chargement des prédicats
	 * @param <T>	Paramètre de type
	 * @param criteriaBuilder Constructeur de critères
	 * @param criteriaQuery	Requete de critères
	 * @param predicates	Liste des predicats
	 */
	public static void addPredicates(CriteriaBuilder criteriaBuilder, Root<?> root, CriteriaQuery<?> criteriaQuery, List<Predicat> predicates) {
		
                if(predicates==null) return ;
                
		// Liste de predicats JPA 2
		List<javax.persistence.criteria.Predicate> jpaPredicates = new ArrayList<javax.persistence.criteria.Predicate>();
		
		// Parcours de la liste
		for (Predicat predicate : predicates) {
			
			// Ajout du critere JPA
			jpaPredicates.add(predicate.generateJPAPredicat(criteriaBuilder, root));
		}
		
		criteriaQuery.where(criteriaBuilder.and(jpaPredicates.toArray(new javax.persistence.criteria.Predicate[0])));
	}
        
        /**
	 * Méthode de chargement des ordres
	 * @param <T>	Paramètre de type
	 * @param criteriaQuery	Requete de critères
	 * @param orders	Liste des ordres
	 */
	public static void addOrders(CriteriaBuilder criteriaBuilder, Root<?> root, CriteriaQuery<?> criteriaQuery, Map<String, OrderType> orders) {
		
	   // Si la liste est vide
		if(orders == null || orders.size() == 0) return;
		
		// Liste d'ordres
		List<Order> lOrders = new ArrayList<Order>();
		
		
		// Parcours
		for (String property : orders.keySet()) {
			
			// Si la propriete est vide
			if(property == null || property.trim().length() == 0) continue;
			
			// Evaluation de la ppt
			Path <?> path = buildPropertyPath2(root, property.trim());
			
			// Obtention du Type
			OrderType type = orders.get(property);
			
			// Si le type est null
			if(type == null) continue;
			
			// Si on est en ASC
			if(type.equals(OrderType.ASC)) lOrders.add(criteriaBuilder.asc(path));
			else lOrders.add(criteriaBuilder.desc(path));
		}
		
		// Ajout
		criteriaQuery.orderBy(lOrders);
	}
        
        
      /**
	 * Methode d'ajout des Proprietes a charger a la requete de recherche
	 * @param <T>	Paramètre de type d'entités
	 * @param root	Entités objet du from
	 * @param properties	Conteneur de propriétés
	 */
	private static  void addProperties(Root<?> root, Set<String> properties) {
		
		// Si le conteneur est vide
		if(properties == null || properties.size() == 0) return;
		
		// Parcours du conteneur
		for (String property : properties) {
			
			// Si la ppt est nulle ou vide
			if(property == null || property.trim().length() == 0) {
                            root.join(property, JoinType.LEFT);
                            continue;
                        }
		}
	}

	/**
	 * Methode d'ajout des Proprietes a charger a la requete de recherche
	 * @param <T>	Paramètre de type d'entités
	 * @param root	Entités objet du from
	 * @param query Requete sur l'entité
	 * @param properties	Conteneur de propriétés
	 */
	public static void addProperties(Root<?> root, CriteriaQuery<?> query, Set<String> properties) {
		
		// Ajout des ppt
		addProperties(root, properties);
		
		// On positionne le distict
		query.distinct(true);
	}
        
        /**
	 * Méthode d'ajout de parametres à la requete
	 * @param query	Requete
	 * @param parameters	Map des parametres
	 */
	public static void addQueryParameters(Query query, Map<String, Object> parameters) {
		
		// Si la MAP est nulle
		if(parameters == null || parameters.size() == 0) return;
		
		// Parcours
		for(Map.Entry<String, Object> entry : parameters.entrySet()) {
			
			// Ajout
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}
    
}
