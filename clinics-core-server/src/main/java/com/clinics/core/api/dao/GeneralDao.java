package com.clinics.core.api.dao;

import java.util.Collection;
import java.util.List;

public interface GeneralDao<U> {

    /**
     * Performs lookup by attributes name
     * 
     * <pre>
     *  .findByAttributes(&quot;firstName&quot;, &quot;John&quot;, &quot;lastName&quot;, &quot;Doe&quot;)
     * </pre>
     */
    List<U> findByAttributes(Object... attributes);

    U get(Integer id);

    List<U> getAll();

    void store(U entity);

    void storeAll(Collection<U> entity);

}