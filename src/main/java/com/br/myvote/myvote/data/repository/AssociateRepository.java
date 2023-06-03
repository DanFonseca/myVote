package com.br.myvote.myvote.data.repository;

import com.br.myvote.myvote.data.entity.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface AssociateRepository extends JpaRepository<Associate, String> {
    Associate findByCpf(String cpf);
}
