/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dao;

import com.easys.commons.page.Page;
import com.easys.commons.page.PageModel;
import com.easys.commons.page.Paging;
import com.easys.platform.dao.dialect.Dialect;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

/**
 * 通用DAO的虚类
 * <p>
 * 实现BaseDao接口的通用DAO基类，主要目的是将定义基于底层数据库访问技术，如jdbc,hibernate等技术特性的执行操作
 * 不让除DAO层之外去调整用底层技术特性的操作，从而达到业务系统中的与底层数据库访问技术解耦。
 * </p>
 * User: sys53
 * Date: 2016/3/7 9:19
 * version $Id: AbstractBaseDao.java, v 0.1  9:19 Exp $
 */
@Slf4j
public abstract class AbstractBaseDao<P, I extends Serializable, Q extends Paging> implements BaseDao<P, I, Q>, GenericDao,Clearable {

    /**
     * 数据库方言
     */
    private static Dialect dialect = Dialect.getDialect();

    /**
     * 创建分页model
     *
     * @param paging 分页请求
     * @param total  总页数
     * @return
     */
    protected PageModel createPageModel(Paging paging, int total) {
        return PageModel.builder().pageSize(paging.getPageSize()).pageIndex(paging.getPageIndex()).total(total).build();
    }

    @Override
    public Page<P> queryPage(Q qo) {
        int count = this.count(this.assemblyNameQueryCount(qo));
        PageModel pm = createPageModel(qo, count);
        if (count < qo.getPageSize() * (qo.getPageIndex() - 1)) {
            qo.setPageIndex(pm.getPageIndex());
        }
        Page<P> page = new Page<>();
        page.addAll(this.find(this.assermblyNameQuery(qo)));
        page.setPageModel(pm);
        return page;
    }


    /**
     * 根据查询语句查找对象(可以是非当前对象)集合
     *
     * @param queryString 查询语句
     * @param args        占位参数
     * @return
     */
    protected abstract List<P> findByQueryString(String queryString, Object... args);

    /**
     * 根据查询语句查找对象(单个)
     *
     * @param queryString 查询语句
     * @param args        占位参数
     * @return
     */
    protected abstract P findBySingle(String queryString, Object... args);


    /**
     * 根据查询语句查找对象集合
     *
     * @param queryString 查询语句
     * @param args        占位参数
     * @return
     */
    protected abstract List<P> find(String queryString, Object... args);

    protected abstract <X> List<X> find(String queryString, Class<X> clazz, Object... args);

    /**
     * 执行更新
     *
     * @param queryString 查询语句
     * @param args        占位参数
     */
    protected abstract int executeUpdate(String queryString, Object... args);

    /**
     * 批量更新
     *
     * @param qc 查询条件
     * @return
     */
    protected abstract int bulkUpdate(QueryCondition qc);

    /**
     * 查询语句
     *
     * @param qc 查询条件
     * @return
     */
    protected abstract List find(QueryCondition qc);

    /**
     * 获取主查询语句
     *
     * @return
     */
    protected abstract String getMainQuery();

    /**
     * 获取主查询select语句
     *
     * @return
     */
    protected abstract String getSelectQuery();

    /**
     * 获取统计查询语句
     *
     * @return
     */
    protected abstract String getCountQuery();

    /**
     * 统计总数，一般用于分页的总记录数查询
     *
     * @param qc
     * @return
     */
    protected abstract int count(QueryCondition qc);

    /**
     * 统计总数的原生查询条件
     *
     * @param countQuery
     * @param args
     * @return
     */
    protected abstract int count(String countQuery, Object... args);

    /**
     * 根据qo组装查询条件(名称参数)
     */
    protected QueryCondition assermblyNameQuery(Q qo) {
        QueryCondition queryCondition = transNameQuery(qo);
        transPageQuery(qo, queryCondition);//分页处理
        return queryCondition;
    }

    /**
     * 根据qo组装统计查询条件(名称参数)
     */
    protected QueryCondition assemblyNameQueryCount(Q qo) {
        QueryCondition queryCondition = transNameQuery(qo);
        return queryCondition;
    }

    /**
     * 根据qo组装查询条件
     */
    protected QueryCondition assembly(Q qo) {
        QueryCondition condition = transQuery(qo);
        transPageQuery(qo, condition);//分页处理
        return condition;
    }

    /**
     * 根据qo组装统计查询条件
     */
    protected QueryCondition assemblyCount(Q qo) {
        QueryCondition condition = new QueryCondition(getCountQuery() + getMainQuery());
        transQuery(qo);
        return condition;
    }

    /**
     * 组装成名字参数的hql语句
     *
     * @param qo
     */
    protected QueryCondition transNameQuery(Q qo) {
        return QueryConditionTransferFactory.create(qo).transNameQuery(qo);
    }

    /**
     * 组装成?占位符的hql语句
     *
     * @param qo
     */
    protected QueryCondition transQuery(Q qo) {
        return QueryConditionTransferFactory.create(qo).transQuery(qo);
    }

    /**
     * 转化分页查询条件
     *
     * @param paging
     * @param qc
     */
    protected void transPageQuery(Paging paging, QueryCondition qc) {
        qc.setOffset(paging.getOffset());
        qc.setMaxResults(paging.getPageSize());
    }

    protected boolean checkPluginHost() {
        return false;
    }


}
