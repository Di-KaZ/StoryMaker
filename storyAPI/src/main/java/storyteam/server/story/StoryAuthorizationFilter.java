package storyteam.server.story;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class StoryAuthorizationFilter extends BasicAuthenticationFilter {

	public static final String SECRET = "SECRET_KEY";
	public static final String HEADER_STRING = "Authorization";

	public StoryAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(HEADER_STRING);

		if (header == null) {
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);

		if (token != null) {
			String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build().verify(token).getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}

			return null;
		}

		return null;
	}

}
