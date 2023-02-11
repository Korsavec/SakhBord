package com.sakhbord.bord.models.user;

import com.sakhbord.bord.models.activation.NotActivatedUser;
import com.sakhbord.bord.models.role.RoleUser;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "model_user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -8836759867097004637L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_a_user")
    @SequenceGenerator(name = "seq_a_user", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;


    // Это email пользователя
    @NaturalId
    @Column(name = "email", nullable = false, unique = true, length = 58)
    private String email;


    // Это пароль пользователя
    @Column(name = "password", nullable = false, length = 65)
    private String password;


    // Установите значение true, если пользователь включен
    @Column(name = "enabled", nullable = false, length = 1)
    private boolean enabled;


    // Token для подтверждения email адреса и сброса пароля
    @Column(name = "token", unique = true, length = 45)
    private String token;


    // Установите значение true, если учетная запись не заблокирована
    @Column(name = "account_non_locked", nullable = false, length = 1)
    private boolean accountNonLocked;


    // Это дата создания учётной записи пользователя
    @Column(name = "date_created_user", nullable = false)
    private Instant dateCreatedUser;


    // Это ip адрес с которого была зарегистрирована учётная запись пользователя
    @Column(name = "ip_address_registration", nullable = false, length = 39)
    private String ipAddressRegistration;



    // Это таблица связей ManyToMany User и RoleUser
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "join_user_and_role_user",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_user_id", referencedColumnName = "id"))
    private Set<RoleUser> roleUsers = new LinkedHashSet<>();


    // Сущность активации аккаунта
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private NotActivatedUser notActivatedUser;


    // Установите значение true, если адрес электронной почты подтверждён
    @Column(name = "confirmation_mmail", nullable = false, length = 1)
    private boolean confirmationEmail;


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

    public Instant getDateCreatedUser() {
        return dateCreatedUser;
    }

    public void setDateCreatedUser(Instant dateCreatedUser) {
        this.dateCreatedUser = dateCreatedUser;
    }

    public String getIpAddressRegistration() {
        return ipAddressRegistration;
    }

    public void setIpAddressRegistration(String ipAddressRegistration) {
        this.ipAddressRegistration = ipAddressRegistration;
    }

    public Set<RoleUser> getRoleUsers() {
        return roleUsers;
    }

    public void setRoleUsers(Set<RoleUser> roleUsers) {
        this.roleUsers = roleUsers;
    }

    public NotActivatedUser getNotActivatedUser() {
        return notActivatedUser;
    }

    public void setNotActivatedUser(NotActivatedUser notActivatedUser) {
        this.notActivatedUser = notActivatedUser;
    }

    public boolean isConfirmationEmail() {
        return confirmationEmail;
    }

    public void setConfirmationEmail(boolean confirmationEmail) {
        this.confirmationEmail = confirmationEmail;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        if (isEnabled() != user.isEnabled()) return false;
        if (isAccountNonLocked() != user.isAccountNonLocked()) return false;
        if (isConfirmationEmail() != user.isConfirmationEmail()) return false;
        if (!getId().equals(user.getId())) return false;
        if (!getEmail().equals(user.getEmail())) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        if (!getToken().equals(user.getToken())) return false;
        if (!getDateCreatedUser().equals(user.getDateCreatedUser())) return false;
        if (!getIpAddressRegistration().equals(user.getIpAddressRegistration())) return false;
        if (!getRoleUsers().equals(user.getRoleUsers())) return false;
        return getNotActivatedUser().equals(user.getNotActivatedUser());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + (isEnabled() ? 1 : 0);
        result = 31 * result + getToken().hashCode();
        result = 31 * result + (isAccountNonLocked() ? 1 : 0);
        result = 31 * result + getDateCreatedUser().hashCode();
        result = 31 * result + getIpAddressRegistration().hashCode();
        result = 31 * result + getRoleUsers().hashCode();
        result = 31 * result + getNotActivatedUser().hashCode();
        result = 31 * result + (isConfirmationEmail() ? 1 : 0);
        return result;
    }
}