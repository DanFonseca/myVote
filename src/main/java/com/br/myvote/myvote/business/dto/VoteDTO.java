package com.br.myvote.myvote.business.dto;


import com.br.myvote.myvote.data.entity.Associate;
import com.br.myvote.myvote.data.entity.VoteSession;
import com.br.myvote.myvote.data.entity.Vote;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.CharArrayReader;

public record VoteDTO(Associate associate, VoteSession voteSession, String vote) {
    public VoteDTO(Vote vote) {
        this(vote.getAssociate(), vote.getVoteSession(), vote.getVote());
    }

    public void validate() {
        if (associate == null) {
            throw new IllegalArgumentException("Associate must not be null");
        }

        if (voteSession == null) {
            throw new IllegalArgumentException("Vote Session must not be null");
        }

        if (vote == null) {
            throw new IllegalArgumentException("Vote must not be null");
        }

        try{
            VoteEnum.valueOf(vote.toUpperCase());
        }catch (Exception e){
            throw new IllegalArgumentException("Invalid vote. Vote must be: SIM/NAO");
        }

    }

}
