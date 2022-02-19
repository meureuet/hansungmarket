package com.hansungmarket.demo.controller.user;

import com.hansungmarket.demo.dto.user.SignUpDto;
import com.hansungmarket.demo.service.user.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final AuthService authService;

    // 회원가입
    @PostMapping("/users")
    public void signUp(@RequestBody SignUpDto signUpDto) {
        authService.signUp(signUpDto);
    }
}
