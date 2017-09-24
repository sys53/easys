/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.service.impl;

import com.easys.commons.page.Page;
import com.easys.commons.page.Paging;
import com.easys.commons.util.BeanUtils;
import com.easys.platform.dao.BaseDao;
import com.easys.platform.manager.BaseManager;
import com.easys.platform.service.BaseService;
import com.easys.platform.service.exception.ServiceException;
import com.easys.platform.service.translater.ObjectTranslater;
import com.easys.platform.service.translater.ObjectTranslaterFactorySwitch;
import lombok.extern.log4j.Log4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import static com.easys.platform.service.exception.ServiceMsgEnum.SERVICE_DAO_IS_NULL;

/**
 * 泛化通用服务实现类
 *
 * @author sys53
 * @date 2017/9/4
 */
@Log4j
@Transactional
public class BaseServiceImpl<B, P, I extends Serializable, Q extends Paging> implements BaseService<B, P, I, Q> {

    /**
     * vo转po方法
     * <p/>
     * 如果<code>isOpenManager()</code>为true时，BO直接就是PO
     *
     * @param bo
     * @return
     */
    private P bo2po(B bo) {
        P po;
        if (isOpenManager()) {
            po = (P) bo;
        } else {
            po = getBo2Poer().transfer(BeanUtils.newInstance(getPoClass()), bo);
        }
        return po;
    }


    @Override
    public B getById(I id) {
        B bo = null;

        if (isOpenManager()) {
            bo = (B) baseDao.getById(id);
        } else {
            P po = baseManager.getById(id);

            if (null != po) {
                bo = getPo2Boer().transfer(BeanUtils.newInstance(getBoClass()), po);//po转vo
            }
        }
        return bo;
    }

    @Override
    public List<B> findAll() {
        List<B> result = null ;
        if (isOpenManager()) {
            result = (List<B>) baseDao.findAll();
        }else{
            List<P> poes = baseManager.findAll();
            if (CollectionUtils.isNotEmpty(poes)) {
                result = getPo2Boer().transfer(getBoClass(), poes);//po转bo
            }
        }
        return result;
    }

    @Override
    public void save(B bo) {
        P po = bo2po(bo);
        if (isOpenManager()) {
            if (baseDao == null) {
                throw new ServiceException(SERVICE_DAO_IS_NULL);
            }
            baseDao.save(po);
        } else {
            if (baseManager == null) {
                throw new ServiceException(SERVICE_DAO_IS_NULL);
            }
            baseManager.save(po);
        }
    }

    @Override
    public void removeById(I id) {
        if (isOpenManager()) {
            baseDao.removeById(id);
        } else {
            baseManager.removeById(id);
        }
    }

    @Override
    public void removeByIds(I[] ids) {
        if (isOpenManager()) {
            baseDao.batchRemoveByIds(ids);
        } else {
            baseDao.batchRemoveByIds(ids);
        }
    }

    @Override
    public Page<B> queryPage(Q qo) {
        return null;
    }


    protected ObjectTranslater<P, B> getPo2Boer() {
        return ObjectTranslaterFactorySwitch.create(getPoClass().getName(), getBoClass().getName()).createTransfer();
    }

    protected ObjectTranslater<B, P> getBo2Poer() {
        return ObjectTranslaterFactorySwitch.create(getBoClass().getName(), getPoClass().getName()).createTransfer();
    }

    private Class<P> getPoClass() {
        return (Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

    }

    private Class<B> getBoClass() {
        return (Class<B>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 是否开放Mangager层
     * <p>
     * 如果开放，那么不会执行manager而直接执行dao层代码
     *
     * @return
     */
    protected boolean isOpenManager() {
        return true;
    }

    @Autowired(required = false)
    private BaseManager<P, I, Q> baseManager;

    @Autowired(required = false)
    private BaseDao<P, I, Q> baseDao;
}
