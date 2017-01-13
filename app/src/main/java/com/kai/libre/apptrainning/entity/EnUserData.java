package com.kai.libre.apptrainning.entity;

/**
 * Created by Kai on 1/12/2017.
 */

public class EnUserData {
    private int id;
    private String name;
    private int countDanger;
    private int ocuntSuccess;
    private int creatorId;
    private int avatarId;
    private int userId;
    private String token;

    public EnUserData(int id, String token, String name, int countDanger, int ocuntSuccess, int creatorId, int avatarId , int userId) {
        this.id = id;
        this.token = token;
        this.name = name;
        this.countDanger = countDanger;
        this.ocuntSuccess = ocuntSuccess;
        this.creatorId = creatorId;
        this.avatarId = avatarId;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCountDanger() {
        return countDanger;
    }

    public int getOcuntSuccess() {
        return ocuntSuccess;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public int getAvatarId() {
        return avatarId;
    }
}
