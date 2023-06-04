package com.br.myvote.myvote.business.service;

import com.br.myvote.myvote.business.dto.AgendaDTO;
import com.br.myvote.myvote.business.excpetion.NotFoundException;
import com.br.myvote.myvote.business.service.impl.AgendaServiceImpl;
import com.br.myvote.myvote.data.entity.Agenda;
import com.br.myvote.myvote.data.repository.AgendaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class AgendaServiceTest {

    @Mock
    private AgendaRepository agendaRepository;

    @InjectMocks
    private AgendaServiceImpl agendaService;

    @Test
    public void testFindByIdNotFound() {
        Exception exception = assertThrows(NotFoundException.class, () -> {
            agendaService.findById(1L);
        });
        assertEquals("Agenda not found", exception.getMessage());
    }
}