package com.easys.platform.plugin.setting.controller;

import com.easys.commons.page.Page;
import com.easys.platform.controller.ModelUtils;
import com.easys.platform.controller.PluginBaseController;
import com.easys.platform.plugin.setting.bo.SettingBo;
import com.easys.platform.plugin.setting.dao.SettingQo;
import com.easys.platform.plugin.setting.entity.Setting;
import com.easys.platform.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 配置web控制层类
 *
 * @author sys53
 * @date 2017/9/24
 */
@Slf4j
@Controller
@RequestMapping("/setting")
public class SettingController extends PluginBaseController {

    @Autowired
    private BaseService<SettingBo, Setting, String, SettingQo> settingService;


    /**
     * 配置列表
     * @param settingQo 配置查询对象
     * @return
     */
    @RequestMapping({"","/manager"})
    public String  list(SettingQo settingQo,Model model){
        log.debug("加载配置列表");
        Page<SettingBo> settingBoPage = settingService.queryPage(settingQo);
        ModelUtils.processModel(model,"成功获取配置列表",settingBoPage);
        return "setting/manager";
    }

    @RequestMapping("/save")
    public String save(SettingBo settingBo, Model model) {
        log.debug("保存配置");
        settingService.save(settingBo);
        ModelUtils.processModel(model,"保存配置成功");
        return "redirect:" + getPluginUrl("/params/list");

    }

    @RequestMapping("/remove")
    public String add(String settingId, Model model) {
        log.debug("删除配置,id={}",settingId);
        settingService.removeById(settingId);
        ModelUtils.processModel(model,"删除配置成功");
        return "redirect:" + getPluginUrl("/params/list");

    }
    @RequestMapping("/get")
    public String get(String settingId, Model model) {
        log.debug("获取配置,id={}",settingId);
        SettingBo settingBo = settingService.getById(settingId);
        ModelUtils.processModel(model,"成功获取配置信息",settingBo);
        return "redirect:" + getPluginUrl("/params/list");
    }


}
