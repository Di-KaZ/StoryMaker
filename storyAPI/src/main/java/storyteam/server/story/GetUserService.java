package storyteam.server.story;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import storyteam.server.story.model.User;
import storyteam.server.story.services.UserService;

/**
 * Fonction qui va faire le lien entre notre authenticator et notre base de
 * données.
 *
 * @params username : c'est le nom que l'utilisateur inscrit pour tenter une
 *         authentification
 *
 *         La fonction est autowired avec le repository, elle renvoie un objet
 *         UserDetails pour le attempt authentication
 *
 *         Le service est rattaché à l'API depuis StoryWebSecurity
 */
@Service
public class GetUserService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userService.findByName(username);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(user.get().getName(), user.get().getPassword(),
				new ArrayList<>());
	}

}
