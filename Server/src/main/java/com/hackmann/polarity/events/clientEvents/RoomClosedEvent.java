package com.hackmann.polarity.events.clientEvents;

import com.hackmann.polarity.events.Event;
import com.hackmann.polarity.user.User;

public class RoomClosedEvent implements Event {
    private String className = "RoomClosedEvent";

    public RoomClosedEvent() {

    }

    @Override
    public void run(User user) {

    }
}
