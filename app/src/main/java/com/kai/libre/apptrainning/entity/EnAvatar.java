package com.kai.libre.apptrainning.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kai on 1/12/2017.
 */

public class EnAvatar {
    @SerializedName("description")
    private String description;

    public EnAvatar(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
