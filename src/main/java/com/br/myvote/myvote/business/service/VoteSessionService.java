package com.br.myvote.myvote.business.service;

import com.br.myvote.myvote.business.dto.VoteSessionDTO;
import com.br.myvote.myvote.data.entity.VoteSession;

import java.util.List;

public interface VoteSessionService {
     VoteSession createVoteSession(VoteSessionDTO voteSessionDTO);
     List<VoteSessionDTO> findAll ();
     VoteSessionDTO findById(Long id);
}
