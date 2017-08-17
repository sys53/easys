/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.authority;


import com.easys.platform.engine.kernel.FromVersion;

import java.util.List;
import java.util.Set;

/**
 * 定义权限代码api接口
 * <p/>
 * User: sys53
 * Date: 2016/4/19 11:07
 * version $Id: AuthCode.java, v 0.1  11:07 Exp $
 */
public interface AuthCode extends FromVersion {

    List<AuthScope> getAuthScopes();

    void setAuthScopes(List<AuthScope> authScopes);

    Set<Function> getFunctions();

    void setFunctions(Set<Function> functions);

    String getAuthCodeId();

    void setAuthCodeId(String authCodeId);

    String getCode();

    void setCode(String code);

    String getMaskCode();

    void setMaskCode(String maskCode);

    String getAuthType();

    void setAuthType(String authType);

    String getAuthName();

    void setAuthName(String authName);

    String getAuthDesc();

    void setAuthDesc(String authDesc);

    Boolean getIsScope();

    void setIsScope(Boolean isScope);

    String getBelongtosys();

    void setBelongtosys(String belongtosys);

    Boolean getIsGeneral();

    void setIsGeneral(Boolean isGeneral);

    Boolean getDisabled();

    void setDisabled(Boolean disabled);

}
