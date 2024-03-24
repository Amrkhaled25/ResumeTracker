package com.resumetracker.ResumeTracker.service;

import com.resumetracker.ResumeTracker.config.JwtUtil;
import com.resumetracker.ResumeTracker.dto.MyData;
import com.resumetracker.ResumeTracker.dto.ResponseDto;
import com.resumetracker.ResumeTracker.model.Resume;
import com.resumetracker.ResumeTracker.repository.ResumeRepository;
import com.resumetracker.ResumeTracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final GoogleDriveService service;
    private final ResumeRepository resumeRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    public ResponseDto uploadResume(MultipartFile request , HttpHeaders headers){


        String resumeId =  service.uploadFile(request);

        String jwtToken = headers.getFirst(HttpHeaders.AUTHORIZATION).split(" ")[1];
        String username = jwtUtil.extractAllClaims(jwtToken).getSubject();
        var userData = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User Not Found"));

        Resume resume = Resume.builder()
                .path(resumeId)
                .user(userData)
                .build();
        resumeRepository.save(resume);

        ResponseDto  responseData = ResponseDto.builder()
                .message("Resume uploaded successfully")
                .data(MyData.builder().path(resumeId).build())
                .build();
        return  responseData ;
    }
}
