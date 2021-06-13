package com.hackmann.polarity.events;

import com.hackmann.polarity.user.User;

public abstract interface Event {

    public abstract void run(User user);
}