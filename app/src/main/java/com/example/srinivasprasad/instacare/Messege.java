package com.example.srinivasprasad.instacare;

import java.util.Date;
public class Messege {

    public String user_id, messege;
    public Date timestamp;
    public String image_url, desc, image_thumb;


    public Messege(String user_id, String messege, Date timestamp, String image_url, String desc, String image_thumb) {
        this.user_id = user_id;
        this.messege = messege;
        this.timestamp = timestamp;
        this.image_url = image_url;
        this.desc = desc;
        this.image_thumb = image_thumb;
    }

    public Messege() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage_thumb() {
        return image_thumb;
    }

    public void setImage_thumb(String image_thumb) {
        this.image_thumb = image_thumb;
    }



}
