/**
 * 
 */
package com.bekosoftware.genericdaolayer.encryption.impl;

import com.megatim.common.annotations.Encryption;
import com.megatimgroup.mgt.commons.tools.SystemEncryptioMetaData;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;

/**
 * @author mgt
 *
 */
public abstract class GenericEncryptionImpl<T extends Object>  {
	
	 public static  <T> T CrypteBean(T _cible) {
	   	 Field[] cibleFields = getObjectDeclaredFields(_cible.getClass());//_cible.getClass().getDeclaredFields();//
	        //Declaration du dico des champs cible
	        Map<String, Field> cibleFieldsMap = new HashMap<String, Field>();
	        for (Field _field : cibleFields) {
	            cibleFieldsMap.put(_field.getName(), _field);
	        }
	        /**
	         * Ajouter Par @ntchuente 23/03/2017
	         * securisation des données juger critiques
	         */
	        // crypter les champs annotés  @Encryption
	        for(Field field :cibleFields){
	        	 //Verification de l'existance de @Encryption
	            if (field.isAnnotationPresent(Encryption.class)) {
	            	
	            	Encryption annot = field.getAnnotation(Encryption.class);
	            	String fieldName = field.getName();
	            	String CryptefieldName = field.getName();
	            	
	                if (!annot.mappedBy().trim().isEmpty()) {
	                	CryptefieldName = annot.mappedBy();
	                }
	                if (!annot.hashMappedBy().trim().isEmpty()) {
	                	fieldName = annot.hashMappedBy();
	                }
	                
	                // recupération du champ à crypter
	                Field _fieldToCrypte = cibleFieldsMap.get(fieldName);
	                // recuppération du champ qui doit contenir la valeur crypter
	                Field _crypteField = cibleFieldsMap.get(CryptefieldName);
	                // crypter la veleur _fieldToCrypte et copier la valeur crypter dans _crypteField
	                _crypteField.setAccessible(true);
	                _fieldToCrypte.setAccessible(true);
	            
	                SystemEncryptioMetaData encrypt= SystemEncryptioMetaData.getInstance();
                try {
	          		_crypteField.set(_cible,encrypt.encryptText(_fieldToCrypte.get(_cible).toString()));
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                
	            }
	        	
	        }
			return _cible;
	 }
			
		 public abstract <T> T DECrypteBean(T _cible);
	 

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

	        //System.out.println("Liste des champs is ::::::::::::::::::::::::::::::::::: "+_fields);
	        Field[] _filedss = new Field[_fields.size()];

	        int index = 0;
	        for (Field _field : _fields) {
	            _filedss[index] = _field;

	            index++;
	        }

	        return _filedss;
	    }

}
