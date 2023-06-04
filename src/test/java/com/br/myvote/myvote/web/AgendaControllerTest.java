package com.br.myvote.myvote.web;

import com.br.myvote.myvote.business.dto.AgendaDTO;
import com.br.myvote.myvote.business.fixture.AgendaFixture;
import com.br.myvote.myvote.business.service.AgendaService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AgendaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgendaService agendaService;

    @Test
    public void testGetAllAgendas() throws Exception {
        List<AgendaDTO> agendaDTOS = new ArrayList<>();
        AgendaDTO agendaDTO = AgendaFixture.createAgendaDTO();
        agendaDTOS.add(agendaDTO);
        agendaDTOS.add(agendaDTO);

        when(agendaService.findAll()).thenReturn(agendaDTOS);

        mockMvc.perform(get("/agenda")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetAgendaById() throws Exception {
        AgendaDTO agendaDTO = AgendaFixture.createAgendaDTO();

        when(agendaService.findById(1L)).thenReturn(agendaDTO);

        mockMvc.perform(get("/agenda/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void testCreateAgenda() throws Exception {
        AgendaDTO agendaDTO = AgendaFixture.createAgendaDTO();

        when(agendaService.createAgenda(any())).thenReturn(AgendaFixture.createAgenda());

        mockMvc.perform(post("/agenda")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(agendaDTO)))
                .andExpect(status().isOk());

        verify(agendaService, times(1)).createAgenda(agendaDTO);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}