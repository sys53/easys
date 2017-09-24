/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dao;

import com.easys.commons.page.Paging;
import com.easys.platform.annotation.Field;
import com.easys.platform.annotation.Table;
import lombok.Builder;
import lombok.Data;

/**
 * @author sys53
 * @date 2017/8/20
 */
@Builder
@Data
@Table(value = "Hoop_user")
public class UserQo extends Paging {
    @Field(name="userName",operator = "=",value = "userName")
    private String name;

    @Field(name="age",operator = ">=",value = "age")
    private int minAge;


    @Field(name="age",operator = "<=",value = "age")
    private int maxAge;
}
