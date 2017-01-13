package com.kai.libre.apptrainning.entity;

/**
 * Created by Kai on 1/13/2017.
 */

public class EnReportBadge {
    private String token;
    private int userId;
    private int creatorId;

    public EnReportBadge(String token, int userId, int creatorId) {
        this.token = token;
        this.userId = userId;
        this.creatorId = creatorId;
    }

    public String getToken() {
        return token;
    }

    public int getUserId() {
        return userId;
    }

    public int getCreatorId() {
        return creatorId;
    }
}
