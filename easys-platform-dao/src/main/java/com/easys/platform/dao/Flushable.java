/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dao;

/**
 * @author sys53
 * @date 2017/8/17
 */
public interface Flushable {
    /**
     * 将缓存中的数据刷入数据库
     */
    void flush();
}
