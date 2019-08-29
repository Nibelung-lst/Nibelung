package com.example.nibelung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @ Author    :Nibelung
 * @ Date      ：Created in 17:24 2019/8/28
 * @ Description :
 * @ Modified By :
 * @ Version : $
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
