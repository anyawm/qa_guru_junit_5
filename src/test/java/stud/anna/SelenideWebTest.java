package stud.anna;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GoogleWebTest {

    @BeforeEach
    void setUp() {
        open("https://google.com");
    }

/*    @CsvFileSource (
            resources = "example.csv" //если большой файл
    )*/
    @CsvSource(value = {
            "selenide , https://ru.selenide.org , 1",
            "junit 5 ,  https://junit.org , 1"
    }
    //,delimiter = '|' //если в данных используется запятая и как разделитель она не подходит
    )

    @ParameterizedTest(name = "Проверка наличия урла {1} " + "в результатах выдачи гугла на запросу {0}")
    @Tags({@Tag("BLOCKER"), @Tag("FEATURE")})
    void googleSearchTest(String searchQuery, String expectedUrl, int resultCount) {
        $("[name=q]").setValue(searchQuery).pressEnter();
        $("[id=search]").shouldHave(text(expectedUrl));
        $$("[id=search]").should(CollectionCondition.size(resultCount)); //если надо посчитать результат
    }

    @ValueSource(strings = {"selenide", "junit 5"}) //если одна тест дата в тексте
   // @ValueSource(ints = {100, 45, 5}) //если надо числа
    @DisplayName("Popup Photo")
    @ParameterizedTest
    @Tag("BLOCKER")
    void googlePhotoPopupButtonTest(String arg) {
        $("img[alt='Поиск с помощью камеры']").click();
        $(byText("Выполните поиск по изображению в Google Объективе")).shouldBe(visible);
    }

    @ParameterizedTest
    @Tag("BLOCKER")
    void selenideButtonsTest(String locale, List<String> buttons) {

    }


    @DisplayName("Popup Photo")
    @Test
    @Tag("BLOCKER")
    void googlePhotoPopupOrigin() {
        $("img[alt='Поиск с помощью камеры']").click();
        $(byText("Выполните поиск по изображению в Google Объективе")).shouldBe(visible);
    }
}
