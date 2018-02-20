/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.jsf.layer.mbean.processor;


import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.megatim.common.annotations.Champ;
import com.megatim.common.annotations.OrderType;
import com.megatim.common.annotations.Search;
import com.megatim.common.annotations.SearchType;

import java.awt.Color;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 * Responsable de la validation des champs annot�es et du transfert du
 * contenue entre l'object source et cible
 *
 * @author BEKONDO KANGUE Dieunedort <bekondo_dieu@yahoo.fr /tel :6 95 42 78 74>
 */
public class ValidateAndFillBeans {

    /**
     * Cache des champs
     */
    private static Map<String, Field> fieldsMap = null;

    private static SearchAnnotationsProcessor annotationSearchProcessor = null;

    private static List<OrderByItem> orders = null;
    /**
     * Critere d'ordre de la requete
     */
    private static Map<String, OrderType> ordersCriteria = new HashMap<String, OrderType>();

    /**
     *
     * @param _source
     * @return
     */
    public static Field[] getObjectDeclaredFields(Class _source) {

        List<Field> _fields = new ArrayList<Field>();

        Field[] fields = null;

        Class superclass = _source.getSuperclass();

        if (superclass != null && !superclass.equals(JComponent.class)) {

            fields = getObjectDeclaredFields(superclass);

            for (Field _field : fields) {
                if (_field != null) {
                    _fields.add(_field);
                }
            }
        }
        fields = _source.getDeclaredFields();

        for (Field _field : fields) {
            if (_field != null) {
                _fields.add(_field);
            }
        }

        //System.out.println("Liste des champs ::::::::::::::::::::::::::::::::::: "+_fields);
        Field[] _filedss = new Field[_fields.size()];

        int index = 0;
        for (Field _field : _fields) {
            _filedss[index] = _field;

            index++;
        }

        return _filedss;
    }

    /**
     * Classe responsable de la validation des champs annot�es @Champ
     *
     * @param _bean
     */
    public static boolean validateBean(Object _bean) throws IllegalArgumentException, IllegalAccessException {

        boolean _valide = true;
        //Verification de la clause de non nullit�
        if (_bean == null) {
            return false;
        }
        //Recuperation de la liste des champs
        Field[] fields = getObjectDeclaredFields(_bean.getClass());
        //Field[] fields = _bean.getClass().getDeclaredFields();

        //Traitement sequentiel des champs
        for (Field field : fields) {

            //Validation du champs
            _valide &= validateField(_bean, field);
        }

        return _valide;
    }

    /**
     * R�initialise les champs d'un object
     *
     * @param _source
     */
    public static void cleanBeanField(Object _source) throws IllegalArgumentException, IllegalAccessException {
        //System.out.println("ValidateAndFillbeans.cleanBeanField(Object _source) :::::::: "+_source);
        if (_source == null) {
            return;
        }

        //Recuperation de la liste des champs
        Field[] _fields = getObjectDeclaredFields(_source.getClass());
        //Field[] _fields = _source.getClass().getDeclaredFields();

        for (Field _field : _fields) {

            _field.setAccessible(true);

            Object _value = _field.get(_source);

            if (_value instanceof JTextComponent) {
                ((JTextComponent) _value).setText("");
            } else if (_value instanceof JComboBox) {
                if (((JComboBox) _value).getItemCount() > 0) {
                    ((JComboBox) _value).setSelectedIndex(0);
                }
            }
        }
    }

