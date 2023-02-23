package com.sakhbord.bord.models.type.category;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "model_type_category")
public class TypeCategory implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;



    // Это название категории
    @Column(name = "name", nullable = false, unique = true, length = 30, insertable = false, updatable = false)
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
        if (!(o instanceof TypeCategory that)) return false;

        if (!getId().equals(that.getId())) return false;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}
