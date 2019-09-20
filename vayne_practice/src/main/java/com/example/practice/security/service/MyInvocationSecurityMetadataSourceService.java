package com.example.practice.security.service;

import com.example.practice.security.dao.SysPermissionMapper;
import com.example.practice.security.model.SysPermission;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @author : Yang Jian
 * @date : 2019/9/11 18:52
 */
@Component
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    private HashMap<String, Collection<ConfigAttribute>> map = null;

    public void loadResourceDefine() {
        map = new HashMap<>();
        Collection<ConfigAttribute> array;
        List<SysPermission> permissions = sysPermissionMapper.findAll();
        for (SysPermission permission : permissions) {
            array = new ArrayList<>();
            ConfigAttribute configAttribute = new SecurityConfig(permission.getName());
            array.add(configAttribute);
            map.put(permission.getUrl(), array);
        }
    }

    //此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if (map == null) {
            loadResourceDefine();
        }
        HttpServletRequest request = ((FilterInvocation) o).getRequest();
        for (String key : map.keySet()) {
            AntPathRequestMatcher matcher = new AntPathRequestMatcher(key);
            if (matcher.matches(request)) {
                return map.get(key);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
