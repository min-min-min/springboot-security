package com.chenjing.springbootauth.server.service.impl;

import com.chenjing.springbootauth.server.entity.User;
import com.chenjing.springbootauth.server.mapper.RoleMapper;
import com.chenjing.springbootauth.server.mapper.UserMapper;
import com.chenjing.springbootauth.server.mapper.UserRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Chenjing on 2017/9/7.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("执行userDetails的loadUserByUsername方法");
        User user = userMapper.findByUsername(username);
        Assert.notNull(user, "查询出来的用户为空");
        List<String> roles = userRoleMapper.listByUserId(user.getId()).stream().map(x ->
                roleMapper.selectByPrimaryKey(x.getRoleId()).getName()
        ).collect(Collectors.toList());
        /**
         * 权限如果前缀是ROLE_，security就会认为这是个角色信息，而不是权限，例如ROLE_MENBER就是MENBER角色，CAN_SEND就是CAN_SEND权限
         */
        List<GrantedAuthority> grantedAuthorityList = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        /**
         * 添加一个add权限 因为不是ROLE_开头 所以security会认为他是一个权限
         */
        grantedAuthorityList.add(new SimpleGrantedAuthority("add"));
        user.setAuthorities(grantedAuthorityList);
        return user;
    }
}
