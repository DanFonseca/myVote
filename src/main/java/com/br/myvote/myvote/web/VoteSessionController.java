package com.br.myvote.myvote.web;

import com.br.myvote.myvote.business.dto.SessionDTO;
import com.br.myvote.myvote.business.service.VoteSessionService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vote-session")
public class VoteSessionController {

    private final VoteSessionService voteSessionService;

    public VoteSessionController(VoteSessionService voteSessionService){
        this.voteSessionService = voteSessionService;
    }


    @ResponseBody()
    @GetMapping
    public List<SessionDTO> getAssociate(){
        return voteSessionService.findAll();
    }

    @ResponseBody()
    @GetMapping( "/{id}")
    public SessionDTO getAssociateByCpf(@PathParam("id") Long id){
        return voteSessionService.findById(id);
    }

    @ResponseBody
    @PostMapping
    public void postAssociate (@RequestBody SessionDTO associateDTO) {
        voteSessionService.createVoteSession(associateDTO);
    }
}