    /**
     * Copie des champs de l'entite dans le composant IHM cible
     *
     * @param <T>
     * @param _source
     * @param cible
     */
    public static void fillViewBean(Object _source, Object _cible) throws IllegalArgumentException, IllegalAccessException {

        //test de non nullit�
        if (_source == null || _cible == null) {
            return;
        }
       //Liste des champs Cibles
        //Field[] cibleFields = _cible.getClass().getDeclaredFields();
        //Recuperation de la liste des champs
        Field[] cibleFields = getObjectDeclaredFields(_cible.getClass());
       //System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBB ::::::::::::::::::::::::::::::: "+cibleFields); 
        //Declaration du dico des champs cible
        Map<String, Field> cibleFieldsMap = new HashMap<String, Field>();

        for (Field _field : cibleFields) {

            String fieldName = _field.getName();
            //Verification de l'existance de @Champ
            if (_field.isAnnotationPresent(Champ.class)) {

                Champ annot = _field.getAnnotation(Champ.class);

                if (!annot.mappedBy().trim().isEmpty()) {
                    fieldName = annot.mappedBy();
                }

            }

            cibleFieldsMap.put(fieldName, _field);

        }

        //System.out.println("ValidateAndFillBean.setIHMFieldValue() ::: :::::::::"+_cible+" :::::::::::"+_source+":::: "+cibleFieldsMap);
      //Liste des champs source
        //Field[] sourceFields = _source.getClass().getDeclaredFields();
        Field[] sourceFields = getObjectDeclaredFields(_source.getClass());

        //traitement de chaque champs
        for (Field _field : sourceFields) {

            String _fieldName = _field.getName();
            //Recuperation du  champs cible correspondant
            Field _cibleField = cibleFieldsMap.get(_fieldName);
            //test de non nullit�
            if (_cibleField == null) {
                continue;
            }

            //Activation de l'accessibilit�
            _field.setAccessible(true);
            _cibleField.setAccessible(true);
          // System.out.println("ValidateAndFillBean.setIHMFieldValue() ::: "+_cibleField+":::::::::"+_cible+" ::: "+_field.+"::::::::"+_source);      

          //Copie de la valeur du champ source vers la cible
            //_cibleField.set(_cible, getFieldValue(_source, _field));
            setIHMFieldValue(_cibleField, _cible, _field, _source);

        }
    }

    /**
     *
     * @param _cibleField
     * @param _cible
     * @param _sourceField
     * @param _source
     */
    private static void setIHMFieldValue(Field _cibleField, Object _cible, Field _sourceField, Object _source) throws IllegalArgumentException, IllegalAccessException {
       //System.out.println("ValidateAndFillBean.setIHMFieldValue() ::: "+_cibleField+" :::: "+_cible+" ::: "+_sourceField+" ::: "+_source);
        //_sourceField.setAccessible(true);
        Object value = getFieldValue(_source, _sourceField);

        //_cibleField.setAccessible(true);
        if (_cibleField.getType().equals(JTextField.class)) {
            ((JTextField) _cibleField.get(_cible)).setText(value == null ? "" : "" + value);
        } else if (_cibleField.getType().equals(JPasswordField.class)) {
            //if(value!=null) System.out.println("ValidateAndFillBean.fillViewBean() ::: "+value+" ::::: ---- ::::: "+DESEncrypter.getInstance().decryptText((String)value));
            ((JPasswordField) _cibleField.get(_cible)).setText(value == null ? "" : "" + value);
        } else if (_cibleField.getType().equals(JFormattedTextField.class)) {
            ((JFormattedTextField) _cibleField.get(_cible)).setValue(value);
        } else if (_cibleField.getType().equals(JCheckBox.class)) {
            ((JCheckBox) _cibleField.get(_cible)).setSelected((Boolean) value);
        } else if (_cibleField.getType().equals(JRadioButton.class)) {
            ((JRadioButton) _cibleField.get(_cible)).setSelected((Boolean) value);
        } else if (_cibleField.getType().equals(JTextArea.class)) {
            ((JTextArea) _cibleField.get(_cible)).setText(value == null ? "" : "" + value);
        } else if (_cibleField.getType().equals(JComboBox.class)) {
            if (value != null) {
                ((JComboBox) _cibleField.get(_cible)).setSelectedItem(value);
            }
        } else if (_cibleField.getType().equals(JSpinner.class)) {
                if (value != null) {
                    ((JSpinner) _cibleField.get(_cible)).setValue(value);
                } 
        }  else if (_cibleField.getType().equals(JList.class)) {
            //Cas ou les donn�es sont stock� dans des liste         
            JList liste = ((JList) _cibleField.get(_cible));
            DefaultListModel model = (DefaultListModel) liste.getModel();
            if (value instanceof List) {
                List rows = ((List) value);
                if (rows != null && !rows.isEmpty()) {

                    List rowss = new ArrayList();
                    int index = 0 ;
                    for (Object obj : rows) {
                        rowss.add(obj);
                        model.addElement(obj);
                        index++;
                    }                   

                    liste.setModel(model);
                }
            }
        }
    }

