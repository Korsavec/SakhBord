package com.sakhbord.bord.models.role;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "model_role_user")
public class RoleUser implements Serializable {



  // Это ID Роле
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;


  // Это Enum перечисление ролей.
  @Column(name = "name", nullable = false, unique = true, length = 45, insertable = false, updatable = false)
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
    if (!(o instanceof RoleUser roleUser)) return false;

    if (!getId().equals(roleUser.getId())) return false;
    return getName().equals(roleUser.getName());
  }

  @Override
  public int hashCode() {
    int result = getId().hashCode();
    result = 31 * result + getName().hashCode();
    return result;
  }
}