package com.minicourse.iron.zb.minicourse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InviteController {
    @GetMapping("/api/v1/invite")
    public String getInvite() {
        return "invite";
    }

    @PostMapping("/api/v1/invite/{link}")
    public String setInvite(@PathVariable("link") String linkIn) {
        return linkIn;
    }
}
