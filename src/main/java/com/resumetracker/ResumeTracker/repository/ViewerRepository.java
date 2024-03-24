package com.resumetracker.ResumeTracker.repository;

import com.resumetracker.ResumeTracker.dto.ViewerDto;
import com.resumetracker.ResumeTracker.model.Viewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ViewerRepository extends JpaRepository<Viewer, Long> {
    @Query("SELECT  v FROM Viewer v WHERE v.resume.id = :id")
    List<Viewer> findAllViewersWithResumeId(Long id);
}
