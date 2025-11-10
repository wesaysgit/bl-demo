package com.es.esdemo.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class JHpayCallBack {

	private String POSID;
	
	private String BRANCHID;
	
	private String ORDERID;
	
	private BigDecimal PAYMENT;
	
	private String CURCODE;
	
	private String REMARK1;
	
	private String REMARK2;
	
	private String ACC_TYPE;
	
	private String SUCCESS;
	
	private String TYPE;
	
	private String REFERER;
	
	private String CLIENTIP;
	
	private String ACCDATE;
	
	private String USRMSG;
	
	private String USRINFO;
	
	private String PAYTYPE;
	
	private String SIGN;

}
