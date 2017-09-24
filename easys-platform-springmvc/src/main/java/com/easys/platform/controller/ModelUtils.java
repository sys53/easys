package com.easys.platform.controller;

import com.easys.platform.EasysConst;
import com.easys.platform.engine.runtime.RunBinder;
import org.springframework.ui.Model;

/**
 * Created by sys53 on 2017/6/27.
 */
public class ModelUtils {
    public static void processModel(Model model, String successMsg) {
        if (RunBinder.hasErrors()) {
            model.addAttribute(EasysConst.SUCCESS, false);
            model.addAttribute(EasysConst.MESSAGE, RunBinder.getStrErrors());
        } else {
            model.addAttribute(EasysConst.SUCCESS, true);
            model.addAttribute(EasysConst.MESSAGE, successMsg);
        }
    }

    public static void processModel(Model model, String successMsg,Object result) {
        if (RunBinder.hasErrors()) {
            model.addAttribute(EasysConst.SUCCESS, false);
            model.addAttribute(EasysConst.MESSAGE, RunBinder.getStrErrors());
        } else {
            model.addAttribute(EasysConst.SUCCESS, true);
            model.addAttribute(EasysConst.MESSAGE, successMsg);
            model.addAttribute(EasysConst.RESULT,result);
        }
    }

    public static void processModel(Model model, boolean success, String successMsg) {
        model.addAttribute(EasysConst.SUCCESS, success);
        model.addAttribute(EasysConst.MESSAGE, successMsg);
    }
}