    /**
     * Copie des valeurs des champs de la classe _source vers la classe _cible
     *
     * @param _source
     * @param _cible
     */
    public static <T> T fillBean(Object _source, T _cible) throws IllegalAccessException {

        //test de non nullite
        if (_cible == null || _source == null) {
            return _cible;
        }
      //Liste des champs Cibles
        //Field[] cibleFields = _cible.getClass().getDeclaredFields();
        Field[] cibleFields = getObjectDeclaredFields(_cible.getClass());
        //Declaration du dico des champs cible
        Map<String, Field> cibleFieldsMap = new HashMap<String, Field>();
        for (Field _field : cibleFields) {
            cibleFieldsMap.put(_field.getName(), _field);

        }
      //Liste des champs source
        //Field[] _fields = _source.getClass().getDeclaredFields();
        Field[] _fields = getObjectDeclaredFields(_source.getClass());
        //traitement de chaque champs
        for (Field _field : _fields) {

            String _fieldName = _field.getName();
            //Verification de l'existance de @Champ
            if (_field.isAnnotationPresent(Champ.class)) {

                Champ annot = _field.getAnnotation(Champ.class);

                if (!annot.mappedBy().trim().isEmpty()) {
                    _fieldName = annot.mappedBy();
                }

            }
            //Recuperation du  champs cible correspondant
            Field _cibleField = cibleFieldsMap.get(_fieldName);
            //test de non nullit�
            if (_cibleField == null) {
                continue;
            }
            //Activation de l'accessibilit�
            _field.setAccessible(true);
            _cibleField.setAccessible(true);
            //Copie de la valeur du champ source vers la cible
            _cibleField.set(_cible, getFieldValue(_source, _field));

        }

        return _cible;
    }

    /**
     * Validation des champs
     *
     * @param _field
     */
    private static synchronized boolean validateField(Object _bean, Field _field) throws IllegalArgumentException, IllegalAccessException {
        //Verification de  clause de non nullit�
        if (_field == null) {
            return true;
        }
        //verification de l'existance de l'annotation
        if (!_field.isAnnotationPresent(Champ.class)) {
            return true;
        }
        //Recuperation de l'annotation @Champ
        Champ annot = _field.getAnnotation(Champ.class);
        //Rendre le champ accessible
        _field.setAccessible(true);
        //System.out.println("Je suis dans ValidateField===== "+_field.getType());
        boolean _validate = true;
        //Initialisation du champ
        initialiseErrorFields(_bean, annot.errorMessageField(), annot.errorMessage());
        //Lecture de la valeur du champs
        Object value = null;

        try {
            value = getFieldValue(_bean, _field);
        } catch (NumberFormatException ex) {

            errorFieldsMapInitialised(_bean, annot.errorMessageField(), ex.getMessage());
            return false;
        }
        //traitement du champs length
        if (annot.length() > 0) {

            //Verification que le type est String
            if (value.getClass().equals(String.class)) {

                //verification de la longueur
                if (value == null || ((String) value).isEmpty()) {
                    _validate &= true;
                }

                if (((String) value).length() <= annot.length()) {
                    _validate &= true;
                } else {
                    errorFieldsMapInitialised(_bean, annot.errorMessageField(), annot.errorMessage());
                    _validate &= false;
                }

            }
        }
        //Traitement de l'attribut min et max
        if (annot.min() < annot.max()) {

              //Object value = getFieldValue(_bean, _field) ;            
            //Aucune valeur fourni
            if (value == null) {
                return true;
            }
            Double _value = Double.valueOf(value.toString().trim());
            if (_value.doubleValue() >= annot.min() && _value.doubleValue() <= annot.max()) {
                _validate &= true;
            } else {
                //Traitement de l'affichage du message d'erreur
                errorFieldsMapInitialised(_bean, annot.errorMessageField(), annot.errorMessage());
                _validate &= false;
            }
        }
        //Traitement de l'attribut pattern
        if (!annot.pattern().trim().isEmpty()) {
            //Obtention de la valeur
            //Object _value = getFieldValue(_bean, _field);
            //Ne concerne que les chaines de caracteres
            //Test de non nullite
            if (value == null) {
                _validate = true;
            }
            if (value.getClass().equals(String.class)) {
                //Recuperation de la valeur
                String _value = (String) value;
                //Verification
                if (_value.matches(annot.pattern())) {
                    _validate &= true;
                } else {
                    //Traitement de l'affichage du message d'erreur
                    errorFieldsMapInitialised(_bean, annot.errorMessageField(), annot.errorMessage());
                    _validate &= false;
                }
            }
        }
        /**
         * Traitement de la non nullit�
         */
        if (!annot.nullable()) {
            //System.out.println("ValidateAndFillBeans.validateBean().nullabel :::::::::::::::::: "+_field+" ::::::::::::: "+value);
            //test de non nullit�
            if (value == null) {
                errorFieldsMapInitialised(_bean, annot.errorMessageField(), annot.errorMessage());
                _validate &= false;
            } else if (((value instanceof String) && ((String) value).trim().isEmpty())) {
                errorFieldsMapInitialised(_bean, annot.errorMessageField(), annot.errorMessage());
                _validate &= false;
            } 

        }

        return _validate;
    }

