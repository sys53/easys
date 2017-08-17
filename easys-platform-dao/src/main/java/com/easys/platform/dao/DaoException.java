/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dao;


import com.easys.commons.enums.IEnum;
import com.easys.platform.EasysException;

/**
 * Dao层异常类
 * <p/>
 * User: sys53
 * Date: 15/12/16 下午3:04
 * version $Id: DaoException.java, v 0.1  下午3:04 Exp $
 */
public class DaoException extends EasysException {
    public DaoException(IEnum en, Throwable e) {
        super(en, e);
    }

    public DaoException(IEnum en) {
        super(en);
    }

    public DaoException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public DaoException(String errorCode, String errorMsg, Throwable e) {
        super(errorCode, errorMsg, e);
    }
}
