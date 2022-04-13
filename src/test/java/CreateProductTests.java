import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.*;
import ru.geekbrains.miniMarket.dto.Product;
import ru.geekbrains.miniMarket.service.ProductService;
import ru.geekbrains.miniMarket.util.RetrofitUtils;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.geekbrains.miniMarket.base.enums.CategoryType.FOOD;

public class CreateProductTests {
    private static ProductService productService;
    private static int productId;
    private Product product;

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withCategoryTitle(FOOD.getTitle())
                .withPrice(200)
                .withTitle("Маслице");
    }

    @DisplayName("Создать новый продукт(Позитивный тест)")
    @SneakyThrows
    @Test
    void createNewProductTest() {
        retrofit2.Response<Product> response =
                productService.createProduct(product)
                        .execute();
        productId = response.body().getId();
        assertThat(response.isSuccessful()).isTrue();
    }

    @DisplayName("Создать новый продукт(Негативный тест)")
    @SneakyThrows
    @Test
    void createNewProductNegativeTest() {
        retrofit2.Response<Product> response =
                productService.createProduct(product.withId(1245))
                        .execute();

        assertThat(response.code()).isEqualTo(400);
        response.toString().contains("Id must be null for new entity");
    }





   @AfterAll
    static void afterAll() {
        try {
            retrofit2.Response<ResponseBody> response =
                    productService.deleteProduct(productId)
                            .execute();
            assertThat(response.isSuccessful()).isTrue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @AfterEach
    void tearDown() {


    }


}
