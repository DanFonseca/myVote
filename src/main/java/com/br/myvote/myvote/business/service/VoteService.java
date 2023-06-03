package com.br.myvote.myvote.business.service;

import com.br.myvote.myvote.business.dto.VoteDTO;
import com.br.myvote.myvote.data.entity.Vote;

import java.util.List;
import java.util.Map;

public interface VoteService {
     Vote createVote(VoteDTO voteDTO);
     List<VoteDTO> findAll ();
     VoteDTO findById(Long id);

     List<Vote> findByVoteSessionId (Long id);

      Map<String, Integer> result (Long sessionId);
}
