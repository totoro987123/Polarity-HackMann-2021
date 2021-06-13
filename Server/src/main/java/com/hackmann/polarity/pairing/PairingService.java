package com.hackmann.polarity.pairing;

import com.hackmann.polarity.user.User;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
@EnableScheduling
public class PairingService {

    private HashMap<String, Queue> queues = new HashMap<>();

    public PairingService() { }

    public Queue makeNewQueue(String topicName) {
        Queue queue = new Queue(topicName);
        this.queues.put(topicName, queue);
        return queue;
    }

    public Queue getQueue(String topicName) {
        Queue queue = this.queues.get(topicName);

        return queue != null ? queue : this.makeNewQueue(topicName);
    }

    public void removeFromQueues(User user) {
        this.queues.forEach((k, queue) -> {
            queue.removeUserFromQueue(user);
        });
    }

    @Scheduled(fixedDelay = 1000)
    public void updateQueues() {
        this.queues.forEach((k, queue) -> {
            queue.updateQueue();
        });
    }
}
