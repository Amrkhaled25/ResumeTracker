package com.resumetracker.ResumeTracker.controller;

import com.resumetracker.ResumeTracker.dto.MyData;
import com.resumetracker.ResumeTracker.dto.ResponseDto;
import com.resumetracker.ResumeTracker.service.ViewersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ViewResume {

    private final ViewersService viewers;
    @GetMapping("/view/resume/{id}")
    public ResponseEntity<ResponseDto> viewResume(@PathVariable("id") String id){
        try{
            viewers.addViewer(id);
            return ResponseEntity.ok().body(new ResponseDto("Resume viewed",new MyData()));
        }catch(Exception e){
            System.out.println(e.getMessage());
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(e.getMessage(),new MyData()));
        }
    }

    @GetMapping("/viewers/resume/{id}")
    public ResponseEntity<ResponseDto> getViewer(@PathVariable("id") Long id){
        try{


            var response = viewers.getAllViewers(id);
            return ResponseEntity.ok().body(response);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(e.getMessage(),new MyData()));
        }
    }


}
