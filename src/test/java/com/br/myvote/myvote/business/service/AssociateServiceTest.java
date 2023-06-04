package com.br.myvote.myvote.business.service;

import com.br.myvote.myvote.business.dto.AssociateDTO;
import com.br.myvote.myvote.business.excpetion.NotFoundException;
import com.br.myvote.myvote.business.fixture.AssociateFixture;
import com.br.myvote.myvote.business.service.impl.AgendaServiceImpl;
import com.br.myvote.myvote.business.service.impl.AssociateServiceImpl;
import com.br.myvote.myvote.data.entity.Associate;
import com.br.myvote.myvote.data.repository.AgendaRepository;
import com.br.myvote.myvote.data.repository.AssociateRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AssociateServiceTest {

    @Mock
    private AssociateRepository associateRepository;

    @InjectMocks
    private AssociateServiceImpl associateService;

    @Test
    public void testCreateAssociate() {
        AssociateDTO associateDTO = AssociateFixture.createAssociateDTO();


        Associate associate = AssociateFixture.createAssociate();

        when(associateRepository.save(associate)).thenReturn(associate);

        Associate createdAssociate = associateService.createAssociate(associateDTO);

        assertNotNull(createdAssociate);
        assertEquals(createdAssociate.getName(), "The Associate");
    }

    @Test
    public void testFindAllAssociates() {
        List<Associate> associates = new ArrayList<>();


        associates.add(AssociateFixture.createAssociate());
        associates.add(AssociateFixture.createAssociate());

        when(associateRepository.findAll()).thenReturn(associates);

        List<AssociateDTO> associateDTOS = associateService.findAll();

        assertNotNull(associateDTOS);
        assertEquals(associateDTOS.size(), 2);
    }
    @Test
    public void testFindByIdNotFound() {
        Exception exception = assertThrows(NotFoundException.class, () -> {
            associateService.findByCpf("123");
        });
        assertEquals("Associate not found", exception.getMessage());
    }
}