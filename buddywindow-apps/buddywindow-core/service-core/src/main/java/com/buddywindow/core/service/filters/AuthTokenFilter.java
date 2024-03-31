package com.buddywindow.core.service.filters;

import java.io.IOException;
import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.buddywindow.core.service.entity.UserProfile;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(0)
public class AuthTokenFilter extends OncePerRequestFilter {

	private static final String AUTHORIZATION_HEADER = "Authorization";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// RequestContext.initialize(request);
		long startTime = System.nanoTime();
		// log.info("doFilterInternal",
		// LogUtil.Template.HTTP_REQUEST_IN.getMessage(),RequestContext.getCorrelationId(),
		// RequestContext.getURL(), RequestContext.getIPAddress());
		try {
			this.authenticateRequest(request);
			filterChain.doFilter(request, response);
			// log.info("doFilterInternal",
			// LogUtil.Template.HTTP_REQUEST_IN_PROCESSED.getMessage(),RequestContext.getCorrelationId(),
			// RequestContext.getURL(), RequestContext.getIPAddress(),
			// ChronoUtil.timeDiffInMilliSeconds(startTime));
		} catch (Exception e) {
			// log.error("doFilterInternal", "Exception[type={}, cause={}]",
			// e.getClass().getSimpleName(), e.getErrorDescription());
			// setInvalidTokenResponse(response, e.getErrorCode(), e.getMessage());
		} catch (Throwable e) {
			// log.error("doFilterInternal", "Exception[type={}, cause={}]",
			// e.getClass().getSimpleName(), e.getMessage());
			// setInvalidTokenResponse(response, "Unauthorized", "Unauthorized request");

		} finally {
			// RequestContext.clear();
			SecurityContextHolder.getContext().setAuthentication(null);
		}

	}

	private void authenticateRequest(HttpServletRequest request) throws Exception {
		String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
		if (authorizationHeader != null && !authorizationHeader.isBlank()) {
			UserProfile userProfile = new UserProfile();
			// this.authAdapter.authenticateToken(authorizationHeader);

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userProfile,null, List.of());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} else {
//            log.warn("authenticateRequest", "HTTP request[correlationId = {}, url={} ip = {}] "+ AUTHORIZATION_HEADER + " is null",
//                    RequestContext.getCorrelationId(), RequestContext.getURL(), RequestContext.getIPAddress());
		}
	}

}
