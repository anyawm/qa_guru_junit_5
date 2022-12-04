package stud.anna;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SelenideWebTest {

    @BeforeEach
    void setUp() {
        open("https://selenide.org");
    }

    static Stream<Arguments> selenideButtonsTest() {
        return Stream.of(
                Arguments.of("EN", List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")),
                Arguments.of("RU", List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка наличия кнопок {1} " + "на сайте селенида {0}")
    @Tag("BLOCKER")
    void selenideButtonsTest(String locale, List<String> buttons) {
$$("#languages a").find(text(locale)).click(); // а - все кнопки
$$(".main-menu-pages a").filter(visible)
        .shouldHave(CollectionCondition.texts(buttons));
    }

}
