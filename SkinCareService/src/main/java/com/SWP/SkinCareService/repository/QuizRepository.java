package com.SWP.SkinCareService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SWP.SkinCareService.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
