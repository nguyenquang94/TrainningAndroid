package com.kai.libre.apptrainning.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kai on 1/14/2017.
 */

public class EnClockInStatus {

    @SerializedName("code")
    private int code;
    @SerializedName("description")
    private String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