    /**
     * Responsable du traitement de @Field(updatable=F=
     */
    public static void updatableAnnotationProcessor(Object _source) throws IllegalArgumentException, IllegalAccessException {

        if (_source == null) {
            return;
        }
        

    }

    /**
     * Desactive tous tes champs de saisies
     *
     * @param _source
     */
    public static void disableAllFields(Object _source) throws IllegalArgumentException, IllegalAccessException {

        if (_source == null) {
            return;
        }
        //Field[] _fields = _source.getClass().getDeclaredFields();

        
    }

    public static void initialiseErrorFields(Object _instance, String errorMessageField,
            String errorMesssage) throws IllegalArgumentException, IllegalAccessException {
        //Teste de non nullite
        if (_instance == null) {
            return;
        }
        //Aucun valeur n'est affecter
        if (errorMessageField == null || errorMessageField.trim().isEmpty()) {
            return;
        }
        //Aucun valeur n'est affect��
        if (errorMesssage == null || errorMesssage.trim().isEmpty()) {
            return;
        }

        //Champs co
        Field chechField = null;
        //Liste des champs declarees
        Field[] fields = _instance.getClass().getDeclaredFields();

        for (Field _field : fields) {

            //Verification nom du champs
            if (_field.getName().equals(errorMessageField)) {

                chechField = _field;

                break;
            }
        }

        //Verification de la recherche
        if (chechField == null) {
            return;
        }
        chechField.setAccessible(true);
        //Activation de la
        if (chechField.get(_instance).getClass().equals(JLabel.class)) {
            ((JLabel) chechField.get(_instance)).setForeground(Color.black);
            ((JLabel) chechField.get(_instance)).setText("");
        }
        return;
    }

    /**
     * Affiche le message d'erreur dans les champs prevu a cet effet
     *
     * @param _instance
     * @param errorMessageField
     * @param errorMesssage
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void errorFieldsMapInitialised(Object _instance, String errorMessageField,
            String errorMesssage) throws IllegalArgumentException, IllegalAccessException {
        //Teste de non nullite
        if (_instance == null) {
            return;
        }
        //Aucun valeur n'est affecter
        if (errorMessageField == null || errorMessageField.trim().isEmpty()) {
            return;
        }
        //Aucun valeur n'est affect��
        if (errorMesssage == null || errorMesssage.trim().isEmpty()) {
            return;
        }

         return;
    }

    /**
     * Cette classe renvoie la valeur d'un champs
     *
     * @param _bean
     * @param _field
     * @return
     */
    private static Object getFieldValue(Object _bean, Field _field) throws IllegalArgumentException, IllegalAccessException, NumberFormatException {

        Object value = _field.get(_bean);

        Champ annot = _field.getAnnotation(Champ.class);

        /*if(annot==null)
         return null ;*/
        //Section valeur nu�erique
        //System.out.println("ValidateAndAndFillBeans.getWrapObject(Class c , String value) ::::::::::::::: "+_bean+" :::::::::::: "+value);
        if (_field.getType().equals(Short.class)) {
            value = _field.getShort(_bean);
        } else if (_field.getType().equals(Integer.class)) {
            value = _field.getInt(_bean);
        } else if (_field.getType().equals(Long.class)) {
            value = _field.getLong(_bean);
        } else if (_field.getType().equals(Float.class)) {
            value = _field.getFloat(_bean);
        } else if (_field.getType().equals(Double.class)) {
            value = _field.getDouble(_bean);
        } else if (_field.getType().equals(String.class)) {
            value = _field.get(_bean);
        } else if (_field.getType().equals(Date.class)) {
            value = _field.get(_bean);
        } else if (_field.getType().equals(Boolean.class)) {
            value = _field.get(_bean);
        } else if (_field.getType().equals(BigDecimal.class)) {
            value = ((BigDecimal) _field.get(_bean));
        } else if (_field.getType().equals(List.class)) {
            value = ((List) _field.get(_bean));
        }

        //System.out.println("ValidateAndAndFillBeans.getWrapObject(Class c , String value) ::: "+_field.getType()+" :::::::::: "+value);
        
        return value;
    }

