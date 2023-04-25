package com.example.demowithtests.passport;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.domain.PassportStatus;
import com.example.demowithtests.dto.PassportRequestDto;
import com.example.demowithtests.dto.PassportResponseDto;
import com.example.demowithtests.service.PassportService;
import com.example.demowithtests.web.passportControllers.PassportControllerBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@WebMvcTest(PassportControllerBean.class)
@DisplayName("Passport controller test")
public class PassportControllerTests {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private PassportService passportService;

    @Autowired
    private MockMvc mockMvc;

    private Passport passport;
    private PassportRequestDto passportRequestDto;
    private PassportResponseDto passportResponseDto;

    @Before
    public void setup() {
        passport = new Passport(1, "Mark", LocalDate.now(), "XX0000XX", LocalDate.now().plusYears(5),
                new Employee(), Boolean.TRUE, Boolean.FALSE, PassportStatus.ACTIVE, null);

        passportRequestDto = new PassportRequestDto();
        passportRequestDto.name = passport.getName();
        passportRequestDto.dateOfBirth = passport.getDateOfBirth();
        passportRequestDto.expireDate = passport.getExpireDate();
        passportRequestDto.status = passport.getStatus();

        passportResponseDto = new PassportResponseDto();
        passportResponseDto.name = passport.getName();
        passportResponseDto.status = passport.getStatus();
        passportResponseDto.dateOfBirth = passport.getDateOfBirth();
        passportResponseDto.expireDate = passport.getExpireDate();
        passportResponseDto.serialNumber = passport.getSerialNumber();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    public void savePassportSuccess() throws Exception {
        when(passportService.create(any(PassportRequestDto.class))).thenReturn(passportResponseDto);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/passports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(passportRequestDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", CoreMatchers.is("Mark")));

    }

    @Test
    public void shouldReturnFiveGeneratedPassportsAsResponseDto() throws Exception {
        List<PassportResponseDto> generatedPassports = new ArrayList<>();
        generatedPassports.add(passportResponseDto);
        generatedPassports.add(passportResponseDto);
        generatedPassports.add(passportResponseDto);
        generatedPassports.add(passportResponseDto);
        generatedPassports.add(passportResponseDto);
        when(passportService.generateFreePassports()).thenReturn(generatedPassports);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/passports/generate/5")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldChangePassportAndReturnResponseDto() throws Exception {
        when(passportService.updateById(passport.getId(), passportRequestDto)).thenReturn(passportResponseDto);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/passports/generate/5")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(passportRequestDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

    }

}
