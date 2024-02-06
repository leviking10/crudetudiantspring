package com.groupeisi.crudetudiant.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantDto {
    private Long id;
    private String nom;
    private String prenom;
    private String classe;
    private String telephone;
}
