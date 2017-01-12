package com.kai.libre.apptrainning.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kai on 1/12/2017.
 */

public class EnUserResponse {

    @SerializedName("data")
    private ArrayList<Data> data;
    @SerializedName("link")
    private ArrayList<Link> link;

    public ArrayList<Data> getData() {
        return data;
    }

    public static class Avatar {
        @SerializedName("id")
        private int id;
        @SerializedName("owner_id")
        private String owner_id;
        @SerializedName("url")
        private String url;
        @SerializedName("created_at")
        private String created_at;
        @SerializedName("updated_at")
        private String updated_at;
        @SerializedName("origin")
        private String origin;
        @SerializedName("thumb")
        private String thumb;

        public int getId() {
            return id;
        }

        public String getOwner_id() {
            return owner_id;
        }

        public String getUrl() {
            return url;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public String getOrigin() {
            return origin;
        }

        public String getThumb() {
            return thumb;
        }
    }

    public static class Badge {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("shortname")
        private String shortname;
        @SerializedName("icon")
        private String icon;
        @SerializedName("color_class")
        private String color_class;
        @SerializedName("point")
        private String point;
        @SerializedName("created_at")
        private String created_at;
        @SerializedName("updated_at")
        private String updated_at;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getShortname() {
            return shortname;
        }

        public String getIcon() {
            return icon;
        }

        public String getColor_class() {
            return color_class;
        }

        public String getPoint() {
            return point;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }
    }

    public static class User_badges {
        @SerializedName("id")
        private int id;
        @SerializedName("user_id")
        private String user_id;
        @SerializedName("badge_id")
        private String badge_id;
        @SerializedName("creator_id")
        private int creator_id;
        @SerializedName("description")
        private String description;
        @SerializedName("applied_date")
        private String applied_date;
        @SerializedName("created_at")
        private String created_at;
        @SerializedName("updated_at")
        private String updated_at;
        @SerializedName("badge")
        private Badge badge;

        public int getId() {
            return id;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getBadge_id() {
            return badge_id;
        }

        public int getCreator_id() {
            return creator_id;
        }

        public String getDescription() {
            return description;
        }

        public String getApplied_date() {
            return applied_date;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public Badge getBadge() {
            return badge;
        }
    }

    public static class Data {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("email")
        private String email;
        @SerializedName("university")
        private String university;
        @SerializedName("major")
        private String major;
        @SerializedName("graduated_year")
        private String graduated_year;
        @SerializedName("hometown")
        private String hometown;
        @SerializedName("address")
        private String address;
        @SerializedName("phonenumber")
        private String phonenumber;
        @SerializedName("skype")
        private String skype;
        @SerializedName("bank_account")
        private String bank_account;
        @SerializedName("bank_name")
        private String bank_name;
        @SerializedName("role_id")
        private String role_id;
        @SerializedName("status_id")
        private String status_id;
        @SerializedName("avatar_id")
        private int avatar_id;
        @SerializedName("token")
        private String token;
        @SerializedName("created_at")
        private String created_at;
        @SerializedName("updated_at")
        private String updated_at;
        @SerializedName("avatar")
        private Avatar avatar;
        @SerializedName("user_badges")
        private List<User_badges> user_badges;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getUniversity() {
            return university;
        }

        public String getMajor() {
            return major;
        }

        public String getGraduated_year() {
            return graduated_year;
        }

        public String getHometown() {
            return hometown;
        }

        public String getAddress() {
            return address;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public String getSkype() {
            return skype;
        }

        public String getBank_account() {
            return bank_account;
        }

        public String getBank_name() {
            return bank_name;
        }

        public String getRole_id() {
            return role_id;
        }

        public String getStatus_id() {
            return status_id;
        }

        public int getAvatar_id() {
            return avatar_id;
        }

        public String getToken() {
            return token;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public Avatar getAvatar() {
            return avatar;
        }

        public List<User_badges> getUser_badges() {
            return user_badges;
        }
    }

    public static class Link {
    }


}
