package com.br.myvote.myvote.business.service;

import com.br.myvote.myvote.business.excpetion.NotFoundException;
import com.br.myvote.myvote.business.service.impl.AgendaServiceImpl;
import com.br.myvote.myvote.business.service.impl.AssociateServiceImpl;
import com.br.myvote.myvote.data.repository.AgendaRepository;
import com.br.myvote.myvote.data.repository.AssociateRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AssociateServiceTest {

    @Mock
    private AssociateRepository associateRepository;

    @InjectMocks
    private AssociateServiceImpl associateService;
    @Test
    public void testFindByIdNotFound() {
        Exception exception = assertThrows(NotFoundException.class, () -> {
            associateService.findByCpf("123");
        });
        assertEquals("Associate not found", exception.getMessage());
    }
}