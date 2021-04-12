package com.example.fundamentals.integrations;

import com.example.fundamentals.dtos.ApplicationDto;
import com.example.fundamentals.dtos.BugTrackerResponse;
import com.example.fundamentals.exceptions.ResourceNotFoundException;
import com.example.fundamentals.models.Application;
import com.example.fundamentals.services.ApplicationService;
import com.example.fundamentals.utils.BaseApplicationControllerTest;
import com.example.fundamentals.utils.Constants;
import com.example.fundamentals.utils.ResponseUtilService;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;


import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



public class ApplicationControllerTest extends BaseApplicationControllerTest {

    @SpyBean
    private ApplicationService applicationService;

    private ResponseUtilService responseUtilService;

    @Test
    public void testGetAllApplications() throws Exception {
        doReturn(BugTrackerResponse.builder()
                .requestSuccessful(true)
                .data(Arrays.asList(
                        ApplicationDto.builder()
                                .code("rajui")
                                .description("Random description")
                                .name("Test application name")
                                .build()
                ))
                .message(Constants.SUCCESS_RESPONSE_MESSAGE)
                .build())
                .when(applicationService).retrieveApplications();
        mockMvc.perform(get(Constants.API_VERSION_1+ "applications")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.requestSuccessful").value(true))
                .andExpect(jsonPath("$.message").value(Constants.SUCCESS_RESPONSE_MESSAGE));

        verify(applicationService, times(1)).retrieveApplications();
    }

    @Test
    public void testSuccessfullyGetApplicationByCode() throws Exception {

        Application application = seedTestApplication();
        ApplicationDto applicationDto = buildApplicationDtoFromApplication(application);

        doReturn(BugTrackerResponse.builder()
                .requestSuccessful(true)
                .data(applicationDto)
                .message(Constants.SUCCESS_RESPONSE_MESSAGE)
                .build())
                .when(applicationService).retrieveByCode(application.getCode());

        mockMvc.perform(get(String.format("%sapplications/%s",Constants.API_VERSION_1, application.getCode()))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.requestSuccessful").value(true))
                .andExpect(jsonPath("$.message").value(Constants.SUCCESS_RESPONSE_MESSAGE));

        verify(applicationService, times(1)).retrieveByCode(application.getCode());
    }

    @Test
    public void testGetApplicationByCodeReturnsErrorForInvalidCode() throws Exception {
        String invalidCode = "invalid-code";
        doThrow(ResourceNotFoundException.class)
                .when(applicationService).retrieveByCode(invalidCode);

        mockMvc.perform(get(String.format("%sapplications/%s",Constants.API_VERSION_1, invalidCode))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data").doesNotExist())
                .andExpect(jsonPath("$.requestSuccessful").value(false));

        verify(applicationService, times(1)).retrieveByCode(invalidCode);
    }




}
