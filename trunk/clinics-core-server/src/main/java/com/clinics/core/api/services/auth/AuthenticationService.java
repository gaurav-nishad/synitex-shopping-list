/**
 * 
 */
package com.clinics.core.api.services.auth;


/**
 * @author Leonids M<leonidms@gmail.com>
 * 
 */
public interface AuthenticationService {

    Authentication authenticate(String userIdentity, byte[] credentials) throws AuthenticationException;
}
