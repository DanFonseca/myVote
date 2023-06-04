package com.br.myvote.myvote.business.utils;

import com.br.myvote.myvote.business.dto.VoteSessionDTO;
import com.br.myvote.myvote.business.fixture.VoteFixture;
import com.br.myvote.myvote.data.entity.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalcUtilTest {

    @Test
    public void testCalcVotes() {
        List<Vote> votes = new ArrayList<>();
        votes.add(VoteFixture.createVote("sim"));
        votes.add(VoteFixture.createVote("nao"));
        votes.add(VoteFixture.createVote("sim"));

        Map<String, Integer> result = CalcUtil.calcVotes(votes, 1L);

        assertNotNull(result);
        assertEquals(result.get("Totais de sim: ").intValue(), 2);
        assertEquals(result.get("Totais de não: ").intValue(), 1);
        assertEquals(result.get("Id da sessão de votação: ").intValue(), 1);
    }

    @Test
    public void testVoteSessionIsExpired() {
        VoteSessionDTO voteSessionDTO = VoteFixture.createVoteSessionExpiredDTO();


        assertTrue(CalcUtil.voteSessionIsExpired(voteSessionDTO));
    }
}