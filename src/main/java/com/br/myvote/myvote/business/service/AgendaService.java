package com.br.myvote.myvote.business.service;

import com.br.myvote.myvote.business.dto.AgendaDTO;
import com.br.myvote.myvote.data.entity.Agenda;

import java.util.List;
import java.util.Optional;

public interface AgendaService {
    public Agenda createAgenda (AgendaDTO agendaDTO);
    public List<AgendaDTO> findAll ();
    public AgendaDTO findById (Long id);
}
