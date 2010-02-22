/**
 * 
 */
package com.clinics.core.api.services.auth.impl;

import java.util.List;

import javax.persistence.DiscriminatorValue;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.clinics.core.api.dao.PersonDao;
import com.clinics.core.api.entities.Person;
import com.clinics.core.api.services.auth.AuthorizationException;
import com.clinics.core.api.services.auth.AuthorizationService;
import com.clinics.core.api.services.auth.PersonAuthorizationService;
import com.clinics.core.api.services.auth.Role;

/**
 * @author Leonids M<leonidms@gmail.com>
 * 
 */
public class AuthorizationServiceImpl implements AuthorizationService, PersonAuthorizationService {
    private PersonDao personDao;

    public PersonDao getPersonDao() {
        return personDao;
    }

    @Override
    public Role getRole(Person person) throws AuthorizationException {
        Assert.notNull(person, "Person should be specified");
        final Person.RoleDiscriminators discriminator = getDiscriminator(person);
        return discriminatorToRole(discriminator);
    }

    /**
     * {@inhetitedDoc}
     */
    @Override
    public Role getRole(String userIdentity) throws AuthorizationException {
        Assert.notNull(userIdentity, "user id should not be null");
        final Person personById = findPersonById(userIdentity);
        final Person.RoleDiscriminators discriminator = getDiscriminator(personById);
        return discriminatorToRole(discriminator);
    }

    @Override
    public boolean hasRole(Person person, Role role) throws AuthorizationException {
        Assert.notNull(role, "Role must be specified");
        return role.equals(getRole(person));
    }

    /**
     * {@inhetitedDoc}
     */
    @Override
    public boolean hasRole(String userIdentity, Role role) throws AuthorizationException {
        Assert.notNull(role, "Role must be specified");
        return role.equals(getRole(userIdentity));
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    private Role discriminatorToRole(Person.RoleDiscriminators dv) {
        if (dv == null) {
            dv = Person.RoleDiscriminators.U;
        }

        Role role;
        switch (dv) {
        case A:
            role = Role.ADMIN;
            break;
        case C:
            role = Role.CLIENT;
            break;
        case E:
            role = Role.EXPERT;
            break;
        case U:

        default:
            role = Role.ADMIN;
            break;
        }
        return role;
    }

    private Person findPersonById(String userId) throws AuthorizationException {
        List<Person> personsById = null;
        try {
            personsById = getPersonDao().findByAttributes("userId", userId);
        } catch (final DataAccessException dae) {
            throw new AuthorizationException(dae);
        }
        if (CollectionUtils.size(personsById) > 1) {
            throw new AuthorizationException("Only one user with such userId (userId=" + userId
                    + ")should be presented");
        }
        if (CollectionUtils.isEmpty(personsById)) {
            throw new AuthorizationException("Could not find any person with userId=" + userId);
        }

        final Person person = personsById.get(0);
        Assert.notNull(person, "Person should be not null in order to proceed");
        return person;
    }

    private Person.RoleDiscriminators getDiscriminator(Person person) {
        final DiscriminatorValue dv = person.getClass().getAnnotation(DiscriminatorValue.class);
        Assert.notNull(dv, "Disriminator value should be present");
        Assert.hasText(dv.value(), "Disriminator value should be present");
        return Person.RoleDiscriminators.valueOf(dv.value());
    }

}
