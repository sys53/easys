/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dao.dialect;

/**
 * db2数据库方言
 * <p>
 * User: shenyongsheng
 * Date: 2016/10/26 18:44
 * version $Id: Db2Dialect.java, v 0.1  18:44 Exp $
 */
public class OracleDialect extends Dialect {
	@Override
	public String getCheckTableExistsSql () {
		return "select COUNT(1) from dba_tables t where t.TABLE_NAME = ? and t.OWNER = ?";
	}
}
