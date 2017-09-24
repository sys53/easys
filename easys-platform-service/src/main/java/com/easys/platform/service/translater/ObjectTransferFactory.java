/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.service.translater;

/**
 * @author sys53
 * @date 2017/9/11
 */
public interface ObjectTransferFactory {
    <S, T> ObjectTranslater<S, T> createTransfer();
}
