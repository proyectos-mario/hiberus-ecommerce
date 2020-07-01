package com.hiberus.checkoutecommerce.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the client database table.
 * 
 */
@Entity
@Table(schema = "ecommerce", name = "client")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "client_id")
	private Integer personaId;

	private String name;

	private String lastName;

	private String phoneNumber;

	public Integer getPersonaId() {
		return personaId;
	}

	public void setPersonaId(Integer personaId) {
		this.personaId = personaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}