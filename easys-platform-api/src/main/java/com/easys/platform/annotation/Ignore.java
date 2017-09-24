/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.annotation;

import java.lang.annotation.*;

/**
 * 忽略查询的注解
 *
 * @author sys53
 * @date 2017/8/27
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Ignore {
}
