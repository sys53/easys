/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dao;


import com.easys.commons.page.Paging;
import com.easys.commons.util.BeanUtils;
import com.easys.platform.annotation.Field;
import com.easys.platform.annotation.Ignore;
import com.easys.platform.annotation.Table;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 查询条件QueryCondition换转者
 * <p/>
 * User: sys53
 * Date: 18/08/22 下午2:33
 * version $Id: QueryConditionTransfer.java, v 0.1  下午2:33 Exp $
 */
public class QueryConditionTransfer<Q extends Paging> {

    public QueryCondition transNameQuery(Q qo) {
        return createQueryCondition(qo);
    }

    public QueryCondition transQuery(Q qo) {
        //默认空实现
        return null;
    }

    private QueryCondition createQueryCondition(Q qo) {
        QueryCondition queryCondition = createSelect(qo);
        buildWhere(queryCondition, qo);
        return queryCondition;
    }

    private void buildWhere(QueryCondition queryCondition, Q qo) {
        Map<String, Object> propMap = BeanUtils.transferPropMap(qo, Ignore.class);
        for (Map.Entry<String, Object> entry : propMap.entrySet()) {
            Field annotation = BeanUtils.getField(qo.getClass(),entry.getKey()).getAnnotation(Field.class);
            if (annotation != null && entry.getValue() != null) {
                queryCondition.append("And obj.").append(annotation.name()).append(annotation.operator()).add(":"+entry.getKey(),entry.getKey(), entry.getValue());
            }
        }
    }


    private QueryCondition createSelect(Q qo) {
        assert qo != null;

        Table table = getQueryTarget(qo);
        if (table == null) {
            throw new DaoException("err-001", "Execute default qct,but the Qo not assign Table annotation.");
        }
        if ("hibernate".equals(table.type())) {
            QueryCondition queryCondition = new QueryCondition("Select obj From ");
            queryCondition.appendQueryString(table.value());
            queryCondition.appendQueryString(" As obj");
            return queryCondition;
        } else {
            QueryCondition queryCondition = new QueryCondition("Select * From ");
            queryCondition.appendQueryString(table.value());
            queryCondition.appendQueryString(" As obj");
            return queryCondition;
        }

    }

    private Table getQueryTarget(Q qo) {
        Annotation[] annotations = qo.getClass().getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Table) {
                return (Table) annotation;
            }
        }
        return null;
    }
}
