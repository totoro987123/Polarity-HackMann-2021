package com.hackmann.polarity.events.serverEvents;

import com.hackmann.polarity.events.Event;
import com.hackmann.polarity.user.User;

public class LeaveChatEvent implements Event {

    private String className;

    public LeaveChatEvent(String className) {
        this.className = className;
    }

    @Override
    public void run(User user) {
        user.leaveChatRoom();
    }
}
