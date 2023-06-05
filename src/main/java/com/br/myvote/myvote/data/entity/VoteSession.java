package com.br.myvote.myvote.data.entity;

import com.br.myvote.myvote.business.dto.VoteSessionDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static com.br.myvote.myvote.business.dto.MinutesToExpireENUM.DEFAULT_MINUTES_TO_EXPIRE;

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

    @Column(name = "minutes_to_expire")
    Integer minutesToExpire;

    @Column(name = "was_sent_message")
    boolean wasSentMessage;


    @ManyToOne (fetch = FetchType.EAGER)
    private Agenda agenda;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JsonIgnore
    private List<Vote> votes;

    public VoteSession(VoteSessionDTO voteSessionDTO) {
        this.agenda = voteSessionDTO.agenda();
        this.createdAt = new Date();

        if(voteSessionDTO.minutesToExpire() == null){
            this.minutesToExpire = DEFAULT_MINUTES_TO_EXPIRE.getMinutes();
        }else {
            this.minutesToExpire = voteSessionDTO.minutesToExpire();
        }

    }
}
