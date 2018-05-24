package com.xerpa.acme.workload;

import com.xerpa.acme.BaseTest;
import com.xerpa.acme.EmployeeTestUtils;
import com.xerpa.acme.workload.repository.TimeClockEntriesRepositoryImpl;
import com.xerpa.acme.workload.service.BalanceDayService;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BalanceDayServiceTest extends BaseTest {

    private static final LocalDate WORK_DAY = LocalDate.of(2018, 5, 23);
    private static final LocalDate DAY_OFF = LocalDate.of(2018, 5, 26);

    private BalanceDayService balanceDayService;
    private EmployeeTestUtils util;

    @Override
    public void setUp() {
        balanceDayService = new BalanceDayService(new TimeClockEntriesRepositoryImpl());
        util = new EmployeeTestUtils();
    }

    @Test
    public void missedWorkOnDayOff() {
        final Duration balance = balanceDayService.getBalance(util.employeeWorkDay4Entries(), DAY_OFF);
        assertThat(balance, is(Duration.ZERO));
    }


    @Test
    public void missedWorkOnWorkDay() {
        final Duration balance = balanceDayService.getBalance(util.employeeDayOff4Entries(), WORK_DAY);
        assertThat(balance, is(Duration.ofMinutes(-300)));
    }

    @Test
    public void workOnDayOff() {
        final Duration balance = balanceDayService.getBalance(util.employeeDayOff4Entries(), DAY_OFF);

        assertThat(balance, is(Duration.ofMinutes(300)));
    }

    @Test
    public void workOnWorkDay() {
        final Duration balance = balanceDayService.getBalance(util.employeeWorkDay4Entries(), WORK_DAY);
        assertThat(balance, is(Duration.ZERO));
    }

    @Test()
    public void oddEntries() {
        final Duration balance = balanceDayService.getBalance(util.employeeWorkDay3Entries(), WORK_DAY);

        assertThat(balance, is(Duration.ZERO));
    }

    @Test
    public void moreThan4Entries() {
        final Duration balance = balanceDayService.getBalance(util.employeeWorkDay6Entries(), WORK_DAY);

        assertThat(balance, is(Duration.ZERO));
    }

    @Test
    public void workOnWorkDayWithoutInterval() {
        final Duration balance = balanceDayService.getBalance(util.employeeWorkDay2Entries(), WORK_DAY);
        assertThat(balance, is(Duration.ZERO));
    }

    @Test
    public void intervalPlusBalance() {
        final Duration balance = balanceDayService.getBalance(util.employeeWorkDay4EntriesShortInterval(), WORK_DAY);
        assertThat(balance, is(Duration.ofMinutes(60)));
    }

    @Test
    public void intervalGreaterThanWorkloadInterval() {
        final Duration balance = balanceDayService.getBalance(util.employeeWorkDay4EntriesBigInterval(), WORK_DAY);
        assertThat(balance, is(Duration.ZERO));
    }

    @Test
    public void intervalGreaterThanWorkloadIntervalButWorkHoursNot50Percent() {
        final Duration balance = balanceDayService.getBalance(util.employeeWorkDay4EntriesBigIntervalLowerWorkHours(), WORK_DAY);
        assertThat(balance, is(Duration.ofHours(-5)));
    }
}