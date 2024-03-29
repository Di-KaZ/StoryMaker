package storyteam.server.story;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// Implémentation de notre logique de sécurisation
// en ajoutant le @EnableWebSecurity et en héritant de WebSecurityConfigurerAdapter
// Qui vient lui aussi de SpringFramework security
@EnableWebSecurity
public class StoryWebSecurity extends WebSecurityConfigurerAdapter {

	public static final String SIGN_UP_URL = "/user/register";
	public static final String SIGN_IN_URL = "/user/login";
	public static final String STORY_TRENDING = "/story/trending";

	// Lien entre la BDD et notre bloc de sécuritée
	private GetUserService getUserService;
	// Bean pour hacher les mdp (springSecurity)
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public StoryWebSecurity(GetUserService getUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.getUserService = getUserService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	/**
	 * On paramètre l'API pour que lorsque l'on tente de s'inscrire ou de se
	 * connecter on applique des filtres que nous avons créer pour JWT
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// Pour éviter les erreur de cors authenticator on autorise les inscription et
		// les connections.
		http.cors().and().authorizeRequests().antMatchers(HttpMethod.GET, STORY_TRENDING).permitAll().and()
				.authorizeRequests().antMatchers(HttpMethod.POST, SIGN_UP_URL, SIGN_IN_URL).permitAll().anyRequest()
				.authenticated().and().addFilter(new StoryUsernamePasswordAuthenticationFilter(authenticationManager()))
				.addFilter(new StoryAuthorizationFilter(authenticationManager())).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(getUserService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", corsConfiguration);

		return source;
	}

}
