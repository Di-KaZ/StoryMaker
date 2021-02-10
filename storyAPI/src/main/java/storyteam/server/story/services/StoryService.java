package storyteam.server.story.services;

import org.springframework.beans.factory.annotation.Autowired;

import storyteam.server.story.repository.StoryRepo;

// @Service
public class StoryService {
	@Autowired
	StoryRepo repository;
}
