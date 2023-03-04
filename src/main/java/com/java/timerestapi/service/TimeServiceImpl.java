package com.java.timerestapi.service;

import com.java.timerestapi.Model.Times;
import com.java.timerestapi.dao.TimeZone;
import com.java.timerestapi.exception.NotUSTimeZoneException;
import org.springframework.stereotype.Service;

@Service
public class TimeServiceImpl implements TimeService {


    @Override
    public TimeZone findByTimezone(Times times) throws NotUSTimeZoneException {

        if (times.getTimezone().contains("America")) {

            return TimeZone.build(times.getAbbreviation(), times.getTimezone(), times.getDatetime());
        } else {
            throw new NotUSTimeZoneException("Not a US TimeZone: " + times.getTimezone());
        }
    }
}
