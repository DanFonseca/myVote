package com.br.myvote.myvote.web.messagering;

import com.br.myvote.myvote.business.service.VoteSessionService;
import com.br.myvote.myvote.data.entity.VoteSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SendVoteSessionMessage {
    Logger logger = LoggerFactory.getLogger(SendVoteSessionMessage.class);

    private final VoteSessionService voteSessionService;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic.name.finishedVoteSessionTopicName}")
    private String finishedVoteSessionTopicName;

    public SendVoteSessionMessage(VoteSessionService voteSessionService, KafkaTemplate<String, String> kafkaTemplate){
        this.voteSessionService = voteSessionService;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void notifyVoteSessionIsFinished(VoteSession voteSession, Map<String, Integer> result) {
        logger.info("Seeding Message to topic {}", finishedVoteSessionTopicName);

        voteSession.setWasSentMessage(true);
        voteSessionService.updateVoteSession(voteSession);
        VoteResultMessage voteResultMessage = new VoteResultMessage(voteSession.getId(), result);

        kafkaTemplate.send(finishedVoteSessionTopicName, voteResultMessage.toString());
        logger.info("Notify Completed, message: " + voteResultMessage);
    }
}
