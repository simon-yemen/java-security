package com.juejin.security.vo;

import lombok.Data;

@Data
public abstract class BaseReq {

    private String traceId;

    private Integer pageIndex = 1;

    private Integer pageSize = 10;

}
