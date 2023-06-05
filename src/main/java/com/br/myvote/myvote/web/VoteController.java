package com.br.myvote.myvote.web;

import com.br.myvote.myvote.business.dto.VoteDTO;
import com.br.myvote.myvote.business.service.VoteService;
import com.br.myvote.myvote.data.entity.Vote;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }


    @ResponseBody()
    @GetMapping
    public List<VoteDTO> getAssociate() {
        return voteService.findAll();
    }

    @ResponseBody()
    @GetMapping("/{id}")
    public VoteDTO getVoteById(@PathVariable("id") Long id) {
        return voteService.findById(id);
    }

    @ResponseBody()
    @GetMapping("/session-id/{id}")
    public List<Vote> getVoteBySessionId(@PathVariable("id") Long id) {
        return voteService.findByVoteSessionId(id);
    }

    @ResponseBody()
    @GetMapping("/result")
    public Map<String, Integer> getResult(@RequestParam("session-id") Long id) {
        return voteService.result(id);
    }

    @ResponseBody
    @PostMapping
    public void postVote(@RequestBody VoteDTO voteDTO) {
        voteService.createVote(voteDTO);
    }
}
