/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.engine.kernel;

/**
 * 指定从哪个版本升级来的接口
 * <p>
 * User: shenyongsheng
 * Date: 2016/10/28 17:02
 * version $Id: FromVersion.java, v 0.1  17:02 Exp $
 */
public interface FromVersion {
	String getFromVersion();
	void setFromVersion(String fromVersion);
}
