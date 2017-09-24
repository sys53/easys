/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.service.exception;

import com.easys.commons.enums.IEnum;
import com.easys.platform.EasysException;

/**
 * Service层异常类
 *
 * @author sys53
 * @date 2017/9/11
 */
public class ServiceException extends EasysException {
    public ServiceException(IEnum iEnum, Throwable e) {
        super(iEnum, e);
    }

    public ServiceException(IEnum iEnum) {
        super(iEnum);
    }

    public ServiceException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public ServiceException(String errorCode, String errorMsg, Throwable e) {
        super(errorCode, errorMsg, e);
    }
}
