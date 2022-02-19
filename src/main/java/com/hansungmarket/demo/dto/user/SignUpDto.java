package com.hansungmarket.demo.dto.user;

import com.hansungmarket.demo.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpDto {
    private String username;

    private String password;

    private String nickname;

    private String email;

    @Builder
    private SignUpDto(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .email(email)
                .build();
    }
}
