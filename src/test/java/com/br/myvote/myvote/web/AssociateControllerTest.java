package com.br.myvote.myvote.web;

import com.br.myvote.myvote.business.dto.AssociateDTO;
import com.br.myvote.myvote.business.fixture.AssociateFixture;
import com.br.myvote.myvote.business.service.AssociateService;
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
class AssociateControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssociateService associateService;

    @Test
    public void testGetAllAssociates() throws Exception {
        List<AssociateDTO> associateDTOS = new ArrayList<>();
        AssociateDTO associateDTO = AssociateFixture.createAssociateDTO();
        associateDTOS.add(associateDTO);
        associateDTOS.add(associateDTO);

        when(associateService.findAll()).thenReturn(associateDTOS);

        mockMvc.perform(get("/associate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetAssociateByCpf() throws Exception {
        AssociateDTO associateDTO = AssociateFixture.createAssociateDTO();


        when(associateService.findByCpf("12345678")).thenReturn(associateDTO);

        mockMvc.perform(get("/associate/12345678")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf", is("12345678")));
    }

    @Test
    public void testCreateAssociate() throws Exception {

        AssociateDTO associateDTO = AssociateFixture.createAssociateDTO();

        when(associateService.createAssociate(associateDTO)).thenReturn(AssociateFixture.createAssociate());

        mockMvc.perform(post("/associate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(associateDTO)))
                .andExpect(status().isOk());

        verify(associateService, times(1)).createAssociate(associateDTO);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}