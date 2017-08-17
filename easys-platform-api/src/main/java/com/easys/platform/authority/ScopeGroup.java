/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.authority;

/**
 * 定义范围域组api接口
 * Created by tianjiongming on 2017/2/23.
 */
public   interface ScopeGroup {

      String getScopeGroupId() ;

      void setScopeGroupId(String scopeGroupId) ;
      String getName();

      void setName(String name) ;

      String getMemo() ;

      void setMemo(String memo) ;

      String getOrgId() ;

      void setOrgId(String orgId) ;
}
