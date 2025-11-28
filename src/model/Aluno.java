package model;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private String matricula;
    private List<Disciplina> disciplinas;

    public Aluno(String nome, String matricula) {
        this.matricula = matricula;
        this.nome = nome;
        this.disciplinas = new ArrayList<>();
    }

    public List<Disciplina> getDisciplina() {
        return disciplinas;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setDisciplina(List<Disciplina> disciplina) {
        this.disciplinas = disciplina;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return matricula;
    }

}
