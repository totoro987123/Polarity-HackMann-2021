package com.hackmann.polarity.events.serverEvents;

import com.hackmann.polarity.events.Event;
import com.hackmann.polarity.events.clientEvents.WelcomeEvent;
import com.hackmann.polarity.user.User;

public class HiEvent implements Event {
    private String className;

    public HiEvent(String className){
        this.className = className;
    }

    @Override
    public void run(User user){
        user.sayHi();
        user.getConnection().send(new WelcomeEvent());
    }
}
