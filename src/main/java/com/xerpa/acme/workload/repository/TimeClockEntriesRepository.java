package com.xerpa.acme.workload.repository;

import com.xerpa.acme.workload.vo.TimeClockDayVO;

import java.time.LocalDate;

/**
 * Repositório de entradas de funcionários
 */
public interface TimeClockEntriesRepository {
    /**
     * Encontra entradas de um funcionário para um determinado dia
     * @param pisNumber que representa um funcionário
     * @param day dia das entradas
     * @return entradas de um funcionário para um determinado dia
     */
    TimeClockDayVO findTimeClockDay(String pisNumber, LocalDate day);
}
