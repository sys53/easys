/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.manager;import com.easys.commons.page.Page;
import com.easys.commons.page.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * 泛化通用manager层接口
 *
 * @author sys53
 * @date 2017/9/4
 */
public interface BaseManager<P, I extends Serializable, Q extends Paging> {
    /**
     * 根据Id得到实体对象
     *
     * @param id
     * @return
     */
    P getById(I id);

    /**
     * 查询所有对象
     *
     * @return
     */
    List<P> findAll();

    /**
     * 添加一个对象
     *
     * @param po
     */
    void save(P po);

    /**
     * 根据ID删除一个对象
     *
     * @param id
     */
    void removeById(I id);

    /**
     * 根据ids删除一个对象
     *
     * @param ids
     */
    void removeByIds(I[] ids);

    /**
     * 默认的分页查询（仅在不写相关service、biz层代码时使用）
     *
     * @param qo
     * @return
     */
    Page<P> queryPage(Q qo);
}
