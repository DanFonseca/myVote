package com.br.myvote.myvote.business.service.impl;


import com.br.myvote.myvote.business.dto.VoteDTO;
import com.br.myvote.myvote.business.service.VoteService;
import com.br.myvote.myvote.data.entity.Vote;
import com.br.myvote.myvote.data.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {

    Logger logger = LoggerFactory.getLogger(VoteServiceImpl.class);
    private final VoteRepository voteRepository;

    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote createVote (VoteDTO voteDTO) {
       Vote vote = new Vote(voteDTO);
        return voteRepository.save(vote);
    }

    public List<VoteDTO> findAll () {
        return voteRepository.findAll().stream().map(VoteDTO::new).toList();
    }

    public VoteDTO findById(Long id) {
        Optional<Vote> result = voteRepository.findById(id);

        if(result.isPresent()){
            return new VoteDTO(result.get());
        }

        logger.info("Vote with id " + id + " was not found");
        throw new IllegalArgumentException("Vote not found");
    }


    public List<Vote> findByVoteSessionId(Long id) {
        return voteRepository.findByVoteSessionId(id);
    }

}
