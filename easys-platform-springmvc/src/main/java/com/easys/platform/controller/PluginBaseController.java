package com.easys.platform.controller;

import com.easys.platform.engine.kernel.plugin.PluginInfo;
import com.easys.platform.engine.kernel.plugin.PluginUtils;
import com.easys.platform.engine.kernel.plugin.PluginsContext;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.net.URL;

/**
 * 插件基础Controller，所有的插件controller必须继承此类
 * <p>
 * Created by sys53 on 2017/9/24.
 */
public class PluginBaseController extends AbstractBaseController {

    @ModelAttribute
    protected void initPluginVariable(ModelMap modelMap) {
        PluginInfo pluginInfo = getPluginInfo();
        if (pluginInfo == null) {
            return;
        }
        modelMap.put("pluginName", pluginInfo.getName());
        modelMap.put("pluginVersion", pluginInfo.getVersion());
        modelMap.put("pluginVendor", pluginInfo.getVendor());
    }


    protected String getPluginUrl (String url) {
        PluginInfo pluginInfo = getPluginInfo();
        if (pluginInfo == null) {
            return url;
        }
        return "/plugin/" + pluginInfo.getName() + "/" + pluginInfo.getVersion() + url;
    }

    private PluginInfo getPluginInfo() {
        URL location = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        return PluginsContext.getPluginInfo(PluginUtils.unifyLocationFile(location.getFile()));
    }

}
