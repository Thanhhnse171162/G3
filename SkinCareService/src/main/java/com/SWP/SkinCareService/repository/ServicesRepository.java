package com.SWP.SkinCareService.repository;

import com.SWP.SkinCareService.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ServicesRepository extends JpaRepository<Services, Integer> {
    boolean existsByName(String name);
}
