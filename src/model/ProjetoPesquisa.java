package model;

public class ProjetoPesquisa {
    private String nome;
    private ProfessorVitalicio orientador;

    public ProjetoPesquisa(String nome, ProfessorVitalicio orientador) {
        this.nome = nome;
        this.orientador = orientador;
    }

    public String getNome() {
        return nome;
    }

    public ProfessorVitalicio getOrientador() {
        return orientador;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setOrientador(ProfessorVitalicio orientador) {
        this.orientador = orientador;
    }
}
