package com.br.myvote.myvote.business.service.impl;


import com.br.myvote.myvote.business.dto.AgendaDTO;
import com.br.myvote.myvote.business.excpetion.NotFoundException;
import com.br.myvote.myvote.business.service.AgendaService;
import com.br.myvote.myvote.data.entity.Agenda;
import com.br.myvote.myvote.data.repository.AgendaRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaServiceImpl implements AgendaService {

    Logger logger = LoggerFactory.getLogger(AgendaServiceImpl.class);

    private final AgendaRepository agendaRepository;
    public AgendaServiceImpl( AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public Agenda createAgenda (AgendaDTO agendaDTO) {
       Agenda agenda = new Agenda(agendaDTO);
        return agendaRepository.save(agenda);
    }

    public List<AgendaDTO> findAll () {
        return agendaRepository.findAll().stream().map(AgendaDTO::new).toList();
    }

    public AgendaDTO findById (Long id) {
        Optional<Agenda> result = agendaRepository.findById(id);

        if(result.isPresent()){
            return new AgendaDTO(result.get());
        }

        logger.info("Agenda with id " + id + " was not found");
        throw new NotFoundException("Agenda not found");
    }
}
