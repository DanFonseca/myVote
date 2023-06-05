package com.br.myvote.myvote.business.utils;

import com.br.myvote.myvote.business.dto.VoteSessionDTO;
import com.br.myvote.myvote.data.entity.Vote;
import com.br.myvote.myvote.data.entity.VoteSession;

import java.sql.Timestamp;
import java.util.*;

public class CalcUtil {

    public static Map<String, Integer> calcVotes(List<Vote> votes, Long id) {
        int sim = 0;
        int nao = 0;
        for (Vote vote : votes) {
            if (vote.getVote().equalsIgnoreCase("sim")) {
                sim++;
            }
            if (vote.getVote().equalsIgnoreCase("nao")) {
                nao++;
            }
        }

        Map<String, Integer> result = new HashMap<>();
        result.put("Totais de sim: ", sim);
        result.put("Totais de não: ", nao);
        result.put("Id da sessão de votação: ", Integer.parseInt(id.toString()));

        return result;
    }

    public static boolean voteSessionIsExpired(VoteSessionDTO voteSessionDTO) {
        Calendar calender = Calendar.getInstance();
        calender.setTimeInMillis(voteSessionDTO.createdAt().getTime());
        calender.add(Calendar.MINUTE, voteSessionDTO.minutesToExpire());
        long newTimeStamp = calender.getTimeInMillis();

        Date voteSessionExpireTime = new Date(newTimeStamp);
        Date now = new Date();

        return now.after(voteSessionExpireTime);
    }
}
