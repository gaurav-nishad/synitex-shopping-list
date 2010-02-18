package com.clinics.core.api.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base class for oll the entities in the open clinics core project
 * 
 * @author Leonids M<leonidms@gmail.com>
 * 
 */
@MappedSuperclass
public class OcEntity implements java.io.Serializable {
    private static final long serialVersionUID = -6305420200927141395L;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Id
    @Column(name = "ID", insertable = true, updatable = true)
    private Long id;

    public Long getId() {
        return id;
    }
}