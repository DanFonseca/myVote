package com.br.myvote.myvote.web.v1;

import com.br.myvote.myvote.business.dto.VoteDTO;
import com.br.myvote.myvote.business.service.VoteService;
import com.br.myvote.myvote.data.entity.Vote;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vote/v1")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }


    @ResponseBody()
    @GetMapping
    public List<VoteDTO> geVote() {
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
