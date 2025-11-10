package com.es.esdemo.pojo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ShareItem{
        private String shopId;
        private Integer amount;
        private String remark;
    }