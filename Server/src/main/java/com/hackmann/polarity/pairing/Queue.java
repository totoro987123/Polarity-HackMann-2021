package com.hackmann.polarity.pairing;

import com.hackmann.polarity.chat.ChatRoom;
import com.hackmann.polarity.user.User;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;

public class Queue {
    private ArrayList<User> users = new ArrayList<>();
    private String topicName;

    public Queue(String topicName) {
        this.topicName = topicName;
    }

    public void addUserToQueue(User user) {
        this.users.add(user);
    }

    public void removeUserFromQueue(User user) {
        if (this.users.contains(user)) {
            this.users.remove(user);
        }
    }

    public boolean wouldBeGoodMatch(User user1, User user2) {
        int preference1 = user1.getPreference(this.topicName);
        int preference2 = user2.getPreference(this.topicName);

        return Math.abs(preference1 - preference2) >= 2;
    }

    public void updateQueue() {
        for (int i = 0; i < this.users.size(); i++) {
            User primaryUser = this.users.get(i);
            for (int j = i+1; j < this.users.size(); j++) {
                User secondaryUser = this.users.get(j);

                if (this.wouldBeGoodMatch(primaryUser, secondaryUser)) {
                    this.removeUserFromQueue(primaryUser);
                    this.removeUserFromQueue(secondaryUser);

                    ChatRoom newChatRoom = new ChatRoom(this.topicName);
                    primaryUser.joinRoom(newChatRoom);
                    secondaryUser.joinRoom(newChatRoom);
                }
            }
        }
    }
}
