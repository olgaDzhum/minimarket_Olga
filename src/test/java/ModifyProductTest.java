import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.*;
import ru.geekbrains.miniMarket.dto.Product;
import ru.geekbrains.miniMarket.service.ProductService;
import ru.geekbrains.miniMarket.util.RetrofitUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.geekbrains.miniMarket.base.enums.CategoryType.FOOD;

public class ModifyProductTest extends BaseTest {


    @DisplayName("Изменение данных о продукте")
    @SneakyThrows
    @Test
    void modifyProductTest(){
        Product productForChange = new Product()
                .withId(productId)
                .withCategoryTitle(FOOD.getTitle())
                .withPrice(200)
                .withTitle("Сырничек");
        retrofit2.Response<Product> response =
                productService.modifyProduct(productForChange)
                        .execute();
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.body().withCategoryTitle("Сырничек"));
        assertThat(response.body().withId(productId));
    }

    @DisplayName("Изменение данных о продукте, негативный тест")
    @SneakyThrows
    @Test
    void modifyProductNegativeTest(){
        Product productForChange = new Product()
                .withId(0)
                .withCategoryTitle(FOOD.getTitle())
                .withPrice(200)
                .withTitle("Сырничек");
        retrofit2.Response<Product> response =
                productService.modifyProduct(productForChange)
                        .execute();
        assertThat(response.code()).isEqualTo(400);
        response.toString().contains("Product with id: 0 doesn't exist");
    }


}
