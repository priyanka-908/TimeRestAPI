package com.java.timerestapi.controller;
import com.java.timerestapi.Model.Times;
import com.java.timerestapi.dao.TimeZone;
import com.java.timerestapi.exception.NotUSTimeZoneException;
import com.java.timerestapi.service.TimeService;
import com.java.timerestapi.service.TimeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/time")
public class TimeZoneController {

    public static final String url = "https://worldtimeapi.org/api/timezone";
    private static final String SLASH="/";
    private final RestTemplate restTemplate;
    private final TimeService timeService;

    @Autowired
    public TimeZoneController(RestTemplate restTemplate, TimeServiceImpl timeService) {
        this.restTemplate = restTemplate;
        this.timeService = timeService;
    }

    @GetMapping("/")
    public ResponseEntity<TimeZone> getTime(@RequestParam("timezone") String timezone) throws NotUSTimeZoneException {
        Times times = restTemplate.getForObject(url + SLASH + timezone, Times.class);
        TimeZone timeZoneDetails = timeService.findByTimezone(times);
        return ResponseEntity.ok(timeZoneDetails);
    }
}
