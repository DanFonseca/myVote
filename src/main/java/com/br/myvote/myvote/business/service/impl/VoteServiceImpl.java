package com.br.myvote.myvote.business.service.impl;


import com.br.myvote.myvote.business.dto.VoteDTO;
import com.br.myvote.myvote.business.dto.VoteSessionDTO;
import com.br.myvote.myvote.business.excpetion.NotFoundException;
import com.br.myvote.myvote.business.service.VoteService;
import com.br.myvote.myvote.business.service.VoteSessionService;
import com.br.myvote.myvote.business.utils.CalcUtil;
import com.br.myvote.myvote.data.entity.Vote;
import com.br.myvote.myvote.data.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {

    Logger logger = LoggerFactory.getLogger(VoteServiceImpl.class);
    private final VoteRepository voteRepository;

    private final VoteSessionService voteSessionService;

    public VoteServiceImpl(VoteRepository voteRepository, VoteSessionService voteSessionService) {
        this.voteRepository = voteRepository;
        this.voteSessionService = voteSessionService;
    }

    public Vote createVote(VoteDTO voteDTO) {
        Vote vote = new Vote(voteDTO);
        validateSessionExpire(voteDTO);

        if(voteRepository.findByAssociateCpf(vote.getAssociate().getCpf()).isPresent()){
            throw new IllegalArgumentException("You had already voted");
        }

        return voteRepository.save(vote);
    }

    public void validateSessionExpire(VoteDTO voteDTO) {
        VoteSessionDTO voteSessionDTO  = voteSessionService.findById(voteDTO.voteSession().getId());
        if (CalcUtil.voteSessionIsExpired(voteSessionDTO)){
            logger.info("Vote Session has expired");
            throw new IllegalArgumentException("Vote Session has expired");
        }
    }

    public List<VoteDTO> findAll() {
        return voteRepository.findAll().stream().map(VoteDTO::new).toList();
    }

    public VoteDTO findById(Long id) {
        Optional<Vote> result = voteRepository.findById(id);

        if (result.isPresent()) {
            return new VoteDTO(result.get());
        }

        logger.info("Vote with id " + id + " was not found");
        throw new NotFoundException("Vote not found");
    }


    public List<Vote> findByVoteSessionId(Long id) {
        return voteRepository.findByVoteSessionId(id);
    }

    public Map<String, Integer> result(Long sessionId) {
        List<Vote> votes = voteRepository.findByVoteSessionId(sessionId);
        return CalcUtil.calcVotes(votes, sessionId);
    }

}
