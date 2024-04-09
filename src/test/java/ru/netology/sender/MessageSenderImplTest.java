package ru.netology.sender;

import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import java.util.HashMap;
import java.util.Map;


class MessageSenderImplTest {


    @DisplayName("Russian langauge")
    @Test
    void sendTestRus() {

        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);

        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("Mockow", Country.RUSSIA,
                null, 0));
        Mockito.when(localizationService.locale(Mockito.any())).thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.");
        String expected = "Добро пожаловать";
        String actual = messageSender.send(headers);

        Assertions.assertEquals(expected, actual);

    }


    @DisplayName("Calling method")
    @Test
    void sendTestRusVerify() {

        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);

        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("Mockow", Country.RUSSIA,
                null, 0));
        Mockito.when(localizationService.locale(Mockito.any())).thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.");
        String expected = "Добро пожаловать";
        String actual = messageSender.send(headers);
        Mockito.verify(geoService, Mockito.times(1)).byIp("172.");

    }

    @DisplayName("English language")
    @Test
    void sendTestUsa() {

        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);

        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("NY", Country.USA,
                null, 0));
        Mockito.when(localizationService.locale(Mockito.any())).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.");
        String expected = "Welcome";
        String actual = messageSender.send(headers);

        Assertions.assertEquals(expected, actual);

    }

    @DisplayName("ArgumentCaptor")
    @Test
    void sendTestRusCaptor() {

        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);

        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("Mockow", Country.RUSSIA,
                null, 0));
        Mockito.when(localizationService.locale(Mockito.any())).thenReturn("Добро пожаловать");

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.");
        messageSender.send(headers);


        Mockito.verify(geoService).byIp(argumentCaptor.capture());
        Assertions.assertEquals(headers.get(MessageSenderImpl.IP_ADDRESS_HEADER), argumentCaptor.getValue());
    }
}