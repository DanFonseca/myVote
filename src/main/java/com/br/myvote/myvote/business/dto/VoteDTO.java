package com.br.myvote.myvote.business.dto;


import com.br.myvote.myvote.data.entity.Associate;
import com.br.myvote.myvote.data.entity.Vote;

public record VoteDTO(Associate associate, VoteEnum voteEnum) {
    public VoteDTO (Vote vote) {
        this(vote.getAssociate(), vote.getVoteEnum());
    }


}
