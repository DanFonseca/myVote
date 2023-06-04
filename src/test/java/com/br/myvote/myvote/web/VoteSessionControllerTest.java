package com.br.myvote.myvote.web;

import com.br.myvote.myvote.business.dto.VoteSessionDTO;
import com.br.myvote.myvote.business.fixture.VoteFixture;
import com.br.myvote.myvote.business.service.VoteSessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VoteSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoteSessionService sessionService;

    @Test
    public void shouldReturnAllSessions() throws Exception {
        List<VoteSessionDTO> sessions = new ArrayList<>();
        VoteSessionDTO voteSessionDTO = VoteFixture.createVoteSessionDTO();

        sessions.add(voteSessionDTO);
        sessions.add(voteSessionDTO);

        given(sessionService.findAll()).willReturn(sessions);

        mockMvc.perform(get("/vote-session"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldReturnSessionById() throws Exception {
        VoteSessionDTO voteSessionDTO = VoteFixture.createVoteSessionDTO();

        given(sessionService.findById(1L)).willReturn(voteSessionDTO);

        mockMvc.perform(get("/vote-session/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void shouldCreateVoteSession() throws Exception {
        VoteSessionDTO voteSessionDTO = VoteFixture.createVoteSessionDTO();

        mockMvc.perform(post("/vote-session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(voteSessionDTO)))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}