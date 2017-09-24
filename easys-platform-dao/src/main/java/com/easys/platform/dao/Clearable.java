/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dao;

/**
 * @author sys53
 * @date 2017/8/17
 */
public interface Clearable {
    /**
     * 分离所有正在被管理的实体
     */
    void clear();
}
