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

@EnableWebSecurity
public class StoryWebSecurity extends WebSecurityConfigurerAdapter {

    public static final String SIGN_UP_URL = "/user/register";
    public static final String SIGN_IN_URL = "/user/login";

    private GetUserService getUserService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public StoryWebSecurity(GetUserService getUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.getUserService = getUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().and().authorizeRequests().antMatchers(HttpMethod.POST, SIGN_UP_URL, SIGN_IN_URL).permitAll()
                .anyRequest().authenticated().and()
                .addFilter(new StoryUsernamePasswordAuthenticationFilter(authenticationManager()))
                .addFilter(new StoryAuthorizationFilter(authenticationManager())).sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // http.cors().and().authorizeRequests().antMatchers(HttpMethod.POST,
        // SIGN_UP_URL).permitAll()
        // .antMatchers(HttpMethod.POST,
        // SIGN_IN_URL).permitAll().anyRequest().authenticated().and()
        // .addFilter(new
        // StoryUsernamePasswordAuthenticationFilter(authenticationManager()))
        // .addFilter(new
        // StoryAuthorizationFilter(authenticationManager())).sessionManagement()
        // .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
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
