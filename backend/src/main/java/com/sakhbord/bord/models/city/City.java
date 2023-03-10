package com.sakhbord.bord.models.city;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;

@Entity
@Table(name = "model_city")
public class City implements Serializable {

    public City(Long id) {
        this.id = id;
    }

    public City() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // Это название города
    @NaturalId
    @Column(name = "name", nullable = false, unique = true, length = 30)
    private String name;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City city)) return false;

        if (!getId().equals(city.getId())) return false;
        return getName().equals(city.getName());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}
