package com.br.myvote.myvote.data.entity;

import com.br.myvote.myvote.business.dto.VoteDTO;
import com.br.myvote.myvote.business.dto.VoteEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "vote")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @org.springframework.data.annotation.Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private VoteEnum voteEnum;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cpf")
    private Associate associate;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "vote_session_id")
    private VoteSession voteSession;

    public Vote (VoteDTO voteDTO) {
        this.associate = voteDTO.associate();
        this.voteSession = voteDTO.voteSession();
        this.voteEnum = voteDTO.voteEnum();
    }
}
