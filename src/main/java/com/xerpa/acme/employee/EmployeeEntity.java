package com.xerpa.acme.employee;

import com.xerpa.acme.resources.Day;

import java.time.LocalDate;
import java.util.List;

/**
 * Entidade que representa um funcionário no sistema
 */
public class EmployeeEntity {

    private List<Day> daysOfWork;
    private String pisNumber;
    private int workloadInMinutes;
    private int minimumRestIntervalInMinutes;

    /**
     * Se é esperado que funcionário trabalhe em determinado dia
     * @param day dia a ser analisado
     * @return se é folga
     */
    public boolean isDayOff(LocalDate day) {
        for (Day dayOfWork : daysOfWork)
            if (dayOfWork.name().equals(day.getDayOfWeek().name()))
                return false;
        return true;
    }

    public void setDaysOfWork(List<Day> daysOfWork) {
        this.daysOfWork = daysOfWork;
    }

    public String getPisNumber() {
        return pisNumber;
    }

    public void setPisNumber(String pisNumber) {
        this.pisNumber = pisNumber;
    }

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
}
