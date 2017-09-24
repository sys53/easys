package com.easys.platform.engine.threadlocal;

/**
 * 业务日志信息存储类
 * <p/>
 * User: liulu
 * Date: 2016/4/25 10:07
 * version $Id: ThreadLocalLogStorage.java, v 0.1  10:07 Exp $
 */
public class ThreadLocalStorage {

	public static ClientInfo getClientInfo(){
		return ThreadLocalClientStorage.getClientInfo ();
	}

}
