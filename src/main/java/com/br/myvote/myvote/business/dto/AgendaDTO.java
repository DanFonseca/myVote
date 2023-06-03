package com.br.myvote.myvote.business.dto;

import com.br.myvote.myvote.data.entity.Agenda;

public record AgendaDTO(Long id,
                        String title,
                        String description
) {
    public AgendaDTO(Agenda agenda) {
        this(agenda.getId(), agenda.getTitle(), agenda.getDescription());
    }

    public void validate() {
        if (title == null) {
            throw new IllegalArgumentException("Title must not be null");
        }

        if (description == null) {
            throw new IllegalArgumentException("Description must not be null");
        }
    }
}
