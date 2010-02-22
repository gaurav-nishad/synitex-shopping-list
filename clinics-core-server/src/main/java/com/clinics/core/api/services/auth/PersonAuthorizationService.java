/**
 * 
 */
package com.clinics.core.api.services.auth;

import com.clinics.core.api.entities.Person;

/**
 * @author Leonids M<leonidms@gmail.com>
 * 
 */
public interface PersonAuthorizationService {
    Role getRole(Person person) throws AuthorizationException;

    boolean hasRole(Person person, Role role) throws AuthorizationException;
}
