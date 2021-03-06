package com.cn.bee.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by mervin on 2018/7/4.
 */
@Controller
public class KungfuController {
    private final  String PREFIX="pages/";
    /**
     * 欢迎页
     */
    @GetMapping("/")
    public String index(){
        return  "welcome";
    }
    /**
     * 登录页
     */
    @GetMapping("/userlogin")
    public String loginPage(){
        return  PREFIX+"login";
    }

    /**
     * level1页面映射
     */
    @GetMapping("/level1/{path}")
    public String level1(@PathVariable("path") String path){
        return  PREFIX+"level1/"+path;
    }
    /**
     * level2页面映射
     */
    @GetMapping("/level2/{path}")
    public String level2(@PathVariable("path") String path){
        return  PREFIX+"level2/"+path;
    }
    /**
     * level3页面映射
     */
    @GetMapping("/level3/{path}")
    public String level3(@PathVariable("path") String path){
        return  PREFIX+"level3/"+path;
    }

}
