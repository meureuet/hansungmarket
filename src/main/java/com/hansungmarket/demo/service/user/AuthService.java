package com.hansungmarket.demo.service.user;

import com.hansungmarket.demo.dto.user.SignUpDto;
import com.hansungmarket.demo.entity.user.User;
import com.hansungmarket.demo.repository.user.RoleRepository;
import com.hansungmarket.demo.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    
    // 회원가입
    public void signUp(SignUpDto signUpDto) {
        User user = signUpDto.toEntity();
        user.setRole(roleRepository.findByRoleName("ROLE_USER"));

        userRepository.save(user);
    }

}
