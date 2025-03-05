package com.SWP.SkinCareService.service;

import com.SWP.SkinCareService.dto.request.Services.ServiceCategoryRequest;
import com.SWP.SkinCareService.dto.response.Services.ServiceCategoryResponse;
import com.SWP.SkinCareService.entity.ServiceCategory;
import com.SWP.SkinCareService.exception.AppException;
import com.SWP.SkinCareService.mapper.ServiceCategoryMapper;
import com.SWP.SkinCareService.repository.ServiceCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ServiceCategoryServiceTest {
    @Mock
    private ServiceCategoryRepository repository;
    @Mock
    private ServiceCategoryMapper mapper;
    @InjectMocks
    private ServiceCategoryService service;

    @Test
    void createServiceCategoryTest() {
        //Tạo request
        ServiceCategoryRequest request = new ServiceCategoryRequest("Category 1");
        //Tạo category
        ServiceCategory category = ServiceCategory.builder().name("Category 1").build();
        //Tạo response
        ServiceCategoryResponse response = new ServiceCategoryResponse();
        response.setId(1);
        response.setName("Category 1");

        Mockito.when(repository.save(any(ServiceCategory.class))).thenReturn(category);
        Mockito.when(mapper.toResponse(any(ServiceCategory.class))).thenReturn(response);

        ServiceCategoryResponse result = service.create(request);
        
        assertNotNull(result);
        assertEquals(response.getId(), result.getId());
        assertEquals(response.getName(), result.getName());

    }
    @Test
    void getServiceCategoryTest() {
        int id = 1;
        //Tạo category
        ServiceCategory category = ServiceCategory.builder().id(1).name("Category 1").build();
        //Tạo response
        ServiceCategoryResponse response = new ServiceCategoryResponse();
        response.setId(1);
        response.setName("Category 1");

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(category));
        Mockito.when(mapper.toResponse(any(ServiceCategory.class))).thenReturn(response);

        ServiceCategoryResponse result = service.getById(id);

        assertEquals(result.getId(), response.getId());
        assertEquals(result.getName(), response.getName());
    }
    @Test
    void getNonExistServiceCategoryTest() {
        int id = 999;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(AppException.class, () -> service.getById(id));
    }
    @Test
    void updateServiceCategoryTest() {
        int id = 1;
        //Tạo request
        ServiceCategoryRequest updateRequest = new ServiceCategoryRequest("Updated category 1");
        //Tạo category hiện có
        ServiceCategory existing = ServiceCategory.builder().id(id).name("Category 1").build();
        //Category sau khi update
        ServiceCategory updated = ServiceCategory.builder().id(id).name("Updated category 1").build();
        // Tạo response
        ServiceCategoryResponse updatedResponse = new ServiceCategoryResponse();
        updatedResponse.setId(id);
        updatedResponse.setName("Updated category 1");

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(existing));
        Mockito.when(repository.save(any(ServiceCategory.class))).thenReturn(updated);
        Mockito.when(mapper.toResponse(any(ServiceCategory.class))).thenReturn(updatedResponse);

        ServiceCategoryResponse result = service.update(id, updateRequest);

        assertNotNull(result);
        assertEquals(updatedResponse.getId(), result.getId());
        assertEquals(updatedResponse.getName(), result.getName());
    }
    @Test
    void updateServiceCategoryExceptionTest() {
        int id = 999;
        //Tạo request
        ServiceCategoryRequest updateRequest = new ServiceCategoryRequest("Updated category 1");

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(AppException.class, () -> service.update(id, updateRequest));
    }
    @Test
    void deleteServiceCategoryTest() {
        int id = 1;
        //Tạo category hiện có
        ServiceCategory existing = ServiceCategory.builder().id(id).name("Category 1").build();

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(existing));

        service.delete(id);
    }
    @Test
    void deleteNonExistingCategoryTest() {
        int id = 999;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(AppException.class, () -> service.delete(id));
    }
}
