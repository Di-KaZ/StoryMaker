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

import io.github.cdimascio.dotenv.Dotenv;

public class StoryAuthorizationFilter extends BasicAuthenticationFilter {

	// Pour récupérer des variables d'environnement
	public static final Dotenv dotenv = Dotenv.load();
	public static final String SECRET = dotenv.get("SECRET_KEY");
	public static final String HEADER_STRING = "Authorization";

	public StoryAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	// Interception d'une requete
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// on décortique la requète en regardant le header
		String header = request.getHeader(HEADER_STRING);

		// Si il n'y a pas de header dans la requete on stop la fonction ici
		if (header == null) {

			chain.doFilter(request, response);
			return;
		}

		// Sinon on vérifie le token reçu dans le header dans getAuthentication
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

		// Si c'est ok on retourne le token avec un accès validé
		// Ensuite on l'enregistre

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	// Quand on s'identifie on passe par la pour crypter/decrypter le mdp
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);

		// Quand on est connecté et que le token expire, on a des erreurs
		// Affichage de la date de validitée du token
		// getExpiresAt retourne un java.util.Date object

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
