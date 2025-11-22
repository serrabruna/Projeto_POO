package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Disciplina {
    private String nome;
    private String codigo;
    private int cargaHoraria;
    private Professor professorResponsavel;
    private List<Aluno> alunosMatriculados;

    public Disciplina(String nome, String codigo, int cargaHoraria, Professor professorResponsavel, String alunosMatriculados) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.professorResponsavel = professorResponsavel;
        this.alunosMatriculados = new ArrayList<>();
    }

    public abstract void verificarDisciplina();

    public void listarDisciplina(){
        System.out.printf("Disciplina: %s (%s) - CH: %d - Professor: %s%n", nome, codigo, cargaHoraria, professorResponsavel != null ? professorResponsavel.getNome() : "Não existe nenhuma disciplina");
    }

    public void editarDisciplina(String novoNome, int novaCargaHoraria, Professor novoProfessor){
        this.nome = novoNome;
        this.cargaHoraria = novaCargaHoraria;
        this.professorResponsavel = novoProfessor;
    }

    public void adicionarAluno(Aluno aluno){
        if (aluno != null){
            alunosMatriculados.add(aluno);
        }
    }

    public void visualizarAlunosMatriculados(){
        System.out.println("Alunos matriculados em " + nome + ":");
        for (Aluno a : alunosMatriculados){
            System.out.printf("- %s (%s)%n", a.getNome(), a.getMatricula());
        }
    }

    public List<Aluno> getAlunosMatriculados() {
        return new ArrayList<>(alunosMatriculados); // retorna cópia para proteger a lista interna
    }

    public void setAlunosMatriculados(List<Aluno> alunos) {
        this.alunosMatriculados = alunosMatriculados != null ? new ArrayList<>(alunos) : new ArrayList<>();
    }

    public boolean removerAluno(Aluno aluno) {
        if (aluno != null) {
            return alunosMatriculados.remove(aluno);
        }
        
        return false;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public Professor getProfessorResponsavel() {
        return professorResponsavel;
    }
}
