package com.example.security;

import com.example.domain.*;
import com.example.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * SPRING SECURITY 用户登录处理
 */
@Service("MyUserDetailsServiceImpl")
public class MyUserDetailsService implements UserDetailsService {
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysFunctionMapper sysFunctionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("loadUserByUsername --> [{}]", username);
        SysUser sysUser = sysUserMapper.findByName(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("username not found.");
        }
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.findByUserId(sysUser.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<Integer> roleIds = new ArrayList<>();
        Optional<List<SysUserRole>> sysUserRoleListOptional = Optional.ofNullable(sysUserRoleList);
        sysUserRoleListOptional.ifPresent(sysUserRoleList1 -> {
            sysUserRoleList1.forEach(sysUserRole1 -> {
                roleIds.add(sysUserRole1.getRoleId());
                if (sysUserRole1.getRoleId() == 1L) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_root");//root角色特权
                    grantedAuthorities.add(grantedAuthority);
                }
            });
        });

        List<SysFunction> sysFunctionList = sysFunctionMapper.findByRoleIds(StringUtil.listToString(roleIds));
        if (sysFunctionList != null && sysFunctionList.size() > 0) {
            sysFunctionList.forEach(sysFunction -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + sysFunction.getFunctionUrl());//必须ROLE_为前缀
                grantedAuthorities.add(grantedAuthority);
            });
        }
        LOGGER.info("grantedAuthorities --> {}", grantedAuthorities);
        return new User(username, sysUser.getPassword(), true, true, true, true, grantedAuthorities);
    }
}