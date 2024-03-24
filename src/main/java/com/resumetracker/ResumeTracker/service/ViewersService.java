package com.resumetracker.ResumeTracker.service;

import com.resumetracker.ResumeTracker.dto.MyData;
import com.resumetracker.ResumeTracker.dto.ResponseDto;
import com.resumetracker.ResumeTracker.dto.ViewerDto;
import com.resumetracker.ResumeTracker.model.Viewer;
import com.resumetracker.ResumeTracker.repository.ResumeRepository;
import com.resumetracker.ResumeTracker.repository.ViewerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewersService {
    private final ResumeRepository resumeRepository;
    private final ViewerRepository viewerRepository;
    @Transactional
    public void addViewer(String id){
        try {
            var resume = resumeRepository.findByPath(id).orElseThrow(() -> new Exception("Resume not found"));
            var viewer = viewerRepository.save(Viewer.builder().resume(resume).build());
            //resumeRepository.save(resume);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public ResponseDto getAllViewers(Long id){
        var viewers = viewerRepository.findAllViewersWithResumeId(id);
        List<ViewerDto> viewerDataList = new ArrayList<>();
        for(var viewer : viewers){
            viewerDataList.add(ViewerDto.builder().id(viewer.getId()).createdAt(viewer.getCreatedAt()).build());
        }


        var response = ResponseDto.builder().
                message("List of Viewers").
                data(MyData.builder().
                        viewerDto(viewerDataList)
                        .build())
                .build() ;
        return response;
    }
}
