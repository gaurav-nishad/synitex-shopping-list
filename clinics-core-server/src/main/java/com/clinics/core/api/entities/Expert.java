/**
 * 
 */
package com.clinics.core.api.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Leonids M<leonidms@gmail.com>
 * 
 */
@Entity(name="Expert")
@DiscriminatorValue(value = "E")
public class Expert extends Person {
    private static final long serialVersionUID = -3020170110983135333L;

    public Expert() {
    }

}