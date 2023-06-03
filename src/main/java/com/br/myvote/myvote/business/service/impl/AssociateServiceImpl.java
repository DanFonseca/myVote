package com.br.myvote.myvote.business.service.impl;

import com.br.myvote.myvote.business.dto.AssociateDTO;
import com.br.myvote.myvote.business.service.AssociateService;
import com.br.myvote.myvote.data.entity.Associate;
import com.br.myvote.myvote.data.repository.AssociateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociateServiceImpl implements AssociateService {
    private final AssociateRepository associateRepository;

    public AssociateServiceImpl(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    public Associate createAssociate(AssociateDTO associateDTO) {
        Associate associate = new Associate(associateDTO);
        return associateRepository.save(associate);
    }

    public List<AssociateDTO> findAll() {
        return associateRepository.findAll().stream().map(AssociateDTO::new).toList();
    }


    public AssociateDTO findByCpf(String cpf) {
        Optional<Associate> result = associateRepository.findById(cpf);

        if(result.isPresent()){
            return new AssociateDTO(result.get());
        }

        throw new IllegalArgumentException("Associate not found");
    }

}
