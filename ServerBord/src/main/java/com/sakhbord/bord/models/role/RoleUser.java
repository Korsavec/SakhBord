package com.sakhbord.bord.models.role;

import com.sakhbord.bord.enam.RoleEnum;
import com.sakhbord.bord.models.user.User;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "role_user")
public class RoleUser implements Serializable {


  @Serial
  private static final long serialVersionUID = -3994861096666567490L;

  // Это ID Роле
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role_user")
  @SequenceGenerator(name = "seq_role_user", allocationSize = 1)
  @Column(name = "id", nullable = false)
  private Long id;

  // Ссылка на сущность пользователя User
  @ManyToMany(mappedBy = "roleUsers", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
  private Set<User> users = new LinkedHashSet<>();

  // Это Enum перечисление ролей.
  @Enumerated(EnumType.STRING)
  @Column(name = "name", nullable = false, unique = true, length = 45, insertable = false, updatable = false)
  private RoleEnum roleEnum;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }

  public RoleEnum getRoleEnum() {
    return roleEnum;
  }

  public void setRoleEnum(RoleEnum roleEnum) {
    this.roleEnum = roleEnum;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RoleUser roleUser)) return false;

    if (!getId().equals(roleUser.getId())) return false;
    return getRoleEnum() == roleUser.getRoleEnum();
  }

  @Override
  public int hashCode() {
    int result = getId().hashCode();
    result = 31 * result + getRoleEnum().hashCode();
    return result;
  }
}