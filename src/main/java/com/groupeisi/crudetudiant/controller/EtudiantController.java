package com.groupeisi.crudetudiant.controller;

import com.groupeisi.crudetudiant.dto.EtudiantDto;
import com.groupeisi.crudetudiant.services.EtudiantService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EtudiantDto createEtudiant(@RequestBody @Validated EtudiantDto etudiantDto) {
        return etudiantService.createEtudiant(etudiantDto);
    }

    @GetMapping("/{id}")
    public EtudiantDto getEtudiantById(@PathVariable Long id) {
        return etudiantService.getEtudiantById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EtudiantDto> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }

    @PutMapping("/{id}")
    public EtudiantDto updateEtudiant(@PathVariable Long id, @RequestBody @Validated EtudiantDto etudiantDto) {
        return etudiantService.updateEtudiant(id, etudiantDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
    }
}
