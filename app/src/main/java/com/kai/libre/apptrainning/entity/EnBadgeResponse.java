package com.kai.libre.apptrainning.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kai on 1/11/2017.
 */

public class EnBadgeResponse {
    @SerializedName("data")
    private List<EnBadgeResponse> data;
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("icon")
    private String icon;
    @SerializedName("color_class")
    private String colorClass;
    @SerializedName("point")
    private String point;

    public EnBadgeResponse(int id, String name, String icon, String colorClass, String point) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.colorClass = colorClass;
        this.point = point;
    }

    public List<EnBadgeResponse> getData() {
        return data;
    }

    public void setData(List<EnBadgeResponse> data) {
        this.data = data;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColorClass() {
        return colorClass;
    }

    public void setColorClass(String colorClass) {
        this.colorClass = colorClass;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
