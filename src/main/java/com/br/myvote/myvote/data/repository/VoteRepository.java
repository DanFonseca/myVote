package com.br.myvote.myvote.data.repository;

import com.br.myvote.myvote.data.entity.Associate;
import com.br.myvote.myvote.data.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByVoteSessionId (Long id);
    Optional<Vote> findByAssociateCpf(String cpf);
}
