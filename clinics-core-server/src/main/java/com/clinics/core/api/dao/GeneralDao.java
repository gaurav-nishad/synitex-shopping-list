package com.clinics.core.api.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface GeneralDao<U> {

    /**
     * Performs lookup by attributes name
     * 
     * <pre>
     *  .findByAttributes(&quot;firstName&quot;, &quot;John&quot;, &quot;lastName&quot;, &quot;Doe&quot;)
     * </pre>
     */
    List<U> findByAttributes(Object... attributes) throws DataAccessException;

    U get(Integer id) throws DataAccessException;

    List<U> getAll() throws DataAccessException;

    void store(U entity) throws DataAccessException;

    void storeAll(Collection<U> entity) throws DataAccessException;

}