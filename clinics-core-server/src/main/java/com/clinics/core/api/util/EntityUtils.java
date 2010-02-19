package com.clinics.core.api.util;

import java.util.Collection;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.clinics.core.api.entities.OcEntity;

/**
 * Utility methods for handling entities. Separate from the BaseEntity class mainly because of dependency on the
 * ORM-associated ObjectRetrievalFailureException.
 * 
 * @see OcEntity
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
    public static <T extends OcEntity> T getById(Collection<T> entities, Class<? extends T> entityClass, int entityId)
            throws ObjectRetrievalFailureException {
        for (final T entity : entities) {
            if (entity == null) {
                continue;
            }

            final Number numberId = entity.getId();
            if (numberId != null && numberId.intValue() == entityId //
                    && entityClass.isInstance(entity)) {
                return entity;
            }
        }

        throw new ObjectRetrievalFailureException(entityClass, Integer.valueOf(entityId));
    }

}
