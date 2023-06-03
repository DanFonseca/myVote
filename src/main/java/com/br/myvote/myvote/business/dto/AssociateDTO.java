package com.br.myvote.myvote.business.dto;

import com.br.myvote.myvote.data.entity.Associate;
import com.br.myvote.myvote.data.entity.Vote;

import java.util.List;

public record AssociateDTO(
        String name,
        String email,
        String cpf,
        List<Vote> votes) {
    public AssociateDTO(Associate associate) {
        this(associate.getName(), associate.getEmail(), associate.getCpf(), associate.getVotes());
    }

    public void validate () {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }

        if (cpf == null) {
            throw new IllegalArgumentException("CPF must not be null");
        }
    }
}