package controller;

import java.util.List;
import model.Aluno;
import model.Disciplina;
import repository.AlunoRepository;
import repository.DisciplinaRepository;

public class AlunoController {
    private final AlunoRepository repo;
    private final DisciplinaRepository disciplinaRepo;

    public AlunoController(AlunoRepository repo, DisciplinaRepository disciplinaRepo) {
        this.repo = repo;
        this.disciplinaRepo = disciplinaRepo;
    }

    public void cadastrarAluno(Aluno a) {
        repo.cadastrarAluno(a);
    }

    public List<Aluno> listarAlunos() {
        return repo.listarAlunos();
    }

    // retorna Aluno ou nulo
    public Aluno buscarPorMatricula(String matricula) {
        return repo.buscarPorMatricula(matricula);
    }

    public boolean removerPorMatricula(String matricula) {
        return repo.removerPorMatricula(matricula);
    }

    public boolean editarAluno(String matricula, Aluno novo) {
        return repo.editarAluno(matricula, novo);
    }

    // matricula aluno em disciplina (usa instância do repositório de disciplinas)
    public boolean matricularEmDisciplina(String codigoDisciplina, Aluno aluno) {
        Disciplina d = disciplinaRepo.buscarPorCodigo(codigoDisciplina);
        if (d == null || aluno == null) return false;
        d.adicionarAluno(aluno);
        return true;
    }

    public void listarAlunosPorDisciplina(String codigoDisciplina) {
        Disciplina d = disciplinaRepo.buscarPorCodigo(codigoDisciplina);
        if (d == null) {
            System.out.println("Disciplina não encontrada.");
            return;
        }
        d.visualizarAlunosMatriculados();
    }
}