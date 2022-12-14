package com.javachinna.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@NoArgsConstructor
@Getter
@Setter

public class Referentiel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRef;
    private String code;
    private String Intitule;

    @JsonIgnore
    @OneToOne
    private Domain domain;
    @JsonIgnore
    @OneToOne
    private Question question;


}
