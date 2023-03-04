package com.java.timerestapi;

import com.java.timerestapi.Model.Times;
import com.java.timerestapi.dao.TimeZone;
import com.java.timerestapi.exception.NotUSTimeZoneException;
import com.java.timerestapi.service.TimeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class TimeApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TimeService timeService;


    @Test
    void getTime_success() throws Exception {

        TimeZone timeZone = TimeZone.build("CST", "America/Chicago", "2023-03-03T18:08:24.015086+03:00");

        Mockito.lenient().when(timeService.findByTimezone(any(Times.class))).thenReturn(timeZone);

        mockMvc.perform(MockMvcRequestBuilders.get("/time/").queryParam("timezone", "America/Chicago").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.abbreviation").value("CST"));
    }


    @Test
    void getTime_NotaValidTimeZone() throws Exception {

        TimeZone timeZone = TimeZone.build("CST", "Africa/Chicago", "2023-03-03T18:08:24.015086+03:00");

        Mockito.lenient().when(timeService.findByTimezone(any(Times.class))).thenReturn(timeZone);

        mockMvc.perform(MockMvcRequestBuilders.get("/time/").queryParam("timezone", "Africa/Chicago").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andExpect(jsonPath("$.error").value("unknown location Africa/Chicago"));
    }

    @Test
    void getTime_NotaUSTimeZone() throws Exception {

        Mockito.lenient().when(timeService.findByTimezone(any(Times.class))).thenThrow(NotUSTimeZoneException.class);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/time/").queryParam("timezone", "Africa/Abidjan").contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

    }
}
