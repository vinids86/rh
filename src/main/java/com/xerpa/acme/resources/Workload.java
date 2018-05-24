package com.xerpa.acme.resources;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Workload {

    @SerializedName("workload_in_minutes")
    private int workloadInMinutes;
    @SerializedName("minimum_rest_interval_in_minutes")
    private int minimumRestIntervalInMinutes;
    private List<Day> days;

    public int getWorkloadInMinutes() {
        return workloadInMinutes;
    }

    public void setWorkloadInMinutes(int workloadInMinutes) {
        this.workloadInMinutes = workloadInMinutes;
    }

    public int getMinimumRestIntervalInMinutes() {
        return minimumRestIntervalInMinutes;
    }

    public void setMinimumRestIntervalInMinutes(int minimumRestIntervalInMinutes) {
        this.minimumRestIntervalInMinutes = minimumRestIntervalInMinutes;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
