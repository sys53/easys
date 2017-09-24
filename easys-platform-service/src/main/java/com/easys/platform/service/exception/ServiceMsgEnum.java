/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.service.exception;

import com.easys.commons.enums.IEnum;
import lombok.Getter;

/**
 * @author sys53
 * @date 2017/9/11
 */

@Getter
public enum ServiceMsgEnum implements IEnum {

    SERVICE_DAO_IS_NULL("ERR_S_001", "dao未定义"),
    SERVICE_MANAGER_IS_NULL("ERR_S_002", "manager未定义"),
    ;
    private String code;
    private String name;


    ServiceMsgEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String getSimpleName() {
        return this.name;
    }
}
