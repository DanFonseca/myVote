package com.br.myvote.myvote.data.repository;

import com.br.myvote.myvote.data.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}