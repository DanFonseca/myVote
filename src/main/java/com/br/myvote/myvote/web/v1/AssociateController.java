package com.br.myvote.myvote.web.v1;

import com.br.myvote.myvote.business.dto.AssociateDTO;
import com.br.myvote.myvote.business.service.AssociateService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/associate/v1")
public class AssociateController {

    private final AssociateService associateService;

    public AssociateController (AssociateService associateService){
        this.associateService = associateService;
    }


    @ResponseBody()
    @GetMapping
    public List<AssociateDTO> getAssociate(){
        return associateService.findAll();
    }

    @ResponseBody()
    @GetMapping( "/{cpf}")
    public AssociateDTO getAssociateByCpf(@PathVariable("cpf") String cpf){
        return associateService.findByCpf(cpf);
    }

    @ResponseBody
    @PostMapping
    public void postAssociate (@RequestBody AssociateDTO associateDTO) {
         associateService.createAssociate(associateDTO);
    }
}
