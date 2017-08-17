/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.login;

import com.easys.platform.authority.AuthCode;
import com.easys.platform.authority.AuthScope;
import com.easys.platform.authority.Role;
import com.easys.platform.user.Organization;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.SetUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * 当前登录的用户所有信息
 * <p/>
 * User:TJM
 * Date:2016/4/269:35
 * version $id:LoginUser.java,v 0.1 Exp$
 */
public class LoginUser implements Serializable {

    private static final long serialVersionUID = -8709511727288090870L;
    private String userId;

    /**
     * 编号
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 手机
     */
    private String mobile;

    /**
     * email
     */
    private String email;

    /**
     * 是否第一次登录
     */
    private Boolean isFirstLogin;

    /**
     * sex
     */
    private Boolean sex;

    private Organization organization;

    /**
     * 受托人, 不为空 表示 受托人 免密登录
     */
    private String consignee;

    /**
     * 用户角色
     */
    private List<Role> roleList;

    private List<AuthCode> authCodeInfoList;

    private List<AuthScope> authScopeList;

    /**
     * 登录用户的权限及范围域
     */
    private Map<String, Map<String, Set<String>>> authCodeMap;

    public Map<String, String> getAuthCodeMask() {
        return authCodeMask;
    }

    public void setAuthCodeMask(Map<String, String> authCodeMask) {
        this.authCodeMask = authCodeMask;
    }

    /**
     * 权限码掩码
     */
    private Map<String, String> authCodeMask = new HashMap<>();

    public Map<String, Map<String, Set<String>>> getAuthCodeMap() {
        return authCodeMap;
    }

    public void setAuthCodeMap(
            Map<String, Map<String, Set<String>>> authCodeMap) {
        this.authCodeMap = authCodeMap;
    }

