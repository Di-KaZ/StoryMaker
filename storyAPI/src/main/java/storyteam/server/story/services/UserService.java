package storyteam.server.story.services;

import java.util.Optional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import storyteam.server.story.model.User;
import storyteam.server.story.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repository;

	@Autowired
	@Qualifier("JWT_SECRET")
	String SECRET;

	public Optional<User> findByName(String name) {
		return repository.findByName(name);
	}

	public void deleteByName(String name) {
		repository.deleteByName(name);
	}

	public User save(User user) {
		return repository.save(user);
	}

	public Optional<User> findUserByToken(String token) {
		String username = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build().verify(token).getSubject();
		return repository.findByName(username);
	}
}
