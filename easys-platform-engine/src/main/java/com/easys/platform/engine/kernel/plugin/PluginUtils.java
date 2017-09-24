package com.easys.platform.engine.kernel.plugin;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class PluginUtils {

    /**
     * 获取插件key
     *
     * @param name
     * @param version
     * @return
     */
    public static String getPluginKey(String name, String version) {

        return String.format("%s_%s", name, version);
    }

    /**
     * 统一locationFile名称，主要处理斜杠
     *
     * @param locationFile
     * @return
     */
    public static String unifyLocationFile(String locationFile) {

        if (StringUtils.isBlank(locationFile)) {
            return "";
        }
        locationFile = StringUtils.replace(locationFile, "\\", "/");
        while (!StringUtils.startsWith(locationFile, "/")) {
            locationFile = "/" + locationFile;
        }
        return locationFile;
    }

    /**
     * 是否加载匹配
     *
     * @param fileName
     * @param loadConfigs
     * @return
     */
    public static boolean isLoadMatch(String fileName, List<String> loadConfigs) {

        for (String conf : loadConfigs) {

            conf = StringUtils.replace(conf, ".", "\\.");
            conf = StringUtils.replace(conf, "*", ".*");

            if (fileName.matches(conf)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 读取xml流为doc
     *
     * @param in
     * @return
     */
    public static Document readXml(InputStream in) {
        try {
            SAXReader saxReader = new SAXReader();
            return saxReader.read(in);
        } catch (DocumentException e) {
            throw new PluginException("","读取xml文件失败", e);
        }
    }
}