    /**
     * Cette classe renvoie la valeur d'un champs
     *
     * @param _bean
     * @param _field
     * @return
     */
    private static Object getFieldValue2(Object _bean, Field _field) throws IllegalArgumentException, IllegalAccessException, NumberFormatException {

        Object value = _field.get(_bean);

        Search annot = _field.getAnnotation(Search.class);

        if (annot == null) {
            return null;
        }
        //Section valeur nu�erique
        if (_field.getType().equals(Short.class)) {
            value = _field.getShort(_bean);
        } else if (_field.getType().equals(Integer.class)) {
            value = _field.getInt(_bean);
        } else if (_field.getType().equals(Long.class)) {
            value = _field.getLong(_bean);
        } else if (_field.getType().equals(Float.class)) {
            value = _field.getFloat(_bean);
        } else if (_field.getType().equals(Double.class)) {
            value = _field.getDouble(_bean);
        } else if (_field.getType().equals(String.class)) {
            value = _field.get(_bean);
        } else if (_field.getType().equals(Date.class)) {
            value = _field.get(_bean);
        } else if (_field.getType().equals(Boolean.class)) {
            value = _field.get(_bean);
        } else if (_field.getType().equals(BigDecimal.class)) {
            value = ((BigDecimal) _field.get(_bean));
        }else if (_field.getType().equals(List.class)) {
            value = ((List) _field.get(_bean));
        }

        return value;
    }

   
   private static Object getWrapObject(Class c , String value) throws NumberFormatException{    
          
           if(value==null || c ==null||value.trim().equalsIgnoreCase("null"))
                    return null;
           
           if(c.equals(Short.class)||c.equals(short.class))
                     return new Short(value);
           else if(c.equals(Integer.class)||c.equals(int.class))
                      return new Integer(value);
           else if(c.equals(Long.class)||c.equals(long.class))
                       return new Long(value);
           else if(c.equals(Float.class)||c.equals(float.class))
                       return new Float(value);
           else if(c.equals(Double.class)||c.equals(double.class))
                       return new Double(value);
           else if(c.equals(BigDecimal.class))
                       return new BigDecimal(value);
           else if(c.equals(Boolean.class)||c.equals(boolean.class))
                       return new Boolean(value);
           else if(c.equals(BigDecimal.class))
               return new BigDecimal(value);
          
           return value ;
       
     
   }
   
   
   /**
	 * Methode responsable du traitement des annotations @Search en vue de construire 
	 * la fonction de recherche
	 * 
     * @param source
	 * @return 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static  RestrictionsContainer executeSearchAnnotation(Object source) throws IllegalArgumentException, IllegalAccessException{
		//System.out.println("ValidateAndFillBean.RestrictionsContainer executeSearchAnnotation(Object source) :::  :::: "+source);
                     
	      //Ne rien faire si la source est null
		if(source==null) 
			             return null;
		
                //Construction du containeur de restriction
		RestrictionsContainer restrictionContainer = RestrictionsContainer.newInstance();
                
                //Initialisation des criteres d'ordres
		ordersCriteria = new HashMap<String,OrderType>();
				
		//Creation de la liste des criteres
		orders = new ArrayList<OrderByItem>();
		
		
		//Recuperation de la liste des champs de sources
		//Field[] fields = source.getClass().getDeclaredFields();	
                Field[] fields =getObjectDeclaredFields(source.getClass());
		
		//Traitement de la liste des champs de l'objet source
		for(int i=0 ; i<fields.length;i++){
			
			//Recherche des champs annoté Search
			if(fields[i].isAnnotationPresent(Search.class)){
                           
				//Rendre accessible le champ
				fields[i].setAccessible(true);
				
				//Recuperation de l'annotation
				Search  annotation = fields[i].getAnnotation(Search.class);
				//Verification de la clause de non vidite
                                Object _value = getFieldValue2(source, fields[i]);
                                //System.out.println("ValidateAndFillBean.RestrictionsContainer executeSearchAnnotation(Object source) ::: "+fields[i]+" :::: "+_value);
                                /**if(_value==null)
                                           continue;**/
                                if((_value!=null)&&(_value instanceof String) && ((String)_value).trim().isEmpty())
                                                     continue ;
				//Recuperation de la liste des criteres de recherches
				SearchType[] criteres = annotation.value();
				
				//Traitement des criteres de recherches
				for(SearchType criteria : criteres)
					predicatBuilder(source ,criteria ,annotation, fields[i],restrictionContainer);
				
