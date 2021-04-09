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

import storyteam.server.story.model.User;

public class StoryUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private static final Logger LOGGER = LoggerFactory.getLogger(StoryUsernamePasswordAuthenticationFilter.class);

	public static final String SECRET = "SECRET_KEY"; // TODO pass this via environement
	public static final long EXPIRATION_TIME = 900_000;
	public static final String SIGN_IN_URL = "/user/login";

	public StoryUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;

		setFilterProcessesUrl(SIGN_IN_URL);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
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
		String token = JWT.create().withSubject(((UserDetails) authResult.getPrincipal()).getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(SECRET.getBytes()));

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String body = String.format("{\"token\" : \"%s\"}", token);
		response.getWriter().write(body);
		response.getWriter().flush();
	}

}
