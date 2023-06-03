package com.br.myvote.myvote.business.service;

import com.br.myvote.myvote.business.dto.AssociateDTO;
import com.br.myvote.myvote.data.entity.Associate;

import java.util.List;
import java.util.Optional;

public interface AssociateService {
     Associate createAssociate (AssociateDTO associateDTO);
     List<AssociateDTO> findAll ();

     AssociateDTO findByCpf(String cpf);
}
