package com.sakhbord.bord.models.admin;

import com.sakhbord.bord.models.role.RoleAdmin;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "model_admin")
public class Admin implements Serializable {

    @Serial
    private static final long serialVersionUID = 6669976518037084669L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_a_admin")
    @SequenceGenerator(name = "seq_a_admin")
    @Column(name = "id", nullable = false)
    private Long id;

    // Это email администратора
    @NaturalId
    @Column(name = "email", nullable = false, unique = true, length = 58)
    private String email;


    // Это пароль администратора
    @Column(name = "password", nullable = false, length = 65)
    private String password;


    // Установите значение true, если администратор включен
    @Column(name = "enabled", nullable = false, length = 1)
    private boolean enabled;

    // Token для подтверждения email адреса и сброса пароля
    @Column(name = "token", unique = true, length = 45)
    private String token;

    // Установите значение true, если учетная запись не заблокирована
    @Column(name = "account_non_locked", nullable = false, length = 1)
    private boolean accountNonLocked;


    // Это таблица связей ManyToMany PrivatePerson и RoleUser
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "join_admin_and_role_admin",
            joinColumns = @JoinColumn(name = "admin_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_admin_id", referencedColumnName = "id"))
    private Set<RoleAdmin> adminRoles = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Set<RoleAdmin> getAdminRoles() {
        return adminRoles;
    }

    public void setAdminRoles(Set<RoleAdmin> adminRoles) {
        this.adminRoles = adminRoles;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin admin)) return false;

        if (isEnabled() != admin.isEnabled()) return false;
        if (isAccountNonLocked() != admin.isAccountNonLocked()) return false;
        if (!getId().equals(admin.getId())) return false;
        if (!getEmail().equals(admin.getEmail())) return false;
        if (!getPassword().equals(admin.getPassword())) return false;
        if (!getToken().equals(admin.getToken())) return false;
        return getAdminRoles().equals(admin.getAdminRoles());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + (isEnabled() ? 1 : 0);
        result = 31 * result + getToken().hashCode();
        result = 31 * result + (isAccountNonLocked() ? 1 : 0);
        result = 31 * result + getAdminRoles().hashCode();
        return result;
    }
}