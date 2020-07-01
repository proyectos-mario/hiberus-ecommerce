package voecommercecom.hiberus.commons.vo;

import java.io.Serializable;

public class OutBillVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean procesoOk;
	
	private String mensaje;
	
	private Double sum;

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public Boolean getProcesoOk() {
		return procesoOk;
	}

	public void setProcesoOk(Boolean procesoOk) {
		this.procesoOk = procesoOk;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
