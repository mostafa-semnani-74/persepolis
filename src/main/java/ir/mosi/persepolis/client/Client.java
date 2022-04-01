package ir.mosi.persepolis.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mosi.persepolis.model.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Client {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String PRODUCT_CONTROLLER_BASE_URL = "http://localhost:9092/rest/api/product/";

    public static List<Product> getAllProducts() {
        ResponseEntity<String> getAllProductsResponseStr
                = restTemplate.getForEntity(PRODUCT_CONTROLLER_BASE_URL, String.class);

        List<Product> productList = null;
        try {
            productList = objectMapper.readValue(getAllProductsResponseStr.getBody(), new TypeReference<List<Product>>() {
            });
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException in Client Class in getAllProducts method in readValue of ObjectMapper");
        }

        return productList;
    }
}
