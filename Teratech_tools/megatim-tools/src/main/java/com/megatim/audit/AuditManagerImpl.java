/**
 * 
 */
package com.megatim.audit;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;

import com.megatim.common.annotations.Encryption;
import com.megatimgroup.mgt.commons.tools.SystemEncryptioMetaData;

/**
 * @author ntchuente
 *
 */
public class AuditManagerImpl<T extends Object> {

	public  AuditEntity DECrypteBean(T _cible, String code, String description,String typeAudit) {
		AuditEntity _audit =new AuditEntity();
		List<AuditElements>_auditELts = new ArrayList<AuditElements>();
		
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
	        // crypter les champs annotés  @Cryptage
	        for(Field field :cibleFields){
	        	AuditElements _elements = new AuditElements();
	    		boolean _result = true ;
	        	 //Verification de l'existance de @Encryption
	            if (field.isAnnotationPresent(Encryption.class)) {
	            	
	            	Encryption annot = field.getAnnotation(Encryption.class);
	            	
	            	String CryptefieldName = field.getName();
	            	String nameColunm = "";
	            	
	                if (!annot.mappedBy().trim().isEmpty()) {
	                	CryptefieldName = annot.mappedBy();
	                	nameColunm=annot.hashMappedBy();
	                }
	                
	              
	                // recuppération du champ crypter
	                Field _crypteField = cibleFieldsMap.get(CryptefieldName);
	                
	                // recuppérer la valeur non crypter
	                Field _FieldNormal = cibleFieldsMap.get(nameColunm);
	                // decrypter la veleur _crypteField
	                _crypteField.setAccessible(true);
	                _FieldNormal.setAccessible(true);
	                SystemEncryptioMetaData encrypt= SystemEncryptioMetaData.getInstance();
	                
               try {
            	   BigDecimal valueInit = new BigDecimal(_FieldNormal.get(_cible).toString());
            	   BigDecimal valuedecrypter = BigDecimal.ZERO;
            	  
            	   if(_crypteField.get(_cible)!=null){
            		   String valueDecryptStrng = _crypteField.get(_cible).toString() ;
            	    valuedecrypter = new BigDecimal(encrypt.decryptText(valueDecryptStrng));
            	   }
            	   if(valueInit.compareTo(valuedecrypter)!=0)
            		   _result=false;
	          		_elements = new AuditElements(nameColunm, valueInit, valuedecrypter,_result);
	          		_auditELts.add(_elements);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                
	            }
	        	
	        }
	        _audit.setCode(code);
	        _audit.setDescription(description);
	        _audit.setNameEntity(_cible.getClass().getName());
	        _audit.setTypeAudit(typeAudit);
	        _audit.setListAuditElts(_auditELts);
			return _audit;
	
}


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
