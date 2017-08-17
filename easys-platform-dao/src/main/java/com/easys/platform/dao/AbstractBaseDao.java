/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dao;

import com.easys.commons.page.PageList;
import com.easys.commons.page.PagingOrder;
import com.easys.commons.page.PagingTools;
import com.easys.platform.dao.dialect.Dialect;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

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
public abstract class AbstractBaseDao< T, ID extends Serializable, QO extends PagingOrder> implements BaseDao< T, ID, QO > {

	/**
	 * 获取领域类
	 *
	 * @return
	 */
	public abstract Class<T> getPersistentClass();

	/**
	 * 数据库方言
	 */
	private static Dialect DIALECT = Dialect.getDialect ();

	@Value( "${hoop.schema}" )
	private String schema;

	/**
	 * 创建分页工具
	 *
	 * @param pagingOrder
	 * @return
	 */
	protected PagingTools createPagingTools (PagingOrder pagingOrder, int items ) {
		PagingTools pageTools = new PagingTools ();
		pageTools.setItems ( items );
		pageTools.setItemsPerPage ( pagingOrder.getPageSize () );
		pageTools.setPage ( pagingOrder.getPageNum () );
		return pageTools;
	}

	@Override
	public PageList< T > queryPageList (QO qo ) {
		int count = this.count ( this.assemblyNameQueryCount ( qo ) );
		PagingTools pageTools = createPagingTools ( qo, count );
		if ( count < qo.getPageSize () * ( qo.getPageNum () - 1 ) ) {
			qo.setPageNum ( pageTools.getCurPage () );
		}
		PageList< T > pageList  = new PageList< T > ();
		pageList.addAll ( this.find ( this.assermblyNameQuery ( qo ) ) );
		if ( pageList.size () < pageTools.getItemsPerPage () ) {
			pageTools.setItems ( ( pageTools.getCurPage () - 1 ) * pageTools.getItemsPerPage () + pageList.size () );
		}
		pageList.setPageTools ( pageTools );
		return pageList;
	}


	/**
	 * 根据查询语句查找对象(可以是非当前对象)集合
	 *
	 * @param queryString 查询语句
	 * @param args 占位参数
	 * @return
	 */
	protected abstract List< T > findByQueryString ( String queryString, Object... args );

	/**
	 * 根据查询语句查找对象(单个)
	 *
	 * @param queryString 查询语句
	 * @param args 占位参数
	 * @return
	 */
	protected abstract T findBySingle ( String queryString, Object... args );


	/**
	 * 根据查询语句查找对象集合
	 *
	 * @param queryString 查询语句
	 * @param args 占位参数
	 * @return
	 */
	protected abstract List< T > find ( String queryString, Object... args );

	protected abstract <X> List<X> find ( String queryString, Class<X> clazz, Object... args );

	/**
	 * 执行更新
	 *
	 * @param queryString 查询语句
	 * @param args 占位参数
	 */
	protected abstract int executeUpdate ( String queryString, Object... args );

	/**
	 * 批量更新
	 *
	 * @param qc 查询条件
	 * @return
	 */
	protected abstract int bulkUpdate ( QueryCondition qc );

	/**
	 * 查询语句
	 *
	 * @param qc 查询条件
	 * @return
	 */
	protected abstract List find ( QueryCondition qc );

	/**
	 * 获取主查询语句
	 *
	 * @return
	 */
	protected abstract String getMainQuery ();

	/**
	 * 获取主查询select语句
	 *
	 * @return
	 */
	protected abstract String getSelectQuery ();

	/**
	 * 获取统计查询语句
	 *
	 * @return
	 */
	protected abstract String getCountQuery ();

	/**
	 * 统计总数，一般用于分页的总记录数查询
	 *
	 * @param qc
	 * @return
	 */
	protected abstract int count ( QueryCondition qc );

	/**
	 * 统计总数的原生查询条件
	 *
	 * @param countQuery
	 * @param args
	 * @return
	 */
	protected abstract int count ( String countQuery, Object... args );

