package com.xerpa.acme.workload.vo;

import com.xerpa.acme.workload.exception.NoEntriesException;
import com.xerpa.acme.workload.exception.TimeClockDayException;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

/**
 * Classe que representa todas as entradas feitas em um determinado dia
 */
public class TimeClockDayVO {
    private List<LocalTime> entries;

    /**
     * Adiciona as entradas do dia
     *
     * @param entries
     */
    public void addEntries(List<LocalTime> entries) {
        this.entries = entries;
    }

    /**
     * @return se funcionário fez intervalo em determinado dia
     * @throws NoEntriesException    caso não haja entradas no dia
     * @throws TimeClockDayException caso as entradas sejam inválidas
     */
    private boolean hasInterval() throws TimeClockDayException, NoEntriesException {
        validEntry();
        return entries.size() == 4;
    }

    /**
     * @return as horas trabalhadas em um determinado dia
     * @throws NoEntriesException caso não haja entradas no dia
     * @throws TimeClockDayException caso as entradas sejam inválidas
     */
    public Duration getWorkHours() throws TimeClockDayException, NoEntriesException {
        if (hasInterval())
            return Duration.between(entries.get(0), entries.get(1))
                    .plus(Duration.between(entries.get(2), entries.get(3)));
        return Duration.between(entries.get(0), entries.get(1));
    }

    /**
     * @return intervalo caso exista de um determinado dia
     * @throws NoEntriesException caso não haja entradas no dia
     * @throws TimeClockDayException caso as entradas sejam inválidas
     */
    public Duration getInterval() throws TimeClockDayException, NoEntriesException {
        return hasInterval() ?
                Duration.between(entries.get(1), entries.get(2)) : Duration.ZERO;
    }

    /**
     * Valida se as entradas de um determinado dia são validas, ou seja 2 ou 4
     * @throws NoEntriesException caso não haja entradas no dia
     * @throws TimeClockDayException caso as entradas sejam inválidas
     */
    private void validEntry() throws TimeClockDayException, NoEntriesException {
        if (entries == null)
            throw new NoEntriesException();
        if (entries.size() % 2 != 0 || entries.size() > 4)
            throw new TimeClockDayException(entries);
    }
}
