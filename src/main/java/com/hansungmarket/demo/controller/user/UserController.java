package com.hansungmarket.demo.controller.user;

import com.hansungmarket.demo.dto.user.SignUpDto;
import com.hansungmarket.demo.service.user.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final AuthService authService;

    // 회원가입
    @PostMapping("/users")
    public void signUp(@RequestBody SignUpDto signUpDto) {
        authService.signUp(signUpDto);
    }

    // 회원정보 출력
    @GetMapping("/users")
    public void getUserDetails(Authentication authentication, HttpSession session) {
        String username = authentication.getName();
        session.setAttribute("user", authService.getLoginNickname(username));
        session.getAttribute("user");
    }

}
