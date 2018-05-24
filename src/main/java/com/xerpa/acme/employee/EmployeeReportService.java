package com.xerpa.acme.employee;

import com.xerpa.acme.resources.report.EmployeeReport;
import com.xerpa.acme.resources.report.History;
import com.xerpa.acme.resources.report.Summary;
import com.xerpa.acme.workload.service.BalanceDayService;
import com.xerpa.acme.workload.vo.ReportPeriodVO;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Serviço responável por criar relatório com balanço e históricos de uma lista de funcionário por um período de tempo
 */
public class EmployeeReportService {

    private final BalanceDayService balanceDayService;

    public EmployeeReportService(BalanceDayService balanceDayService) {
        this.balanceDayService = balanceDayService;
    }

    /**
     * Cria relatório de uma lista de funcionários em um período específico de tempo
     * @param employees lista de funcionários
     * @param period período de tempo do relatório
     * @return lista de relatório
     */
    public List<EmployeeReport> createEmployeeListReport(List<EmployeeEntity> employees, ReportPeriodVO period) {
        final List<EmployeeReport> reports = new ArrayList<>();
        for (EmployeeEntity employee : employees)
            reports.add(createEmployeeReport(employee, period));
        return reports;
    }

    /**
     * Cria relatório de um determinado funcionário
     * @param employee funcionário a ser analisado
     * @param period período de tempo do relatório
     * @return relatório
     */
    private EmployeeReport createEmployeeReport(EmployeeEntity employee, ReportPeriodVO period) {
        final EmployeeReport report = new EmployeeReport();
        final List<History> histories = new ArrayList<>();

        LocalDate day = period.getStart().minusDays(1);
        while ((day = day.plusDays(1)).isBefore(period.getEnd())) {
            final History history = new History();
            history.setBalance(balanceDayService.getBalance(employee, day));
            history.setDay(day);
            histories.add(history);
        }
        final Summary summary = calculateSummaryBalance(histories);

        report.setPisNumber(employee.getPisNumber());
        report.setHistory(histories);
        report.setSummary(summary);
        return report;
    }

    /**
     * Calcula o saldo acumulado no período
     * @param histories histórico de registros
     * @return saldo acumulado no poríodo
     */
    private Summary calculateSummaryBalance(List<History> histories) {
        final Summary summary = new Summary();

        Duration balance = Duration.ZERO;
        for (History history : histories) {
            balance = balance.plus(history.getBalance());
        }

        summary.setBalance(balance);
        return summary;
    }

}
