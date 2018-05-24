package com.xerpa.acme.resources.in;

import com.google.gson.annotations.SerializedName;
import com.xerpa.acme.resources.Employee;

import java.time.LocalDate;
import java.util.List;

public class Config {

    private LocalDate today;
    @SerializedName("period_start")
    private LocalDate periodStart;
    private List<Employee> employees;

    public LocalDate getToday() {
        return today;
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }

    public LocalDate getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(LocalDate periodStart) {
        this.periodStart = periodStart;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
