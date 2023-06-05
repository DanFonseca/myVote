package com.br.myvote.myvote.data.entity;

import com.br.myvote.myvote.business.dto.AgendaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Table(name = "agenda")
@Entity(name = "agenda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JsonIgnore
    private List<VoteSession> voteSession;

    public Agenda(AgendaDTO agendaDTO) {
        agendaDTO.validate();
        title = agendaDTO.title();
        description = agendaDTO.description();;
    }
}
