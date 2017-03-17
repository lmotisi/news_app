package com.example.lmotisi.myapp;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Html;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Post implements Parcelable {

    @JsonProperty("title")
    public String title;

    @JsonProperty("content")
    public String content;

    @JsonProperty("excerpt")
    public String excerpt;

    @JsonProperty("url")
    public String url;

    @JsonProperty("attachments")
    public ArrayList<Attachment> attachments;


    public Date date;


    @JsonProperty("date")
    public void setDate(String date)
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.date = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @JsonProperty("excerpt")
    public void setExcerpt(String exp)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.excerpt = Html.fromHtml(exp, Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            this.excerpt = Html.fromHtml(exp).toString();
        }
    }


    public Post() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.excerpt);
        dest.writeString(this.url);
        dest.writeTypedList(this.attachments);
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
    }

    protected Post(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
        this.excerpt = in.readString();
        this.url = in.readString();
        this.attachments = in.createTypedArrayList(Attachment.CREATOR);
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
