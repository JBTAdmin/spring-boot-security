package com.javabeginnerstutorial.bootdemo.config.jwt;

import org.springframework.security.core.AuthenticationException;

public class TokenAuthenticationException extends AuthenticationException {
	private static final long serialVersionUID = -71545632186596342L;
	public TokenAuthenticationException(String e) {
        super(e);
    }
}
