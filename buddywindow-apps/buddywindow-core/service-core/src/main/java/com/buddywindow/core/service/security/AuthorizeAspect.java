package com.buddywindow.core.service.security;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.buddywindow.core.service.annotation.RequiresPermission;
import com.buddywindow.core.service.entity.UserProfile;
import com.buddywindow.core.service.request.RequestContext;

@Aspect
@Component
public class AuthorizeAspect {



@Before("@annotation(requiresPermission)")
public void checkPermission(JoinPoint joinPoint, RequiresPermission requiresPermission) {
    // Get required permission
    String permission = requiresPermission.value();
    
    // Get user profile and roles from the context
    UserProfile userProfile = RequestContext.getUserProfile();
    //Set<String> roles = RequestContext.getUserRoles();
    
    if (userProfile == null) {
//        auditService.logAccess("anonymous", 
//                              joinPoint.getSignature().getDeclaringTypeName(), 
//                              null, 
//                              false);
        throw new SecurityException("Access denied: Not authenticated");
    }
    List<String> rights = userProfile.getRights() != null ? userProfile.getRights() : new ArrayList<String>();
    // Check if user has required permission based on roles
    if (!rights.contains(permission)) {
//        auditService.logAccess(userProfile.getId(), 
//                              joinPoint.getSignature().getDeclaringTypeName(), 
//                              null, 
//                              false);
        throw new SecurityException("Access denied: Missing required permission " + permission);
    }
    
    // Log successful access
//    auditService.logAccess(userProfile.getId(), 
//                          joinPoint.getSignature().getDeclaringTypeName(), 
//                          null, 
//                          true);
}

//private boolean hasPermission(Set<String> roles, Permission permission) {
//    // Implementation would check if any of the user's roles grant the required permission
//    // This could use a role-permission mapping service or repository
//    return true; // Placeholder implementation
//}
}