/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dao.enums;

/**
 * Write class comments here
 * <p/>
 * User: sys53
 * Date: 15/12/16 下午4:12
 * version $Id: LockModelType.java, v 0.1  下午4:12 Exp $
 */
public enum LockModeType {

	READ,
	WRITE,
	OPTIMISTIC,
	OPTIMISTIC_FORCE_INCREMENT,
	PESSIMISTIC_READ,
	PESSIMISTIC_WRITE,
	PESSIMISTIC_FORCE_INCREMENT,
	NONE
}
