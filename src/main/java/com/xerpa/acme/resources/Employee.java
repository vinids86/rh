package com.xerpa.acme.resources;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Employee {

    private List<Workload> workload;
    @SerializedName("pis_number")
    private String pisNumber;
    private String name;

    public List<Workload> getWorkload() {
        return workload;
    }

    public void setWorkload(List<Workload> workload) {
        this.workload = workload;
    }

    public String getPisNumber() {
        return pisNumber;
    }

    public void setPisNumber(String pisNumber) {
        this.pisNumber = pisNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
