package repository;

import java.util.ArrayList;
import java.util.List;
import model.ProjetoPesquisa;

public class ProjetoPesquisaRepository {
    private final List<ProjetoPesquisa> projetos = new ArrayList<>();

    public void cadastrarProjeto(ProjetoPesquisa p) {
        if (p == null) return;
        projetos.add(p);
    }

    public List<ProjetoPesquisa> listarProjetos() {
        return new ArrayList<>(projetos);
    }
}