/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dao;

import com.easys.commons.page.Paging;
import com.easys.commons.util.ReflectHelper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 条件查询罗辑转换器工厂类
 * <p/>
 * User: sys53
 * Date: 15/12/22 下午3:16
 * version $Id: QueryConditionTransferFactory.java, v 0.1  下午3:16 Exp $
 */
@Slf4j
public class QueryConditionTransferFactory {
    private QueryConditionTransferFactory() {
    }

    /**
     * 默认空转换器
     */
    private final static QueryConditionTransfer blank = new QueryConditionTransfer();

    /**
     * 配置文件定义
     */
    private static final String CONFIG_FILE = "qctransfer";

    /**
     * 配置项后缀
     */
    private static final String CONFIG_KEY_FIX = ".transfer";

    private static Properties properties;

    /**
     * 全局单例的qct实例
     */
    private static Map<Class<?>, QueryConditionTransfer> qcts =
            new ConcurrentHashMap<>();

    /**
     * 加载配置文件
     */
    static {
        try {
            properties = new Properties();
            Enumeration<URL> qctResources = Thread.currentThread().getContextClassLoader()
                    .getResources(CONFIG_FILE + ".properties");
            while (qctResources.hasMoreElements()) {
                URL url = qctResources.nextElement();
                properties.load(url.openStream());
            }
        } catch (IOException e) {
            throw new DaoException("err-100", String.format("读取资源文件%s.properties", CONFIG_FILE), e);
        }
    }


    public static <Q extends Paging> QueryConditionTransfer<Q> create(Q qo) {
        String factoryClassName = null;
        try {
            if (qo != null) {
                factoryClassName = properties.getProperty(qo.getClass().getName() + CONFIG_KEY_FIX);
                if (factoryClassName == null) {
                    return blank;
                }
                Class<QueryConditionTransfer<Q>> clazz = ReflectHelper.classForName(factoryClassName);
                QueryConditionTransfer<Q> qct = qcts.get(clazz);
                if (qct == null) {
                    qct = clazz.newInstance();
                    qcts.put(clazz, qct);
                }
                return qct;
            }
        } catch (ClassNotFoundException e) {
            log.warn("找不到类", e);
        } catch (Exception e) {
            log.warn("读取配置异常", e);
        }
        return blank;
    }
}
