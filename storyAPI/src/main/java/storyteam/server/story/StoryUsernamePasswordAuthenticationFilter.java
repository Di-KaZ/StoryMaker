package storyteam.server.story;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.github.cdimascio.dotenv.Dotenv;
import storyteam.server.story.model.User;

// UsernamePasswordAuthenticationFilter => classe de base dans SpringFramework Security pour l'authentification
public class StoryUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private static final Logger LOGGER = LoggerFactory.getLogger(StoryUsernamePasswordAuthenticationFilter.class);

	public static final Dotenv dotenv = Dotenv.load();
	public static final String SECRET = dotenv.get("SECRET_KEY");
	public static final long EXPIRATION_TIME = 120000; // On set la durée de la validitée du token 900_000
	public static final String SIGN_IN_URL = "/user/login"; // On déclare la route par laquelle on passe pour se log in

	/*
	 * On hérite de la classe de Spring Sécurity pour lui apporter des améliorations
	 * On créer un filtre d'authentification, dès qu'un user tente // de se
	 * connecter On passe dans cette classe
	 */

	public StoryUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;

		/*
		 * setFilterProcessUrl permet de désigner une url de route par laquelle le
		 * programme va passer pour authentifier un utilisateur. Cette url est passée en
		 * paramètres, notre programme saura après Quelle route il doit emprunter pour
		 * vérifier les credentials d'un utilisateur
		 */
		setFilterProcessesUrl(SIGN_IN_URL);
	}

	// Quand l'utilisateur se connecte, cette méthode est appelé en premier

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		/*
		 * Le programme créer un objet utilisateur Il utilise ensuite
		 * l'authenticationManager pour lire le nom et le mdp inscrits, puis il regarde
		 * si il y'a un utilisateur correspondant => si oui il appelle
		 * successfulAuthentication()
		 */
		try {
			User credentials = new ObjectMapper().readValue(request.getInputStream(), User.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getName(),
					credentials.getPassword(), new ArrayList<>()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// L'utilisateur est connu dans la BDD il reçoit les infos de l'user
		// Depuis spring security
		String token = JWT.create().withSubject(((UserDetails) authResult.getPrincipal()).getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(SECRET.getBytes()));

		/*
		 * Le token est créer avec JWT, on lui passe le username de l'utilsateur la date
		 * d'expiration du token et une signature avec la secretKey On mélange le tout
		 * et on crypte tout ça token => username+expirationToken+signature => cryptage
		 * résultat eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.....
		 */
		LOGGER.info("Mon token expire le {}", System.currentTimeMillis() + EXPIRATION_TIME);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String body = String.format("{\"token\" : \"%s\"}", token);
		response.getWriter().write(body);
		response.getWriter().flush();
	}

}
