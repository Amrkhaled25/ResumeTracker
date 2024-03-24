package com.resumetracker.ResumeTracker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.resumetracker.ResumeTracker.model.Role;
import com.resumetracker.ResumeTracker.model.Viewer;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyData {
    private Long id ;
    private String email ;

    private String username ;

    private Role role ;
    private List<Viewer> viewer ;
    private Date createdAt;
    private String path ;
    private List<ViewerDto> viewerDto ;

}
