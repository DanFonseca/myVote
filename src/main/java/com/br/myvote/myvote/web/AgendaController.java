package com.br.myvote.myvote.web;

import com.br.myvote.myvote.business.dto.AgendaDTO;
import com.br.myvote.myvote.business.service.AgendaService;
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
    public List<AgendaDTO> getAgenda(){
        return agendaservice.findAll();
    }

    @ResponseBody()
    @GetMapping(value =  "/{id}")
    public AgendaDTO getAgendaById(@PathVariable("id") Long  id){
        return agendaservice.findById(id);
    }

    @ResponseBody
    @PostMapping
    public void postAgenda (@RequestBody AgendaDTO associateDTO) {
         agendaservice.createAgenda(associateDTO);
    }
}
