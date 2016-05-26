package com.palbecki.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.palbecki.domain.Comment;
import com.palbecki.repository.CommentRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value="/comments")
public class CommentsService {
	
	@Autowired
	CommentRepository commentRepository;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Comment getCommentByUser(@PathVariable Long id) {
		return commentRepository.findOne(id);
    }

    @RequestMapping(value="/bands/{bandId}", method=RequestMethod.GET)
    List<Comment> getCommentsByBand(@PathVariable Long bandId) {
    	return commentRepository.findByBandId(bandId);
    }

    @RequestMapping(value="/bands/{bandId}/{albumId}", method=RequestMethod.GET)
    List<Comment> getCommentsByBand(@PathVariable Long bandId, @PathVariable Long albumId) {
    	List<Comment> list = commentRepository.findByBandId(bandId);
    	return list.stream()
    			.filter(e -> e.getAlbumId().equals(albumId))
    			.collect(Collectors.toList());
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public void deleteComment(@PathVariable Long id) {
    	commentRepository.delete(id);
    }

    @RequestMapping(method=RequestMethod.POST)
    public Comment postComment(@RequestBody Comment comment) {
    	return commentRepository.save(comment);
    }

}
