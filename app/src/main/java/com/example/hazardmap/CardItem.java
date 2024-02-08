package com.example.hazardmap;

import android.view.View;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardItem {

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("reporter")
    @Expose
    public String reporter;

    @SerializedName("lat")
    @Expose
    public String lat;

    @SerializedName("lng")
    @Expose
    public String lng;

    @SerializedName("hour")
    @Expose
    public String hour;

    @SerializedName("minute")
    @Expose
    public String minute;

    @SerializedName("tod")
    @Expose
    public String tod;

    @SerializedName("day")
    @Expose
    public String day;

    @SerializedName("month")
    @Expose
    public String month;

    @SerializedName("year")
    @Expose
    public String year;
}