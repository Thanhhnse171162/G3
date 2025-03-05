package com.SWP.SkinCareService.repository;

import com.SWP.SkinCareService.entity.BookingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingSessionRepository extends JpaRepository<BookingSession, Integer> {
}
