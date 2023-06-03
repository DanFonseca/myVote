package com.br.myvote.myvote.business.dto;

import com.br.myvote.myvote.data.entity.Agenda;
import com.br.myvote.myvote.data.entity.Vote;
import com.br.myvote.myvote.data.entity.Session;

import java.sql.Timestamp;
import java.util.List;


public record SessionDTO(Agenda agenda, Timestamp createdAt, List<Vote> votes) {
    public SessionDTO(Session session) {
        this(session.getAgenda(), session.getCreatedAt(), session.getVotes());
    }
}