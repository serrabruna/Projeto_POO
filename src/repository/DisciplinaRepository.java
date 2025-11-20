package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Disciplina;

public class DisciplinaRepository {
    private List<Disciplina> disciplinas = new ArrayList<>();

    public void cadastrarDisciplina(Disciplina d){
        disciplinas.add(d);
    }

    public List<Disciplina> listarDisciplinas(){
        return new ArrayList<>(disciplinas);
    }

    public Optional<Disciplina> buscarPorCodigo(String codigo){
        return disciplinas.stream().filter(d -> d.getCodigo().equals(codigo)).findFirst();
    }

    public boolean removerPorCodigo(String codigo){
        Optional<Disciplina> found = buscarPorCodigo(codigo);
        if (found.isPresent()){
            disciplinas.remove(found.get());
            return true;
        }
        return false;
    }

    public boolean editarDisciplina(String codigo, Disciplina nova){
        Optional<Disciplina> found = buscarPorCodigo(codigo);
        if (found.isPresent()){
            int idx = disciplinas.indexOf(found.get());
            disciplinas.set(idx, nova);
            return true;
        }
        return false;
    }
}