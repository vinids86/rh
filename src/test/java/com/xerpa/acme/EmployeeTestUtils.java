package com.xerpa.acme;

import com.xerpa.acme.employee.EmployeeEntity;
import com.xerpa.acme.resources.Day;

import java.util.Arrays;
import java.util.List;

public class EmployeeTestUtils {

    private static final List<Day> WORKDAYS = Arrays.asList(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY);

    public EmployeeEntity employeeWorkDay2Entries() {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setPisNumber("22");
        employee.setDaysOfWork(WORKDAYS);
        employee.setWorkloadInMinutes(700);
        employee.setMinimumRestIntervalInMinutes(0);
        return employee;
    }

    public EmployeeEntity employeeWorkDay3Entries() {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setPisNumber("111111111");
        employee.setDaysOfWork(WORKDAYS);
        employee.setWorkloadInMinutes(300);
        employee.setMinimumRestIntervalInMinutes(15);
        return employee;
    }

    public EmployeeEntity employeeDayOff4Entries() {
        final EmployeeEntity employee = new EmployeeEntity();
        employee.setPisNumber("123456789");
        employee.setDaysOfWork(WORKDAYS);
        employee.setWorkloadInMinutes(300);
        employee.setMinimumRestIntervalInMinutes(15);
        return employee;
    }

    public EmployeeEntity employeeWorkDay6Entries() {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setPisNumber("666");
        employee.setDaysOfWork(WORKDAYS);
        employee.setWorkloadInMinutes(666);
        employee.setMinimumRestIntervalInMinutes(0);
        return employee;
    }

    public EmployeeEntity employeeWorkDay4Entries() {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setPisNumber("789456123");
        employee.setDaysOfWork(WORKDAYS);
        employee.setWorkloadInMinutes(480);
        employee.setMinimumRestIntervalInMinutes(0);
        return employee;
    }

    public EmployeeEntity employeeWorkDay4EntriesShortInterval() {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setPisNumber("852");
        employee.setDaysOfWork(WORKDAYS);
        employee.setWorkloadInMinutes(480);
        employee.setMinimumRestIntervalInMinutes(60);
        return employee;
    }

    public EmployeeEntity employeeWorkDay4EntriesBigInterval() {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setPisNumber("963");
        employee.setDaysOfWork(WORKDAYS);
        employee.setWorkloadInMinutes(480);
        employee.setMinimumRestIntervalInMinutes(60);
        return employee;
    }

    public EmployeeEntity employeeWorkDay4EntriesBigIntervalLowerWorkHours() {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setPisNumber("741");
        employee.setDaysOfWork(WORKDAYS);
        employee.setWorkloadInMinutes(480);
        employee.setMinimumRestIntervalInMinutes(60);
        return employee;
    }

    public EmployeeEntity employeeEmpty() {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setPisNumber("0");
        employee.setDaysOfWork(WORKDAYS);
        employee.setWorkloadInMinutes(480);
        employee.setMinimumRestIntervalInMinutes(60);
        return employee;
    }
}
