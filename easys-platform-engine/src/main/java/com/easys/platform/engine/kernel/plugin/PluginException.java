package com.easys.platform.engine.kernel.plugin;

import com.easys.commons.enums.IEnum;
import com.easys.platform.EasysException;

/**
 * @author sys53
 * @date 2017/9/24
 */
public class PluginException extends EasysException {

    public PluginException(IEnum iEnum, Throwable e) {
        super(iEnum, e);
    }

    public PluginException(IEnum iEnum) {
        super(iEnum);
    }

    public PluginException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public PluginException(String errorCode, String errorMsg, Throwable e) {
        super(errorCode, errorMsg, e);
    }
}
