/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.manager.impl;

import com.easys.commons.page.Page;
import com.easys.commons.page.Paging;
import com.easys.platform.dao.BaseDao;
import com.easys.platform.manager.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @author sys53
 * @date 2017/9/4
 */
public class BaseManagerImpl<P, I extends Serializable, Q extends Paging> implements BaseManager<P, I, Q> {

    @Override
    public P getById(I id) {
        return baseDao.getById(id);
    }

    @Override
    public List<P> findAll() {
        return baseDao.findAll();
    }

    @Override
    public void save(P po) {
        baseDao.save(po);
    }

    @Override
    public void removeById(I id) {
        baseDao.removeById(id);
    }

    @Override
    public void removeByIds(I[] ids) {
        baseDao.batchRemoveByIds(ids);
    }

    @Override
    public Page<P> queryPage(Q qo) {
        return baseDao.queryPage(qo);
    }

    @Autowired
    private BaseDao<P, I, Q> baseDao;
}
