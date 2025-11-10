package com.es.esdemo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 泊链用户中心-用户信息 dto
 * @author pengzhuo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlUserInfoDTO implements Serializable {
    private static final long serialVersionUID = -3267806328590932742L;

    /** 用户id */
    private Integer userId;

    /** 手机号 */
    private String mobile;

}
