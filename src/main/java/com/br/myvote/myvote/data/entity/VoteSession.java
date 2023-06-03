package com.br.myvote.myvote.data.entity;

import com.br.myvote.myvote.business.dto.VoteSessionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Table (name = "vote_session")
@Entity (name = "vote_session")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class VoteSession {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @org.springframework.data.annotation.Id
    private Long id;
    @Column(name = "created_at")
    private Timestamp createdAt;


    @ManyToOne (fetch = FetchType.EAGER)
    private Agenda agenda;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Vote> votes;


    public VoteSession(VoteSessionDTO voteSessionDTO) {
        // TODO voteSessionDTO.validate();
        this.agenda = voteSessionDTO.agenda();
        this.createdAt = voteSessionDTO.createdAt();
    }
}
