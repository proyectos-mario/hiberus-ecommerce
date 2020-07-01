package com.hiberus.logisticecommerce.repository;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class LogisticDao {
	
	
	@Autowired
    public EntityManager em;


    public Integer getNumberOrder() {
    	try {
        Query q= em.createNativeQuery("SELECT nextval('ecommercelogistic.order_id_seq')");
       BigInteger seq=((BigInteger) q.getSingleResult());
       return seq.intValue();
    	}
    	catch(Exception e) {
    		LogManager.getLogger(this.getClass().getName()).error("Logistic Dao process fail:"+ e.getMessage());
    		throw e;
    	}
       
    }
}
