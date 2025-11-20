package model;

public class DisciplinaObrigatoria extends Disciplina {
    public DisciplinaObrigatoria(String nome, String codigo, int cargaHoraria, Professor professorResponsavel, String alunosMatriculados) {
        super(nome, codigo, cargaHoraria, professorResponsavel, alunosMatriculados);
    }

    public boolean verificarCargaHoraria(){
        // ajuste o valor mínimo conforme o enunciado do PDF (ex.: 30 ou 60)
        int minimo = 30;
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
    }
    
}