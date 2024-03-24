package com.resumetracker.ResumeTracker.repository;

import com.resumetracker.ResumeTracker.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Resume> findByPath(String path);
}
