package org.example.project1.schedule;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	@Query("SELECT e FROM Schedule e WHERE " +
		"e.user.id = :userId AND " +
		"(e.startDate BETWEEN :startOfMonth AND :endOfMonth) OR " +
		"(e.endDate BETWEEN :startOfMonth AND :endOfMonth) OR " +
		"(e.startDate < :startOfMonth AND e.endDate > :endOfMonth) ")
	List<Schedule> findSchedulesInMonth(
		@Param("userId") Long userId,
		@Param("startOfMonth") LocalDate startOfMonth,
		@Param("endOfMonth") LocalDate endOfMonth);
}