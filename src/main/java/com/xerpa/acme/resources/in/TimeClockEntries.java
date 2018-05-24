package com.xerpa.acme.resources.in;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;

public class TimeClockEntries {

    @SerializedName("pis_number")
    private String pisNumber;
    private List<LocalDateTime> entries;

    public String getPisNumber() {
        return pisNumber;
    }

    public List<LocalDateTime> getEntries() {
        return entries;
    }
}
