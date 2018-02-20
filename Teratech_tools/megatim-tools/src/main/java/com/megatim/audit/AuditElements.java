package com.megatim.audit;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Transient;

/**
 * @author nctchuente
 *
 */

public class AuditElements implements Serializable, Comparable<AuditElements> {
	
	@Transient
    private static String code ;
	
	@Transient
	private String namecolunm;
	@Transient
	private BigDecimal valueInitial;
	@Transient
	private BigDecimal valueCryter;
	@Transient
	private boolean result ;
	
	
	public AuditElements(){
		
	}
	
	public AuditElements(String namecolunm,BigDecimal valueInitial,BigDecimal valueCryter , boolean result){
		this.namecolunm = namecolunm;
		this.valueInitial= valueInitial;
		this.valueCryter=valueCryter;
		this.result=result;
		
	}
	
	/**
	 * @return the namecolunm
	 */
	public String getNamecolunm() {
		return namecolunm;
	}
	/**
	 * @param namecolunm the namecolunm to set
	 */
	public void setNamecolunm(String namecolunm) {
		this.namecolunm = namecolunm;
	}
	/**
	 * @return the valueInitial
	 */
	public BigDecimal getValueInitial() {
		return valueInitial;
	}
	/**
	 * @param valueInitial the valueInitial to set
	 */
	public void setValueInitial(BigDecimal valueInitial) {
		this.valueInitial = valueInitial;
	}
	/**
	 * @return the valueCryter
	 */
	public BigDecimal getValueCryter() {
		return valueCryter;
	}
	/**
	 * @param valueCryter the valueCryter to set
	 */
	public void setValueCryter(BigDecimal valueCryter) {
		this.valueCryter = valueCryter;
	}

	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}
	
	/**
	 * @return the code
	 */
	public static String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public static void setCode(String code) {
		AuditElements.code = code;
	}

	
	public int compareTo(AuditElements o) {
		return namecolunm.compareTo(o.namecolunm);
	}
    

}
