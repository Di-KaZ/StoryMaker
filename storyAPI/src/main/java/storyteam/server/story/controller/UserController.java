package storyteam.server.story.controller;

import java.util.Optional;
import java.util.regex.Pattern;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public static final String SECRET = "SECRET_KEY";

	// Pour récupérer des variables d'environnement
	// Dotenv dotenv = Dotenv.load();
	// Ensuite faire dotenv.get("indexVariable") du fichier .env

	Pattern emailRegex = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

	@Autowired
	UserService userService;

	@Autowired
	@Qualifier("passwordEncoder")
	BCryptPasswordEncoder bCryptPasswordEncoder;

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
	public ResponseEntity<User> createUser(@RequestBody User user) {
		Optional<User> userCheck = userService.findByName(user.getName());

		// Si un utilistateur avec ce nom exister erreur
		if (userCheck.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}

		// check if the email IS an email
		if (!emailRegex.matcher(user.getEmail()).matches()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}

		try {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
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

	@GetMapping(value = "/infos")
	public ResponseEntity<User> getAccountInfos(@RequestHeader("Authorization") String auth) {
		String username = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build().verify(auth).getSubject();
		Optional<User> user = userService.findByName(username);
		if (user.isPresent()) {
			return ResponseEntity.of(user);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
