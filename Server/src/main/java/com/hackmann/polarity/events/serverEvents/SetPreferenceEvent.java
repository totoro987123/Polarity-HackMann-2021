package com.hackmann.polarity.events.serverEvents;

import com.hackmann.polarity.events.Event;
import com.hackmann.polarity.user.User;

public class SetPreferenceEvent implements Event {

    private String className;
    private String topic;
    private int preference;

    public SetPreferenceEvent(String className, String topic, int preference){
        this.className = className;
        this.topic = topic;
        this.preference = preference;
    }

    @Override
    public void run(User user) {
        user.setPreference(this.topic, this.preference);
    }
}
