package com.backendprojekti.yolento;

import com.backendprojekti.yolento.domain.Asunto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class YolentoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testAsuntoConstructorAndGetters() {
        // Luodaan uusi Asunto-olio konstruktorilla
        Asunto asunto = new Asunto("Testikatu 1", "Helsinki", "Kallio", "Vuokrattu");

        // Testataan getterit
        assertEquals("Testikatu 1", asunto.getOsoite());
        assertEquals("Helsinki", asunto.getKaupunki());
        assertEquals("Kallio", asunto.getKaupunginOsa());
        assertEquals("Vuokrattu", asunto.getStatus());
    }

    @Test
    void testAsuntoSetters() {
        // Luodaan uusi Asunto-olio ilman parametreja
        Asunto asunto = new Asunto();

        // Asetetaan arvot
        asunto.setOsoite("Testitie 2");
        asunto.setKaupunki("Espoo");
        asunto.setKaupunginOsa("Tapiola");
        asunto.setStatus("Vapaa");

        // Testataan setterit ja getterit
        assertEquals("Testitie 2", asunto.getOsoite());
        assertEquals("Espoo", asunto.getKaupunki());
        assertEquals("Tapiola", asunto.getKaupunginOsa());
        assertEquals("Vapaa", asunto.getStatus());
    }

    @Test
    void testAsuntoToString() {
        // Luodaan uusi Asunto-olio
        Asunto asunto = new Asunto("Esimerkkikatu 3", "Vantaa", "Myyrmäki", "Vuokrattu");

        // Testataan toString-metodia
        String expected = "Asunto [asuntoId=null, osoite=Esimerkkikatu 3, kaupunki=Vantaa, kaupunginOsa=Myyrmäki, status=Vuokrattu]";
        assertEquals(expected, asunto.toString());
    }
}
