package com.unshell.easyshiro.common.authentication;

import com.unshell.easyshiro.common.utils.Md5Util;
import com.unshell.easyshiro.system.entity.Manager;
import com.unshell.easyshiro.system.entity.Role;
import com.unshell.easyshiro.system.entity.enums.ManagerStatus;
import com.unshell.easyshiro.system.service.IManagerService;
import com.unshell.easyshiro.system.service.IMenuService;
import com.unshell.easyshiro.system.service.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义实现 ShiroRealm，包含认证和授权两大模块
 */
@Component
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private IManagerService managerService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRoleService roleService;

    /**
     * 授权模块，获取管理员角色和权限
     *
     * @param principal
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        Manager manager = (Manager) SecurityUtils.getSubject().getPrincipal();
        String loginName = manager.getLoginName();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 设置管理员权限集合
        List<String> permissions = menuService.queryManagerPermissions(loginName);
        simpleAuthorizationInfo.addStringPermissions(permissions);

        // 设置管理员角色代码
        Role role = roleService.getById(manager.getRoleId());
        simpleAuthorizationInfo.addRole(role.getRoleCode());
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String loginName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        Manager manager = managerService.queryManagerByLoginName(loginName);
        if (manager == null || !StringUtils.equals(Md5Util.encrypt(manager.getSignature(), password), manager.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码错误");
        }
        if (manager.getStatus().equals(ManagerStatus.DISABLE.ordinal())) {
            throw new LockedAccountException("账号已被锁定，请联系管理员");
        }
        return new SimpleAuthenticationInfo(manager, password, getName());
    }

    /**
     * 清除当前用户权限缓存
     * 使用方法：在需要清除用户权限的地方注入 ShiroRealm,
     * 然后调用其 clearCache方法。
     */
    public void clearCache() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}