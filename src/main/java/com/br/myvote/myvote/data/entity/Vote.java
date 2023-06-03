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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cpf")
    private Associate associate;
    @ManyToOne ()
    @JoinColumn(name = "vote_session_id")
    private Session session;

    public Vote (VoteDTO voteDTO) {
        this.associate = voteDTO.associate();
        this.voteEnum = voteDTO.voteEnum();
    }
}
