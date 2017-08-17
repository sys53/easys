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
public class Db2Dialect extends Dialect {
	@Override
	public String getCheckTableExistsSql () {
		return "select count(*) from SYSCAT.TABLES where TABNAME=? AND TYPE='T' AND TABSCHEMA=?";
	}
}
