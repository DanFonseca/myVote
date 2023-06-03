package com.br.myvote.myvote.data.repository;

import com.br.myvote.myvote.data.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
