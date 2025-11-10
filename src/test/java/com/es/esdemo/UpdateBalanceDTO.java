package com.es.esdemo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UpdateBalanceDTO implements Serializable {

    private static final long serialVersionUID = -4403315674415757904L;

    private Integer type;

    private BigDecimal money;

    private Long id;

    private Integer isAdd;

}
