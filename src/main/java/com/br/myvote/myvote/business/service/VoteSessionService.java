package com.br.myvote.myvote.business.service;

import com.br.myvote.myvote.business.dto.SessionDTO;
import com.br.myvote.myvote.data.entity.Session;

import java.util.List;

public interface VoteSessionService {
     Session createVoteSession(SessionDTO sessionDTO);
     List<SessionDTO> findAll ();
     SessionDTO findById(Long id);
}