    /**
     * 检查授权码，通过为true,否则false
     *
     * @param checkedAuthCode
     * @return
     */
    public boolean checkAuth(String checkedAuthCode) {
        if (StringUtils.isEmpty(checkedAuthCode)) {
            return true;
        }
        if (MapUtils.isEmpty(this.authCodeMap)) {
            return false;
        } else {
            String[] authCodes = checkedAuthCode.split(",");
            for (String authCode : authCodes) {
                if (StringUtils.isNotBlank(authCode)) {
                    String[] arr = authCode.split(":");
                    if (arr.length == 2) {
                        if (this.authCodeMap.containsKey(arr[0]) && this.checkAuthMask(arr[0], arr[1])) {
                            return true;
                        }
                    } else {
                        if (this.authCodeMap.containsKey(authCode)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 验证权限掩码是否匹配上，验证的掩码上只有有一位不等于'-'的字符串与该权限对应的掩码匹配上就通过
     *
     * @param authCode
     * @param maskCode
     * @return
     */
    public boolean checkAuthMask(String authCode, String maskCode) {
        if (StringUtils.isBlank(maskCode)) {
            return false;
        }
        String bindMaskCode = authCodeMask.get(authCode);
        if (StringUtils.isNotBlank(bindMaskCode)) {
            if (StringUtils.equals(bindMaskCode, maskCode)) {
                return true;
            }
            int len = maskCode.length();
            int bLen = bindMaskCode.length();
            if (len == 4 && bLen == 4) {
                for (int i = 0; i < maskCode.length(); i++) {
                    char c = maskCode.charAt(i);
                    if (c != '-' && c == bindMaskCode.charAt(i)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 是否拥有某个角色
     *
     * @param roleCode
     * @return
     */
    public boolean hasRole(String roleCode) {
        if (CollectionUtils.isEmpty(this.roleList)) {
            return false;
        }
        for (Role role : this.roleList) {
            if (StringUtils.equals(role.getRoleCode(), roleCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取某个角色
     *
     * @param roleCode
     * @return
     */
    public Role getRole(String roleCode) {
        if (CollectionUtils.isEmpty(this.roleList)) {
            return null;
        }
        for (Role role : this.roleList) {
            if (StringUtils.equals(role.getRoleCode(), roleCode)) {
                return role;
            }
        }
        return null;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public List<AuthCode> getAuthCodeInfoList() {
        return authCodeInfoList;
    }

    public void setAuthCodeInfoList(List<AuthCode> authCodeInfoList) {
        this.authCodeInfoList = authCodeInfoList;
        if (CollectionUtils.isNotEmpty(authCodeInfoList)) {
            if (authCodeMap == null) {
                authCodeMap = new HashMap<String, Map<String, Set<String>>>();
            }
            for (AuthCode authCode : authCodeInfoList) {
                processAuthCode(authCode);
            }
        }
    }

    /**
     * 处理权限码
     *
     * @param authCode
     */
    private void processAuthCode(AuthCode authCode) {
        if (StringUtils.isEmpty(authCode.getCode())) {
            return;
        }

        Map<String, Set<String>> authScopeMap = authCodeMap.get(authCode.getCode());
        if (authScopeMap == null) {
            authScopeMap = new HashMap<>();
            authCodeMap.put(authCode.getCode(), authScopeMap);
        }
        //直接使权限码的掩码，因为后台取用户权限码时已将权限
        if (BooleanUtils.isTrue(authCode.getIsGeneral()) && StringUtils.isNotBlank(authCode.getMaskCode())) {
            String maskCode = authCodeMask.get(authCode.getCode());
            if (StringUtils.isBlank(maskCode)) {
                authCodeMask.put(authCode.getCode(), authCode.getMaskCode());
            } else {
                authCodeMask.put(authCode.getCode(), merge(maskCode, authCode.getMaskCode()));
            }
        }
        if (CollectionUtils.isNotEmpty(authCode.getAuthScopes())) {
            for (AuthScope as : authCode.getAuthScopes()) {
                processAuthScope(as, authScopeMap);
            }
        }
    }

    /**
     * 合并掩码 如 “-rw-”,"-r-a" 将返回"-rwa"
     *
     * @param firstMaskCode
     * @param secondMaskCode
     * @return
     */
    private String merge(String firstMaskCode, String secondMaskCode) {
        char[] firstMaskCodes = firstMaskCode.toCharArray();
        char[] secondMaskCodes = secondMaskCode.toCharArray();
        StringBuilder sb = new StringBuilder();
        if (firstMaskCodes.length == 4 && secondMaskCodes.length == 4) {
            for (int i = 0; i < 4; i++) {
                char c = firstMaskCodes[i] == '-' ? '-' : firstMaskCodes[i];
                sb.append(c == '-' ? secondMaskCodes[i] : c);
            }
        }
        return sb.toString();
    }

    /**
     * 获得权限获范域
     *
     * @param authCode  权限码
     * @param scopeType 范围域类型
     * @return 目标ID或者表达式
     */
    public Set<String> getScopeTargartIds(String authCode, String scopeType) {
        Map<String, Set<String>> authScopeMap = authCodeMap.get(authCode);
        if (authScopeMap != null) {
            return authScopeMap.get(scopeType) == null ? SetUtils.EMPTY_SET : authScopeMap.get(scopeType);
        }
        return SetUtils.EMPTY_SET;
    }

    private void processAuthScope(AuthScope as, Map<String, Set<String>> authScopeMap) {

        Set<String> scopes = authScopeMap.get(as.getTargetScopeType());
        if (scopes == null) {
            scopes = new HashSet<>();
            authScopeMap.put(as.getTargetScopeType(), scopes);
        }
        if (StringUtils.isNotBlank(as.getTargetScopeId())) {
            scopes.add(as.getTargetScopeId());
        }
        if (StringUtils.isNotBlank(as.getTargetScopeExp())) {
            scopes.add(as.getTargetScopeExp());
        }

    }

    public List<AuthScope> getAuthScopeByAuthCode(String authCode) {
        if (authCode == null || "".equals(authCode)) {
            return null;
        }
        if (CollectionUtils.isNotEmpty(authCodeInfoList)) {
            for (AuthCode ac : authCodeInfoList) {
                if (ac != null && authCode.equals(ac.getCode())) {
                    return ac.getAuthScopes();
                }
            }
        }
        return null;
    }

    public List<AuthScope> getAuthScopeList() {
        return authScopeList;
    }

    public void setAuthScopeList(List<AuthScope> authScopeList) {
        this.authScopeList = authScopeList;
    }

    public Boolean getFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        isFirstLogin = firstLogin;
    }


    /**
     * 获取某种范围域类型的 范围域
     *
     * @param targetScopeType
     * @return
     */
    public List<AuthScope> getAuthScopeByTargetScopeType(String targetScopeType) {

        List<AuthScope> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(authScopeList)) {
            for (AuthScope as : authScopeList) {
                if (as != null && targetScopeType.equals(as.getTargetScopeType())) {
                    list.add(as);
                }
            }
        }
        return list;
    }

}
