/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.service.translater;

import java.util.Collection;
import java.util.List;


/**
 * DTO 通用接口
 * <p/>
 * User: sys53
 * Date: 17-09-10 上午10:42
 * version $Id: ObjectTranslator.java, v 0.1 Exp $
 */
public interface ObjectTranslater<S, T> {

    /**
     * 源对象 转化为 目标对象
     *
     * @param t 目标对象
     * @param s 源对象
     * @return 目标对象
     */
    T transfer(T t, S s);

    /**
     * 源对象 转化为 目标对象
     *
     * @param t                目标对象
     * @param s                源对象
     * @param ignoreProperties 忽略拷贝的属性
     * @return 目标对象
     */
    T transfer(T t, S s, String[] ignoreProperties);

    /**
     * 源对象集转 转化为 目标集合
     *
     * @param clazz
     * @param ses
     * @return 分页列表
     */
    List<T> transfer(Class<T> clazz, Collection<S> ses);

    /**
     * 源对象集转 转化为 目标集合
     *
     * @param clazz            目标集合的class
     * @param ses              源集合
     * @param ignoreProperties 忽略拷贝的属性
     * @return 分页列表
     */
    List<T> transfer(Class<T> clazz, Collection<S> ses, String[] ignoreProperties);


}
