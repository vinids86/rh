package com.xerpa.acme.workload.exception;

import java.time.LocalTime;
import java.util.List;

/**
 * Indica que entradas são inválidas
 */
public class TimeClockDayException extends Exception {
    public TimeClockDayException(List<LocalTime> entries) {
        super(entries.toString());
    }
}
