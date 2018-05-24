package com.xerpa.acme.utils;

import com.google.gson.reflect.TypeToken;
import com.xerpa.acme.db.Singleton;
import com.xerpa.acme.employee.EmployeeAssembler;
import com.xerpa.acme.employee.EmployeeEntity;
import com.xerpa.acme.employee.EmployeeReportService;
import com.xerpa.acme.resources.Employee;
import com.xerpa.acme.resources.in.Config;
import com.xerpa.acme.resources.in.TimeClockEntries;
import com.xerpa.acme.utils.file.FileUtils;
import com.xerpa.acme.utils.json.GsonUtils;
import com.xerpa.acme.workload.repository.TimeClockEntriesRepositoryImpl;
import com.xerpa.acme.workload.service.BalanceDayService;
import com.xerpa.acme.workload.vo.ReportPeriodVO;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe utilitária para dar start no serviço de geração de relatório
 */
public class Load {

    private Config config;

    /**
     * Imprimi relatório gerado no terminal
     */
    public void print() {
        final Singleton instance = Singleton.getInstance();
        if (instance.getMap().size() == 0) {
            initializeSingleton("config.json", "timeclock_entries.json");
        }
        final TimeClockEntriesRepositoryImpl timeClockEntriesRepository = new TimeClockEntriesRepositoryImpl();
        final BalanceDayService balanceDayService = new BalanceDayService(timeClockEntriesRepository);
        final EmployeeAssembler employeeAssembler = new EmployeeAssembler();
        final ReportPeriodVO period = new ReportPeriodVO(config.getPeriodStart(), config.getToday());
        final EmployeeReportService employeeReportService = new EmployeeReportService(balanceDayService);
        final List<EmployeeEntity> entities = employeeAssembler.mapToEntityList(config.getEmployees());
        System.out.println(GsonUtils.getGson().toJson(employeeReportService.createEmployeeListReport(entities, period)));
    }

    /**
     * Carrega arquivo de configuração
     * @param configJson nome do arquivo a ser carregado
     */
    private void config(String configJson) {
        Config config = null;
        try {
            config = GsonUtils.getGson().fromJson(new FileUtils().getFileFromClasspath(configJson), Config.class);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        this.config = config;
    }

    /**
     * Carrega arquivo com as entradas de batidas de ponto
     * @param timeclockEntriesJson nome do arquivo com as entradas
     */
    private String timeClockEntries(String timeclockEntriesJson) {
        String timeclockEntries = null;
        try {
            timeclockEntries = new FileUtils().getFileFromClasspath(timeclockEntriesJson);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return timeclockEntries;
    }

    /**
     * Cria mapa para armazenar de maneira mais prática as entradas de cada funcionário por dia
     * @param configJson nome do arquivo de configuração
     * @param timeclockEntriesJson nome do arquivo com as entradas
     */
    public void initializeSingleton(String configJson, String timeclockEntriesJson) {
        if (config == null)
            config(configJson);
        if (Singleton.getInstance().getMap().size() > 0) {
            return;
        }
        final Singleton instance = Singleton.getInstance();

        Type collectionType = new TypeToken<Collection<TimeClockEntries>>() {
        }.getType();

        final List<TimeClockEntries> tce = GsonUtils.getGson().fromJson(timeClockEntries(timeclockEntriesJson), collectionType);

        final Map<String, List<LocalDateTime>> employeeEntries = tce.stream().collect(Collectors.toMap(TimeClockEntries::getPisNumber, TimeClockEntries::getEntries));

        for (Employee employee : config.getEmployees()) {
            Collections.sort(employeeEntries.get(employee.getPisNumber()));
            for (LocalDateTime day : employeeEntries.get(employee.getPisNumber())) {
                addEntries(instance.getMap(), employee.getPisNumber() + "/" + day.toLocalDate(), day.toLocalTime());
            }
        }
    }

    /**
     * Adiciona novos registros ao mapa
     * @param map mapa com as entradas
     * @param key pisNumber do funcionário '/' e data
     * @param item registro de entrada
     * @return lista com as entradas
     */
    private synchronized boolean addEntries(Map<String, List<LocalTime>> map, String key, LocalTime item) {
        List<LocalTime> list = map.computeIfAbsent(key, k -> new ArrayList<>());
        return list.add(item);
    }
}

