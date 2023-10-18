package com.minicourse.iron.zb.minicourse.controller;

import com.minicourse.iron.zb.minicourse.dto.InviteDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1")
public class InviteController {
    @GetMapping("/invite/{link}")
    public String getInvite(@PathVariable("link") String linkIn) {
        return linkIn;
    }

    @PostMapping("/invite")
    public InviteDto setInvite(@Valid @RequestBody InviteDto inviteDtoIn) {
        return inviteDtoIn;
    }
}
