package org.jinq.sscce.jpa.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * @author <a href="mailto:jaro.fietz@uniscon.de">Jaro Fietz</a>
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class SubClass extends SuperClass {
    private String name;
}
