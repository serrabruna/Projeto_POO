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

    public ProjetoPesquisa buscarPorNome(String nome){
        for (ProjetoPesquisa p: projetos) {
            if(p.getNome().equals(nome)){
                return p;
            }
        }
        return null;
    }

    public boolean removerPorNome(String nome){
        ProjetoPesquisa p = buscarPorNome(nome);
        if(p != null){
            projetos.remove(p);
            return true;
        }
        return false;
    }

    public boolean editarProjeto(String nome, ProjetoPesquisa novo){
        ProjetoPesquisa p = buscarPorNome(nome);
        if(p != null){
            int idx = projetos.indexOf(p);
            projetos.set(idx, novo);
            return true;
        }
        return false;
    }

    
}