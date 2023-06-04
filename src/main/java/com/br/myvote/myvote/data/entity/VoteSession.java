package com.br.myvote.myvote.data.entity;

import com.br.myvote.myvote.business.dto.VoteSessionDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Table (name = "vote_session")
@Entity (name = "vote_session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class VoteSession {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @org.springframework.data.annotation.Id
    private Long id;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "time_to_expire")
    Integer timeToExpire;


    @ManyToOne (fetch = FetchType.EAGER)
    private Agenda agenda;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vote> votes;

    public VoteSession(VoteSessionDTO voteSessionDTO) {
        // TODO voteSessionDTO.validate();
        this.agenda = voteSessionDTO.agenda();
        this.createdAt = new Date();

        if(voteSessionDTO.timeToExpire() == null){
            this.timeToExpire = 1;
        }else {
            this.timeToExpire = voteSessionDTO.timeToExpire();
        }

    }
}
