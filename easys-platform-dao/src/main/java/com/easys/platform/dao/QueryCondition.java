/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dao;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;


/**
 * 泛化查询条件辅助类
 *
 * @author sys53
 */
@Slf4j
public class QueryCondition implements Serializable {

    /**
     * 默认构造函数
     */
    public QueryCondition() {
        queryString = new StringBuilder();
        whereString = new StringBuilder();
        setString = new StringBuilder();
        havingString = new StringBuilder();
        groupBy = new StringBuilder();
    }

    /**
     * 带有from子句的构造函数
     *
     * @param fromStr
     */
    public QueryCondition(final String fromStr) {
        this();
        queryString.append(fromStr);
    }

    /**
     * 获取起始列
     *
     * @return
     */
    public int getOffset() {
        return offset;
    }

    /**
     * 附加查询子句，即from子句
     *
     * @param queryString
     * @return
     */
    public QueryCondition appendQueryString(final String queryString) {
        if (this.queryString == null) {
            this.queryString = new StringBuilder();
        }
        this.queryString.append(queryString);
        return this;
    }

    /**
     * 获取最大数记录数
     *
     * @return
     */
    public int getMaxResults() {
        return maxResults;
    }

    /**
     * 设置最大记录数
     *
     * @param maxResults
     * @return
     */
    public QueryCondition setMaxResults(final int maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    /**
     * 设置起始行
     *
     * @param offset
     */
    public QueryCondition setOffset(final int offset) {
        this.offset = offset;
        return this;
    }

    /**
     * 增加update的set子句
     *
     * @param setString set子句字符串占位符子句
     * @param name      参数名
     * @param value     参数值
     * @return
     */
    public QueryCondition addSetValue(final String setString, final String name, final Object value) {
        assert setString != null;
        assert setString.indexOf(':') >= 0;
        this.setString.append(setString);
        paramAndValues.put(name, value);
        log.debug("{},:name={},value={}", new Object[]{setString, name, value});
        return this;
    }

    /**
     * 增加update的set子句
     *
     * @param setStr set子句?占位符子句
     * @param value  参数值
     * @return
     */
    public QueryCondition addSetValue(final String setStr, final Object value) {
        join(setString, setStr, value);
        return this;
    }

    /**
     * 增加where条件
     *
     * @param whereStr where条件的?占位符子句
     * @param value    参数值
     * @return
     */
    public QueryCondition add(final String whereStr, final Object value) {
        join(this.whereString, whereStr, value);
        return this;
    }

    private void join(final StringBuilder sb, final String str, final Object value) {
        assert str != null;
        assert str.indexOf('?') >= 0;
        sb.append(BLANK);
        sb.append(str);
        this.values.add(value);
        log.debug("{},value={}", str, value);
    }

    /**
     * 增加where条件
     *
     * @param whereStr where条件的字符串占位符子句
     * @param name     参数名
     * @param value    参数值
     * @return
     */
    public QueryCondition add(final String whereStr, final String name, final Object value) {
        assert whereStr != null;
        assert whereStr.indexOf(':') >= 0;
        whereString.append(BLANK);
        whereString.append(whereStr);
        paramAndValues.put(name, value);
        log.debug("{},:name={},value={}", new Object[]{whereString, name, value});
        return this;
    }

    /**
     * 增加where条件
     *
     * @param whereStr
     * @return
     */
    public QueryCondition append(final String whereStr) {
        assert whereStr != null;
        this.whereString.append(BLANK);
        this.whereString.append(whereStr);
        log.debug("whereString:{}", whereString);
        return this;
    }

    /**
     * 增加having 条件
     *
     * @param havingStr
     * @return
     */
    public QueryCondition addHaving(final String havingStr) {
        assert havingStr != null;
        this.havingString.append(BLANK);
        this.havingString.append(havingStr);
        return this;
    }

    /**
     * 增加having 条件
     *
     * @param havingStr
     * @return
     */
    public QueryCondition addHaving(final String havingStr, final Object value) {
        assert havingStr != null;
        this.havingString.append(BLANK);
        this.havingString.append(havingStr);
        values.add(value);
        return this;
    }

    /**
     * 增加groupby
     *
     * @param groupByStr
     * @return
     */
    public QueryCondition groupBy(final String groupByStr) {
        assert groupByStr != null;
        this.groupBy.append(groupByStr);
        return this;
    }

    /**
     * 增加OrderBy子句 （不能有Order By关键子）
     *
     * @param orderStr OrderBy子句
     * @return
     */
    public QueryCondition appenOrderBy(final String orderStr) {
        assert orderStr != null;
        orders.add(orderStr);
        return this;
    }

    /**
     * 重置查询条件
     */
    public void clear() {
        this.offset = 0;
        this.maxResults = 0;
        this.queryString = new StringBuilder();
        this.whereString = new StringBuilder();
        this.setString = new StringBuilder();
        this.groupBy = new StringBuilder();
        this.paramAndValues.clear();
        this.values.clear();
        this.orders.clear();
    }

    /**
     * 获取查询语句
     *
     * @return
     */
    public String getQueryString() {
        final StringBuilder sb = getSelectAndWhereQueryString();

        if (this.groupBy != null && this.groupBy.length() > 0) {
            sb.append(" GROUP BY ").append(groupBy);
        }

        if (this.havingString != null && this.havingString.length() > 0) {
            sb.append(" having 1=1 ");
            sb.append(this.havingString);
        }

        if (CollectionUtils.isNotEmpty(orders)) {
            sb.append(ORDER_BY);
            for (String orderBy : this.orders) {
                sb.append(orderBy);
                sb.append(',');
            }
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 获取Count查询语句
     *
     * @return
     */
    public String getCountQueryString() {
        final StringBuilder sb = new StringBuilder(this.queryString.toString());
        if (this.whereString != null && this.whereString.length() > 0) {
            sb.append(" Where 1=1 ");
            sb.append(this.whereString);
        }
        return sb.toString();
    }

    /**
     * 获得where语句包括where之前的语句，可能如下情况：
     * select ... where ...
     * update ... set ... where...
     * delete ... where ...
     *
     * @return
     */
    private StringBuilder getSelectAndWhereQueryString() {
        final StringBuilder sb = new StringBuilder(this.queryString);
        if (this.setString != null && this.setString.length() > 0) {
            sb.append(" set ").append(this.setString);
        }
        if (this.whereString != null && this.whereString.length() > 0) {
            sb.append(" Where 1=1 ");
            sb.append(this.whereString);
        }
        return sb;
    }

    /**
     * 获取参数值
     *
     * @param param 参数名
     * @return
     */
    public Object getParamValue(final String param) {
        return paramAndValues.get(param);
    }

    /**
     * 获取参数值列表，是?占位符的参数值
     *
     * @return
     */
    public List<Object> getValues() {
        return values;
    }

    /**
     * 获取参数名集合
     *
     * @return
     */
    public Set<String> getParamNames() {
        return paramAndValues.keySet();
    }

    /**
     * 获取参数值集合,是字符串占位符的参数值
     *
     * @return
     */
    public Collection<Object> getParamValues() {
        return paramAndValues.values();
    }

    /**
     * 判断是否命名参数化（采用字符串占位）
     *
     * @return
     */
    public boolean isParamQuery() {
        return !paramAndValues.isEmpty();
    }

    /**
     * 是否是删除或更新语句
     *
     * @return
     */
    public boolean isUpdateOrDelete() {
        String queryStr = this.getQueryString().toUpperCase().trim();
        if (StringUtils.isNotBlank(queryStr)) {
            return queryStr.startsWith("UPDATE") || queryStr.startsWith("DELETE");
        } else {
            return false;
        }
    }

    private static final long serialVersionUID = -6127099696242870474L;

    /**
     * 语句间的空格
     */
    private static final String BLANK = " ";

    /**
     * Order By 关键字
     */
    private static final String ORDER_BY = " Order By ";

    /**
     * 查询语句,一般指From子句，或者select Count(*) From 子句
     */
    private transient StringBuilder queryString;

    /**
     * update的set子句
     */
    private transient StringBuilder setString;

    /**
     * 条件子句
     */
    private transient StringBuilder whereString;

    /**
     * group by 的条件语句
     */
    private transient StringBuilder havingString;

    private transient StringBuilder groupBy;

    /**
     * 参数名与参数值Map
     */
    private final transient Map<String, Object> paramAndValues = new HashMap<>();

    /**
     * 参数值列表
     */
    private final transient List<Object> values = new ArrayList<>(0);

    /**
     * order by子句集合
     */
    private final transient List<String> orders = new ArrayList<>(0);

    /**
     * 查询起始位置
     */
    private int offset;

    /**
     * 查询最大数结果数
     */
    private int maxResults;
}
