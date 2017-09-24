/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dao;

/**
 * @author sys53
 * @date 2017/8/20
 */
public interface GenericDao {

    /**
     * 刷新po对象
     * @param obj
     * @param <E>
     */
    <E> void refresh(E obj);

    /**
     * 保存对象
     *
     * @param obj
     */
    <E> void save(E obj);

    /**
     * 保存/更新对象
     *
     * @param obj
     */
    <E> E merge(E obj);

    /**
     * 根据ID查找对象
     * @param clazz
     * @param id
     * @param <E>
     * @return
     */
    <E,I> E getById(Class<E> clazz,I id);

    /**
     * 根据ID删除对象
     * @param clazz
     * @param id
     * @param <E>
     */
    <E,I> void removeById(Class<E> clazz,I id);

    /**
     * 根据id数组删除一组对象
     * @param clazz
     * @param ids
     * @param <E>
     */
    <E,I> void batchRemoveByIds(Class<E> clazz,I... ids);


}
