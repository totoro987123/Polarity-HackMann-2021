package com.hackmann.polarity.events.clientEvents;

import com.hackmann.polarity.events.Event;
import com.hackmann.polarity.user.User;

public class ReceiveMessageEvent implements Event {
    private String className = "ReceiveMessageEvent";
    private String sender;
    private String message;

    public ReceiveMessageEvent(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    @Override
    public void run(User user) {

    }
}
