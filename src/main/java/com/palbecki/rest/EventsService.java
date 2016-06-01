package com.palbecki.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.palbecki.domain.Event;
import com.palbecki.repository.EventRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value="/events")
public class EventsService {
	
	@Autowired
	EventRepository eventRepository;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Event getEventById(@PathVariable Long id) {
		return eventRepository.findOne(id);
    }

    @RequestMapping(value="/bands/{bandId}", method=RequestMethod.GET)
    List<Event> getEventsByBand(@PathVariable Long bandId) {
    	return eventRepository.findByBandId(bandId);
    }
    
    @RequestMapping(method=RequestMethod.GET)
    List<Event> getAllEvents() {
    	return eventRepository.findAll();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public void deleteEvent(@PathVariable Long id) {
    	eventRepository.delete(id);
    }

    @RequestMapping(method=RequestMethod.POST)
    public Event postEvent(@RequestBody Event event) {
    	return eventRepository.save(event);
    }

}
