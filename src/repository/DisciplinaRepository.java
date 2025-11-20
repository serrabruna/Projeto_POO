package repository;

import java.util.ArrayList;
import java.util.List;
import model.Disciplina;

public class DisciplinaRepository {
    private List<Disciplina> disciplinas = new ArrayList<>();

    public void cadastrarDisciplina(Disciplina d){
        if (d == null) return;
        disciplinas.add(d);
    }

    public List<Disciplina> listarDisciplinas(){
        return new ArrayList<>(disciplinas);
    }

    // Retorna Disciplina ou nulo
    public Disciplina buscarPorCodigo(String codigo){
        if (codigo == null) return null;
        for (Disciplina d : disciplinas) {
            if (codigo.equals(d.getCodigo())) return d;
        }
        return null;
    }

    public boolean removerPorCodigo(String codigo){
        Disciplina found = buscarPorCodigo(codigo);
        if (found != null){
            disciplinas.remove(found);
            return true;
        }
        return false;
    }

    public boolean editarDisciplina(String codigo, Disciplina nova){
        Disciplina found = buscarPorCodigo(codigo);
        if (found != null){
            int idx = disciplinas.indexOf(found);
            disciplinas.set(idx, nova);
            return true;
        }
        return false;
    }
}