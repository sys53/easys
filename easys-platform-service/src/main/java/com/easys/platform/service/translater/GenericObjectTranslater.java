/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.service.translater;


import com.easys.commons.converter.BeanConverter;

import java.util.Collection;
import java.util.List;


/**
 * 通用象转化
 * <p/>
 * User: sys53
 * Date: 14-12-10 下午12:01
 * version $Id: GenericObjectTranslator.java, v 0.1 Exp $
 */
public class GenericObjectTranslater<S, T> implements ObjectTranslater<S, T> {
    public T transfer(T t, S s) {
        if (s == null) {
            return t;
        }
        return BeanConverter.convert(t, s);
    }

    @Override
    public T transfer(T t, S s, String[] ignoreProperties) {
        if (s == null) {
            return t;
        }
        return BeanConverter.convert(t, s, ignoreProperties);
    }

    public List<T> transfer(Class<T> clazz, Collection<S> ses) {
        return BeanConverter.convert(clazz, ses);
    }

    @Override
    public List<T> transfer(Class<T> clazz, Collection<S> ses, String[] ignoreProperties) {
        return BeanConverter.convert(clazz, ses, ignoreProperties);
    }
}
