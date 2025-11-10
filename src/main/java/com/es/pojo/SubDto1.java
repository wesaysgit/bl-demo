package com.es.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SubDto1 extends BaseDto {
    private Long userId;
}
