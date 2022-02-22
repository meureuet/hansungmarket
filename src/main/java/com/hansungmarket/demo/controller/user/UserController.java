package com.hansungmarket.demo.controller.user;

import com.hansungmarket.demo.dto.user.SignUpDto;
import com.hansungmarket.demo.dto.user.UserDto;
import com.hansungmarket.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/users")
    public void signUp(@RequestBody @Valid SignUpDto signUpDto) {
        userService.signUp(signUpDto);
    }

    // 로그인한 회원정보 출력
    @GetMapping("/users")
    public UserDto getUserDetails(Authentication authentication) {
        return userService.getUser(authentication.getName());
    }

}
