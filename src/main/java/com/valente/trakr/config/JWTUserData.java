package com.valente.trakr.config;

import com.valente.trakr.entity.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class JWTUserData {

    private String userId;
    private String email;
    private List<Role> roles;
}
