package com.wardanger.ProjectPorsche;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wardanger.ProjectPorsche.controller.ServiceRecordController;
import com.wardanger.ProjectPorsche.model.Car;
import com.wardanger.ProjectPorsche.model.ServiceRecord;
import com.wardanger.ProjectPorsche.model.User;
import com.wardanger.ProjectPorsche.service.CarService;
import com.wardanger.ProjectPorsche.service.ServiceRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ServiceRecordController.class)
public class ServiceRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceRecordService serviceRecordService;

    @MockBean
    private CarService carService;

    private User currentUser;
    private Car car;
    private ServiceRecord serviceRecord;

    @BeforeEach
    void setUp() {
        currentUser = new User();
        currentUser.setId(1L);

        car = new Car();
        car.setId(2L); // Jane Smith's car ID is 2
        car.setUser(currentUser);

        serviceRecord = new ServiceRecord();
        serviceRecord.setId(1L);
        serviceRecord.setCar(car);

        // Mock user authentication and service responses
        when(serviceRecordService.getServiceRecordById(1L)).thenReturn(Optional.of(serviceRecord));
        when(carService.checkCarOwnership(2L, currentUser)).thenReturn(car);
    }

    @Test
    void getServiceRecordById_shouldReturnServiceRecordWhenAuthorized() throws Exception {
        mockMvc.perform(get("/service-records/1")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("janesmith@example.com", "asd123")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(serviceRecord.getId()));
    }

    @Test
    void getServiceRecordsByCarId_shouldReturnRecordsWhenAuthorized() throws Exception {
        when(serviceRecordService.getServiceRecordsByCarId(2L)).thenReturn(Collections.singletonList(serviceRecord));

        mockMvc.perform(get("/service-records/car/2")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("janesmith@example.com", "asd123")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(serviceRecord.getId()));
    }

    @Test
    void createServiceRecord_shouldCreateRecordWhenAuthorized() throws Exception {
        when(serviceRecordService.saveServiceRecord(any(ServiceRecord.class))).thenReturn(serviceRecord);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(serviceRecord);

        mockMvc.perform(post("/service-records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("janesmith@example.com", "asd123")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(serviceRecord.getId()));
    }

    @Test
    void updateServiceRecord_shouldUpdateRecordWhenAuthorized() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(serviceRecord);

        mockMvc.perform(put("/service-records/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("janesmith@example.com", "asd123")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(serviceRecord.getId()));
    }

    @Test
    void deleteServiceRecord_shouldDeleteRecordWhenAuthorized() throws Exception {
        mockMvc.perform(delete("/service-records/1")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("janesmith@example.com", "asd123")))
                .andExpect(status().isOk());
    }

    @Test
    void unauthorizedAccess_shouldReturnForbidden() throws Exception {
        // This simulates Jane trying to access a resource not associated with her
        mockMvc.perform(get("/service-records/car/1")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("janesmith@example.com", "asd123")))
                .andExpect(status().isForbidden()); // Expect 403 Forbidden for unauthorized access
    }
}
