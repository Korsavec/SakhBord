package com.sakhbord.bord.models.restrictions;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "model_setting")
public class Settings implements Serializable {

    @Serial
    private static final long serialVersionUID = 6824271619233823012L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    // Это лимит на количество добавленных объявлений
    @Column(name = "name", length = 50)
    private String name;


    // Это лимит на количество добавленных объявлений
    @Column(name = "value", length = 50)
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Settings settings)) return false;

        if (!getId().equals(settings.getId())) return false;
        if (!getName().equals(settings.getName())) return false;
        return getValue().equals(settings.getValue());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getValue().hashCode();
        return result;
    }
}
