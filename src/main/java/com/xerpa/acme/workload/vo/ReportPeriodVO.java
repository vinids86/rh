package com.xerpa.acme.workload.vo;

import java.time.LocalDate;

/**
 * Período de tempo, com início e fim
 */
public class ReportPeriodVO {

    private final LocalDate start;
    private final LocalDate end;

    public ReportPeriodVO(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
