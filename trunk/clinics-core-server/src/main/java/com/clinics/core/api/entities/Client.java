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
@Entity
@DiscriminatorValue(value = "C")
public class Client extends Person {
    private static final long serialVersionUID = -3020170110983135333L;

    public Client() {
    }

}
