package storyteam.server.story.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import storyteam.server.story.model.BlocStory;
import storyteam.server.story.model.Story;
import storyteam.server.story.repository.BlocStoryRepository;

@Service
public class BlocStoryService {
    
    @Autowired
    BlocStoryRepository blocStoryRepository;


}
