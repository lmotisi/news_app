package com.example.lmotisi.myapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Categorie implements Parcelable {

    @JsonProperty("id")
    public int id;
    @JsonProperty("slug")
    public String slug;
    @JsonProperty("title")
    public String title;
    @JsonProperty("description")
    public String description;


    public Categorie() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.slug);
        dest.writeString(this.title);
        dest.writeString(this.description);
    }

    protected Categorie(Parcel in) {
        this.id = in.readInt();
        this.slug = in.readString();
        this.title = in.readString();
        this.description = in.readString();
    }

    public static final Creator<Categorie> CREATOR = new Creator<Categorie>() {
        @Override
        public Categorie createFromParcel(Parcel source) {
            return new Categorie(source);
        }

        @Override
        public Categorie[] newArray(int size) {
            return new Categorie[size];
        }
    };
}

