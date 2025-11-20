package view;

import controller.ProfessorController;
import java.util.List;
import java.util.Scanner;
import model.Professor;
import model.ProfessorSubstituto;
import model.ProfessorVitalicio;
import repository.DisciplinaRepository;
import repository.ProfessorRepository;

public class ProfessorView {
    private final ProfessorController controller;
    private final Scanner in = new Scanner(System.in);

    public ProfessorView() {
        this.controller = new ProfessorController(new ProfessorRepository(), new DisciplinaRepository());
    }

    public void menu() {
        while (true) {
            System.out.println();
            System.out.println("--- Menu Professores ---");
            System.out.println("1. Cadastrar professor");
            System.out.println("2. Listar professores");
            System.out.println("3. Editar professor");
            System.out.println("4. Remover professor");
            System.out.println("5. Atribuir disciplina a professor");
            System.out.println("6. Listar disciplinas de um professor");
            System.out.println("7. Calcular salário");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            String op = in.nextLine().trim();
            switch (op) {
                case "1": 
                    cadastrar(); 
                break;

                case "2": 
                    listar(); 
                break;

                case "3":
                    editar(); 
                break;

                case "4":
                    remover(); 
                break;

                case "5":
                    atribuir(); 
                break;

                case "6":
                    listarDisciplinas();
                break;

                case "7":
                    calcular();
                break;

                case "0": return;

                default: 
                    System.out.println("Opção inválida"); 
                break;
            }
        }
    }

    private void cadastrar() {
        String tipo;
        // exige escolha válida (1 ou 2)
        while (true) {
            System.out.println();
            System.out.println("Cadastrar professor - escolha o tipo");
            System.out.println("1 Vitalício");
            System.out.println("2 Substituto");
            System.out.print("Tipo (1 ou 2): ");
            tipo = in.nextLine().trim();
            if ("1".equals(tipo) || "2".equals(tipo)) break;
            System.out.println("Opção inválida. Digite 1 para Vitalício ou 2 para Substituto.");
        }

        System.out.print("Nome: ");
        String nome = in.nextLine().trim();
        System.out.print("Matrícula: ");
        String matricula = in.nextLine().trim();
        System.out.print("Titulação: ");
        String titulacao = in.nextLine().trim();

        Professor p;
        if ("1".equals(tipo)) {
            double salarioBase;
            while (true) {
                System.out.print("Salário base (ex: 3000.50): ");
                String s = in.nextLine().trim();
                try {
                    salarioBase = Double.parseDouble(s);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido. Digite um número.");
                }
            }
            p = new ProfessorVitalicio(nome, matricula, titulacao, salarioBase);
        } else { // "2"
            int horasAula;
            while (true) {
                System.out.print("Horas aula (inteiro): ");
                String s = in.nextLine().trim();
                try {
                    horasAula = Integer.parseInt(s);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido. Digite um número inteiro.");
                }
            }
            p = new ProfessorSubstituto(nome, matricula, titulacao, horasAula);
        }

        controller.cadastrarProfessor(p);
        System.out.println("Professor cadastrado");
    }

    private void listar() {
        List<Professor> lista = controller.listarProfessores();
        if (lista.isEmpty()) {
            System.out.println("Nenhum professor cadastrado");
            return;
        }
        System.out.println();
        for (Professor p : lista) {
            System.out.printf("- %s (%s) Titulação: %s - Salário: R$ %.2f - Disciplinas: %d%n",
                p.getNome(), p.getMatricula(), p.getTitulacao(), p.calcularSalario(), p.getDisciplinas().size());
        }
    }

    private void editar() {
        System.out.print("Matrícula do professor a editar: ");
        String mat = in.nextLine().trim();
        Professor p = controller.buscarPorMatricula(mat);
        if (p == null) {
            System.out.println("Professor não encontrado");
            return;
        }
        System.out.print("Novo nome (enter para manter " + p.getNome() + "): ");
        String nome = in.nextLine().trim();
        if (!nome.isEmpty()) p.setNome(nome);

        System.out.print("Nova titulação (enter para manter " + p.getTitulacao() + "): ");
        String tit = in.nextLine().trim();
        if (!tit.isEmpty()) p.setTitulacao(tit);

        if (p instanceof ProfessorVitalicio) {
            ProfessorVitalicio pv = (ProfessorVitalicio) p;
            System.out.print("Novo salário base (enter para manter " + pv.getSalarioBase() + "): ");
            String s = in.nextLine().trim();
            if (!s.isEmpty()) {
                try { 
                    pv.setSalarioBase(Double.parseDouble(s)); 
                } catch (NumberFormatException e) { 
                    System.out.println("Valor inválido, mantido"); 
                }
            }
        } else if (p instanceof ProfessorSubstituto) {
            ProfessorSubstituto ps = (ProfessorSubstituto) p;
            System.out.print("Nova horas aula (enter para manter " + ps.getHorasAula() + "): ");
            String s = in.nextLine().trim();
            if (!s.isEmpty()) {
                try { 
                    ps.setHorasAula(Integer.parseInt(s)); 
                } catch (NumberFormatException e) { 
                    System.out.println("Valor inválido, mantido"); 
                }
            }
        }

        System.out.println("Professor atualizado");
    }

    private void remover() {
        System.out.print("Matrícula do professor a remover: ");
        String mat = in.nextLine().trim();
        boolean ok = controller.removerPorMatricula(mat);
        System.out.println(ok ? "Removido" : "Não encontrado");
    }

    private void atribuir() {
        System.out.print("Matrícula do professor: ");
        String mat = in.nextLine().trim();
        System.out.print("Código da disciplina: ");
        String codigo = in.nextLine().trim();
        boolean ok = controller.atribuirDisciplina(mat, codigo);
        System.out.println(ok ? "Disciplina atribuída" : "Erro: professor ou disciplina não encontrados");
    }

    private void listarDisciplinas() {
        System.out.print("Matrícula do professor: ");
        String mat = in.nextLine().trim();
        controller.listarDisciplinasDoProfessor(mat);
    }

    private void calcular() {
        System.out.print("Matrícula do professor: ");
        String mat = in.nextLine().trim();
        double sal = controller.calcularSalario(mat);
        System.out.printf("Salário calculado: %.2f%n", sal);
    }

    public static void main(String[] args) {
        new ProfessorView().menu();
    }
}
