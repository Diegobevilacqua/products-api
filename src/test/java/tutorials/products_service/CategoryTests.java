package tutorials.products_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tutorials.products_service.controller.CategoryController;
import tutorials.products_service.entity.Category;
import tutorials.products_service.service.CategoryService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryTests {
    @Value("${url.endpoint.categories-url}")
    private String categoriesUrl;

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    CategoryController categoryController;

    @Mock
    CategoryService categoryService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetCategories() {
        Category category1 = Category.builder().detail("category1").build();
        Category category2 = Category.builder().detail("category2").build();
        List<Category> expectedCategories = Arrays.asList(category1, category2);

        when(categoryService.getCategories()).thenReturn(expectedCategories);

        List<Category> obtainedCategories = categoryController.getCategories().getBody();
        assertEquals(obtainedCategories, expectedCategories);
    }

    @Test
    void testSaveCategory() throws Exception {
        Category category = Category.builder().detail("category").build();

        mockMvc.perform(MockMvcRequestBuilders.post(categoriesUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(category)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
