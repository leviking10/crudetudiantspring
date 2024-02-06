package com.groupeisi.crudetudiant.services;

import com.groupeisi.crudetudiant.dto.EtudiantDto;

import java.util.List;

public interface EtudiantService {
    EtudiantDto createEtudiant(EtudiantDto etudiantDto);

    EtudiantDto getEtudiantById(Long id);

    List<EtudiantDto> getAllEtudiants();

    EtudiantDto updateEtudiant(Long id, EtudiantDto articleDto);

    void deleteEtudiant(Long id);
}
