package com.hackmann.polarity.chat;

import com.hackmann.polarity.events.Event;
import com.hackmann.polarity.events.clientEvents.JoinedRoomEvent;
import com.hackmann.polarity.events.clientEvents.RoomClosedEvent;
import com.hackmann.polarity.user.User;
import org.apache.logging.log4j.message.Message;

import java.util.ArrayList;

public class ChatRoom {

    private ArrayList<User> members = new ArrayList<>();
    private String topic;
    private boolean open = true;

    public ChatRoom(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return this.topic;
    }

    public void join(User user) {
        if (this.open) {
            System.out.println("Added user.");
            this.members.add(user);
            user.getConnection().send(new JoinedRoomEvent());
        }
    }

    public void sendMessage(Event content, User sender) {
        System.out.println(this.members.size());
        for (User user : this.members) {
            System.out.println("member");
            if (user != sender) {
                System.out.println("non-sender member! Sent!");
                user.getConnection().send(content);
            }
        }
    }

    public void shutdown() {
        for (User user : this.members) {
            user.getConnection().send(new RoomClosedEvent());
        }
        this.members.clear();
        this.open = false;
    }
}
