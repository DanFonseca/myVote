package com.br.myvote.myvote.job;

import com.br.myvote.myvote.business.service.VoteService;
import com.br.myvote.myvote.business.service.VoteSessionService;
import com.br.myvote.myvote.business.utils.CalcUtil;
import com.br.myvote.myvote.data.repository.VoteSessionRepository;
import com.br.myvote.myvote.web.messagering.SendVoteSessionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SendMessageVoteSessionFinishedTask {
    Logger logger = LoggerFactory.getLogger(SendMessageVoteSessionFinishedTask.class);
    private final VoteSessionRepository voteSessionRepository;
    private final SendVoteSessionMessage sendVoteSessionMessage;

    private final VoteService voteService;

    public SendMessageVoteSessionFinishedTask(VoteSessionRepository voteSessionRepository,
                                              SendVoteSessionMessage sendVoteSessionMessage,
                                              VoteService voteService) {
        this.voteSessionRepository = voteSessionRepository;
        this.sendVoteSessionMessage = sendVoteSessionMessage;
        this.voteService = voteService;
    }

    @Scheduled(fixedRateString = "${myVote.job.fixedRate}")
    public void printHelloWorld() {
        logger.info("Initializing SendMessageToKafkaTask");
        logger.info("Initializing Find All Vote Session Repository");

        voteSessionRepository.findAll().forEach(voteSession -> {
            if(CalcUtil.voteSessionIsExpired(voteSession) && !voteSession.isWasSentMessage()){
                Map<String, Integer> result = voteService.result(voteSession.getId());
                sendVoteSessionMessage.notifyVoteSessionIsFinished(voteSession, result);
            }
        });

        logger.info("Job SendMessageVoteSessionFinishedTask Completed =)");
    }
}