				//Mise a jour de la liste des criteres d'ordres
				if(annotation.orderBy()!=OrderType.NONE){
					
					 String fieldName = annotation.field().trim().isEmpty() ? fields[i].getName() : annotation.field().trim();
					 
					 orders.add(new OrderByItem(fieldName, annotation.orderBy(), annotation.rang()));
				}
				
			}//end of if(fields[i].isAnnotationPresent(Search.class)){
			
		}//end for(int i=0 ; i<fields.length;i++){
		//System.out.println("Avant le tri ::: "+orders);
		//trie de la liste selon le rang
		Collections.sort(orders);
                //System.out.println("Apres le tri ::: "+orders);
		//onstruction du criteres d'ordre
		for(OrderByItem item : orders){
			ordersCriteria.put(item.getFieldName(), item.getType());
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
	private static void predicatBuilder(Object source , SearchType criteria ,Search annot,Field field, RestrictionsContainer container) throws IllegalArgumentException, IllegalAccessException{
		
                //recuperation de la valeur du champs
		Object fieldValue = getFieldValue2(source, field);
                /**
                if(fieldValue==null)
                              return ;**/
                
		if(criteria==SearchType.EQUAL&&fieldValue!=null){
			
			      String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
			      if(fieldValue.getClass() == String.class){
			    	  container.addEq(fieldName,(String)fieldValue);
			      }else if(fieldValue.getClass()==Character.class||fieldValue.getClass()==char.class){
			    	  container.addEq(fieldName,(Character)fieldValue);
			      }else if(fieldValue.getClass()==Date.class) {
			    	  container.addEq(fieldName,(Date)fieldValue);
			      }else if(fieldValue.getClass()==Short.class||fieldValue.getClass()==short.class){
			    	  container.addEq(fieldName,(Short)fieldValue);
			      }else if(fieldValue.getClass()==Byte.class||fieldValue.getClass()==byte.class){
			    	  container.addEq(fieldName,(Byte)fieldValue);
			      }else if(fieldValue.getClass()==Integer.class||fieldValue.getClass()==int.class){
			    	  container.addEq(fieldName,(Integer)fieldValue);
			      }else if(fieldValue.getClass()==Long.class||fieldValue.getClass()==long.class){
			    	  container.addEq(fieldName,(Long)fieldValue);
			      }else if(fieldValue.getClass()==Float.class ||fieldValue.getClass()==float.class){
			    	  container.addEq(fieldName,(Float)fieldValue);
			      }else if(fieldValue.getClass()==Double.class||fieldValue.getClass()==double.class){
			    	  container.addEq(fieldName, (Double) fieldValue);
			      }else if(fieldValue.getClass()==Boolean.class||fieldValue.getClass()==boolean.class){
			    	  container.addEq(fieldName,(Boolean)fieldValue);
			      }else {
                                  container.addEq(fieldName, (Comparable)fieldValue);
                              }
		}else if(criteria==SearchType.GE&&fieldValue!=null){
			
		      String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
		      if(fieldValue.getClass() == String.class){
		    	  container.addGe(fieldName,(String)fieldValue);
		      }else if(fieldValue.getClass()==Character.class||fieldValue.getClass()==char.class){
		    	  container.addGe(fieldName,(Character)fieldValue);
		      }else if(fieldValue.getClass()==Date.class) {
		    	  container.addGe(fieldName,(Date)fieldValue);
		      }else if(fieldValue.getClass()==Short.class||fieldValue.getClass()==short.class){
		    	  container.addGe(fieldName,(Short)fieldValue);
		      }else if(fieldValue.getClass()==Byte.class||fieldValue.getClass()==byte.class){
		    	  container.addGe(fieldName,(Byte)fieldValue);
		      }else if(fieldValue.getClass()==Integer.class||fieldValue.getClass()==int.class){
		    	  container.addGe(fieldName,(Integer)fieldValue);
		      }else if(fieldValue.getClass()==Long.class||fieldValue.getClass()==long.class){
		    	  container.addGe(fieldName,(Long)fieldValue);
		      }else if(fieldValue.getClass()==Float.class ||fieldValue.getClass()==float.class){
		    	  container.addGe(fieldName,(Float)fieldValue);
		      }else if(fieldValue.getClass()==Double.class||fieldValue.getClass()==double.class){
		    	   container.addGe(fieldName, (Double) fieldValue);
		      }else {
                            container.addGe(fieldName, (Comparable)fieldValue);
                      }
	  }else if(criteria==SearchType.GT&&fieldValue!=null){
			
	      String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      if(fieldValue.getClass() == String.class){
	    	  container.addGt(fieldName,(String)fieldValue);
	      }else if(fieldValue.getClass()==Character.class||fieldValue.getClass()==char.class){
	    	  container.addGt(fieldName,(Character)fieldValue);
	      }else if(fieldValue.getClass()==Date.class) {
	    	  container.addGt(fieldName,(Date)fieldValue);
	      }else if(fieldValue.getClass()==Short.class||fieldValue.getClass()==short.class){
	    	  container.addGt(fieldName,(Short)fieldValue);
	      }else if(fieldValue.getClass()==Byte.class||fieldValue.getClass()==byte.class){
	    	  container.addGt(fieldName,(Byte)fieldValue);
	      }else if(fieldValue.getClass()==Integer.class||fieldValue.getClass()==int.class){
	    	  container.addGt(fieldName,(Integer)fieldValue);
	      }else if(fieldValue.getClass()==Long.class||fieldValue.getClass()==long.class){
	    	  container.addGt(fieldName,(Long)fieldValue);
	      }else if(fieldValue.getClass()==Float.class ||fieldValue.getClass()==float.class){
	    	  container.addGt(fieldName,(Float)fieldValue);
	      }else if(fieldValue.getClass()==Double.class||fieldValue.getClass()==double.class){
	    	  container.addGt(fieldName, (Double) fieldValue);
	      }else {
                    container.addGt(fieldName, (Comparable)fieldValue);
               }
      }else if(criteria==SearchType.ISEMPTY){
			
	      String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      
	      if(annot.type().equals(String.class)){
	    	  container.addIsEmpty(fieldName,(String)fieldValue);
	      }else if(annot.type().equals(Collection.class)){
	    	  //container.addIsEmpty(fieldName,(Collection)fieldValue);
	      }
      }else if(criteria==SearchType.ISFALSE){
			
	      String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      
	      if(annot.type().equals(Boolean.class)){
	    	  container.addIsFalse(fieldName);
	      }
      }else if(criteria==SearchType.ISNOTEMPTY){
			
	      String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      
	      if(annot.type().equals(String.class)){
	    	  container.addIsNotEmpty(fieldName,(String)fieldValue);
	      }
      }else if(criteria==SearchType.ISNOTNULL){
		
	      String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();              
              //System.out.println("ValidateAndFillBean.RestrictionsContainer executeSearchAnnotation(Object source) ::: "+fieldName+" :::: "+fieldValue);
              container.addNotNull(fieldName, (Comparable)fieldValue);                      
	      
      }else if(criteria==SearchType.ISNULL){
			
	      String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      container.addIsNull(fieldName, (Comparable)fieldValue);              
	      
      }else if(criteria==SearchType.ISTRUE){
			
	      String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
          
	      if(annot.type().equals(Boolean.class)){
	    	  container.addIsTrue(fieldName);
	      }	      
      }else if(criteria==SearchType.LE&&fieldValue!=null){
    	  
    	  String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      if(fieldValue.getClass() == String.class){
	    	  container.addLe(fieldName,(String)fieldValue);
	      }else if(fieldValue.getClass()==Character.class||fieldValue.getClass()==char.class){
	    	  container.addLe(fieldName,(Character)fieldValue);
	      }else if(fieldValue.getClass()==Date.class) {
	    	  container.addLe(fieldName,(Date)fieldValue);
	      }else if(fieldValue.getClass()==Short.class||fieldValue.getClass()==short.class){
	    	  container.addLe(fieldName,(Short)fieldValue);
	      }else if(fieldValue.getClass()==Byte.class||fieldValue.getClass()==byte.class){
	    	  container.addLe(fieldName,(Byte)fieldValue);
	      }else if(fieldValue.getClass()==Integer.class||fieldValue.getClass()==int.class){
	    	  container.addLe(fieldName,(Integer)fieldValue);
	      }else if(fieldValue.getClass()==Long.class||fieldValue.getClass()==long.class){
	    	  container.addLe(fieldName,(Long)fieldValue);
	      }else if(fieldValue.getClass()==Float.class||fieldValue.getClass()==float.class){
	    	  container.addLe(fieldName,(Float)fieldValue);
	      }else if(fieldValue.getClass()==Double.class||fieldValue.getClass()==double.class){
	    	  container.addLe(fieldName, (Double) fieldValue);
	      }else {
                    container.addLe(fieldName, (Comparable)fieldValue);
               }
      }else if(criteria==SearchType.LIKE&&fieldValue!=null){
    	  
          String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      
	      if(annot.type().equals(String.class)){
	    	  container.addLike(fieldName,(String)fieldValue);
	      }
      }else if(criteria==SearchType.LT&&fieldValue!=null){
    	  
    	  String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      if(fieldValue.getClass() == String.class){
	    	  container.addLt(fieldName,(String)fieldValue);
	      }else if(fieldValue.getClass()==Character.class||fieldValue.getClass()==char.class){
	    	  container.addLt(fieldName,(Character)fieldValue);
	      }else if(fieldValue.getClass()==Date.class) {
	    	  container.addLt(fieldName,(Date)fieldValue);
	      }else if(fieldValue.getClass()==Short.class||fieldValue.getClass()==short.class){
	    	  container.addLt(fieldName,(Short)fieldValue);
	      }else if(fieldValue.getClass()==Byte.class||fieldValue.getClass()==byte.class){
	    	  container.addLt(fieldName,(Byte)fieldValue);
	      }else if(fieldValue.getClass()==Integer.class||fieldValue.getClass()==int.class){
	    	  container.addLt(fieldName,(Integer)fieldValue);
	      }else if(fieldValue.getClass()==Long.class||fieldValue.getClass()==long.class){
	    	  container.addLt(fieldName,(Long)fieldValue);
	      }else if(fieldValue.getClass()==Float.class ||fieldValue.getClass()==float.class){
	    	  container.addLt(fieldName,(Float)fieldValue);
	      }else if(fieldValue.getClass()==Double.class||fieldValue.getClass()==double.class){
	    	  container.addLt(fieldName,(Double)fieldValue);
	      }else {
                    container.addLt(fieldName, (Comparable)fieldValue);
               }
      }else if(criteria==SearchType.NOTEQ&&fieldValue!=null){
    	  
    	  String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      if(fieldValue.getClass() == String.class){
	    	  container.addNotEq(fieldName,(String)fieldValue);
	      }else if(fieldValue.getClass()==Character.class||fieldValue.getClass()==char.class){
	    	  container.addNotEq(fieldName,(Character)fieldValue);
	      }else if(fieldValue.getClass()==Date.class) {
	    	  container.addNotEq(fieldName,(Date)fieldValue);
	      }else if(fieldValue.getClass()==Short.class||fieldValue.getClass()==short.class){
	    	  container.addNotEq(fieldName,(Short)fieldValue);
	      }else if(fieldValue.getClass()==Byte.class||fieldValue.getClass()==byte.class){
	    	  container.addNotEq(fieldName,(Byte)fieldValue);
	      }else if(fieldValue.getClass()==Integer.class||fieldValue.getClass()==int.class){
	    	  container.addNotEq(fieldName,(Integer)fieldValue);
	      }else if(fieldValue.getClass()==Long.class||fieldValue.getClass()==long.class){
	    	  container.addNotEq(fieldName,(Long)fieldValue);
	      }else if(fieldValue.getClass()==Float.class ||fieldValue.getClass()==float.class){
	    	  container.addNotEq(fieldName,(Float)fieldValue);
	      }else if(fieldValue.getClass()==Double.class ||fieldValue.getClass()==double.class ){
	    	   container.addNotEq(fieldName, (Double) fieldValue);
	      }else if(fieldValue.getClass()==Boolean.class||fieldValue.getClass()==boolean.class){
	    	  container.addNotEq(fieldName,(Boolean)fieldValue);
	      }else {
                    container.addNotEq(fieldName, (Comparable)fieldValue);
                }
      }else if(criteria==SearchType.NOTLIKE&&fieldValue!=null){
    	  
    	  String fieldName = annot.field().trim().isEmpty() ? field.getName() : annot.field().trim();
	      
	      if(annot.type().equals(String.class)){
	    	  container.addNotLike(fieldName,(String)fieldValue);
	      }
      }
			
		return  ;
	}
	
	
	
	public static Map<String, OrderType> getOrdersCriteria() {
		return ordersCriteria;
	}



	
   /**
    * Construit le filtre de echarche a parti d'un formulaire
    * @param _source 
    */
   public static RestrictionsContainer buildSearchCriteria(Object _source) throws IllegalArgumentException, IllegalAccessException{
       
       return executeSearchAnnotation(_source);
   }
   
   
   /**
    * Validation du RIB 
    * @param rib
    * @return 
    */
   public static boolean checkRib(String rib) {
        StringBuilder extendedRib = new StringBuilder(rib.length());
        for (char currentChar : rib.toCharArray()) {
            //Works on base 36
            int currentCharValue = Character.digit(currentChar, Character.MAX_RADIX);
            //Convert character to simple digit
            extendedRib.append(currentCharValue < 10 ? currentCharValue : (currentCharValue + (int) StrictMath.pow(2, (currentCharValue - 10) / 9)) % 10);
        }

        return new BigDecimal(extendedRib.toString()).remainder(new BigDecimal(97)).intValue() == 0;
    }

}
