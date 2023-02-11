package com.sakhbord.bord.models.activation;

import com.sakhbord.bord.models.user.User;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "not_activated_user")
public class NotActivatedUser implements Serializable {


    @Serial
    private static final long serialVersionUID = 195655790623562467L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_not_activated_user")
    @SequenceGenerator(name = "seq_not_activated_user", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    // Это дата удаления аккаунта с неподтверждённым адресом электронной почты
    @Column(name = "date_deletion_user")
    private Instant dateDeletionUser;

    // Активирован аккаунт или нет
    @Column(name = "active", nullable = false, length = 1)
    private boolean active;

    // Ссылка на сущность пользователя User
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateDeletionUser() {
        return dateDeletionUser;
    }

    public void setDateDeletionUser(Instant dateDeletionUser) {
        this.dateDeletionUser = dateDeletionUser;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotActivatedUser that)) return false;

        if (isActive() != that.isActive()) return false;
        if (!getId().equals(that.getId())) return false;
        if (!getDateDeletionUser().equals(that.getDateDeletionUser())) return false;
        return getUser().equals(that.getUser());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getDateDeletionUser().hashCode();
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + getUser().hashCode();
        return result;
    }
}