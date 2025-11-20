package controller;

import java.util.ArrayList;
import java.util.List;
import model.Disciplina;
import model.Professor;
import repository.DisciplinaRepository;
import repository.ProfessorRepository;

public class ProfessorController {
    private final ProfessorRepository repo;
    private final DisciplinaRepository disciplinaRepo;

    public ProfessorController(ProfessorRepository repo, DisciplinaRepository disciplinaRepo) {
        this.repo = repo;
        this.disciplinaRepo = disciplinaRepo;
    }

    public void cadastrarProfessor(Professor p) {
        repo.cadastrarProfessor(p);
    }

    public List<Professor> listarProfessores() {
        return repo.listarProfessores();
    }

    // retorna Professor ou null
    public Professor buscarPorMatricula(String matricula) {
        return repo.buscarPorMatricula(matricula);
    }

    public boolean removerPorMatricula(String matricula) {
        return repo.removerPorMatricula(matricula);
    }

    public boolean editarProfessor(String matricula, Professor novo) {
        return repo.editarProfessor(matricula, novo);
    }

    // atribui disciplina ao professor e marca professor como responsável na disciplina
    public boolean atribuirDisciplina(String matriculaProfessor, String codigoDisciplina) {
        Professor prof = repo.buscarPorMatricula(matriculaProfessor);
        Disciplina disc = disciplinaRepo.buscarPorCodigo(codigoDisciplina);
        if (prof == null || disc == null) return false;

        List<Disciplina> atual = prof.getDisciplinas();
        if (atual == null) atual = new ArrayList<>();

        if (!atual.contains(disc)) {
            atual.add(disc);
            prof.setDisciplinas(atual);
        }

        disc.editarDisciplina(disc.getNome(), disc.getCargaHoraria(), prof);
        return true;
    }

    public void listarDisciplinasDoProfessor(String matriculaProfessor) {
        Professor p = repo.buscarPorMatricula(matriculaProfessor);
        if (p == null) {
            System.out.println("Professor não encontrado.");
            return;
        }
        System.out.println("Disciplinas de " + p.getNome() + ":");
        for (Disciplina d : p.getDisciplinas()) {
            System.out.printf("- %s (%s) CH:%d%n", d.getNome(), d.getCodigo(), d.getCargaHoraria());
        }
    }

    public double calcularSalario(String matriculaProfessor) {
        Professor p = repo.buscarPorMatricula(matriculaProfessor);
        return p != null ? p.calcularSalario() : 0.0;
    }
}