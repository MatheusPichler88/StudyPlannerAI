package com.ufn.studyplannerai.controller;

import com.ufn.studyplannerai.entity.MateriaEntity;
import com.ufn.studyplannerai.service.ChatGptService;
import com.ufn.studyplannerai.service.MateriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
public class CronogramaController {

    private final ChatGptService chatGptService;
    private final MateriaService materiaService;

    public CronogramaController(ChatGptService chatGptService, MateriaService materiaService) {
        this.chatGptService = chatGptService;
        this.materiaService = materiaService;
    }

    @GetMapping("/cronograma")
    public Mono<ResponseEntity<String>> gerarCronograma() {
        List<MateriaEntity> materias = materiaService.listar();
        return chatGptService.gerarCronograma(materias)
                .map(cronograma -> ResponseEntity.ok(cronograma))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}
