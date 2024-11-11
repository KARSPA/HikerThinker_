package fr.karspa.hikerthinkerv3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TestController {

    @GetMapping("/user/test")
    public String test() {
        return "oui.";
    }
    @GetMapping("/admin/test")
    public String testAdmin() {
        return "oui. admin";
    }
}
