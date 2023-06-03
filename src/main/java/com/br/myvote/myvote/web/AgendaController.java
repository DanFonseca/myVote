package com.br.myvote.myvote.web;

import com.br.myvote.myvote.business.dto.AgendaDTO;
import com.br.myvote.myvote.business.service.AgendaService;

import jakarta.websocket.server.PathParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final AgendaService agendaservice;

    public AgendaController(AgendaService agendaservice){
        this.agendaservice = agendaservice;}


    @ResponseBody()
    @GetMapping
    public List<AgendaDTO> getAssociate(){
        return agendaservice.findAll();
    }

    @ResponseBody()
    @GetMapping(value =  "/{id}")
    public AgendaDTO getAssociateById(@PathVariable("id") Long  id){
        return agendaservice.findById(id);
    }

    @ResponseBody
    @PostMapping
    public void postAssociate (@RequestBody AgendaDTO associateDTO) {
         agendaservice.createAgenda(associateDTO);
    }
}
