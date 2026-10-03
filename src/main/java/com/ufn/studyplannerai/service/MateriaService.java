package com.ufn.studyplannerai.service;
import com.ufn.studyplannerai.entity.MateriaEntity;
import com.ufn.studyplannerai.repository.MateriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {

    private final MateriaRepository materiaRepository;

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    public MateriaEntity salvar(MateriaEntity materia){
        return materiaRepository.save(materia);
    }

    public List<MateriaEntity> salvarTodas(List<MateriaEntity> materias) {
        return materiaRepository.saveAll(materias);
    }

    public Optional<MateriaEntity> buscarPorId(Long id){
        return materiaRepository.findById(id);
    }

    public List<MateriaEntity> listar(){
        return materiaRepository.findAll();
    }

    public Optional<MateriaEntity> atualizar(Long id, MateriaEntity materia){
        return materiaRepository.findById(id)
                .map(materiaExistente -> {
                    materiaExistente.setNome(materia.getNome());
                    materiaExistente.setArea(materia.getArea());
                    materiaExistente.setDificuldade(materia.getDificuldade());
                    materiaExistente.setDataProva(materia.getDataProva());
                    materiaExistente.setHorasEstudadas(materia.getHorasEstudadas());
                    return materiaRepository.save(materiaExistente);
                });
    }

    public void deletar(Long id){
        materiaRepository.deleteById(id);
    }
}
