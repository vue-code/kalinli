package com.maxwit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/admin/hello")
    public String admin() {
        return "Hello admin";
    }
    @RequestMapping("/user/hello")
    public String user() {
        return "Hello user";
    }

}
