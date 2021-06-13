package com.hackmann.polarity.user;

import org.springframework.stereotype.Service;

@Service
public class PositionService {
    private String[] positions = {"Strongly Against", "Against", "Neutral", "Support", "Strongly Support"};

    public String getPositionText(int position) {
        return this.positions[position + 2];
    }
}
