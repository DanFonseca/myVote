package com.br.myvote.myvote.business.dto;

import com.br.myvote.myvote.data.entity.Agenda;
import com.br.myvote.myvote.data.entity.Session;

import java.util.List;

public record AgendaDTO(Long id,
                        String title,
                        String description,
                        List<Session> session
) {
    public AgendaDTO (Agenda agenda) {
        this(agenda.getId(), agenda.getTitle(), agenda.getDescription(), agenda.getSession());
    }

    public void validate () {
        if (title == null) {
            throw new IllegalArgumentException("Title must not be null");
        }

        if (description == null) {
            throw new IllegalArgumentException("Description must not be null");
        }
    }
}
