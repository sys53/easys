/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dao.jpa;

import com.easys.commons.page.Paging;
import com.easys.platform.dao.AbstractBaseDao;
import com.easys.platform.dao.QueryCondition;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * 泛化的基础Dao类
 *
 * @author sys53
 * @date 2017/9/12
 */
public class JpaBaseDaoImpl<P, I extends Serializable, Q extends Paging> extends AbstractBaseDao<P, I, Q> {

    @Override
    public <E> void refresh(E obj) {
        entityManager.refresh(obj);
    }

    @Override
    public <E> void save(E obj) {
        entityManager.persist(obj);
    }

    @Override
    public <E> E merge(E obj) {
        return entityManager.merge(obj);
    }

    @Override
    public <E, I> E getById(Class<E> clazz, I id) {
        return entityManager.find(clazz,id);
    }

    @Override
    public <E, I> void removeById(Class<E> clazz, I id) {
        E obj = getById(clazz,id);
        entityManager.remove(obj);
    }

    @Override
    public <E, I> void batchRemoveByIds(Class<E> clazz, I[] ids) {
        int i=0;
        for (I id:ids){
            removeById(clazz,id);
            if(i==1000){
                clear();
                i=0;
            }
            i++;

        }
    }

    @Override
    public P getById(I id) {
        return null;
    }

    @Override
    public void removeById(I id) {

    }

    @Override
    public void batchRemoveByIds(I[] ids) {

    }

    @Override
    public List<P> findAll() {
        return null;
    }

    @Override
    protected List<P> findByQueryString(String queryString, Object... args) {
        return null;
    }

    @Override
    protected P findBySingle(String queryString, Object... args) {
        return null;
    }

    @Override
    protected List<P> find(String queryString, Object... args) {
        return null;
    }

    @Override
    protected <X> List<X> find(String queryString, Class<X> clazz, Object... args) {
        return null;
    }

    @Override
    protected int executeUpdate(String queryString, Object... args) {
        return 0;
    }

    @Override
    protected int bulkUpdate(QueryCondition qc) {
        return 0;
    }

    @Override
    protected List find(QueryCondition qc) {
        return null;
    }

    @Override
    protected String getMainQuery() {
        return null;
    }

    @Override
    protected String getSelectQuery() {
        return null;
    }

    @Override
    protected String getCountQuery() {
        return null;
    }

    @Override
    protected int count(QueryCondition qc) {
        return 0;
    }

    @Override
    protected int count(String countQuery, Object... args) {
        return 0;
    }

    @Override
    public void clear() {
        entityManager.clear();
    }

    @PersistenceContext
    private transient EntityManager entityManager;


}
