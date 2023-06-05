package com.br.myvote.myvote.business.service;

import com.br.myvote.myvote.business.dto.VoteDTO;
import com.br.myvote.myvote.business.dto.VoteSessionDTO;
import com.br.myvote.myvote.business.fixture.AssociateFixture;
import com.br.myvote.myvote.business.fixture.VoteFixture;
import com.br.myvote.myvote.business.service.impl.VoteServiceImpl;
import com.br.myvote.myvote.data.entity.Associate;
import com.br.myvote.myvote.data.entity.Vote;
import com.br.myvote.myvote.data.repository.AssociateRepository;
import com.br.myvote.myvote.data.repository.VoteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class VoteServiceTest {
    @Mock
    private VoteRepository voteRepository;

    @Mock
    private VoteSessionService voteSessionService;

    @Mock
    private AssociateRepository associateRepository;

    @InjectMocks
    private VoteServiceImpl voteService;



    @Test
    public void testCreateVote() {
        VoteDTO voteDTO =  VoteFixture.createVoteDTO();
        VoteSessionDTO voteSessionDTO = VoteFixture.createVoteSessionDTO();
        Associate associate = AssociateFixture.createAssociate();

        when(voteRepository.findByAssociateCpf(any())).thenReturn(Optional.empty());
        when(voteSessionService.findById(voteDTO.voteSession().getId())).thenReturn(voteSessionDTO);
        when(associateRepository.findByCpf(any())).thenReturn(associate);

        Vote vote = new Vote(voteDTO);

        when(voteRepository.save(vote)).thenReturn(vote);

        Vote createdVote = voteService.createVote(voteDTO);

        assertNotNull(createdVote);
        assertEquals(createdVote.getAssociate().getCpf(), "123");
    }



    @Test
    public void testCreateVoteAlreadyVoted() {
        VoteDTO voteDTO = VoteFixture.createVoteDTO();
        VoteSessionDTO voteSessionDTO = VoteFixture.createVoteSessionDTO();
        Associate associate = AssociateFixture.createAssociate();

        when(voteRepository.findByAssociateCpf(any())).thenReturn(Optional.of(new Vote()));
        when(voteSessionService.findById(voteDTO.voteSession().getId())).thenReturn(voteSessionDTO);
        when(associateRepository.findByCpf(any())).thenReturn(associate);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            voteService.createVote(voteDTO);
        });
        assertEquals("You had already voted", exception.getMessage());
    }

    @Test
    public void testValidateSessionExpire() {
        VoteSessionDTO voteSessionDTO = VoteFixture.createVoteSessionExpiredDTO();
        VoteDTO voteDTO = VoteFixture.createVoteDTO();


        when(voteSessionService.findById(1L)).thenReturn(voteSessionDTO);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            voteService.validateSessionExpire(voteDTO);
        });
        assertEquals("Vote Session has expired", exception.getMessage());
    }

    @Test
    public void testAssociateNotFound() {
        VoteDTO voteDTO =  VoteFixture.createVoteDTO();
        VoteSessionDTO voteSessionDTO = VoteFixture.createVoteSessionDTO();

        when(voteRepository.findByAssociateCpf(any())).thenReturn(Optional.empty());
        when(voteSessionService.findById(voteDTO.voteSession().getId())).thenReturn(voteSessionDTO);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            voteService.createVote(voteDTO);
        });

        assertEquals("Associate Not Found", exception.getMessage());
    }
}