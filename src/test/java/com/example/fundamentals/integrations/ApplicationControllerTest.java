package com.example.fundamentals.integrations;

import com.example.fundamentals.controllers.ApplicationController;
import com.example.fundamentals.dtos.ApplicationDto;
import com.example.fundamentals.dtos.BugTrackerResponse;
import com.example.fundamentals.services.ApplicationService;
import com.example.fundamentals.utils.Constants;
import com.example.fundamentals.utils.ResponseUtilService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



public class ApplicationControllerTest extends BaseIntegrationTest {

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


}
