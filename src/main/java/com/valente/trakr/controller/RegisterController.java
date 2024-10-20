package com.valente.trakr.controller;

import com.valente.trakr.controller.request.RegisterUserRequest;
import com.valente.trakr.entity.Role;
import com.valente.trakr.repository.User;
import com.valente.trakr.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserRepository repository;

    @PostMapping
    public ResponseEntity<Void> register(@RequestHeader(value = "isAdmin", required = false) boolean isAdmin,
                                         @RequestBody RegisterUserRequest request) {
        User newUser = User.builder()
                .name(request.getNome())
                .email(request.getEmail())
                .roles(isAdmin ? List.of(Role.ADMIN, Role.USER) : List.of(Role.USER))
                .password(new BCryptPasswordEncoder().encode(request.getSenha()))
                .build();

        repository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
