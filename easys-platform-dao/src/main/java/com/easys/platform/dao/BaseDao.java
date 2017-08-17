/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dao;


import com.easys.commons.page.PageList;
import com.easys.commons.page.PagingOrder;

import java.io.Serializable;
import java.util.List;

/**
 * 泛化的Dao基础接口
 * <p/>
 * User: sys53
 * Date: 15-8-31 上午9:47
 * version $Id: BaseDao.java, v 0.1 Exp $
 */
public interface BaseDao<T, ID extends Serializable, QO extends PagingOrder> {

    /**
     * 根据ID查找对象
     *
     * @param id 对象ID
     * @return 找到的对象
     */
    T getById(ID id);


    /**
     * 刷新po对象
     *
     * @param obj
     * @return
     */
    T refresh(T obj);

    /**
     * 保存对象
     *
     * @param obj
     */
    void save(T obj);

    /**
     * 保存/更新对象
     *
     * @param obj
     */
    T merge(T obj);

    /**
     * 更新对象
     *
     * @param obj
     */
    void update(T obj);

    /**
     * 根据ID删除对象
     *
     * @param id
     */
    void deleteById(ID id);

    /**
     * 根据id数组删除一组对象
     *
     * @param ids
     */
    void deleteByIds(ID[] ids);

    /**
     * 删除对象
     *
     * @param obj 要删除的对象必须包含ID值
     */
    void delete(T obj);

    /**
     * 将缓存中的数据刷入数据库
     */
    void flush();

    /**
     * 分离所有正在被管理的实体
     */
    void clear();

    /**
     * 默认的分页查询（仅在相关service、biz层代码时使用）
     *
     * @param qo
     * @return
     */
    PageList<T> queryPageList(QO qo);

    /**
     * 查找全部
     *
     * @return
     */
    List<T> findAll();


}
