package com.easys.platform.plugin.setting.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sys53
 * @date 2017/9/24
 */
@Data
@NoArgsConstructor
public class SettingBo {
    /** @see com.easys.platform.plugin.setting.entity.Setting#settingId */
    private String settingId;

    /** @see com.easys.platform.plugin.setting.entity.Setting#settingName */
    private String settingName;
}
