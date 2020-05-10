/**
 * 
 */
package com.cg.obs.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cg.obs.service.MyUserDetailsService;
import com.cg.obs.util.JwtUtil;

/**
 * @author sohel
 *
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization"); // Extracting AUthorization Header
		
		String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {  // if the header starts with Bearer then it's a JWT based Authorization
            jwt = authorizationHeader.substring(7); // remove the first 7 characters which are "Bearer ", which leaves you with the jwt token
            username = jwtUtil.extractUsername(jwt); // it's extracting username
        }
        
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); // if there's actually a user in my db
            																				// this is again not a microservice appoach
            																				// I will just comment it out at the 2 hrs meetings

            if (jwtUtil.validateToken(jwt, userDetails)) { // if the jwt is not expired, and if there is actually a user with the same name in the db. Stupid Appraoch ://

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
               
            }
        }
        chain.doFilter(request, response); // let this code block be copied and pasted for working conditions

	}

}
