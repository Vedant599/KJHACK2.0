package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class Growth {
    @SerializedName("Year")
    private int year;
    @SerializedName("Growth_Rate")
    private float growth_rate;

    public int getYear() {
        return year;
    }

    public float getGrowth_rate() {
        return growth_rate;
    }
}
