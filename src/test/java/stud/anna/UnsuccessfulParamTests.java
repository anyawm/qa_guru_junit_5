package stud.anna;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.*;

public class UnsuccessfulParamTests {

    @BeforeEach
    void setUp() {
        open("https://hochupuri.com/");
        Configuration.holdBrowserOpen = true;
    }

    @CsvSource(value = {
            "Барнаул , Грузинский ресторанчик «Хочу Пури» в Барнауле , 1",
            "Пермь ,  Грузинский ресторанчик «Хочу Пури» в Перми , 1"
    }
    )

    @ParameterizedTest(name = "Проверка наличия ресторанов {1} " + "при выборе города {0}")
    @Tag("BLOCKER")
    void chooseCity(String searchCity, String expectedRest, int resultCount) {
        $x("//a[contains(text(),'searchCity')]").click();
        $x("//h1[contains(.,'expectedRest')]");
        $$("[id=search]").should(CollectionCondition.size(resultCount)); //если надо посчитать результат
    }

    @Test
    void TestLang () {
        //$(".hp-cities li:nth-child(4) > a").click();
        $x("//a[contains(text(),'Барнаул')]").click();
        //$(".border-thin > .shnobel").shouldHave(text("Грузинский ресторанчик «Хочу Пури» в Барнауле"));
        $x("//h1[contains(.,'Грузинский ресторанчик «Хочу Пури» в Барнауле')]");
    }

}
