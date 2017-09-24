package com.easys.platform.engine.util;

import com.easys.commons.enums.IEnum;
import com.easys.commons.util.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 错误编号转换器
 * <p/>
 * User: qianbobo
 * Date: 2016/3/7 9:39
 * version $Id: ErrorCodeTransfer.java, v 0.1  9:39 Exp $
 */
public class ErrorCodeTransfer {

    private static final String DEFAULT_ERROR_MESSAGE_FILE = "error-message.properties";

    /**
     * 获取自定义错误信息
     *
     * @param errorCode 错误代码
     * @param defaultMsg 默认提示信息
     *
     * @return
     */
    public static String getCustomMessage(String errorCode, String defaultMsg) {
        String errorMsg = PropertiesUtils.getValue(DEFAULT_ERROR_MESSAGE_FILE, errorCode);
        return StringUtils.isBlank(errorMsg) ? defaultMsg : errorMsg;
    }

    /**
     * 获取自定义错误信息
     *
     * @param errorCode 错误代码
     * @param defaultMsg 默认提示信息
     *
     * @return
     */
    public static String getCustomMessage(String errorCode, String defaultMsg, String... args) {
        String errorMsg = PropertiesUtils.getValue(DEFAULT_ERROR_MESSAGE_FILE, errorCode);
        if (StringUtils.isBlank(errorMsg)) {
            return defaultMsg;
        }
        return String.format(errorMsg, args);
    }

    /**
     * 获取自定义错误信息
     *
     * @param iEnum 错误代码枚举
     *
     * @return
     */
    public static String getCustomMessage(IEnum iEnum, String... args) {
        String errorMsg = PropertiesUtils.getValue(DEFAULT_ERROR_MESSAGE_FILE, iEnum.getCode());
        if (StringUtils.isBlank(errorMsg)) {
            return iEnum.getName();
        }
        return String.format(errorMsg, args);
    }

}
