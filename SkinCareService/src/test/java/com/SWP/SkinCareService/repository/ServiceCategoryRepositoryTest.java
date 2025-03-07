package com.SWP.SkinCareService.repository;

import com.SWP.SkinCareService.entity.ServiceCategory;
import com.SWP.SkinCareService.entity.Services;
import com.SWP.SkinCareService.entity.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class ServiceCategoryRepositoryTest {

    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    private ServiceCategory category;

    @BeforeEach
    void setUp() {
        category = new ServiceCategory();
        category.setName("Test Category");
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    void existsByName_WhenNameExists_ShouldReturnTrue() {
        // Arrange
        serviceCategoryRepository.save(category);

        // Act
        boolean exists = serviceCategoryRepository.existsByName("Test Category");

        // Assert
        assertTrue(exists);
    }

    @Test
    void existsByName_WhenNameDoesNotExist_ShouldReturnFalse() {
        // Act
        boolean exists = serviceCategoryRepository.existsByName("Non-existent Category");

        // Assert
        assertFalse(exists);
    }

    @Test
    void existsByName_WhenNameIsNull_ShouldReturnFalse() {
        // Act
        boolean exists = serviceCategoryRepository.existsByName(null);

        // Assert
        assertFalse(exists);
    }

    @Test
    void save_ShouldPersistCategoryWithAllFields() {
        // Act
        ServiceCategory savedCategory = serviceCategoryRepository.save(category);

        // Assert
        assertNotNull(savedCategory.getId());
        assertEquals("Test Category", savedCategory.getName());
        assertNotNull(savedCategory.getCreatedAt());
        assertNotNull(savedCategory.getUpdatedAt());
    }

    @Test
    void save_WithServices_ShouldPersistCategoryAndServices() {
        // Arrange
        Services service = new Services();
        service.setName("Test Service");
        service.setServiceCategory(category);
        service.setPrice(new BigDecimal("100000"));
        service.setDuration(60);
        service.setSession(1);
        service.setActive(false);
        category.setServices(Arrays.asList(service));

        // Act
        ServiceCategory savedCategory = serviceCategoryRepository.save(category);

        // Assert
        assertNotNull(savedCategory.getId());
        assertNotNull(savedCategory.getServices());
        assertEquals(1, savedCategory.getServices().size());
        assertEquals("Test Service", savedCategory.getServices().get(0).getName());
    }

    @Test
    void save_WithQuiz_ShouldPersistCategoryAndQuiz() {
        // Arrange
        Quiz quiz = new Quiz();
        quiz.setServiceCategory(category);
        category.setQuiz(Arrays.asList(quiz));

        // Act
        ServiceCategory savedCategory = serviceCategoryRepository.save(category);

        // Assert
        assertNotNull(savedCategory.getId());
        assertNotNull(savedCategory.getQuiz());
        assertEquals(1, savedCategory.getQuiz().size());
    }

    @Test
    void findById_WhenCategoryExists_ShouldReturnCategory() {
        // Arrange
        ServiceCategory savedCategory = serviceCategoryRepository.save(category);

        // Act
        ServiceCategory foundCategory = serviceCategoryRepository.findById(savedCategory.getId()).orElse(null);

        // Assert
        assertNotNull(foundCategory);
        assertEquals(savedCategory.getId(), foundCategory.getId());
        assertEquals(savedCategory.getName(), foundCategory.getName());
    }

    @Test
    void findById_WhenCategoryDoesNotExist_ShouldReturnNull() {
        // Act
        ServiceCategory foundCategory = serviceCategoryRepository.findById(999).orElse(null);

        // Assert
        assertNull(foundCategory);
    }

    @Test
    void findAll_ShouldReturnAllCategories() {
        // Arrange
        ServiceCategory category2 = new ServiceCategory();
        category2.setName("Test Category 2");
        serviceCategoryRepository.save(category);
        serviceCategoryRepository.save(category2);

        // Act
        List<ServiceCategory> categories = serviceCategoryRepository.findAll();

        // Assert
        assertEquals(2, categories.size());
        assertTrue(categories.stream().anyMatch(c -> c.getName().equals("Test Category")));
        assertTrue(categories.stream().anyMatch(c -> c.getName().equals("Test Category 2")));
    }

    @Test
    void delete_ShouldRemoveCategory() {
        // Arrange
        ServiceCategory savedCategory = serviceCategoryRepository.save(category);

        // Act
        serviceCategoryRepository.delete(savedCategory);

        // Assert
        assertFalse(serviceCategoryRepository.existsById(savedCategory.getId()));
    }
} 