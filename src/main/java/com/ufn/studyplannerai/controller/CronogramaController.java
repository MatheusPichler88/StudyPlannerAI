package com.ufn.studyplannerai.controller;

import com.ufn.studyplannerai.service.ChatGptService;
import com.ufn.studyplannerai.service.MateriaService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CronogramaController {

    private final ChatGptService chatGptService;
    private final MateriaService materiaService;

    public CronogramaController(ChatGptService chatGptService, MateriaService materiaService) {
        this.chatGptService = chatGptService;
        this.materiaService = materiaService;
    }

}
