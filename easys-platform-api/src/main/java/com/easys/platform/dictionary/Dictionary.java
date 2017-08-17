/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dictionary;


import com.easys.platform.engine.kernel.FromVersion;

import java.util.List;
import java.util.Map;

/**
 * 数据字典值接接口
 * <p/>
 * User: sys53
 * Date: 2016/3/30 11:39
 * version $Id: Dictionary.java, v 0.1  11:39 Exp $
 */
public interface Dictionary extends FromVersion {
    List<Dictionary> getChildDics();

    void setChildDics(List<Dictionary> childDics);

    String getDictionaryId();

    void setDictionaryId(String dictionaryId);

    String getParentId();

    void setParentId(String parentId);

    String getDicKey();

    void setDicKey(String dicKey);

    String getDicVal();

    void setDicVal(String dicVal);

    String getMemo();

    void setMemo(String memo);

    Integer getOrd();

    void setOrd(Integer ord);

    Map<String, Dictionary> getDictionaries();

    void setDictionaries(Map<String, Dictionary> dictionaries);

    /**
     * 获取子字典值，忽略大小写
     *
     * @param dicKey 字典键
     * @return 字典值
     */
    String getChildVal(String dicKey);

    /**
     * 根据字典值匹配字典键，忽略大小写，返回第一个
     *
     * @param dicVal 字典值
     * @return 字典键
     */
    String getChildKey(String dicVal);
}
