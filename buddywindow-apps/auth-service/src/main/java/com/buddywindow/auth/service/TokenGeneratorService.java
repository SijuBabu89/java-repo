package com.buddywindow.auth.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.buddywindow.auth.builder.TokenBuilder;
import com.buddywindow.auth.constant.PrivateClaims;
import com.buddywindow.auth.entity.TokenUserProfile;
import com.buddywindow.auth.util.JsonUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class TokenGeneratorService {

	@Value("${buddywindow.app.access-token-secret}")
	private String accessTokenSecret;
	@Value("${buddywindow.app.refresh-token-secret}")
	private String refreshTokenSecret;
	@Value("${buddywindow.app.access-token-expirationMs}")
	private int accessTokenExpirationMs;
	@Value("${buddywindow.app.refresh-token-expirationMs}")
	private int refershTokenExpirationMs;

	public String generateAccessTokenFormUsername(String username, TokenUserProfile profiles) {
		Map<String, Object> claims = getClaimsFromUserProfile(profiles);
		return TokenBuilder.getInstance().setClaims(claims).setSubject(username).setSecretKey(accessTokenSecret)
				.setTokenExpirationMs(accessTokenExpirationMs).build();
	}

	public String generateRefreshTokenFormUsername(String username, TokenUserProfile profiles) {
		Map<String, Object> claims = getClaimsFromUserProfile(profiles);
		return TokenBuilder.getInstance().setClaims(claims).setSubject(username).setSecretKey(refreshTokenSecret)
				.setTokenExpirationMs(refershTokenExpirationMs).build();

	}

	public String getUserNameFromAccessToken(String token) {
		return Jwts.parser().setSigningKey(accessTokenSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(accessTokenSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
//	      logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
//	      logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
//	      logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
//	      logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
//	      logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}

	private Map<String, Object> getClaimsFromUserProfile(TokenUserProfile profiles) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put(PrivateClaims.PROFILE.key, JsonUtil.serialize(profiles));
		claims.put(PrivateClaims.IDENTITY.key, "UUID-01");
		claims.put(PrivateClaims.UID.key, "UUID-02");
		return claims;
	}

	public int getAccessTokenExpirationMs() {
		return accessTokenExpirationMs;
	}

	public int getRefershTokenExpirationMs() {
		return refershTokenExpirationMs;
	}

}
