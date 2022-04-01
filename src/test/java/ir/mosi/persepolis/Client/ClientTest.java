package ir.mosi.persepolis.Client;

import ir.mosi.persepolis.client.Client;
import ir.mosi.persepolis.model.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ClientTest {

    @Test
    public void getAll_ReturnsProductList() {
        List<Product> productList = Client.getAllProducts();
        System.out.println(productList);
        Assertions.assertEquals(2, productList.size());
    }
}
