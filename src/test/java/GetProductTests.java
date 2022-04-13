import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.geekbrains.miniMarket.dto.Product;
import ru.geekbrains.miniMarket.service.ProductService;
import ru.geekbrains.miniMarket.util.RetrofitUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.geekbrains.miniMarket.base.enums.CategoryType.FOOD;

public class GetProductTests extends BaseTest  {




    @DisplayName("Получить полный список продуктов")
    @Test
    void getAllProductsTest() throws IOException {

        retrofit2.Response<List<Product>> response =
                productService.getAllProducts()
                        .execute();
        assertThat(response.isSuccessful()).isTrue();
        response.toString().toLowerCase(Locale.ROOT).contains("id="+productId);
        response.body().get(0).withId(productId);
        response.body().get(0).withTitle("Хлебушек");


        }



    @DisplayName("Найти продукт по ID")
    @Test
    void getProductByIdTest(){
        try {
            retrofit2.Response<Product> response =
                    productService.getProductById(productId)
                            .execute();
            assertThat(response.isSuccessful()).isTrue();
            assertThat(response.body().getId()).isEqualTo(productId);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @DisplayName("Найти продукт по ID, негативный тест")
    @Test
    void getProductByIdNegativeTest(){
        try {
            retrofit2.Response<Product> response =
                    productService.getProductById(0)
                            .execute();
            assertThat(response.code()).isEqualTo(404);
            response.toString().contains("Unable to find product with id: 0");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
