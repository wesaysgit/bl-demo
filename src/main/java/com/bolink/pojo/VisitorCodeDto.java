package com.bolink.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitorCodeDto {

    private VisitorCodeData data;

    private Long unionId;

    private String sign;

}


