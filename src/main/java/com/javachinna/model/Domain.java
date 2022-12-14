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

public class Domain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDom;
    private String codeDomaine;
    @JsonIgnore
    @OneToOne(mappedBy = "domain")
    private Categorie categorie;
    @JsonIgnore
    @OneToOne(mappedBy = "domain")
    private Referentiel referentiel;

}
