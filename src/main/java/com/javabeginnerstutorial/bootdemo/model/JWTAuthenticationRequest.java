package com.javabeginnerstutorial.bootdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthenticationRequest implements Serializable {

	private static final long serialVersionUID = -6986746378005710855L;
	private String username;
    private String password;
}
