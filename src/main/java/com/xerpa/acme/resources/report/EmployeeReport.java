package com.xerpa.acme.resources.report;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class EmployeeReport {
    @SerializedName("pis_number")
    private String pisNumber;
    private Summary summary;
    private List<History> history;

    public String getPisNumber() {
        return pisNumber;
    }

    public void setPisNumber(String pisNumber) {
        this.pisNumber = pisNumber;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeReport)) return false;
        EmployeeReport that = (EmployeeReport) o;
        return Objects.equals(getPisNumber(), that.getPisNumber()) &&
                Objects.equals(getSummary(), that.getSummary()) &&
                Objects.equals(getHistory(), that.getHistory());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPisNumber(), getSummary(), getHistory());
    }
}
