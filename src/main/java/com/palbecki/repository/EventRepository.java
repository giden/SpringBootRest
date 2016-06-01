package com.palbecki.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.palbecki.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
	
	public List<Event> findByBandId(Long id);
}
