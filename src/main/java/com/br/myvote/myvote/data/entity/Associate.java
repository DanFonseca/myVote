package com.br.myvote.myvote.data.entity;

import com.br.myvote.myvote.business.dto.AssociateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Table(name = "associate")
@Entity
@Getter
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

    @OneToMany
    private List<Vote> votes;
    public Associate(AssociateDTO associateDTO) {
        associateDTO.validate();

        name = associateDTO.name();
        email = associateDTO.email();
        cpf = associateDTO.cpf();
    }
}