package com.example.anandparmeetsingh.books;

public class Word {

    private String mMagnitude;
    private String mLocation;
    private String mDate;
    private String mUrl;

    public Word(String magnitude, String location, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mUrl = url;


    }

    public Word(String magnitude, String location, String date, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
        mUrl = url;


    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getDate() {
        return mDate;
    }

    public String getUrl() {
        return mUrl;
    }


}
