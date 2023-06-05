package com.br.myvote.myvote.business.dto;

import com.br.myvote.myvote.data.entity.Agenda;
import com.br.myvote.myvote.data.entity.Vote;
import com.br.myvote.myvote.data.entity.VoteSession;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


public record VoteSessionDTO(Long id, Agenda agenda, Date createdAt, Integer minutesToExpire, @JsonIgnore  List<Vote> votes) {
    public VoteSessionDTO(VoteSession voteSession) {
        this(voteSession.getId(), voteSession.getAgenda(),voteSession.getCreatedAt(), voteSession.getMinutesToExpire(), voteSession.getVotes());
    }
}