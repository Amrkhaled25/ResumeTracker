package com.resumetracker.ResumeTracker.controller;


import com.resumetracker.ResumeTracker.dto.MyData;

import com.resumetracker.ResumeTracker.dto.ResponseDto;

import com.resumetracker.ResumeTracker.service.ResumeService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    @PostMapping("/upload")
    public ResponseEntity<ResponseDto>uploadResume(@RequestParam("file") MultipartFile request ,
                                                   @RequestHeader HttpHeaders headers
    ){
        try {
            ResponseDto response  = resumeService.uploadResume(request, headers) ;
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ResponseDto(e.getMessage(), new MyData()));
        }


    }
}
