package com.sakhbord.bord.models.announcement;

import com.sakhbord.bord.models.categories.Category;
import com.sakhbord.bord.models.city.City;
import com.sakhbord.bord.models.type.category.TypeCategory;
import com.sakhbord.bord.models.user.User;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "model_announcement")
public class Announcement implements Serializable {


    @Serial
    private static final long serialVersionUID = -997853914176842258L;

    // Это ID объявления
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_a_announcement")
    @SequenceGenerator(name = "seq_a_announcement", allocationSize = 2)
    @Column(name = "id", nullable = false)
    private Long id;


    // Это текст объявление
    @Column(name = "message", nullable = false, length = 335)
    private String message;


    // Это номер телефона пользователя который разместил объявление
    @Column(name = "phone", length = 10)
    private Long phone;


    // Это email пользователя, который разместил объявление
    @Column(name = "email", length = 58)
    private String email;


    // Это telegram пользователя, который разместил объявление
    @Column(name = "telegram", length = 58)
    private String telegram;


    // Это дата создания объявления
    @Column(name = "date_created_announcement", nullable = false, columnDefinition = "Datetime(6)")
//    @Column(name="balance", columnDefinition = "Datetime(6)", length = 19)
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String dateCreatedAnnouncement;



    // Это включено или выключено объявление
    @Column(name = "enabled", nullable = false, length = 1)
    private boolean enabled;


    // Это ip адрес с которого было размещено объявление
    @Column(name = "ip_address_registration", nullable = false, length = 39)
    private String ipAddressRegistration;

    // Это ссылка на город
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    // Это ссылка на категорию
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Это ссылка на пользователя
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;




    // Это тип объявления - куплю, продам,
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_category_id")
    private TypeCategory typeCategory;

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

    public String getDateCreatedAnnouncement() {
        return dateCreatedAnnouncement;
    }

    public void setDateCreatedAnnouncement(String dateCreatedAnnouncement) {
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TypeCategory getTypeCategory() {
        return typeCategory;
    }

    public void setTypeCategory(TypeCategory typeCategory) {
        this.typeCategory = typeCategory;
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
        if (!getCity().equals(that.getCity())) return false;
        if (!getCategory().equals(that.getCategory())) return false;
        if (!getUser().equals(that.getUser())) return false;
        return getTypeCategory().equals(that.getTypeCategory());
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
        result = 31 * result + getCity().hashCode();
        result = 31 * result + getCategory().hashCode();
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getTypeCategory().hashCode();
        return result;
    }
}