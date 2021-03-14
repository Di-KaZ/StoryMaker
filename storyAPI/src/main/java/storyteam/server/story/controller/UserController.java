package storyteam.server.story.controller;

import java.util.Optional;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import storyteam.server.story.model.User;
import storyteam.server.story.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	Pattern emailRegex = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

	@Autowired
	UserService userService;

	/**
	 * Permet a un utilisateur de se connecté la requetre renvoit un utilisateur et
	 * le token de conncetion via le header de la reponse
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	@GetMapping(value = "/login")
	public ResponseEntity<User> getUser(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		LOGGER.info("{} {}", username, password);

		// We check if a user exist with this username
		Optional<User> user = userService.findByName(username);

		if (user.isEmpty()) {
			LOGGER.info("Pas d'utilisateurs inscrit avec ce nom");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}

		// if password does not match
		if (!(user.get().getPassword().equals(password))) {
			LOGGER.info("mot de passe incorrect");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		// user connected we return it and add token in header
		LOGGER.info("Good on renvoit les infos", username, password);
		return ResponseEntity.ok(user.get());
	}

	/**
	 * Enregistre un nouvel utilisateur en bdd renvoit l'utilisateur nouvellement
	 * crée ainsi qu'un token d'authentification
	 *
	 * @param username
	 * @param password
	 * @param email
	 * @return
	 */
	@PostMapping(value = "/register")
	public ResponseEntity<User> createUser(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("email") String email) {
		Optional<User> user = userService.findByName(username);

		// Si un utilistateur avec ce nom exister erreur
		if (user.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}

		// check if the email IS an email
		if (!emailRegex.matcher(email).matches()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}

		try {
			User newUser = new User(username, password, email);
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(newUser));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
		}
	}

	/**
	 * Change connected user email
	 *
	 * @param email
	 * @param newEmail
	 * @return
	 */
	@PostMapping(value = "/update/email", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateMail(@RequestParam("email") Optional<String> email,
			@RequestParam("newEmail") Optional<String> newEmail) {
		// Modifying the datas
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
	}

	/**
	 * Change connected user email
	 *
	 * @param email
	 * @param newEmail
	 * @return
	 */
	@PostMapping(value = "/update/password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updatePassword(@RequestParam("password") Optional<String> password,
			@RequestParam("newPassword") Optional<String> newPassword) {
		// Modifying the datas
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
	}

	// When a user wants to delete his account, only him can do this
	@DeleteMapping(value = "/delete/{username}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("username") String username) {
		userService.deleteByName(username);

	}
}
