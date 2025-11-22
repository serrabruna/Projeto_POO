package controller;

import java.util.ArrayList;
import java.util.List;
import model.Professor;
import model.ProfessorVitalicio;
import model.ProjetoPesquisa;
import repository.ProjetoPesquisaRepository;
import repository.ProfessorRepository;

public class ProjetoPesquisaController {
    private final ProjetoPesquisaRepository repo;
    private final ProfessorRepository profRepo; // Necessário para validar o professor

    public ProjetoPesquisaController(ProjetoPesquisaRepository repo, ProfessorRepository profRepo) {
        this.repo = repo;
        this.profRepo = profRepo;
    }

    // Item 4(c): Cadastrar projeto (apenas Professor Vitalício)
    public String cadastrarProjeto(String nome, String matriculaProfessor) {
        Professor p = profRepo.buscarPorMatricula(matriculaProfessor);
        
        if (p == null){
            return "Professor não encontrado.";
        } 

        if (!(p instanceof ProfessorVitalicio)) {
            return "Erro: Apenas professores Vitalícios podem orientar projetos de pesquisa.";
        }

        ProjetoPesquisa projeto = new ProjetoPesquisa(nome, (ProfessorVitalicio) p);
        ((ProfessorVitalicio) p).adicionarprojetoOrientado(projeto);
        repo.cadastrarProjeto(projeto);
        return "Projeto de pesquisa cadastrado com sucesso.";
    }

    public List<ProjetoPesquisa> listarProjetosPorProfessor(String matriculaProfessor) {
        Professor p = profRepo.buscarPorMatricula(matriculaProfessor);
        
        if (p == null || !(p instanceof ProfessorVitalicio)) {
            return new ArrayList<>();
        }

        ProfessorVitalicio vitalicio = (ProfessorVitalicio) p;
        return vitalicio.getProjetosOrientados();
    }

    public List<ProjetoPesquisa> listarTodosProjetos() {
        return repo.listarProjetos();
    }
}