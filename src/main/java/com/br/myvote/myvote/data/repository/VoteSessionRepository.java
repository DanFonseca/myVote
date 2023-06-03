package com.br.myvote.myvote.data.repository;

import com.br.myvote.myvote.data.entity.VoteSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface VoteSessionRepository extends JpaRepository<VoteSession, Long> {
}
