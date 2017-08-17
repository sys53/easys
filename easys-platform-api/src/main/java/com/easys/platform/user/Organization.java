/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.user;

/**
 * 机构组织的值接口，外部星星过来后都是组织机构的子类
 * <p/>
 * User: weixi
 * Date: 2016/4/5 10:24
 * version $Id: Organization.java, v 0.1  10:24 Exp $
 */
public interface Organization {

    String getOrganizationId() ;

    void setOrganizationId(String organizationId);
    String getOrgCode();

    String getNativeOrgCode();

    void setOrgCode(String orgCode);

    String getOrgName();

    void setOrgName(String orgName);

    String getOrgLevel() ;

    void setOrgLevel(String orgLevel) ;

    String getOrgType() ;

    void setOrgType(String orgType) ;
}
