/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.annotation;

import java.lang.annotation.*;

/**
 * @author sys53
 * @date 2017/8/20
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE })
public @interface Table {
    String value();
    String type() default "hibernate";
}
