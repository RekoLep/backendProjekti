package com.backendprojekti.yolento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// Noniin, pidän tätä alueena jossa voi tutkia projektin edistystä 
// 17.3 oli projektin aloitus konstruktorien luomista yms
// 18.3 reacti nyt on saatu toimimaan suurimmalta osalta. Vielä pitää saada muokkaus vuokrasopimuksissa toimimaan
// todo: seuraavaksi lisätään muokkaus ominaisuudet asuntoihin ja poisto mahdollisuus. Sitten voi miettiä miten saadaan luotua käyttäjiä ja luodaan roolit


@SpringBootApplication
public class YolentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(YolentoApplication.class, args);
	}

}
