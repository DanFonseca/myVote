package com.br.myvote.myvote.business.service.impl;

import com.br.myvote.myvote.business.dto.AssociateDTO;
import com.br.myvote.myvote.business.excpetion.NotFoundException;
import com.br.myvote.myvote.business.service.AssociateService;
import com.br.myvote.myvote.data.entity.Associate;
import com.br.myvote.myvote.data.repository.AssociateRepository;
import com.br.myvote.myvote.web.thirdyparty.config.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociateServiceImpl implements AssociateService {

    Logger logger = LoggerFactory.getLogger(AssociateServiceImpl.class);
    private final AssociateRepository associateRepository;

    private UserInfo userInfo;

    public AssociateServiceImpl(AssociateRepository associateRepository, UserInfo userInfo) {
        this.associateRepository = associateRepository;
        this.userInfo = userInfo;
    }

    public Associate createAssociate(AssociateDTO associateDTO) {

        if (userInfo.validCPF(associateDTO.cpf())) {
            Associate associate = new Associate(associateDTO);
            return associateRepository.save(associate);
        }

        throw  new IllegalArgumentException("CPF is Unable to Vote.");
    }

    public List<AssociateDTO> findAll() {
        return associateRepository.findAll().stream().map(AssociateDTO::new).toList();
    }


    public AssociateDTO findByCpf(String cpf) {
        Optional<Associate> result = associateRepository.findById(cpf);

        if (result.isPresent()) {
            return new AssociateDTO(result.get());
        }

        logger.info("Associate with cpf " + cpf + " was not found");
        throw new NotFoundException("Associate not found");
    }

}
