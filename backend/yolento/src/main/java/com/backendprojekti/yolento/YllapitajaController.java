package com.backendprojekti.yolento;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class YllapitajaController {
    @GetMapping("/message")
    public String getMessage() {
        return "Tästä voi suorittaa kaikki sinulle oikeutetut toiminnot";
    }
}
