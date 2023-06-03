package com.br.myvote.myvote.web;

import com.br.myvote.myvote.business.dto.VoteSessionDTO;
import com.br.myvote.myvote.business.service.VoteSessionService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vote-session")
public class VoteSessionController {

    private final VoteSessionService sessionService;

    public VoteSessionController(VoteSessionService sessionService){
        this.sessionService = sessionService;
    }


    @ResponseBody()
    @GetMapping
    public List<VoteSessionDTO> getSession(){
        return sessionService.findAll();
    }

    @ResponseBody()
    @GetMapping( "/{id}")
    public VoteSessionDTO getSessionById(@PathParam("id") Long id){
        return sessionService.findById(id);
    }

    @ResponseBody
    @PostMapping
    public void postSession (@RequestBody VoteSessionDTO associateDTO) {
        sessionService.createVoteSession(associateDTO);
    }
}