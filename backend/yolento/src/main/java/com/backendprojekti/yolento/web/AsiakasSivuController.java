package com.backendprojekti.yolento.web;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.backendprojekti.yolento.domain.Asiakkaat;
import com.backendprojekti.yolento.domain.AsiakkaatRepository;
import com.backendprojekti.yolento.domain.Asunto;
import com.backendprojekti.yolento.domain.AsuntoRepository;
import com.backendprojekti.yolento.domain.Vuokrasopimus;
import com.backendprojekti.yolento.domain.VuokrasopimusRepository;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class AsiakasSivuController {

    private final AsiakkaatRepository asiakkaatRepository;
    private final AsuntoRepository asuntoRepository;
    private final VuokrasopimusRepository vuokrasopimusRepository;

    public AsiakasSivuController(AsiakkaatRepository asiakkaatRepository, AsuntoRepository asuntoRepository,
            VuokrasopimusRepository vuokrasopimusRepository) {
        this.asiakkaatRepository = asiakkaatRepository;
        this.asuntoRepository = asuntoRepository;
        this.vuokrasopimusRepository = vuokrasopimusRepository;
    }

    // Etusivu
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // Asiakkaiden listaus
    @GetMapping("/asiakkaat-nakyma")
    public String naytaAsiakkaat(Model model) {
        List<Asiakkaat> asiakkaat = asiakkaatRepository.findAll();
        model.addAttribute("asiakkaat", asiakkaat);
        return "asiakkaat";
    }

    // Asuntojen listaus
    @GetMapping("/asunnot-nakyma")
    public String naytaAsunnot(Model model) {
        List<Asunto> asunnot = asuntoRepository.findAll();
        model.addAttribute("asunnot", asunnot);
        return "asunnot";
    }

    // Vuokrasopimusten listaus
    @GetMapping("/vuokrasopimukset-nakyma")
    public String naytaVuokrasopimukset(Model model) {
        List<Vuokrasopimus> vuokrasopimukset = vuokrasopimusRepository.findAll();
        model.addAttribute("vuokrasopimukset", vuokrasopimukset);
        return "vuokrasopimukset";
    }

    // Näyttää lisää asiakas -lomakkeen
    @GetMapping("/addasiakas")
    public String addAsiakas(Model model) {
        model.addAttribute("asiakas", new Asiakkaat());
        return "Addasiakas";
    }

    // Tallentaa asiakasobjektin tietokantaan
    @PostMapping("/saveasiakas")
    public String saveAsiakas(@Valid @ModelAttribute("asiakas") Asiakkaat asiakas, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
        
        return "Addasiakas"; 
    }

    System.out.println("POST-pyyntö vastaanotettu: " + asiakas);
    asiakkaatRepository.save(asiakas); 
    return "redirect:/asiakkaat-nakyma"; 
    }

    // Lisää asuntosivu
    @GetMapping("/addasunto")
    public String addAsunto(Model model) {
        model.addAttribute("asunto", new Asunto());
        return "adddasunto";
    }

    // Asunto lisäys
    @PostMapping("/saveasunto")
    public String save(@ModelAttribute Asunto asunto) {
        asuntoRepository.save(asunto);
        return "redirect:/asunnot-nakyma";
    }

    @GetMapping("/addvuokrasopimus")
    public String addVuokrasopimus(Model model) {
        model.addAttribute("vuokrasopimus", new Vuokrasopimus());
        model.addAttribute("asiakkaat", asiakkaatRepository.findAll());
        model.addAttribute("asunnot", asuntoRepository.findAll());
        return "addvuokrasopimus";
    }

    @PostMapping("/savevuokrasopimus")
    public String saveVuokrasopimus(Vuokrasopimus vuokrasopimus) {
        vuokrasopimusRepository.save(vuokrasopimus);
        return "redirect:/vuokrasopimukset-nakyma";
    }

    @GetMapping("/delete/{id}")
    public String deleteAsiakas(@PathVariable("id") Long asiakasId, Model model) {
        asiakkaatRepository.deleteById(asiakasId);
        return "redirect:/asiakkaat-nakyma";
    }

    @GetMapping("/deleteAsunto/{id}")
    public String deleteAsunto(@PathVariable("id") Long asuntoId, Model model) {
        asuntoRepository.deleteById(asuntoId);
        return "redirect:/asunnot-nakyma";
    }

    @GetMapping("/deleteVuokrasopimus/{id}")
    public String deleteVuokrasopimus(@PathVariable("id") Long sopimusId, Model model) {
        vuokrasopimusRepository.deleteById(sopimusId);
        return "redirect:/vuokrasopimukset-nakyma"; 
    }
    

}