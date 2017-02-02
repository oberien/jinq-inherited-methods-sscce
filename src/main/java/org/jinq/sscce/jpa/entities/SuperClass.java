package org.jinq.sscce.jpa.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "Class")
@Data
public abstract class SuperClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private boolean bool;

    public boolean isBoolTrue() {
        return bool;
    }
}
