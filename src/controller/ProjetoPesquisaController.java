package controller;

import java.util.List;
import model.ProjetoPesquisa;
import repository.ProjetoPesquisaRepository;

public class ProjetoPesquisaController {
    private final ProjetoPesquisaRepository repo;

    public ProjetoPesquisaController(ProjetoPesquisaRepository repo) {
        this.repo = repo;
    }

    public void cadastrarProjeto(String nome, String professor) {
        ProjetoPesquisa p = new ProjetoPesquisa(nome, professor);
        repo.cadastrarProjeto(p);
    }

    public List<ProjetoPesquisa> listarProjetos() {
        return repo.listarProjetos();
    }
}