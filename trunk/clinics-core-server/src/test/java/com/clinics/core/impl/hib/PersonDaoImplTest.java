package com.clinics.core.impl.hib;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.clinics.core.api.dao.PersonDao;
import com.clinics.core.api.entities.Person;
import com.clinics.core.api.util.EntityUtils;

@ContextConfiguration
public class PersonDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    protected PersonDao personDao;

    @Test
    public void getPetTypes() {
        final Collection<Person> petTypes = personDao.getAll();
        assertEquals("JDBC query must show the same number of pet types", //
                countRowsInTable("TYPES"), // 
                petTypes.size());

        final Person t1 = EntityUtils.getById(petTypes, Person.class, 1);
        assertNotNull(t1);
    }

}
