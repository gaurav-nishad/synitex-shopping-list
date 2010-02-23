/**
 * 
 */
package com.clinics.core.api.services.auth.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.clinics.core.api.dao.PersonDao;
import com.clinics.core.api.entities.Person;
import com.clinics.core.api.services.auth.Authentication;
import com.clinics.core.api.services.auth.AuthenticationException;
import com.clinics.core.api.services.auth.AuthenticationService;

/**
 * @author Leonids M<leonidms@gmail.com>
 * 
 */
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final String ERROR_MESSAGE_00 = "Could not authenticate such user with passed in credentials";

    private static final String UTF_8 = "UTF-8";

    private PersonDao personDao;

    /**
     * {@inhetitedDoc}
     */
    @Override
    public Authentication authenticate(String identity, byte[] credentials) throws AuthenticationException {
        final Person person = findPersonByIdAndPassword(identity, credentials);
        if (person == null) {
            throw new AuthenticationException(ERROR_MESSAGE_00);
        }

        return new AuthenticationImpl(identity, getDumbToken(person));

    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    private Person findPersonByIdAndPassword(String userId, byte[] password) throws AuthenticationException {
        List<Person> personsById = null;
        String passwordString;
        try {
            passwordString = password == null ? "" : new String(password, UTF_8);
        } catch (final UnsupportedEncodingException e) {
            throw new AuthenticationException(e);
        }

        try {
            personsById = getPersonDao().findByAttributes("userId", userId, "password", passwordString);
        } catch (final DataAccessException dae) {
            throw new AuthenticationException(dae);
        }

        if (CollectionUtils.size(personsById) > 1) {
            throw new AuthenticationException(ERROR_MESSAGE_00 + ". " + "Multiple matching records found.");
        }

        if (CollectionUtils.isEmpty(personsById)) {
            throw new AuthenticationException(ERROR_MESSAGE_00);
        }

        final Person person = personsById.get(0);
        Assert.notNull(person, "Person should not be null");
        return person;
    }

    private byte[] getDumbToken(Person person) throws AuthenticationException {
        try {
            return String.valueOf(1e3 + (long) Math.random() * (1e9 - 1e3)).getBytes(UTF_8);
        } catch (final UnsupportedEncodingException e) {
            throw new AuthenticationException(e);
        }
    }

}
