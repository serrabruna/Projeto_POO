package view;

import java.util.List;
import controller.DisciplinaController;
import controller.ProfessorController;
import model.Professor;
import model.Disciplina;
import model.ProfessorVitalicio;
import repository.DisciplinaRepository;
import repository.ProfessorRepository;
import model.DisciplinaEletiva;
import java.util.Scanner;

public class RelatorioView {
    private final ProfessorController profController;
    private final DisciplinaController discController;
    private final Scanner in = new Scanner(System.in);

    public RelatorioView(ProfessorRepository profRepo, DisciplinaRepository discRepo) {
        this.profController = new ProfessorController(profRepo, discRepo);
        this.discController = new DisciplinaController(discRepo);
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- 6. Relatórios --- ");
            System.out.println("1. Resumo de Professores");
            System.out.println("2. Resumo de Disciplinas");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Opção: ");
            String op = in.nextLine().trim().toLowerCase();

            switch (op) {
                case "1": 
                    exibirRelatorioProfessores(); 
                break;

                case "2": 
                    exibirRelatorioDisciplinas(); 
                break;

                case "0": return;

                default: 
                    System.out.println("Opção inválida.");
                break;
            }
        }
    }

    private void exibirRelatorioProfessores() {
        List<Professor> professores = profController.gerarRelatorioProfessor();
        System.out.println("\n--- Resumo de Professores ---");

        for (Professor p : professores) {
            String tipo = (p instanceof ProfessorVitalicio) ? "Vitalício" : "Substituto";
            int qtdDisciplinas = p.getDisciplinas().size();
            double salario = p.calcularSalario();

            System.out.println("Matrícula: " + p.getMatricula());
            System.out.println("Nome: " + p.getNome());
            System.out.println("Tipo: " + tipo);
            System.out.println("Quantidade de Disciplinas: " + qtdDisciplinas);
            System.out.printf("Salário: R$ %.2f%n", salario);
            System.out.println("---------------------------");
        }
    }

    private void exibirRelatorioDisciplinas() {
        List<Disciplina> disciplinas = discController.gerarRelatorioDisciplina();

        if(disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }

        System.out.println("\n--- Resumo de Disciplinas ---");
        for (Disciplina d : disciplinas) {
            String tipo = (d instanceof DisciplinaEletiva) ? "Eletiva" : "Obrigatória";
            int qtdAlunos = d.getAlunosMatriculados().size();
            String profNome = (d.getProfessorResponsavel() != null) ? d.getProfessorResponsavel().getNome() : "Nenhum";

            System.out.println("Código: " + d.getCodigo());
            System.out.println("Nome: " + d.getNome());
            System.out.println("Carga Horária: " + d.getCargaHoraria());
            System.out.println("Tipo: " + tipo);
            System.out.println("Quantidade de Alunos Matriculados: " + qtdAlunos);

            if(d instanceof DisciplinaEletiva) {
                DisciplinaEletiva de = (DisciplinaEletiva) d;
                int popularidade = de.calcularPopularidade();
                System.out.println("Popularidade (número de interessados): " + popularidade);
            }

            System.out.println("Professor Responsável: " + profNome);
            System.out.println("---------------------------");
        }
    }
}
