/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dao.dialect;

import com.easys.commons.util.PropertiesUtil;
import com.easys.commons.util.ReflectHelper;
import com.easys.platform.dao.DaoException;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据库方言
 * <p>
 * User: shenyongsheng
 * Date: 2016/10/26 17:49
 * version $Id: Dialect.java, v 0.1  17:49 Exp $
 */
public abstract class Dialect {

	private static Dialect dialect;

	public static Dialect getDialect ( ) {
		if ( dialect == null ) {
			String dialectName = PropertiesUtil.getValue ( "application", "esasys.dialect" );
			dialect = instanceDialect ( dialectName );
		}
		return dialect;
	}

	private static Dialect instanceDialect ( String dialectName ) {
		if ( StringUtils.isBlank ( dialectName ) ) {
			throw new DaoException( "ERR-54", "未配置hoop平台的数据库方言" );
		}
		try {
			return ( Dialect ) ReflectHelper.classForName ( dialectName ).newInstance ();
		} catch ( ClassNotFoundException e ) {
			throw new DaoException ( "ERR-54", "配置hoop平台的数据库方言类未找到", e );
		} catch ( InstantiationException e ) {
			throw new DaoException ( "ERR-54", "不能实例化配置hoop平台的数据库方言类", e );
		} catch ( IllegalAccessException e ) {
			throw new DaoException ( "ERR-54", "无法访问实例化配置hoop平台的数据库方言类", e );
		}
	}


	public String getCheckTableExistsSql () {
		throw new DaoException ( "ERR-53", "该数据库不支持表是否存在的检查" );
	}


}
