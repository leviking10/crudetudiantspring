package com.groupeisi.crudetudiant.repository;

import com.groupeisi.crudetudiant.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
}
