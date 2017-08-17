/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dao.enums;


import com.easys.commons.enums.IEnum;

/**
 * Created by liyingdan on 2016/8/19.
 */
public enum EasysPlatformDaoMsgEnum implements IEnum {


    DAO_GENERIC_OBJ_CAN_NOT_BE_NULL("DAO_ERR_01", "dao操作实体对象不能为空"),

    ID_CAN_NOT_BE_NULL("DAO_ERR_02", "id不能为空"),

    DIALECT_NOT_SET("DAO_ERR_03", "数据库方言未设置"),

    DIALECT_CLASS_NOT_FOUND("DAO_ERR_04", "数据库方言类未找到"),

    DIALECT_INSTANCE_ERROR("DAO_ERR_05", "数据库方言类实例化错误"),
    ;

    private String code;

    private String name;

    EasysPlatformDaoMsgEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSimpleName() {
        return this.name;
    }


}
