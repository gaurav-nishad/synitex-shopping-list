package com.clinics.core.impl.hib;

import java.util.concurrent.atomic.AtomicLong;

import junit.framework.Assert;

import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.clinics.core.api.dao.PersonDao;
import com.clinics.core.api.entities.Person;
import com.clinics.core.api.entities.Person.Gender;

@ContextConfiguration
public class PersonDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests {
    private class TestTransaction {
        @Transactional(readOnly = false)
        public Person storeInTransaction(Long id, AtomicLong longValue, PersonDao personDao) {
            final Person p = storePerson(id);
            longValue.set(p.getId().longValue());
            throw new RuntimeException("runtime-exception");
        }
    }

    @Autowired
    protected TransactionTemplate transactionTemplate;

    @Autowired
    protected PersonDao personDao;

    @Test
    @NotTransactional
    public void testBusinessLogicsPerformsRollback() {
        final AtomicLong idValue = new AtomicLong(-1);
        try {
            transactionTemplate.execute(new TransactionCallback() {
                @Override
                public Object doInTransaction(TransactionStatus status) {
                    new TestTransaction().storeInTransaction(2L, idValue, personDao);
                    return null;
                }
            });
            Assert.fail("Should fail with Runtime exception");
        } catch (final RuntimeException e) {
            try {
                transactionTemplate.execute(new TransactionCallback() {
                    @Override
                    public Object doInTransaction(TransactionStatus status) {
                        Assert.assertNotNull(personDao.get(idValue.get()));
                        Assert.assertEquals("John2", personDao.get(idValue.get()).getFirstName());
                        return null;
                    }
                });

                Assert.fail("Should fail with ObjectNotFoundException exception");
            } catch (final ObjectNotFoundException onf) {
                // expected
            }
        }
    }

    @Test
    public void testStoreTransactional() {
        final Person p = (Person) transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                return storePerson(1L);
            }
        });

        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                Assert.assertNotNull(personDao.get(p.getId()));
                Assert.assertEquals("John1", personDao.get(p.getId()).getFirstName());
                return null;
            }
        });
    }

    private Person storePerson(Long id) {
        final Person p = new Person();
        p.setGender(Gender.M);
        p.setFirstName("John" + id);
        p.setLastName("Doe" + id);
        p.setMiddleName("none" + id);

        return personDao.store(p);
    }

}
