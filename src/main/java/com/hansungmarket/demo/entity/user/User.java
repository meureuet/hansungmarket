package com.hansungmarket.demo.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hansungmarket.demo.entity.board.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "enabled")
    private Boolean enabled;

    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<Board> board = new ArrayList<>();

    @Builder
    private User(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }
    
    // 권한 지정
    public void setRole(Role role) {
        this.role = role;
    }

    // enabled 지정
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
