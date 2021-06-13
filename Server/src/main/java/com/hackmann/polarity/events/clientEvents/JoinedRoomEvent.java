package com.hackmann.polarity.events.clientEvents;

import com.hackmann.polarity.events.Event;
import com.hackmann.polarity.user.User;

public class JoinedRoomEvent implements Event {
    private String className = "JoinedRoomEvent";

    public JoinedRoomEvent() {

    }

    @Override
    public void run(User user) {

    }
}
