package com.br.myvote.myvote.web.messagering;

import java.util.Map;

record  VoteResultMessage(
        Long voteSessionId,
        Map<String, Integer> result
) {
}
