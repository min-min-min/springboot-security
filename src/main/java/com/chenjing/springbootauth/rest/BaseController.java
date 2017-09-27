package com.chenjing.springbootauth.rest;

import com.chenjing.springbootauth.server.entity.User;
import com.chenjing.springbootauth.server.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Chenjing on 2017/9/7.
 */
@RestController
public class BaseController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;


    @GetMapping("/")
    public String b() {
        return "hello world";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String a() {
        return "you has user role";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String c() {
        return "you has admin role";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('add')")
    public String d() {
        return "you has add auth";
    }

    @PostMapping("/auth/token")
    public String createAuthenticationToken(
            @RequestParam("username") String username,
            @RequestParam("password") String password) throws AuthenticationException {
        String token = authService.login(username, password);
        // Return the token
        return token;
    }

    @GetMapping("/auth/refresh")
    public String refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return "null";
        } else {
            return refreshedToken;
        }
    }

    @RequestMapping(value = "/auth/user", method = RequestMethod.POST)
    public Integer register(@RequestBody User addedUser) throws AuthenticationException {
        return authService.register(addedUser);
    }

}
