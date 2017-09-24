/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.service;

import com.easys.commons.page.Page;
import com.easys.commons.page.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * 服务层通用泛化接口
 *
 * @author sys53
 * @date 2017/9/4
 */
public interface BaseService<B,P,I extends Serializable, Q extends Paging> {
    /**
     * 根据Id得到实体对象
     *
     * @param id
     * @return
     */
    B getById(I id);

    /**
     * 查询所有对象
     *
     * @return
     */
    List<B> findAll();

    /**
     * 持久化一个对象
     *
     * @param bo
     */
    void save(B bo);

    /**
     * 根据id删除一个对象
     *
     * @param id
     */
    void removeById(I id);

    /**
     * 根据id数组批量删除
     *
     * @param ids
     */
    void removeByIds(I[] ids);

    /**
     * 分页查询
     *
     * @param qo
     * @return Page<VO>
     */
    Page<B> queryPage(Q qo);
}
