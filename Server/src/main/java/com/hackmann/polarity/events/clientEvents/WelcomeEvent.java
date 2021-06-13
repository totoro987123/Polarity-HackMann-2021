package com.hackmann.polarity.events.clientEvents;

import com.hackmann.polarity.events.Event;
import com.hackmann.polarity.user.User;

public class WelcomeEvent implements Event {

    private String className = "WelcomeEvent";

    public WelcomeEvent() {

    }

    @Override
    public void run(User user) {

    }
}
