package com.br.myvote.myvote.web;

import com.br.myvote.myvote.business.dto.VoteDTO;
import com.br.myvote.myvote.business.fixture.VoteFixture;
import com.br.myvote.myvote.business.service.VoteService;
import com.br.myvote.myvote.data.entity.Vote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoteService voteService;

    @Test
    public void testGetAllVotes() throws Exception {
        List<VoteDTO> voteDTOS = new ArrayList<>();
        VoteDTO voteDTO = VoteFixture.createVoteDTO();

        voteDTOS.add(voteDTO);
        voteDTOS.add(voteDTO);

        when(voteService.findAll()).thenReturn(voteDTOS);

        mockMvc.perform(get("/vote")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetVoteById() throws Exception {
        VoteDTO voteDTO = VoteFixture.createVoteDTO();

        when(voteService.findById(1L)).thenReturn(voteDTO);

        mockMvc.perform(get("/vote/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void testGetVoteBySessionId() throws Exception {
        List<Vote> votes = new ArrayList<>();

        votes.add(new Vote());
        votes.add(new Vote());

        when(voteService.findByVoteSessionId(1L)).thenReturn(votes);

        mockMvc.perform(get("/vote/session-id/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetResult() throws Exception {
        Map<String, Integer> result = new HashMap<>();
        result.put("yes", 10);
        result.put("no", 5);

        when(voteService.result(1L)).thenReturn(result);

        mockMvc.perform(get("/vote/result/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.yes", is(10)))
                .andExpect(jsonPath("$.no", is(5)));
    }

    @Test
    public void testCreateVote() throws Exception {
        VoteDTO voteDTO = VoteFixture.createVoteDTO();

        mockMvc.perform(post("/vote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(voteDTO)))
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