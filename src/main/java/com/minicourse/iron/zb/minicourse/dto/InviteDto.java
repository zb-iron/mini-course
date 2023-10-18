package com.minicourse.iron.zb.minicourse.dto;


import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class InviteDto {
    @NotBlank(message="\"email\"필드는 공백을 허용하지 않습니다.")
    private String email;
    @NotBlank(message="\"name\"필드는 공백을 허용하지 않습니다.")
    private String name;
    @NotBlank(message="\"phone\"필드는 공백을 허용하지 않습니다.")
    private String phone;
    private String groupName;
    private String groupOwner;
}
