package com.xerpa.acme.workload.repository;

import com.xerpa.acme.db.Singleton;
import com.xerpa.acme.workload.vo.TimeClockDayVO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TimeClockEntriesRepositoryImpl implements TimeClockEntriesRepository {

    private static final String SEPARATOR = "/";

    @Override
    public TimeClockDayVO findTimeClockDay(String pisNumber, LocalDate day) {
        final List<LocalTime> entries = Singleton.getInstance().getMap().get(pisNumber + SEPARATOR + day);
        final TimeClockDayVO timeClockDayVO = new TimeClockDayVO();
        timeClockDayVO.addEntries(entries);
        return timeClockDayVO;
    }
}
