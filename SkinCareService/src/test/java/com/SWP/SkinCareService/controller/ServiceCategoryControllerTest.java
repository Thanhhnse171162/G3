package com.SWP.SkinCareService.controller;

import com.SWP.SkinCareService.dto.request.Services.ServiceCategoryRequest;
import com.SWP.SkinCareService.dto.response.ApiResponse;
import com.SWP.SkinCareService.dto.response.Services.ServiceCategoryResponse;
import com.SWP.SkinCareService.service.ServiceCategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ServiceCategoryController.class)
public class ServiceCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ServiceCategoryService serviceCategoryService;

    @Autowired
    private ObjectMapper objectMapper;

    private ServiceCategoryRequest request;
    private ServiceCategoryResponse response;

    @BeforeEach
    void setUp() {
        request = new ServiceCategoryRequest();
        request.setName("Category 1");

        response = new ServiceCategoryResponse();
        response.setId(1);
        response.setName("Category 1");
    }

    @Test
    void createServiceCategory_ReturnCreated() throws Exception {
        when(serviceCategoryService.create(any(ServiceCategoryRequest.class))).thenReturn(response);

        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result.id").value(response.getId()))
                .andExpect(jsonPath("$.result.name").value(response.getName()));

        verify(serviceCategoryService, times(1)).create(any(ServiceCategoryRequest.class));
    }

    @Test
    void getServiceCategoryById_ReturnCategory() throws Exception {
        when(serviceCategoryService.getById(1)).thenReturn(response);

        mockMvc.perform(get("/category/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(1))
                .andExpect(jsonPath("$.result.name").value("Category 1"));

        verify(serviceCategoryService, times(1)).getById(1);
    }

    @Test
    void updateServiceCategory_ReturnUpdatedCategory() throws Exception {
        when(serviceCategoryService.update(eq(1), any(ServiceCategoryRequest.class))).thenReturn(response);

        mockMvc.perform(put("/category/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(1))
                .andExpect(jsonPath("$.result.name").value("Category 1"));

        verify(serviceCategoryService, times(1)).update(eq(1), any(ServiceCategoryRequest.class));
    }

    @Test
    void deleteServiceCategory_ReturnSuccessMessage() throws Exception {
        doNothing().when(serviceCategoryService).delete(1);

        mockMvc.perform(delete("/category/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Service Category delete successfully"));

        verify(serviceCategoryService, times(1)).delete(1);
    }



}
