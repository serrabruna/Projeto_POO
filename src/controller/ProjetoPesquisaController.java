package controller;

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

        ProjetoPesquisa projeto = new ProjetoPesquisa(nome, p.getNome()); 
        repo.cadastrarProjeto(projeto);
        return "Projeto de pesquisa cadastrado com sucesso.";
    }

    public void listarProjetosPorProfessor(String nomeProfessor) {
        List<ProjetoPesquisa> todos = repo.listarProjetos();
        boolean encontrou = false;
        System.out.println("Projetos do professor " + nomeProfessor + ":");
        for (ProjetoPesquisa p : todos) {
            if (p.getProfessor().equalsIgnoreCase(nomeProfessor)) { // Comparando por nome conforme seu model atual
                System.out.println("- " + p.getNome());
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum projeto encontrado para este professor.");
    }
}