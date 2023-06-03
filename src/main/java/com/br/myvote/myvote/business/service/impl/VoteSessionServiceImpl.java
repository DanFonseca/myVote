package com.br.myvote.myvote.business.service.impl;


import com.br.myvote.myvote.business.dto.SessionDTO;
import com.br.myvote.myvote.business.service.VoteSessionService;
import com.br.myvote.myvote.data.entity.Session;
import com.br.myvote.myvote.data.repository.VoteSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteSessionServiceImpl implements VoteSessionService {
    private final VoteSessionRepository voteSessionRepository;
    public VoteSessionServiceImpl(VoteSessionRepository VoteSessionRepository) {
        this.voteSessionRepository = VoteSessionRepository;
    }

    public Session createVoteSession (SessionDTO sessionDTO) {
       Session session = new Session(sessionDTO);
        return voteSessionRepository.save(session);
    }

    public List<SessionDTO> findAll () {
        return voteSessionRepository.findAll().stream().map(SessionDTO::new).toList();
    }

    public SessionDTO findById(Long id) {
        Optional<Session> result = voteSessionRepository.findById(id);

        if(result.isPresent()){
            return new SessionDTO(result.get());
        }

        throw new IllegalArgumentException("Vote Session not found");
    }

}
