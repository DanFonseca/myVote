package com.br.myvote.myvote.data.entity;

import com.br.myvote.myvote.business.dto.AgendaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.List;

@Table(name = "agenda")
@Entity(name = "agenda")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Agenda {
    @Id
    // @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    @OneToMany()
    private List<Session> session;

    public Agenda(AgendaDTO agendaDTO) {
        agendaDTO.validate();

        title = agendaDTO.title();
        description = agendaDTO.description();

    }
}
