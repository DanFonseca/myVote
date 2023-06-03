package com.br.myvote.myvote.web;

import com.br.myvote.myvote.business.dto.VoteDTO;
import com.br.myvote.myvote.business.service.VoteService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService){
        this.voteService = voteService;
    }


    @ResponseBody()
    @GetMapping
    public List<VoteDTO> getAssociate(){
        return voteService.findAll();
    }

    @ResponseBody()
    @GetMapping( "/{id}")
    public VoteDTO getAssociateByCpf(@PathVariable("id") Long id){
        return voteService.findById(id);
    }

    @ResponseBody
    @PostMapping
    public void postVote (@RequestBody VoteDTO voteDTO) {
        voteService.createVote(voteDTO);
    }
}
