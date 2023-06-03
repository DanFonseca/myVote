package com.br.myvote.myvote.data.entity;

import com.br.myvote.myvote.business.dto.SessionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Table (name = "session")
@Entity (name = "session")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @org.springframework.data.annotation.Id
    private Long id;
    @Column(name = "created_at")
    private Timestamp createdAt;


    @ManyToOne
    //@JoinColumn(name = "agenda_id")
    private Agenda agenda;

    @OneToMany()
    private List<Vote> votes;


    public Session(SessionDTO sessionDTO) {
        // TODO voteSessionDTO.validate();
        this.agenda = sessionDTO.agenda();
        this.createdAt = sessionDTO.createdAt();
    }
}
