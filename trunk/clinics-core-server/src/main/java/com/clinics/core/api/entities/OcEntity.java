package com.clinics.core.api.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base class for all the entities in the open clinics core project
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

    /**
     * {@inhetitedDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OcEntity other = (OcEntity) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    /**
     * {@inhetitedDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        return result;
    }

}