package com.clinics.core.impl.hib;

import static junit.framework.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.clinics.core.api.util.EntityUtils;

@ContextConfiguration
public class HibernateClinicTests extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    protected HibernateClinic clinic;

    @Test
    public void getPetTypes() {
        final Collection<Object> petTypes = this.clinic.findOwners("aa");
        assertEquals("JDBC query must show the same number of pet types", super.countRowsInTable("TYPES"),
                petTypes.size());
        final Object t1 = EntityUtils.getById(petTypes, Object.class, 1);
        assertEquals("cat", t1.toString());
        final Object t4 = EntityUtils.getById(petTypes, Object.class, 4);
        assertEquals("snake", t4.toString());
    }

}
