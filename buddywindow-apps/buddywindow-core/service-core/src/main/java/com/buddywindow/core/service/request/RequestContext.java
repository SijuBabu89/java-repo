package com.buddywindow.core.service.request;

import java.util.Set;

import com.buddywindow.core.service.entity.UserProfile;

public class RequestContext {

	private static final ThreadLocal<UserProfile> currentUserProfile = new ThreadLocal<>();
    private static final ThreadLocal<Set<String>> currentUserRoles = new ThreadLocal<>();
    
    // Set the current user profile
    public static void setUserProfile(UserProfile userProfile) {
        currentUserProfile.set(userProfile);
    }
    
    // Get the current user profile
    public static UserProfile getUserProfile() {
        return currentUserProfile.get();
    }
    
    // Set the current user roles
    public static void setUserRoles(Set<String> roles) {
        currentUserRoles.set(roles);
    }
    
    // Get the current user roles
    public static Set<String> getUserRoles() {
        return currentUserRoles.get();
    }
    
    // Clear all thread-local variables
    public static void clear() {
        currentUserProfile.remove();
        currentUserRoles.remove();
    }
}
