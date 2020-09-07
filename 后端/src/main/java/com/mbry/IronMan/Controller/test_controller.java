package com.mbry.IronMan.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class test_controller{
    @GetMapping("/")
    String helloworld(){
        return "hello world";
    }
}