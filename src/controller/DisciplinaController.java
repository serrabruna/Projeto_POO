package controller;

import java.util.List;
import java.util.Optional;
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

    public Optional<Disciplina> buscarPorCodigo(String codigo){
        return repo.buscarPorCodigo(codigo);
    }

    public boolean removerPorCodigo(String codigo){
        return repo.removerPorCodigo(codigo);
    }

    public boolean editarDisciplina(String codigo, Disciplina nova){
        return repo.editarDisciplina(codigo, nova);
    }

    public boolean registrarAluno(String codigoDisciplina, Aluno aluno){
        Optional<Disciplina> opt = repo.buscarPorCodigo(codigoDisciplina);
        if (opt.isPresent()){
            Disciplina d = opt.get();
            // assume Disciplina tem método adicionarAluno(Aluno)
            d.adicionarAluno(aluno);
            return true;
        }
        return false;
    }

    public void visualizarAlunosMatriculados(String codigoDisciplina){
        Optional<Disciplina> opt = repo.buscarPorCodigo(codigoDisciplina);
        opt.ifPresent(d -> d.visualizarAlunosMatriculados());
    }

    public void verificarDisciplina(String codigoDisciplina){
        Optional<Disciplina> opt = repo.buscarPorCodigo(codigoDisciplina);
        opt.ifPresent(d -> d.verificarDisciplina());
    }
}