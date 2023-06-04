package com.br.myvote.myvote.business.fixture;

import com.br.myvote.myvote.business.dto.VoteDTO;
import com.br.myvote.myvote.business.dto.VoteSessionDTO;
import com.br.myvote.myvote.data.entity.Associate;
import com.br.myvote.myvote.data.entity.VoteSession;

import java.util.Date;

public class VoteFixture {

    public static VoteDTO createVoteDTO() {
        Associate associate = new Associate();
        associate.setName("Dummy");
        associate.setCpf("123");
        associate.setEmail("dummy@dummy");

        return new VoteDTO(
                associate,
                createVoteSession(),
                "Sim"
        );
    }
    public static VoteSession createVoteSession () {
        VoteSession voteSession = new VoteSession();
        voteSession.setId(1L);
        voteSession.setTimeToExpire(1);
        voteSession.setCreatedAt(new Date());

        return voteSession;
    }

    public static VoteSession createVoteSessionExpired () {
        VoteSession voteSession = new VoteSession();
        voteSession.setId(1L);
        voteSession.setTimeToExpire(1);
        voteSession.setCreatedAt(new Date(1388530800000L));

        return voteSession;
    }

    public static VoteSessionDTO createVoteSessionDTO (){
        return new VoteSessionDTO(createVoteSession());
    }

    public static VoteSessionDTO createVoteSessionExpiredDTO (){
        return new VoteSessionDTO(createVoteSessionExpired());
    }
}
