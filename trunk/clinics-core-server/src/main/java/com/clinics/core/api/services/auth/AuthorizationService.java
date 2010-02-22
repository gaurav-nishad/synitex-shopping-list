/**
 * 
 */
package com.clinics.core.api.services.auth;

/**
 * @author Leonids M<leonidms@gmail.com>
 * 
 */
public interface AuthorizationService {
    Role getRole(String userIdentity) throws AuthorizationException;

    boolean hasRole(String userIdentity, Role role) throws AuthorizationException;
}
