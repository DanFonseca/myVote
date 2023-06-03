package com.br.myvote.myvote.business.service.impl;


import com.br.myvote.myvote.business.dto.VoteSessionDTO;
import com.br.myvote.myvote.business.service.VoteSessionService;
import com.br.myvote.myvote.data.entity.VoteSession;
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

    public VoteSession createVoteSession (VoteSessionDTO voteSessionDTO) {
       VoteSession voteSession = new VoteSession(voteSessionDTO);
        return voteSessionRepository.save(voteSession);
    }

    public List<VoteSessionDTO> findAll () {
        return voteSessionRepository.findAll().stream().map(VoteSessionDTO::new).toList();
    }

    public VoteSessionDTO findById(Long id) {
        Optional<VoteSession> result = voteSessionRepository.findById(id);

        if(result.isPresent()){
            return new VoteSessionDTO(result.get());
        }

        throw new IllegalArgumentException("Vote Session not found");
    }

}
