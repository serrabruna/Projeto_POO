package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Professor {
    private String nome;
    private String matricula;
    private String titulacao;
    private List<Disciplina> disciplinas;

    public Professor(String nome, String matricula, String titulacao) {
        this.nome = nome;
        this.matricula = matricula;
        this.titulacao = titulacao;
        this.disciplinas = new ArrayList<>();
    }

    // Método abstrato — cada tipo calcula diferente
    public abstract double calcularSalario();

    public abstract int getLimiteDisciplinas();

    public boolean podeAdicionarDisciplina() {
        return getDisciplinas().size() < getLimiteDisciplinas();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public List<Disciplina> getDisciplinas() {
        return new ArrayList<>(disciplinas); // retorna cópia para proteger a lista interna
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas != null ? new ArrayList<>(disciplinas) : new ArrayList<>();
    }
}