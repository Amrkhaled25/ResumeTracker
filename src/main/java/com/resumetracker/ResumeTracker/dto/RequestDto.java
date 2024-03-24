package com.resumetracker.ResumeTracker.dto;

import com.resumetracker.ResumeTracker.model.Role;
import lombok.*;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDto {
    private Long id ;
    private String email ;
    private String password ;
    private String username ;

    private Role role ;
}
