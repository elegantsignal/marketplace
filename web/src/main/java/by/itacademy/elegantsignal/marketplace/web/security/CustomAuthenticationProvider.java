package by.itacademy.elegantsignal.marketplace.web.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IRole;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.service.IUserService;


@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	IUserService userService;

	@Override
	public Authentication authenticate(final Authentication authentication)
			throws AuthenticationException {
		final String username = authentication.getPrincipal() + "";
		final String password = authentication.getCredentials() + "";

		final IUser user = userService.getUserByEmail(username);

		if (!user.getEmail().equals(username) || !user.getPassword().equals(password)) {
			throw new BadCredentialsException("1000");
		}

		final int userId = user.getId();

		final Set<IRole> userRoles = user.getRole();

		final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (final IRole role : userRoles) {
			authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
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