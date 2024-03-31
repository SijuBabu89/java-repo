package com.buddywindow.auth.service;

import com.buddywindow.auth.domain.AuthToken;

public interface IAuthService {

	public AuthToken getAuthToken(String username, String password);
}
