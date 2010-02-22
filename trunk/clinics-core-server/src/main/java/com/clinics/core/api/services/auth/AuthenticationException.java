/**
 * 
 */
package com.clinics.core.api.services.auth;

/**
 * @author Leonids M<leonidms@gmail.com>
 * 
 */
public class AuthenticationException extends Exception {

    private static final long serialVersionUID = 5552708764085069346L;

    /**
     * 
     */
    public AuthenticationException() {
    }

    /**
     * @param message
     */
    public AuthenticationException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public AuthenticationException(Throwable cause) {
        super(cause);
    }
}
