package com.theVoiceAround.music.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * @RestController 等价于 @ResponseBody + @Controller
 */
public class TestController {

    @RequestMapping("/hello")
    public String hello(){
        return "The Voice Around!";
    }
}
