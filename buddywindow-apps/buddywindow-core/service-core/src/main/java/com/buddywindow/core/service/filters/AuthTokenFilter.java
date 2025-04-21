package com.buddywindow.core.service.filters;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.buddywindow.core.service.adapter.IAuthServiceAdapter;
import com.buddywindow.core.service.entity.UserProfile;
import com.buddywindow.core.service.request.RequestContext;
import com.buddywindow.core.service.utils.JwtHelper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(0)
public class AuthTokenFilter extends OncePerRequestFilter {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	@Autowired
	private IAuthServiceAdapter authServiceAdapter;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// RequestContext.initialize(request);
		long startTime = System.nanoTime();
		try {
			this.authenticateRequest(request);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// RequestContext.clear();
			//SecurityContextHolder.getContext().setAuthentication(null);
		}
		filterChain.doFilter(request, response);

	}

	private void authenticateRequest(HttpServletRequest request) throws Exception {
		String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
		if (authorizationHeader != null && !authorizationHeader.isBlank()) {
			try {
				
				this.authServiceAdapter.authenticateToken(authorizationHeader);
				UserProfile userProfile = JwtHelper.extractUserProfile(authorizationHeader);
//				String userId = authenticationService.getUserIdFromToken(token);
                
                // Load user profile
//                UserProfile userProfile = userProfileService.getUserProfile(userId);
                RequestContext.setUserProfile(userProfile);
                
                // Load user roles
//                Set<String> roles = userProfileService.getUserRoles(userId);
//                RequestContext.setUserRoles(roles);
				
				
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userProfile, null, List.of());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (Exception e) {
				// Log the error but don't write to response
				System.out.println("Token validation error: " + e.getMessage());
				// Don't set authentication - the context remains empty
			}

		} else {
//            log.warn("authenticateRequest", "HTTP request[correlationId = {}, url={} ip = {}] "+ AUTHORIZATION_HEADER + " is null",
//                    RequestContext.getCorrelationId(), RequestContext.getURL(), RequestContext.getIPAddress());
		}
	}
	
    private void setInvalidTokenResponse(HttpServletResponse response, String errorCode, String messasge) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String failedResponseData = errorCode + messasge;
        response.addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        try {
            response.getWriter().print(failedResponseData);
        } catch (IOException ex) {
        }

    }

}
