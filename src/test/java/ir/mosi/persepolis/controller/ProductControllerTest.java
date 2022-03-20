package ir.mosi.persepolis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mosi.persepolis.exception.ProductNotFoundException;
import ir.mosi.persepolis.model.entity.Product;
import ir.mosi.persepolis.model.service.ProductService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String baseUri = "/rest/api/product/";

    @Test
    @Order(1)
    public void findAll_ReturnsProductList() throws Exception {
        List<Product> productList = Arrays.asList(new Product(2L, "GT-R R35"), new Product(3L, "Supra"));
        Mockito.when(productService.findAll()).thenReturn(productList);

        mockMvc
                .perform(MockMvcRequestBuilders.get(baseUri))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("GT-R R35"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Supra"))
        ;
    }

    @Test
    @Order(2)
    public void findById_WithExistingProduct_ReturnsProduct() throws Exception {
        Mockito.when(productService.findById(2L)).thenReturn(new Product(2L, "GT-R R35"));

        mockMvc
                .perform(MockMvcRequestBuilders.get(baseUri + 2))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("GT-R R35"))
        ;
    }

    @Test
    @Order(3)
    public void findById_WithWrongId_ReturnsProductNotFoundException() throws Exception {
        Mockito.when(productService.findById(10L)).thenThrow(
                new ProductNotFoundException("Product not found with id : " + 10L));

        mockMvc
                .perform(MockMvcRequestBuilders.get(baseUri + 10))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductNotFoundException))
                .andExpect(result -> assertEquals("Product not found with id : " + 10L,
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @Order(4)
    public void createProduct_ReturnsProduct() throws Exception {
        //need to implement for get the product table sequence
        Product expected = new Product(1L, "ae86");
        Product input = new Product(1L, "ae86");

        Mockito.when(productService.create(input)).thenReturn(expected);
        String jsonStr = objectMapper.writeValueAsString(input);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(baseUri).content(jsonStr).contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ae86"))
        ;
    }

    @Test
    @Order(5)
    public void updateProduct_ReturnsProduct() throws Exception {
        Product input = new Product(2L, "ae86");
        Mockito.when(productService.update(2L, input)).thenReturn(input);
        String jsonStr = objectMapper.writeValueAsString(input);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(baseUri + "{id}", 2)
                        .content(jsonStr)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ae86"))
        ;
    }

    @Test
    @Order(6)
    public void updateProduct_WithWrongId_ReturnsProductNotFoundException() throws Exception {
        Product input = new Product(11L, "LFA");
        Mockito.when(productService.update(11L, input)).thenThrow(
                new ProductNotFoundException("Product not found with id : " + 11L));
        String jsonStr = objectMapper.writeValueAsString(input);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(baseUri + "{id}", 11)
                        .content(jsonStr)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductNotFoundException))
                .andExpect(result -> assertEquals("Product not found with id : " + 11,
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @Order(7)
    public void deleteProduct_ReturnsAccepted() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete(baseUri + "{id}", 2))
                .andExpect(status().isAccepted());
    }

    @Test
    @Order(8)
    public void deleteProduct_WithWrongId_ReturnsProductNotFoundException() throws Exception {
        Mockito.doThrow(new ProductNotFoundException("Product not found with id : " + 12))
                .when(productService).delete(12L);

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete(baseUri + "{id}", 12))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductNotFoundException))
                .andExpect(result -> assertEquals("Product not found with id : " + 12,
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}