	/**
	 * 根据qo组装查询条件(名称参数)
	 */
	protected QueryCondition assermblyNameQuery ( QO qo ) {
		QueryCondition condition = new QueryCondition ( getSelectQuery () + getMainQuery () );
		condition.setPersistentClass ( getPersistentClass () );
		transNameQuery ( qo, condition );
		transPageQuery ( qo, condition );//分页处理
		return condition;
	}

	/**
	 * 根据qo组装统计查询条件(名称参数)
	 */
	protected QueryCondition assemblyNameQueryCount ( QO qo ) {
		QueryCondition condition = new QueryCondition ( getCountQuery () + getMainQuery () );
		condition.setPersistentClass ( getPersistentClass () );
		transNameQuery ( qo, condition );
		return condition;
	}

	/**
	 * 根据qo组装查询条件
	 */
	protected QueryCondition assembly ( QO qo ) {
		QueryCondition condition = new QueryCondition ( getSelectQuery () + getMainQuery () );
		condition.setPersistentClass ( getPersistentClass () );
		transQuery ( qo, condition );
		transPageQuery ( qo, condition );//分页处理
		return condition;
	}

	/**
	 * 根据qo组装统计查询条件
	 */
	protected QueryCondition assemblyCount ( QO qo ) {
		QueryCondition condition = new QueryCondition ( getCountQuery () + getMainQuery () );
		condition.setPersistentClass ( getPersistentClass () );
		transQuery ( qo, condition );
		return condition;
	}

	/**
	 * 组装成名字参数的hql语句
	 *
	 * @param qo
	 * @param condition
	 */
	protected void transNameQuery ( QO qo, QueryCondition condition ) {
		QueryConditionTransferFactory.create ( qo ).transNameQuery ( qo, condition );
	}

	/**
	 * 组装成?占位符的hql语句
	 *
	 * @param qo
	 * @param condition
	 */
	protected void transQuery ( QO qo, QueryCondition condition ) {
		QueryConditionTransferFactory.create ( qo ).transQuery ( qo, condition );
	}

	/**
	 * 转化分页查询条件
	 *
	 * @param pagingOrder
	 * @param qc
	 */
	protected void transPageQuery ( PagingOrder pagingOrder, QueryCondition qc ) {
		qc.setOffset ( pagingOrder.getOffset () );
		qc.setMaxResults ( pagingOrder.getPageSize () );
	}

	protected boolean checkPluginHost () {
		return false;
	}

	/**
	 * 执行sql语句，本方法只能在插件功能中使用，其它地方一律不允许使用
	 *
	 * @param sqls
	 */
	protected void executeSql ( String[] sqls ) {
		if ( ArrayUtils.isEmpty ( sqls ) ) {
			log.debug ( "待执行的sqls为空" );
			return;
		}

		for ( String sql : sqls ) {
			if ( StringUtils.isNotBlank ( sql ) ) {
				log.info ( "待执行的sql:{}",sql );
				executeNativeSql ( sql );
			}
		}
	}

	/**
	 * 执行原生sql
	 *
	 * @param sql
	 */
	protected abstract void executeNativeSql ( String sql );

	/**
	 * 执行原生sql的count语句
	 *
	 * @param sql
	 * @param args
	 * @return
	 */
	protected abstract int countNativeSql ( String sql, Object... args );

	/**
	 * 执行多行的sql语句，要求sql语句必须是;隔开
	 *
	 * @param sql 多行sql用;分隔
	 */
	protected void executeMutiSql ( String sql ) {
		if ( StringUtils.isNotBlank ( sql ) ) {
			String[] sqls = sql.split ( ";" );
			executeSql ( sqls );
		}
	}

	protected boolean existsTable ( String tableName ) {
		log.info ( "检查表,schemaName:{},tableName:{}", schema, tableName );
		int count = countNativeSql ( DIALECT.getCheckTableExistsSql (), tableName, schema );
		return count > 0;
	}
}
