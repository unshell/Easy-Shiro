package com.unshell.easyshiro.common.controller;

import com.unshell.easyshiro.system.entity.Manager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;

public class BaseController {
    private Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    protected Manager getCurrentManager() {
        return (Manager) getSubject().getPrincipal();
    }

    protected void login(AuthenticationToken token) {
        getSubject().login(token);
    }

    protected void logout() {
        getSubject().logout();
    }
}