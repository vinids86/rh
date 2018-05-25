package com.xerpa.acme.workload.service;

import com.xerpa.acme.employee.EmployeeEntity;
import com.xerpa.acme.workload.exception.NoEntriesException;
import com.xerpa.acme.workload.exception.TimeClockDayException;
import com.xerpa.acme.workload.repository.TimeClockEntriesRepository;
import com.xerpa.acme.workload.vo.TimeClockDayVO;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Serviço responsável por calcular balanço total do dia
 */
public class BalanceDayService {

    private TimeClockEntriesRepository timeClockEntriesRepository;

    public BalanceDayService(TimeClockEntriesRepository timeClockEntriesRepository) {
        this.timeClockEntriesRepository = timeClockEntriesRepository;
    }

    /**
     * Faz o calculo do balanco do dia de um determinado funcionário.
     * As horas trabalhadas são somadas ao tempo de intervalo não feito caso exista, e descontado a jornada diária esperada.
     * Caso seja folga do funcionário todas as horas trabalhadas são contadas no balanço.
     * @param employee funcionário a ter as horas calculadas
     * @param day dia a ser verificado
     * @return balanço de horas do dia de um funcionário
     */
    public Duration getBalance(EmployeeEntity employee, LocalDate day) {
        Duration workHours;
        TimeClockDayVO timeClockDayVO = timeClockEntriesRepository.findTimeClockDay(employee.getPisNumber(), day);

        try {
            workHours = timeClockDayVO.getWorkHours().plus(intervalNotTaken(employee, timeClockDayVO));
        } catch (TimeClockDayException e) {
            logMessage(employee, day, e.getMessage());
            return Duration.ZERO;
        } catch (NoEntriesException e) {
            return dayOffOrFault(employee, day);
        }
        return dayOffOrWorkDay(employee, day, workHours);
    }

    /**
     * Retorna o balanço do dia
     * @param employee funcionário a ter as horas calculadas
     * @param day dia a ser verificado
     * @param workHours horas trabalhadas
     * @return caso seja folga, todas as horas trabalhadas, caso contrário a diferença de horas trabalhadas com a jornada diária esperada.
     */
    private Duration dayOffOrWorkDay(EmployeeEntity employee, LocalDate day, Duration workHours) {
        if (employee.isDayOff(day)) return workHours;
        return workHours.minusMinutes(employee.getWorkloadInMinutes());
    }

    /**
     * Retorna o balanço do dia
     * @param employee funcionário a ter as horas calculadas
     * @param day dia a ser verificado
     * @return caso seja folga, 0, caso contrário as horas da jornada diária negativas
     */
    private Duration dayOffOrFault(EmployeeEntity employee, LocalDate day) {
        if (employee.isDayOff(day)) return Duration.ZERO;
        return Duration.of(employee.getWorkloadInMinutes() * -1, ChronoUnit.MINUTES);
    }

    /**
     * Imprimi no terminal mensagem amigável quando as entradas de ponto são inválidas
     * @param employee funcionário com entradas inválidas
     * @param day dia com entradas inválidas
     * @param entries entradas feitas
     */
    private void logMessage(EmployeeEntity employee, LocalDate day, String entries) {
        System.out.println("Erro ao contabilizar balanço do dia " + day + " do funcionário com pis: "
                + employee.getPisNumber() + "\n" + "Registros de ponto encontrados: " + entries);
    }

    /**
     * Incrementa as horas trabalhadas com o intervalo não feito caso funcionário tenha trabalhado mais de 50% da jornada diária
     * @param employee funcionário a ter as horas calculadas
     * @param timeClockDayVO entradas diária do ponto
     * @return tempo de intervalo não feito caso exista
     * @throws NoEntriesException caso não haja entradas no dia
     * @throws TimeClockDayException caso as entradas sejam inválidas
     */
    private Duration intervalNotTaken(EmployeeEntity employee, TimeClockDayVO timeClockDayVO) throws NoEntriesException, TimeClockDayException {
        if (workMore50PercentWorkload(employee, timeClockDayVO.getWorkHours().toMinutes())
                && intervalShortThanExpected(timeClockDayVO.getInterval().toMinutes(), employee.getMinimumRestIntervalInMinutes()))
            return Duration.ofMinutes(employee.getMinimumRestIntervalInMinutes() - timeClockDayVO.getInterval().toMinutes());
        return Duration.ZERO;
    }

    /**
     * @param interval tempo de intervalo feito no dia
     * @param minimumRestIntervalInMinutes intervalo minímo esperado
     * @return se intervalo é menor que o esperado
     */
    private boolean intervalShortThanExpected(long interval, long minimumRestIntervalInMinutes) {
        return minimumRestIntervalInMinutes > interval;
    }

    /**
     * @param employee funcionário a ter jornada analisada
     * @param workloadInMinutes jornada diária esperada
     * @return se mais de 50% da jornada diária foi feita
     */
    private boolean workMore50PercentWorkload(EmployeeEntity employee, long workloadInMinutes) {
        return workloadInMinutes > employee.getWorkloadInMinutes() / 2;
    }
}
