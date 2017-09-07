package com.chenjing.springbootauth.server.service;

import com.chenjing.springbootauth.server.entity.User;

/**
 * Created by Chenjing on 2017/9/7.
 */
public interface AuthService {
    Integer register(User userToAdd);

    String login(String username, String password);

    String refresh(String oldToken);
}
