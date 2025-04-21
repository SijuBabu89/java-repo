package com.buddywindow.core.service.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.buddywindow.core.service.entity.UserProfile;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtHelper {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Extracts UserProfile from JWT token This method properly handles the
	 * extraction of the nested profile JSON string
	 */
	public static UserProfile extractUserProfile(String token) throws Exception {
		// Split the token into parts
		String[] parts = token.split("\\.");
		if (parts.length != 3) {
			throw new IllegalArgumentException("Invalid JWT format");
		}

		// Decode the payload part
		String payloadStr = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);

		// First, parse the payload to a JsonNode
		JsonNode payloadNode = objectMapper.readTree(payloadStr);

		// Extract only the profile field
		if (!payloadNode.has("profile")) {
			throw new IllegalArgumentException("JWT payload does not contain a profile field");
		}

		// Get the profile string and parse it directly to UserProfile
		String profileJson = payloadNode.get("profile").asText();
		return objectMapper.readValue(profileJson, UserProfile.class);
	}
}
	
