package stud.anna;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

// @Disabled может стоять и над классом
public class SimpleTest {

    @Disabled("номер задачи в Jira") //выключает тест из ряда тестов
    @DisplayName("Проверка факта, что 3 > 2") //понятное название теста в результатах
    @Test
    void firstTest() {
        assertTrue(3 > 2);
    }

    @DisplayName("Проверка факта, что 3 > 1")
    @Test
    void secondTest() {
        assertTrue(3 > 1);
    }
}
