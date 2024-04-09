package ru.netology.geo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {
    @DisplayName("GET USA")
    @Test
    void byIpTest() {
        final var ip = GeoServiceImpl.NEW_YORK_IP;
        final var country = Country.USA;
        final var street = " 10th Avenue";
        final var city = "New York";


        GeoServiceImpl geoService = new GeoServiceImpl();
        final Location actual = geoService.byIp(ip);

        Assertions.assertEquals(country, actual.getCountry());
        Assertions.assertEquals(street, actual.getStreet());
        Assertions.assertEquals(city, actual.getCity());
    }

    @DisplayName("Unknown coordinates")
    @Test
    void byIpTestNull() {
        final String ip = "111.";
        GeoServiceImpl geoService = new GeoServiceImpl();
        final Location actual = geoService.byIp(ip);

        Assertions.assertEquals(null,actual);
    }


    @DisplayName("IP Russia")
    @ValueSource(strings = {GeoServiceImpl.MOSCOW_IP, "172."})
    @ParameterizedTest
    void byIpTestParams(String ip) {
        final var expected = Country.RUSSIA;
        GeoServiceImpl geoService = new GeoServiceImpl();
        final Location actual = geoService.byIp(ip);

        Assertions.assertEquals(expected, actual.getCountry());
    }

    @DisplayName("Error")
    @Test
    void byCoordinatesException() {
        final var latitude = 0;
        final var longitude = 0;

        GeoServiceImpl geoService = new GeoServiceImpl();

        assertThrows(RuntimeException.class, () -> geoService.byCoordinates(latitude, longitude));
    }
}