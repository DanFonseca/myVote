package com.br.myvote.myvote.business.service;

import com.br.myvote.myvote.business.dto.AgendaDTO;
import com.br.myvote.myvote.business.excpetion.NotFoundException;
import com.br.myvote.myvote.business.fixture.AgendaFixture;
import com.br.myvote.myvote.business.service.impl.AgendaServiceImpl;
import com.br.myvote.myvote.data.entity.Agenda;
import com.br.myvote.myvote.data.repository.AgendaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class AgendaServiceTest {

    @Mock
    private AgendaRepository agendaRepository;

    @InjectMocks
    private AgendaServiceImpl agendaService;



    @Test
    public void testCreateAgenda() {
        AgendaDTO agendaDTO = AgendaFixture.createAgendaDTO();

        Agenda agenda = AgendaFixture.createAgenda();

        when(agendaRepository.save(any())).thenReturn(agenda);

        Agenda createdAgenda = agendaService.createAgenda(agendaDTO);

        assertNotNull(createdAgenda);
        assertEquals(createdAgenda.getTitle(), "Title Test");
    }

    @Test
    public void testFindAllAgendas() {
        List<Agenda> agendas = new ArrayList<>();


        agendas.add(AgendaFixture.createAgenda());
        agendas.add(AgendaFixture.createAgenda());

        when(agendaRepository.findAll()).thenReturn(agendas);

        List<AgendaDTO> agendaDTOS = agendaService.findAll();

        assertNotNull(agendaDTOS);
        assertEquals(agendaDTOS.size(), 2);
    }

    @Test
    public void testFindByIdNotFound() {
        Exception exception = assertThrows(NotFoundException.class, () -> {
            agendaService.findById(1L);
        });
        assertEquals("Agenda not found", exception.getMessage());
    }

    @Test
    public void testFindById() {
        Agenda agenda = AgendaFixture.createAgenda();

        when(agendaRepository.findById(1L)).thenReturn(Optional.of(agenda));

        AgendaDTO agendaDTO = agendaService.findById(1L);

        assertNotNull(agendaDTO);
        assertEquals(agendaDTO.id(), 1L);
    }
}