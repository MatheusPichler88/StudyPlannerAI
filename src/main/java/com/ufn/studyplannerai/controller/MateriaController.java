package com.ufn.studyplannerai.controller;

import com.ufn.studyplannerai.entity.MateriaEntity;
import com.ufn.studyplannerai.service.MateriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {


    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }


    //POST
    @PostMapping
    public ResponseEntity<List<MateriaEntity>> salvar(@RequestBody List<MateriaEntity> materias) {
        List<MateriaEntity> materiasSalvas = materiaService.salvarTodas(materias);
        return ResponseEntity.status(HttpStatus.CREATED).body(materiasSalvas);
    }

    //GET
    @GetMapping
    public ResponseEntity<List<MateriaEntity>> listar(){
      return ResponseEntity.ok(materiaService.listar());
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<MateriaEntity> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(materiaService.buscarPorId(id).orElse(null));
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<MateriaEntity> atualizar(@PathVariable Long id, @RequestBody MateriaEntity materia){
        return materiaService.atualizar(id, materia)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        materiaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
