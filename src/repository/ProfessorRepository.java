package repository;

import java.util.ArrayList;
import java.util.List;
import model.Professor;

public class ProfessorRepository {
    private final List<Professor> professores = new ArrayList<>();

    public void cadastrarProfessor(Professor p) {
        if (p == null) return;
        professores.add(p);
    }

    public List<Professor> listarProfessores() {
        return new ArrayList<>(professores);
    }

    // retorna Professor ou null
    public Professor buscarPorMatricula(String matricula) {
        for (Professor p : professores) {
            if (p.getMatricula().equals(matricula)){
                return p;
            }
        }
        return null;
    }

    public boolean removerPorMatricula(String matricula) {
        Professor p = buscarPorMatricula(matricula);
        if (p != null) {
            professores.remove(p);
            return true;
        }
        return false;
    }

    public boolean editarProfessor(String matricula, Professor novo) {
        Professor p = buscarPorMatricula(matricula);
        if (p != null) {
            int idx = professores.indexOf(p);
            professores.set(idx, novo);
            return true;
        }
        return false;
    }
}