package com.sakhbord.bord.models.role;

import com.sakhbord.bord.enam.RoleEnum;
import com.sakhbord.bord.models.admin.Admin;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "role_admin")
public class RoleAdmin implements Serializable {

    @Serial
    private static final long serialVersionUID = 8487421284034181602L;

    // Это ID Роле
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role_admin")
    @SequenceGenerator(name = "seq_role_admin", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    // Ссылка на сущность пользователя PrivateSeller
    @ManyToMany(mappedBy = "adminRoles", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Admin> privateAdmin = new LinkedHashSet<>();



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

    public Set<Admin> getPrivateAdmin() {
        return privateAdmin;
    }

    public void setPrivateAdmin(Set<Admin> privateAdmin) {
        this.privateAdmin = privateAdmin;
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
        if (!(o instanceof RoleAdmin roleAdmin)) return false;

        if (!getId().equals(roleAdmin.getId())) return false;
        if (!getPrivateAdmin().equals(roleAdmin.getPrivateAdmin())) return false;
        return getRoleEnum() == roleAdmin.getRoleEnum();
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getPrivateAdmin().hashCode();
        result = 31 * result + getRoleEnum().hashCode();
        return result;
    }
}
