/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform;

import com.easys.commons.enums.IEnum;

/**
 * Easys基础异常类
 * @author sys53
 * @date 2017/8/13
 */
public class EasysException extends RuntimeException {

    private static final long serialVersionUID = -6141133183971970554L;
    private String errCode = "UNKNOWN_EXCEPTION";

    public EasysException(IEnum iEnum, Throwable e) {
        super(iEnum.getName(), e);
        this.errCode = iEnum.getCode();
    }

    public EasysException(IEnum iEnum) {
        super(iEnum.getName());
        this.errCode = iEnum.getCode();
    }

    public EasysException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errCode = errorCode;
    }

    public EasysException(String errorCode, String errorMsg, Throwable e) {
        super(errorMsg, e);
        this.errCode = errorCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}

