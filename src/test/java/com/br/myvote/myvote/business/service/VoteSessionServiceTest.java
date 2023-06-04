package com.br.myvote.myvote.business.service;

import com.br.myvote.myvote.business.dto.AgendaDTO;
import com.br.myvote.myvote.business.dto.VoteSessionDTO;
import com.br.myvote.myvote.business.excpetion.NotFoundException;
import com.br.myvote.myvote.business.fixture.AgendaFixture;
import com.br.myvote.myvote.business.fixture.VoteFixture;
import com.br.myvote.myvote.business.service.impl.VoteSessionServiceImpl;
import com.br.myvote.myvote.data.entity.Agenda;
import com.br.myvote.myvote.data.entity.VoteSession;
import com.br.myvote.myvote.data.repository.VoteSessionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class VoteSessionServiceTest {
    @Mock
    private VoteSessionRepository voteSessionRepository;

    @Mock
    AgendaService agendaService;

    @InjectMocks
    private VoteSessionServiceImpl voteSessionService;



    @Test
    public void testCreateVoteSession() {

        AgendaDTO agendaDTO =  AgendaFixture.createAgendaDTO();
        Agenda agenda = AgendaFixture.createAgenda();
        VoteSessionDTO voteSessionDTO = VoteFixture.createVoteSessionDTO(agenda);


        when(agendaService.findById(1L)).thenReturn(agendaDTO);

        VoteSession voteSession = new VoteSession(voteSessionDTO);

        when(voteSessionRepository.saveAndFlush(voteSession)).thenReturn(voteSession);

        VoteSession createdVoteSession = voteSessionService.createVoteSession(voteSessionDTO);

        assertNotNull(createdVoteSession);
        assertEquals(createdVoteSession.getAgenda().getId(), 1L);
    }
    @Test
    public void testFindByIdNotFound() {
        when(voteSessionRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            voteSessionService.findById(1L);
        });

        assertEquals("Vote Session not found", exception.getMessage());

    }

    @Test
    public void testFindById() {
        VoteSession voteSession = VoteFixture.createVoteSession();

        when(voteSessionRepository.findById(1L)).thenReturn(Optional.of(voteSession));

        VoteSessionDTO voteSessionDTO = voteSessionService.findById(1L);

        assertNotNull(voteSessionDTO);
        assertEquals(voteSessionDTO.id(), 1L);
    }

    @Test
    public void testFindAll() {
        List<VoteSession> voteSessions = new ArrayList<>();
        voteSessions.add(new VoteSession());
        voteSessions.add(new VoteSession());

        when(voteSessionRepository.findAll()).thenReturn(voteSessions);

        List<VoteSessionDTO> voteSessionDTOS = voteSessionService.findAll();

        assertNotNull(voteSessionDTOS);
        assertEquals(voteSessionDTOS.size(), 2);
    }

    @Test
    public void testCreateVoteSessionAgendaNotFound() {
        VoteSessionDTO voteSessionDTO = VoteFixture.createVoteSessionDTO(AgendaFixture.createAgenda());

        when(agendaService.findById(1L)).thenThrow(new NotFoundException("Agenda not found"));

        Exception exception = assertThrows(NotFoundException.class, () -> {
            voteSessionService.createVoteSession(voteSessionDTO);
        });

        assertEquals("Agenda not found", exception.getMessage());
    }
}