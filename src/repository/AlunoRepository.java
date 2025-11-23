package repository;

import java.util.ArrayList;
import java.util.List;
import model.Aluno;

public class AlunoRepository {
    private final List<Aluno> alunos = new ArrayList<>();

    public void cadastrarAluno(Aluno a) {
        if (a == null) return;
        alunos.add(a);
    }

    public List<Aluno> listarAlunos() {
        return new ArrayList<>(alunos);
    }

    // retorna Aluno ou null
    public Aluno buscarPorMatricula(String matricula) {
        if (matricula == null) return null;
        for (Aluno a : alunos) {
            if (matricula.equals(a.getMatricula())){
                return a;
            }
        }
        return null;
    }

    public boolean removerPorMatricula(String matricula) {
        Aluno a = buscarPorMatricula(matricula);
        if (a != null) {
            alunos.remove(a);
            return true;
        }
        return false;
    }

    public boolean editarAluno(String matricula, Aluno novo) {
        Aluno a = buscarPorMatricula(matricula);
        if (a != null) {
            int idx = alunos.indexOf(a);
            alunos.set(idx, novo);
            return true;
        }
        return false;
    }
}