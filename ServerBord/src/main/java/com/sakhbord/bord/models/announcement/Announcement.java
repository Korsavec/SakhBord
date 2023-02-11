package com.sakhbord.bord.models.announcement;

import com.sakhbord.bord.models.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "model_announcement")
public class Announcement implements Serializable {


    @Serial
    private static final long serialVersionUID = -997853914176842258L;

    // Это ID объявления
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_b_announcement")
    @SequenceGenerator(name = "seq_b_announcement", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;


    // Это текст объявление
    @Column(name = "message", nullable = false, length = 355)
    private String message;


    // Это номер телефона пользователя который разместил объявление
    @NaturalId
    @Column(name = "phone", nullable = false, unique = true, length = 10)
    private Long phone;


    // Это email пользователя, который разместил объявление
    @NaturalId
    @Column(name = "email", nullable = false, unique = true, length = 58)
    private String email;


    // Это telegram пользователя, который разместил объявление
    @NaturalId
    @Column(name = "telegram", nullable = false, unique = true, length = 58)
    private String telegram;


    // Это дата создания объявления
    @Column(name = "date_created_product", nullable = false)
    private Instant dateCreatedAnnouncement;



    // Это включено или выключено объявление
    @Column(name = "enabled", nullable = false, length = 1)
    private boolean enabled;


    // Это ip адрес с которого было размещено объявление
    @Column(name = "ip_address_registration", nullable = false, length = 39)
    private String ipAddressRegistration;




    // Это таблица связей ManyToMany Product и User
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "join_announcement_and_user",
            joinColumns = @JoinColumn(name = "announcement_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"))
    private Set<User> users = new LinkedHashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public Instant getDateCreatedAnnouncement() {
        return dateCreatedAnnouncement;
    }

    public void setDateCreatedAnnouncement(Instant dateCreatedAnnouncement) {
        this.dateCreatedAnnouncement = dateCreatedAnnouncement;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getIpAddressRegistration() {
        return ipAddressRegistration;
    }

    public void setIpAddressRegistration(String ipAddressRegistration) {
        this.ipAddressRegistration = ipAddressRegistration;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Announcement that)) return false;

        if (isEnabled() != that.isEnabled()) return false;
        if (!getId().equals(that.getId())) return false;
        if (!getMessage().equals(that.getMessage())) return false;
        if (!getPhone().equals(that.getPhone())) return false;
        if (!getEmail().equals(that.getEmail())) return false;
        if (!getTelegram().equals(that.getTelegram())) return false;
        if (!getDateCreatedAnnouncement().equals(that.getDateCreatedAnnouncement())) return false;
        if (!getIpAddressRegistration().equals(that.getIpAddressRegistration())) return false;
        return getUsers().equals(that.getUsers());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getMessage().hashCode();
        result = 31 * result + getPhone().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getTelegram().hashCode();
        result = 31 * result + getDateCreatedAnnouncement().hashCode();
        result = 31 * result + (isEnabled() ? 1 : 0);
        result = 31 * result + getIpAddressRegistration().hashCode();
        result = 31 * result + getUsers().hashCode();
        return result;
    }
}