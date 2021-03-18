package storyteam.server.story.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import storyteam.server.story.model.User;
import storyteam.server.story.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repository;

	public Optional<User> findByName(String name) {
		return repository.findByName(name);
	}

	public void deleteByName(String name) {
		repository.deleteByName(name);
	}

	public User save(User user) {
		return repository.save(user);
	}
}
