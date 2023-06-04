package com.br.myvote.myvote.data.entity;

import com.br.myvote.myvote.business.dto.AssociateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Table(name = "associate")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cpf")
public class Associate {
    @Id
    private String cpf;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    @OneToMany (cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vote> votes;
    public Associate(AssociateDTO associateDTO) {
        associateDTO.validate();

        name = associateDTO.name();
        email = associateDTO.email();
        cpf = associateDTO.cpf();
    }
}