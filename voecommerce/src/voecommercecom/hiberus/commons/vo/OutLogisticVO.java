package voecommercecom.hiberus.commons.vo;

import java.io.Serializable;

public class OutLogisticVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean processOk;
	
	private String message;
	
	private Integer numberOrder;

	public Boolean getProcessOk() {
		return processOk;
	}

	public void setProcessOk(Boolean processOk) {
		this.processOk = processOk;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getNumberOrder() {
		return numberOrder;
	}

	public void setNumberOrder(Integer numberOrder) {
		this.numberOrder = numberOrder;
	}

}
