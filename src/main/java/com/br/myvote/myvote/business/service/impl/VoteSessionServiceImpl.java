package com.br.myvote.myvote.business.service.impl;


import com.br.myvote.myvote.business.dto.AgendaDTO;
import com.br.myvote.myvote.business.dto.VoteSessionDTO;
import com.br.myvote.myvote.business.excpetion.NotFoundException;
import com.br.myvote.myvote.business.service.AgendaService;
import com.br.myvote.myvote.business.service.VoteSessionService;
import com.br.myvote.myvote.data.entity.Agenda;
import com.br.myvote.myvote.data.entity.VoteSession;
import com.br.myvote.myvote.data.repository.AgendaRepository;
import com.br.myvote.myvote.data.repository.VoteSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteSessionServiceImpl implements VoteSessionService {

    Logger logger = LoggerFactory.getLogger(VoteSessionServiceImpl.class);
    private final VoteSessionRepository voteSessionRepository;
    private final AgendaService agendaService;

    public VoteSessionServiceImpl(VoteSessionRepository VoteSessionRepository, AgendaService agendaService) {
        this.voteSessionRepository = VoteSessionRepository;
        this.agendaService = agendaService;
    }

    public VoteSession createVoteSession(VoteSessionDTO voteSessionDTO) {
        agendaService.findById(voteSessionDTO.agenda().getId());

        VoteSession voteSession = new VoteSession(voteSessionDTO);

        return voteSessionRepository.saveAndFlush(voteSession);
    }

    public List<VoteSessionDTO> findAll() {
        return voteSessionRepository.findAll().stream().map(VoteSessionDTO::new).toList();
    }

    public VoteSessionDTO findById(Long id) {
        Optional<VoteSession> result = voteSessionRepository.findById(id);

        if (result.isPresent()) {
            return new VoteSessionDTO(result.get());
        }

        logger.info("Session with id " + id + " was not found");
        throw new NotFoundException("Vote Session not found");
    }

}
