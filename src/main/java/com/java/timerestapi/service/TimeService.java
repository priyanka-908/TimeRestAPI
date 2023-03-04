package com.java.timerestapi.service;

import com.java.timerestapi.Model.Times;
import com.java.timerestapi.dao.TimeZone;
import com.java.timerestapi.exception.NotUSTimeZoneException;

public interface TimeService {

    TimeZone findByTimezone(Times times) throws NotUSTimeZoneException;
}