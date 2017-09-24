/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dao;


import com.easys.commons.page.Page;
import com.easys.commons.page.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * 泛化的Dao基础接口
 * <p/>
 * User: sys53
 * Date: 15-8-31 上午9:47
 * version $Id: BaseDao.java, v 0.1 Exp $
 */
public interface BaseDao<P, I extends Serializable, Q extends Paging>  extends GenericDao{

    /**
     * 根据ID查找对象
     *
     * @param id 对象ID
     * @return 找到的对象
     */
    P getById(I id);

    /**
     * 根据ID删除对象
     *
     * @param id
     */
    void removeById(I id);

    /**
     * 根据id数组删除一组对象
     *
     * @param ids
     */
    void batchRemoveByIds(I[] ids);

    /**
     * 默认的分页查询（仅在相关service、biz层代码时使用）
     *
     * @param qo
     * @return
     */
    Page<P> queryPage(Q qo);

    /**
     * 查找全部
     *
     * 警告，虽然默认有本接口，但建议对于数据量较大的领域类要禁用此方法
     *
     * @return
     */
    List<P> findAll();


}
