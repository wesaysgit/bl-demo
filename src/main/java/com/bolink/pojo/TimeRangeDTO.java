package com.bolink.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 时间区间DTO，包含毫秒、秒以及日期格式
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeRangeDTO implements Serializable {

    private static final long serialVersionUID = -1754034210140748047L;

    /**
     * 起始时间毫秒
     */
    private long startMillis;
    /**
     * 结束时间毫秒
     */
    private long endMillis;
    /**
     * 起始时间秒
     */
    private long startSec;
    /**
     * 结束时间秒
     */
    private long endSec;
    /**
     * 起始日期
     */
    private Date startDate;
    /**
     * 结束日期
     */
    private Date endDate;
}