package com.es.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SubDto2 extends BaseDto {
    private String userName;
}
