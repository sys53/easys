package com.easys.platform.plugin.setting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static com.easys.platform.EasysConst.ENTITY_TABLE_PREV;
/**
 * 系统参数po
 * <p/>
 * User:TJM
 * Date:2016/4/515:07
 * version $id:SystemParam.java,v 0.1 Exp$
 */
@Entity(name = ENTITY_TABLE_PREV + "SETTING")
@Data
@NoArgsConstructor
public class Setting {
    @Id
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @GeneratedValue(generator = "hibernate-uuid" )
    @Column(name = "SYSTEM_PARAM_ID", nullable = false, length = 32)
    private String settingId;
    @Basic
    @Column(name = "SETTING_NAME", nullable = false, length = 32)
    private String settingName;
    @Basic
    @Column(name = "SETTING_VAL", nullable = false, length = 256)
    private String settingVal;
    @Basic
    @Column(name = "SETTING_DESC", nullable = false, length = 512)
    private String settingDesc;
    @Basic
    @Column(name = "ORD", precision = 10,scale = 0)
    private int ord;
    @Basic
    @Column(name = "IS_SYSTEM", precision = 1,scale = 0)
    private Boolean isSystem;
    @Basic
    @Column(name = "SETTING_TYPE", nullable = false, length = 32)
    private String settingType;
    @Basic
    @Column(name = "ORG_CODE", length = 32)
    private String orgCode;
    @Basic
    @Column(name = "SETTING_LEVEL", nullable = false, length = 32)
    private String settingLevel;
    @Basic
    @Column(name = "USER_CODE", length = 32)
    private String userCode;

}
