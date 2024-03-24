package com.resumetracker.ResumeTracker.service;

import com.resumetracker.ResumeTracker.config.JwtUtil;
import com.resumetracker.ResumeTracker.dto.MyData;

import com.resumetracker.ResumeTracker.dto.RequestDto;
import com.resumetracker.ResumeTracker.dto.ResponseDto;
import com.resumetracker.ResumeTracker.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import com.resumetracker.ResumeTracker.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public ResponseDto register(RequestDto request) {

        var user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        var savedUser = userRepository.save(user);

        MyData myDataForResponse = MyData.builder()
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .build();
        return ResponseDto.builder().
                message("User registered successfully")
                .data(myDataForResponse)
                .build();
    }
}
