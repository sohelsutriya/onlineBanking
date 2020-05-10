/**
 * 
 */
package com.cg.obs.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.obs.model.AuthenticationResponse;
import com.cg.obs.model.LoginCredentials;
import com.cg.obs.service.MyUserDetailsService;

/**
 * @author sohel
 *
 */
@RestController
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginCredentials loginCredentials)
			throws Exception {

		try {
			// below is an example of manually triggering authentication with credentials
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCredentials.getUsername(),
					loginCredentials.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		// if authentication succeeds, then for that username extract details
		final UserDetails userDetails = userDetailsService.loadUserByUsername(loginCredentials.getUsername());

		// using the jwt utils library to generate jwt
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		// returning jwt as json
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
