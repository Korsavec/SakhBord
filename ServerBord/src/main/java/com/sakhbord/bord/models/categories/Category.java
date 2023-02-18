package com.sakhbord.bord.models.categories;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "model_category")
public class Category implements Serializable {


    @Serial
    private static final long serialVersionUID = 6324278907888678400L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_a_category")
    @SequenceGenerator(name = "seq_a_category", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;


    // Это название категории
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
        if (!(o instanceof Category category)) return false;

        if (!getId().equals(category.getId())) return false;
        return getName().equals(category.getName());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}
