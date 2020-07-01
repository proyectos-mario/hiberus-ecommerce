package com.hiberus.checkoutecommerce.enums;
/**
 * API Urls
 * @author soloP
 *
 */
public enum APIEnum {
	BILL_API("http://localhost:7000/api/bill"), LOGISTIC_API("http://localhost:7001/api/logistic");

	private String urlApi;

	private APIEnum(String urlApi) {
		this.urlApi = urlApi;
	}

	public String getUrlApi() {
		return this.urlApi;
	}
}
