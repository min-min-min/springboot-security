package com.chenjing.springbootauth.server.service.impl;

import com.chenjing.springbootauth.server.entity.User;
import com.chenjing.springbootauth.server.mapper.RoleMapper;
import com.chenjing.springbootauth.server.mapper.UserMapper;
import com.chenjing.springbootauth.server.mapper.UserRoleMapper;
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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println("================================");
        User user = userMapper.findByUsername(username);
        Assert.notNull(user, "查询出来的用户为空");
        List<String> roles = userRoleMapper.listByUserId(user.getId()).stream().map(x ->
                roleMapper.selectByPrimaryKey(x.getRoleId()).getName()
        ).collect(Collectors.toList());
        List<GrantedAuthority> grantedAuthorityList = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        user.setAuthorities(grantedAuthorityList);
        return user;
    }
}
