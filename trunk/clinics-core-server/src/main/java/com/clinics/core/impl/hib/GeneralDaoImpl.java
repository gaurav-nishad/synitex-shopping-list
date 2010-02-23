/**
 * 
 */
package com.clinics.core.impl.hib;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.util.Assert;

import com.clinics.core.api.dao.GeneralDao;
import com.clinics.core.api.entities.OcEntity;

/**
 * @author Leonids M<leonidms@gmail.com>
 * 
 */
public class GeneralDaoImpl<U> implements GeneralDao<U> {
    private static final String DAO_SHOULD_BE_GENERALIZED = "DAO class should be generalized";

    @Autowired
    private SessionFactory sessionFactory;

    private transient Class<? extends OcEntity> calculatedHibernateType = null;

    /**
     * {@inhetitedDoc}
     */
    @Override
    public List<U> findByAttributes(Object... attributes) {
        return fetchEntityByAttributes(getHibernateType(), attributes);
    }

    /**
     * {@inhetitedDoc}
     */
    @Override
    public U get(Long id) {
        return fetchEntity(getHibernateType(), id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<U> getAll() {
        return getCurrentSession().createCriteria(getHibernateType()).list();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * {@inhetitedDoc}
     */
    @Override
    public U store(U entity) {
        return storeEntity(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<U> storeAll(Collection<U> entities) {
        Assert.notEmpty(entities, "Entities collection must contain elements");
        Assert.notNull(entities, "Entities should not contain null elements");

        return (Collection<U>) getCurrentSession().merge(entities);
    }

    private DataAccessException castHibernateException(HibernateException e) {
        return SessionFactoryUtils.convertHibernateAccessException(e);
    }

    @SuppressWarnings("unchecked")
    protected U fetchEntity(Class<? extends OcEntity> hibernateType, Long id) {
        try {
            Assert.notNull(id, "id should not be null");
            Assert.isAssignable(OcEntity.class, hibernateType);
            return (U) getCurrentSession().load(hibernateType, id);
        } catch (final HibernateException e) {
            throw castHibernateException(e);
        }
    }

    @SuppressWarnings("unchecked")
    protected List<U> fetchEntityByAttributes(Class<? extends OcEntity> hibernateType, Object[] attributes) {
        Assert.isAssignable(OcEntity.class, hibernateType);
        Assert.notEmpty(attributes, "attributes must be specified");
        Assert.isTrue(attributes.length % 2 == 0, "each attribute passed should have a value");

        try {
            Criterion lhs = null;
            for (int i = 0; i < attributes.length - 1; i += 2) {
                Assert.isInstanceOf(String.class, attributes[i]);
                Assert.notNull(attributes[i + 1], "attribute[" + i + "] should have a value");
                final Criterion rhs = Restrictions.eq((String) attributes[i], attributes[i + 1]);
                lhs = lhs == null ? rhs : Restrictions.and(lhs, rhs);
            }

            return getCurrentSession().createCriteria(hibernateType).add(lhs).list();
        } catch (final HibernateException e) {
            throw castHibernateException(e);
        }
    };

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Class<? extends OcEntity> getHibernateType() {
        if (calculatedHibernateType == null) {
            synchronized (this) {
                if (calculatedHibernateType == null) {
                    final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
                    Assert.notNull(type, DAO_SHOULD_BE_GENERALIZED);
                    final Type[] typeInfo = type.getActualTypeArguments();
                    Assert.notEmpty(typeInfo, DAO_SHOULD_BE_GENERALIZED);

                    @SuppressWarnings("unchecked")
                    final Class<? extends OcEntity> hibernateType = (Class<? extends OcEntity>) typeInfo[0];
                    Assert.isAssignable(OcEntity.class, hibernateType);
                    calculatedHibernateType = hibernateType;
                }
            }
        }

        return calculatedHibernateType;
    }

    @SuppressWarnings("unchecked")
    protected U storeEntity(U entity) {
        Assert.notNull(entity, "Could not persist null entity");
        try {
            return (U) getCurrentSession().merge(entity);
        } catch (final HibernateException e) {
            throw castHibernateException(e);
        }
    }
}
