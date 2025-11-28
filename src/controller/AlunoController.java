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

    public Aluno buscarPorMatricula(String matricula) {
        return repo.buscarPorMatricula(matricula);
    }

    public boolean desmatricularAluno(String codigoDisciplina, String matriculaAluno) {
        Disciplina d = disciplinaRepo.buscarPorCodigo(codigoDisciplina);
        Aluno a = repo.buscarPorMatricula(matriculaAluno);

        if (d != null && a != null) {
            return d.removerAluno(a);
        }

        return false;
    }

    public boolean editarAluno(String matricula, Aluno novo) {
        return repo.editarAluno(matricula, novo);
    }

    public boolean matricularEmDisciplina(String codigoDisciplina, Aluno aluno) {
        Disciplina d = disciplinaRepo.buscarPorCodigo(codigoDisciplina);

        if (d == null || aluno == null)
            return false;

        for (Disciplina disciplinaDoAluno : aluno.getDisciplina()) {
            if (disciplinaDoAluno.getCodigo().equalsIgnoreCase(codigoDisciplina)) {
                return false;
            }
        }
    
        d.adicionarAluno(aluno);
        aluno.getDisciplina().add(d); 
    
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