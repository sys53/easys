/*
 * Zjrcu.com Inc.
 * Copyright (c) 2016-2016 All Rights Reserved.
 */

package com.easys.platform.engine.threadlocal;

/**
 * 客户端信息线性安全存储
 * <p/>
 * User: sys53
 * Date: 2016/6/3 14:00
 * version $Id: ThreadLocalClientStorage.java, v 0.1  14:00 Exp $
 */
public class ThreadLocalClientStorage {
	/** 客户端信息 */
	private static ThreadLocal<ClientInfo > clientInfoThreadLocal = new ThreadLocal< ClientInfo > ();
	public static ClientInfo getClientInfo () {
		return clientInfoThreadLocal.get ();
	}
	public static void setClientInfo (ClientInfo clientInfo) {
		clientInfoThreadLocal.set ( clientInfo );
	}
	public static void removeClientInfo () {
		clientInfoThreadLocal.remove ();
	}
}
