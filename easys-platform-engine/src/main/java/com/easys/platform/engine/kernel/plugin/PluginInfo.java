package com.easys.platform.engine.kernel.plugin;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.Map;

/**
 * Created by liyd on 16/4/25.
 */

@Data
@NoArgsConstructor
public class PluginInfo {

    private String name;

    private String version;

    private String vendor;

    private Boolean isInstalled;

    private Boolean isEnable;

    private String platformMinVersion;

    private String platformMaxVersion;

    private String description;

    private String pluginLocationFile;

    private Boolean isTableExist;

    private Map<String, Object> configMap;

    private Map<String, Object> loadFiles;
    private URL xmlPluginPath;
    /** 插件注册的短url地址 */
    private Map<String, String> shortUrlMap;

    /**
     * 判断url与对应的controller class是否在插件中已注册
     *
     * @param url
     * @param clz
     *
     * @return
     */
    public boolean isRegistedUrl(String url, String clz) {
        if (MapUtils.isEmpty(this.shortUrlMap)) {
            return false;
        } else {
            if (StringUtils.isBlank(url)) {
                return false;
            }
            return StringUtils.equals(this.shortUrlMap.get(url), clz);
        }
    }
}
