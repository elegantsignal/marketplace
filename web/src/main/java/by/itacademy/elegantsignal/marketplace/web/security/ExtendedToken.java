package by.itacademy.elegantsignal.marketplace.web.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;


public class ExtendedToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = -1761444171547254160L;
	private Integer id;

	public ExtendedToken(final Object principal,
			final Object credentials, final Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

}