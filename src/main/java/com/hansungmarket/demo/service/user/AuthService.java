package com.hansungmarket.demo.service.user;

import com.hansungmarket.demo.dto.user.SignUpDto;
import com.hansungmarket.demo.entity.user.User;
import com.hansungmarket.demo.repository.user.RoleRepository;
import com.hansungmarket.demo.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    
    // 회원가입
    @Transactional
    public void signUp(SignUpDto signUpDto) {
        User user = signUpDto.toEntity();
        user.setRole(roleRepository.findByRoleName("ROLE_USER"));
        user.setEnabled(true);

        userRepository.save(user);
    }

    // 로그인한 닉네임 가져오기
    @Transactional(readOnly = true)
    public String getLoginNickname(String username) {
        User user = userRepository.findByUsername(username);
        return user.getNickname();
    }

}
