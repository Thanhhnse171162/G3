package com.SWP.SkinCareService.controller;

import com.SWP.SkinCareService.dto.request.Services.ServiceCategoryRequest;
import com.SWP.SkinCareService.dto.response.ApiResponse;
import com.SWP.SkinCareService.dto.response.Services.ServiceCategoryResponse;
import com.SWP.SkinCareService.exception.AppException;
import com.SWP.SkinCareService.exception.ErrorCode;
import com.SWP.SkinCareService.service.ServiceCategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceCategoryControllerTest {

    @Mock
    private ServiceCategoryService serviceCategoryService;

    @InjectMocks
    private ServiceCategoryController serviceCategoryController;

    private ObjectMapper objectMapper;
    private ServiceCategoryRequest request;
    private ServiceCategoryResponse response;
    private List<ServiceCategoryResponse> responseList;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        
        request = new ServiceCategoryRequest();
        request.setName("Test Category");

        response = new ServiceCategoryResponse();
        response.setId(1);
        response.setName("Test Category");

        responseList = Arrays.asList(response);
    }

    @Test
    void createServiceCategory_WithValidData_ShouldReturnCreatedResponse() {
        when(serviceCategoryService.create(any(ServiceCategoryRequest.class))).thenReturn(response);

        ResponseEntity<?> result = serviceCategoryController.createServiceCategory(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(response, ((ApiResponse<?>) result.getBody()).getResult());
        verify(serviceCategoryService).create(request);
    }

    @Test
    void createServiceCategory_WithNullName_ShouldReturnBadRequest() {
        request.setName(null);

        ResponseEntity<?> result = serviceCategoryController.createServiceCategory(request);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(serviceCategoryService, never()).create(any());
    }

    @Test
    void getServiceCategoryById_WithValidId_ShouldReturnOkResponse() {
        when(serviceCategoryService.getById(anyInt())).thenReturn(response);

        ResponseEntity<?> result = serviceCategoryController.getServiceCategoryById(1);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(response, ((ApiResponse<?>) result.getBody()).getResult());
        verify(serviceCategoryService).getById(1);
    }

    @Test
    void getServiceCategoryById_WithInvalidId_ShouldReturnNotFound() {
        when(serviceCategoryService.getById(anyInt())).thenThrow(new AppException(ErrorCode.CATEGORY_NOT_EXISTED));

        ResponseEntity<?> result = serviceCategoryController.getServiceCategoryById(999);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(serviceCategoryService).getById(999);
    }

    @Test
    void getAllServiceCategory_ShouldReturnOkResponse() {
        when(serviceCategoryService.getAll()).thenReturn(responseList);

        ResponseEntity<?> result = serviceCategoryController.getAllServiceCategory();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(responseList, ((ApiResponse<?>) result.getBody()).getResult());
        verify(serviceCategoryService).getAll();
    }

    @Test
    void getAllServiceCategory_WhenEmpty_ShouldReturnEmptyList() {
        when(serviceCategoryService.getAll()).thenReturn(Arrays.asList());

        ResponseEntity<?> result = serviceCategoryController.getAllServiceCategory();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(((List<?>) ((ApiResponse<?>) result.getBody()).getResult()).isEmpty());
        verify(serviceCategoryService).getAll();
    }

    @Test
    void updateServiceCategory_WithValidData_ShouldReturnOkResponse() {
        when(serviceCategoryService.update(anyInt(), any(ServiceCategoryRequest.class))).thenReturn(response);

        ResponseEntity<?> result = serviceCategoryController.updateServiceCategory(1, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(response, ((ApiResponse<?>) result.getBody()).getResult());
        verify(serviceCategoryService).update(1, request);
    }

    @Test
    void updateServiceCategory_WithInvalidId_ShouldReturnNotFound() {
        when(serviceCategoryService.update(anyInt(), any(ServiceCategoryRequest.class))).thenThrow(new AppException(ErrorCode.CATEGORY_NOT_EXISTED));

        ResponseEntity<?> result = serviceCategoryController.updateServiceCategory(999, request);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(serviceCategoryService).update(999, request);
    }

    @Test
    void deleteServiceCategory_WithValidId_ShouldReturnOkResponse() {
        doNothing().when(serviceCategoryService).delete(anyInt());

        ResponseEntity<?> result = serviceCategoryController.deleteServiceCategory(1);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("Service Category delete successfully", ((ApiResponse<?>) result.getBody()).getMessage());
        verify(serviceCategoryService).delete(1);
    }

    @Test
    void deleteServiceCategory_WithInvalidId_ShouldReturnNotFound() {
        doThrow(new AppException(ErrorCode.CATEGORY_NOT_EXISTED)).when(serviceCategoryService).delete(anyInt());

        ResponseEntity<?> result = serviceCategoryController.deleteServiceCategory(999);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(serviceCategoryService).delete(999);
    }
}
