package voecommercecom.hiberus.commons.vo;

import java.io.Serializable;

public class OutCheckOutVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean processOk;
	
	private String message;
	
	private OutLogisticVO logisticOut;
	
	private OutBillVO billOut;

	public OutLogisticVO getLogisticOut() {
		return logisticOut;
	}

	public void setLogisticOut(OutLogisticVO logisticOut) {
		this.logisticOut = logisticOut;
	}

	public OutBillVO getBillOut() {
		return billOut;
	}

	public void setBillOut(OutBillVO billOut) {
		this.billOut = billOut;
	}

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

}
