package com.xerpa.acme.resources;

import com.google.gson.annotations.SerializedName;

public enum Day {

    @SerializedName("sun") SUNDAY,
    @SerializedName("mon") MONDAY,
    @SerializedName("tue") TUESDAY,
    @SerializedName("wed") WEDNESDAY,
    @SerializedName("thu") THURSDAY,
    @SerializedName("fri") FRIDAY,
    @SerializedName("sat") SATURDAY;
}
