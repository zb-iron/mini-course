package com.minicourse.iron.zb.minicourse.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {
    @PostMapping("/api/v1/group")
    public String addGroup(){
        return "addGroup";
    }
}
