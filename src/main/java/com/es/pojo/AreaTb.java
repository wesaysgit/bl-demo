package com.es.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaTb {

    private Integer id;

    private Integer userId;

    private Integer areaCode;

    private String areaName;

    private Integer areaParentId;

    private Integer areaType;

}
