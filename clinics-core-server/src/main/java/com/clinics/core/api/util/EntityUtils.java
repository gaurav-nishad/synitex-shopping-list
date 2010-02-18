package com.clinics.core.api.util;

import java.util.Collection;

import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * Utility methods for handling entities. Separate from the BaseEntity class mainly because of dependency on the
 * ORM-associated ObjectRetrievalFailureException.
 * 
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 29.10.2003
 * @see org.springframework.samples.petclinic.BaseEntity
 */
public abstract class EntityUtils {

    /**
     * Look up the entity of the given class with the given id in the given collection.
     * 
     * @param entities
     *            the collection to search
     * @param entityClass
     *            the entity class to look up
     * @param entityId
     *            the entity id to look up
     * @return the found entity
     * @throws ObjectRetrievalFailureException
     *             if the entity was not found
     */
    public static <T> T getById(Collection<T> entities, Class<? extends T> entityClass, int entityId)
            throws ObjectRetrievalFailureException {
        for (final T entity : entities) {
            if (entity == null) {
                continue;
            }
            Number numberId;
            try {
                numberId = (Number) entity.getClass().getMethod("getId").invoke(entity);
            } catch (final Exception e) {
                throw new ObjectRetrievalFailureException("Coud not obtain Id", e);
            }
            if (numberId != null && numberId.intValue() == entityId && entityClass.isInstance(entity)) {
                return entity;
            }
        }
        throw new ObjectRetrievalFailureException(entityClass, new Integer(entityId));
    }

}
