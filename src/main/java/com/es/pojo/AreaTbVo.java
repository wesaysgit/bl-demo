package com.es.pojo;

import lombok.Data;

import java.util.List;

@Data
public class AreaTbVo {

    private AreaTb areaTb;

    private List<AreaTbVo> subList;

}
