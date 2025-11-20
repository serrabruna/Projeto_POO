package controller;

import java.util.List;
import model.Aluno;
import model.Disciplina;
import repository.DisciplinaRepository;

public class DisciplinaController {
    private final DisciplinaRepository repo;

    public DisciplinaController(DisciplinaRepository repo){
        this.repo = repo;
    }

    public void cadastrarDisciplina(Disciplina d){
        repo.cadastrarDisciplina(d);
    }

    public List<Disciplina> listarDisciplinas(){
        return repo.listarDisciplinas();
    }

    // retorna Disciplina ou nulo
    public Disciplina buscarPorCodigo(String codigo){
        return repo.buscarPorCodigo(codigo);
    }

    public boolean removerPorCodigo(String codigo){
        return repo.removerPorCodigo(codigo);
    }

    public boolean editarDisciplina(String codigo, Disciplina nova){
        return repo.editarDisciplina(codigo, nova);
    }

    public boolean registrarAluno(String codigoDisciplina, Aluno aluno){
        Disciplina d = repo.buscarPorCodigo(codigoDisciplina);
        if (d != null){
            d.adicionarAluno(aluno);
            return true;
        }
        return false;
    }

    public void visualizarAlunosMatriculados(String codigoDisciplina){
        Disciplina d = repo.buscarPorCodigo(codigoDisciplina);
        if (d != null) {
            d.visualizarAlunosMatriculados();
        } else {
            System.out.println("Disciplina não encontrada.");
        }
    }

    public void verificarDisciplina(String codigoDisciplina){
        Disciplina d = repo.buscarPorCodigo(codigoDisciplina);
        if (d != null) {
            d.verificarDisciplina();
        } else {
            System.out.println("Disciplina não encontrada.");
        }
    }
}