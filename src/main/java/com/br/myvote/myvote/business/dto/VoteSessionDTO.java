package com.br.myvote.myvote.business.dto;

import com.br.myvote.myvote.data.entity.Agenda;
import com.br.myvote.myvote.data.entity.Vote;
import com.br.myvote.myvote.data.entity.VoteSession;

import java.sql.Timestamp;
import java.util.List;


public record VoteSessionDTO(Agenda agenda, Timestamp createdAt, List<Vote> votes) {
    public VoteSessionDTO(VoteSession voteSession) {
        this(voteSession.getAgenda(), voteSession.getCreatedAt(), voteSession.getVotes());
    }
}