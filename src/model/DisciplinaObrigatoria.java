package model;

public class DisciplinaObrigatoria extends Disciplina {
    public DisciplinaObrigatoria(String nome, String codigo, int cargaHoraria, Professor professorResponsavel, String alunosMatriculados) {
        super(nome, codigo, cargaHoraria, professorResponsavel, alunosMatriculados);
    }

    public boolean verificarCargaHoraria(){
        int minimo = 60;
        return getCargaHoraria() >= minimo;
    }

    public boolean verificarProfessor(){
        return getProfessorResponsavel() != null;
    }

    @Override
    public void verificarDisciplina() {
        boolean cargaOk = verificarCargaHoraria();
        boolean profOk = verificarProfessor();
        System.out.printf("Verificação (%s): cargaOk=%b, profOk=%b%n", getNome(), cargaOk, profOk);
        listarDisciplina();
    }
}