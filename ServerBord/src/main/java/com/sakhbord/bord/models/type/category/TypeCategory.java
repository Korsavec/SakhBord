package com.sakhbord.bord.models.type.category;

import com.sakhbord.bord.models.announcement.Announcement;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "model_type_category")
public class TypeCategory implements Serializable {


    @Serial
    private static final long serialVersionUID = 1107009640707087735L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_a_type_category")
    @SequenceGenerator(name = "seq_a_type_category", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;



    // Это название категории
    @Column(name = "name", nullable = false, unique = true, length = 30, insertable = false, updatable = false)
    private String name;

    @OneToMany(mappedBy = "typeCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Announcement> announcements = new LinkedHashSet<>();


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

    public Set<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(Set<Announcement> announcements) {
        this.announcements = announcements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeCategory that)) return false;

        if (!getId().equals(that.getId())) return false;
        if (!getName().equals(that.getName())) return false;
        return getAnnouncements().equals(that.getAnnouncements());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAnnouncements().hashCode();
        return result;
    }
}
