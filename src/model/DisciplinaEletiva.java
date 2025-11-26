package model;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaEletiva extends Disciplina{
    private List<Aluno> interessesRegistrados = new ArrayList<>();

    public DisciplinaEletiva(String nome, String codigo, int cargaHoraria, Professor professorResponsavel, String alunosMatriculados) {
        super(nome, codigo, cargaHoraria, professorResponsavel, alunosMatriculados);
    }

    @Override
    public void verificarDisciplina() {
        listarDisciplina();
    }

    public void registrarInteresse(Aluno aluno){
        if (aluno != null && !interessesRegistrados.contains(aluno)){
            interessesRegistrados.add(aluno);
        }
    }

    public void registrarAluno(Aluno aluno){
        adicionarAluno(aluno);
    }

    public int calcularPopularidade(){
        return interessesRegistrados.size();
    }

    public List<Aluno> getInteressesRegistrados() {
        return interessesRegistrados;
    }

    public void setInteressesRegistrados(List<Aluno> interessesRegistrados) {
        this.interessesRegistrados = interessesRegistrados;
    }
}
