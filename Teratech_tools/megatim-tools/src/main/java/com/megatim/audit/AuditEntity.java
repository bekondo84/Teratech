/**
 * 
 */
package com.megatim.audit;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Transient;

/**
 * @author nctchuente
 *
 */
public class AuditEntity implements Serializable, Comparable<AuditEntity>{
	
	@Transient
    private  String code ;
	
	@Transient
	private String nameEntity ;
	
	@Transient
	private boolean result =true;
	
	@Transient 
	private String description ;
	
	@Transient
	private List<AuditElements> listAuditElts;
	
	@Transient
	private String typeAudit ;

	/**
	 * @return the typeAudit
	 */
	public String getTypeAudit() {
		return typeAudit;
	}

	/**
	 * @param typeAudit the typeAudit to set
	 */
	public void setTypeAudit(String typeAudit) {
		this.typeAudit = typeAudit;
	}

	/**
	 * @return the nameEntity
	 */
	public String getNameEntity() {
		return nameEntity;
	}

	/**
	 * @param nameEntity the nameEntity to set
	 */
	public void setNameEntity(String nameEntity) {
		this.nameEntity = nameEntity;
	}

	/**
	 * @return the result
	 */
	public boolean isResult() {
		for(AuditElements elts : getListAuditElts()){
			if(elts.isResult()==false){
				result=false;
				break;
			}
		}
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * @return the listAuditElts
	 */
	public List<AuditElements> getListAuditElts() {
		return listAuditElts;
	}

	/**
	 * @param listAuditElts the listAuditElts to set
	 */
	public void setListAuditElts(List<AuditElements> listAuditElts) {
		this.listAuditElts = listAuditElts;
	}

	/**
	 * @return the code
	 */
	public  String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public  void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public int compareTo(AuditEntity o) {
		return code.compareTo(o.code);
	}
    
}
