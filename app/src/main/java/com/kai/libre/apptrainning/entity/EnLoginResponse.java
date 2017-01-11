package com.kai.libre.apptrainning.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kai on 1/11/2017.
 */

public class EnLoginResponse {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("role_id")
    private int roleId;
    @SerializedName("status_id")
    private int statusId;
    @SerializedName("avatar_id")
    private int avatarId;
    @SerializedName("token")
    private String token;
    @SerializedName("create_at")
    private String createAt;
    @SerializedName("update_at")
    private String updateAt;

    public EnLoginResponse(int id, String name, String email, int roleId, int statusId, int avatarId, String token, String createAt, String updateAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roleId = roleId;
        this.statusId = statusId;
        this.avatarId = avatarId;
        this.token = token;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
