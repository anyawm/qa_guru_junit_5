package stud.anna;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HWParamTests {

    @BeforeEach
    void setUp() {
        open("https://66.ru/health/");
    }

    @CsvSource(value = {
            "ибупрофен , по запросу «ибупрофен»",
            "аскорбиновая кислота ,  1000 результатов по запросу «аскорбиновая кислота»"
    }
    )

    @ParameterizedTest(name = "Поиск лекарства {1} " + "по запросу {0}")
    @Tag("BLOCKER")
    void chooseRemedy(String searchRemedy, String expectedRemedy) {
        $("#health_form_ac").setValue(searchRemedy).pressEnter();
        $(".drugs-results__heading").shouldHave(text(expectedRemedy));
    }

       static Stream<Arguments> ButtonsTest() {
        return Stream.of(
                Arguments.of("Еда", List.of("perm_identity", "ресторанная критика", "уличная еда", "дегустация")),
                Arguments.of("Поиск лекарств", List.of("perm_identity", "здоровье", "поиск лекарств", "клиники")),
                Arguments.of("Недвижимость", List.of("perm_identity", "новости", "новостройки", "загородное жилье")),
                Arguments.of("Финансы", List.of("perm_identity", "новости", "банки екатеринбурга", "курсы валют")),
                Arguments.of("Авто", List.of("perm_identity", "главное за день", "лучшее за неделю", "хиты"))
        );
    }
    @MethodSource
    @ParameterizedTest(name = "Проверка наличия кнопок {1} " + "при выборе раздела в меню {0}")
    @Tag("BLOCKER")
    void buttonsTest(String menu, List<String> buttons) {
        $$("#nav-mobile a").find(text(menu)).click();
        $$(".tab a").filter(visible)
                .shouldHave(CollectionCondition.texts(buttons));
    }

    @Disabled
    @Test
    void testRemedy () {
        $("#health_form_ac").setValue("ибупрофен").pressEnter();
        $(".drugs-results__heading").shouldHave(text("по запросу «ибупрофен»"));
    }

}
