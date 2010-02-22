/**
 * 
 */
package com.clinics.core.api.services.auth;

/**
 * @author Leonids M<leonidms@gmail.com>
 * 
 */
public class AuthorizationException extends Exception {

    private static final long serialVersionUID = 5552708764085069346L;

    /**
     * 
     */
    public AuthorizationException() {
    }

    /**
     * @param message
     */
    public AuthorizationException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public AuthorizationException(Throwable cause) {
        super(cause);
    }
}
