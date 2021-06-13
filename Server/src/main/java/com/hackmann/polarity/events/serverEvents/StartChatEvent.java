package com.hackmann.polarity.events.serverEvents;

import com.hackmann.polarity.events.Event;
import com.hackmann.polarity.user.User;

public class StartChatEvent implements Event {
    private String className;
    private String topic;

    public StartChatEvent(String className, String topic) {
        this.className = className;
        this.topic = topic;
    }

    @Override
    public void run(User user) {
        user.joinQueue(this.topic);
    }
}
