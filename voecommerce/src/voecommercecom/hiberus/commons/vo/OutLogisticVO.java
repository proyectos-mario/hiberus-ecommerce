package voecommercecom.hiberus.commons.vo;

import java.io.Serializable;

public class OutLogisticVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean procesoOk;
	
	private String message;
	
	private Integer numberOrder;

	public Boolean getProcesoOk() {
		return procesoOk;
	}

	public void setProcesoOk(Boolean procesoOk) {
		this.procesoOk = procesoOk;
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
