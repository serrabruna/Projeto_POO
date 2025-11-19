package repository;

import java.util.ArrayList;
import java.util.List;
import model.Aluno;

public class AlunoRepository {
    // Lista que armazena os alunos
    private List<Aluno> alunos = new ArrayList<>();

    // cadastrar aluno
    public void cadastrarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    // Método findAll() — listar todos
    public List<Aluno> listarAlunos() {
        return alunos;
    }

}