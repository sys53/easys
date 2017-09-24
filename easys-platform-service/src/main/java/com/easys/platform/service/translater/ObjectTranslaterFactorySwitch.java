/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.service.translater;

import com.easys.commons.util.ReflectHelper;
import com.easys.platform.service.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sys53
 * @date 2017/9/11
 */
@Slf4j
public class ObjectTranslaterFactorySwitch {

    private final static ObjectTransferFactory DEFAULT_FACTORY = new DefaultObjectTranslaterFactory();
    /**
     * 转换器后缀常量
     */
    private static final String FIXED_TRANSLATOR = ".transferfactory";

    /**
     * 配置文件定义
     */
    private static final String CONFIG_FILE = "transferfactory";

    private static final String JOIN = ".to.";

    private static Properties properties;

    /**
     * 全局单例的DTF实例
     */
    private static Map<Class<? extends ObjectTransferFactory>, ObjectTransferFactory> DTFS = new ConcurrentHashMap<>();

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
            throw new ServiceException("", "");
        }
    }

    public static ObjectTransferFactory create(String sourceClassName, String targetClassName) {
        String factoryClassName = null;
        Class<? extends ObjectTransferFactory> clazz;
        try {
            factoryClassName = properties.getProperty(sourceClassName + JOIN + targetClassName + FIXED_TRANSLATOR);
            if (factoryClassName == null) {
                return DEFAULT_FACTORY;
            }
            clazz = ReflectHelper.classForName(factoryClassName);
            ObjectTransferFactory dtf = DTFS.get(clazz);
            if (dtf == null) {
                dtf = clazz.newInstance();
                DTFS.put(clazz, dtf);
            }
            return dtf;
        } catch (ClassNotFoundException e) {
            log.warn("不存在类'{}'", e, factoryClassName);
            throw new ServiceException("", "");
        } catch (Exception e) {
            log.warn("不能增加对象转换器", e);
            throw new ServiceException("", "");
        }
    }
}
