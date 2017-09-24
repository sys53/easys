/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.service.translater;

/**
 * @author sys53
 * @date 2017/9/11
 */
public class DefaultObjectTranslaterFactory implements ObjectTransferFactory {
    @Override
    public <S, T> ObjectTranslater<S, T> createTransfer() {
        return new GenericObjectTranslater<S, T>();
    }
}
