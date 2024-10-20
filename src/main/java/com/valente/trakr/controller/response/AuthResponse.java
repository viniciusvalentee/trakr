package com.valente.trakr.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthResponse {

    @JsonProperty("access_token")
    private String accessToken;

    private String nome;
}
