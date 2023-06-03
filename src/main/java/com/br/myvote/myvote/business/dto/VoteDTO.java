package com.br.myvote.myvote.business.dto;


import com.br.myvote.myvote.data.entity.Associate;
import com.br.myvote.myvote.data.entity.VoteSession;
import com.br.myvote.myvote.data.entity.Vote;

public record VoteDTO(Associate associate, VoteSession voteSession, VoteEnum voteEnum) {
    public VoteDTO (Vote vote) {
        this(vote.getAssociate(), vote.getVoteSession(), vote.getVoteEnum());
    }

}
