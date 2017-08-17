/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.setting;

import java.util.Collection;

/**
 * 系统参数服务接口
 * <p/>
 * User: sys53
 * Date: 2016/4/6 13:24
 * version $Id: SettingService.java, v 0.1  13:24 Exp $
 */
public interface SettingService {

    /**
     * 获取参数值
     *
     * @param paramName 参数名
     * @return 含有参数值的result-+*-*-
     */
    String get(String paramName);

    String get(String paramName, String orgCode);

    /**
     * 获取参数值
     *
     * @param paramName 参数名
     * @param readCache 是否从缓存中读取
     * @return
     */
    String get(String paramName, Boolean readCache);

    String get(String paramName, String orgCode, Boolean readCache);


    /**
     * 更新参数值，将原有的参数名为<code>paramName</code>值更新为<code>paramValue</code>
     * 一般在插件更新后，需要自动更新系统参数时才使用。
     *
     * @param paramName  参数名
     * @param paramValue 新的参数值
     * @return
     */
    void put(String paramName, String paramValue);

    void put(String paramName, String paramValue, String orgCode);

    /**
     * 注册系统参数，主要用于新组件加载时，需要将使用的系统参数注册的参数管理模块中去。
     *
     * @param setting <code>Setting</code> 待注册系统参数
     * @return
     */
    void register(Setting setting);

    /**
     * 批量注册系统参
     *
     * @param settings 参数集合
     */
    void register(Collection<Setting> settings);

    /**
     * 插件卸载 删除参数
     *
     * @param values
     */
    void deleteParams(Collection<Setting> values);
}
