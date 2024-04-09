package ru.netology.i18n;

import org.junit.jupiter.api.*;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {
    private static LocalizationServiceImpl localizationService;
    @BeforeAll
    static void setUp() {
        localizationService = new LocalizationServiceImpl();
    }

    @DisplayName("Russian text")
    @Test
    void localeTestRUS() {
        final var country = Country.RUSSIA;

        final var expected = "Добро пожаловать";

        final var actual = localizationService.locale(country);

        assertEquals(expected, actual);

    }

    @DisplayName("English text")
    @Test
    void localeTestUsa() {
        final var country = Country.USA;

        final var expected = "Welcome";

        final var actual = localizationService.locale(country);

        assertEquals(expected, actual);

    }
}