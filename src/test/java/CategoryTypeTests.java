import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.geekbrains.miniMarket.base.enums.CategoryType;
import ru.geekbrains.miniMarket.dto.Category;
import ru.geekbrains.miniMarket.service.CategoryService;
import ru.geekbrains.miniMarket.util.RetrofitUtils;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.geekbrains.miniMarket.base.enums.CategoryType.FOOD;

public class CategoryTypeTests {


   static CategoryService categoryService;

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);

    }

    @DisplayName("Проверка категории товара")
    @Test
    void getFoodCategoryPositiveTest() throws IOException {
        Response<Category> response = categoryService
                .getCategory(FOOD.getId()).
                execute();
        assertThat(response.body().getId()).isEqualTo(1);
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.body().getTitle()).isEqualTo(FOOD.getTitle());
    }

}
