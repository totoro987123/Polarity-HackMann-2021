package com.hackmann.polarity.events.serverEvents;

import com.hackmann.polarity.chat.ChatRoom;
import com.hackmann.polarity.events.Event;
import com.hackmann.polarity.events.clientEvents.ReceiveMessageEvent;
import com.hackmann.polarity.user.User;

public class SendMessageEvent implements Event {

    private String className;
    private String message;

    public SendMessageEvent(String className, String message) {
        this.className = className;
        this.message = message;
    }

    @Override
    public void run(User user) {
        ChatRoom chatRoom = user.getChatRoom();

        chatRoom.sendMessage(new ReceiveMessageEvent(user.getUsername(), this.message), user);
    }
}
