package com.chenjing.springbootauth.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Chenjing on 2017/9/12.
 */
@Controller
public class IndexController {
    @GetMapping("/index")
    public String aaa() {
        return "index";
    }
}
