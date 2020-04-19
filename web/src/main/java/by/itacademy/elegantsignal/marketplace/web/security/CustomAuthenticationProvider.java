package by.itacademy.elegantsignal.marketplace.web.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;


@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

// TODO inject UserService

	@Override
	public Authentication authenticate(final Authentication authentication)
			throws AuthenticationException {
		final String username = authentication.getPrincipal() + "";
		final String password = authentication.getCredentials() + "";

// TODO find use by username (unique)
		if (!"admin".equals(username)) {
			throw new BadCredentialsException("1000");
		}

// TODO verify password (DB contains hash - not a plain password)
		if (!"nimda".equals(password)) {
			throw new BadCredentialsException("1000");
		}

		final int userId = 1; // FIXME: it should be the real user id from DB

		final List<String> userRoles = new ArrayList<>();// TODO get list of user's
// roles
		userRoles.add("ROLE_" + "admin"); // !!! ROLE_ prefix is required

		final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (final String roleName : userRoles) {
			authorities.add(new SimpleGrantedAuthority(roleName));
		}

		final ExtendedToken token = new ExtendedToken(username, password, authorities);
		token.setId(userId);
		return token;

	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}