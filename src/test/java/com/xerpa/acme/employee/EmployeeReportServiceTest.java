package com.xerpa.acme.employee;

import com.xerpa.acme.resources.report.EmployeeReport;
import com.xerpa.acme.BaseTest;
import com.xerpa.acme.EmployeeTestUtils;
import com.xerpa.acme.workload.repository.TimeClockEntriesRepository;
import com.xerpa.acme.workload.repository.TimeClockEntriesRepositoryImpl;
import com.xerpa.acme.workload.service.BalanceDayService;
import com.xerpa.acme.workload.vo.ReportPeriodVO;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EmployeeReportServiceTest extends BaseTest {

    private TimeClockEntriesRepository timeClockEntriesRepository;
    private BalanceDayService balanceDayService;
    private EmployeeReportService employeeReportService;
    private EmployeeTestUtils util;

    @Override
    public void setUp() {
        timeClockEntriesRepository = new TimeClockEntriesRepositoryImpl();
        balanceDayService = new BalanceDayService(timeClockEntriesRepository);
        employeeReportService = new EmployeeReportService(balanceDayService);
        util = new EmployeeTestUtils();
    }

    @Test
    public void calculateSummaryBalance() {
        ReportPeriodVO period = new ReportPeriodVO(
                LocalDate.of(2018, 5, 20), LocalDate.of(2018, 5, 26));
        final List<EmployeeReport> employeeListReport = employeeReportService.createEmployeeListReport(
                Arrays.asList(util.employeeWorkDay4EntriesBigIntervalLowerWorkHours(),
                util.employeeWorkDay4EntriesBigInterval(), util.employeeWorkDay4EntriesShortInterval(),
                        util.employeeEmpty()), period);

        assertThat(employeeListReport.get(0).getSummary().getBalance(), is(Duration.ofMinutes(-2220)));
        assertThat(employeeListReport.get(1).getSummary().getBalance(), is(Duration.ofMinutes(-1920)));
        assertThat(employeeListReport.get(2).getSummary().getBalance(), is(Duration.ofMinutes(-1860)));
        assertThat(employeeListReport.get(3).getSummary().getBalance(), is(Duration.ofMinutes(-2400)));
    }
}