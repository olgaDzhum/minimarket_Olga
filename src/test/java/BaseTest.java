import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import ru.geekbrains.miniMarket.dto.Product;
import ru.geekbrains.miniMarket.service.ProductService;
import ru.geekbrains.miniMarket.util.RetrofitUtils;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.geekbrains.miniMarket.base.enums.CategoryType.FOOD;

public class BaseTest {

   protected static ProductService productService;
    protected static Integer productId;
    protected Product product;

    @SneakyThrows
    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
        Product product = new Product()
                .withCategoryTitle(FOOD.getTitle())
                .withPrice(200)
                .withTitle("Хлебушек");
        retrofit2.Response<Product> response =
                productService.createProduct(product)
                        .execute();
        productId = response.body().getId();
        System.out.println(productId);
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

}
