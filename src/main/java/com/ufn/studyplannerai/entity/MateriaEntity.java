package com.ufn.studyplannerai.entity;

import com.ufn.studyplannerai.entity.enums.Area;
import com.ufn.studyplannerai.entity.enums.Dificuldade;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "materia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MateriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Area area;

    @Enumerated(EnumType.STRING)
    private Dificuldade dificuldade;

    private Integer horasEstudadas;

    private LocalDate dataProva;

}
