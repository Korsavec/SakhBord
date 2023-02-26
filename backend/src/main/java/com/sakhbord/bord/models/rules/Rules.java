package com.sakhbord.bord.models.rules;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "model_rules")
@JsonPropertyOrder({ "id", "header", "regulations"})
public class Rules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    // Это заголовок
    @NaturalId
    @Column(name = "header", nullable = false, unique = true, length = 58)
    private String header;


    // Это пароль пользователя
    @Column(name = "regulations", nullable = false, length = 10000)
    private String regulations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getRegulations() {
        return regulations;
    }

    public void setRegulations(String regulations) {
        this.regulations = regulations;
    }
}
