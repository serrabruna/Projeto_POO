package view;

import controller.DisciplinaController;
import java.util.List;
import java.util.Scanner;
import model.Disciplina;
import model.DisciplinaEletiva;
import model.DisciplinaObrigatoria;
import repository.DisciplinaRepository;

public class DisciplinaView {
    private final DisciplinaController controller;
    private final Scanner in = new Scanner(System.in);

    public DisciplinaView() {
        this.controller = new DisciplinaController(new DisciplinaRepository());
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- 2. Disciplinas ---");
            System.out.println("1. Cadastrar disciplina");
            System.out.println("2. Listar disciplinas");
            System.out.println("3. Editar disciplina");
            System.out.println("4. Remover disciplina");
            System.out.println("5. Visualizar alunos matriculados");
            System.out.println("6. Verificar disciplina");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            String op = in.nextLine().trim();
            switch (op) {
                case "1": 
                    criarDisciplina(); 
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
                    visualizarAlunos(); 
                break;

                case "6": 
                    verificar(); 
                break;

                case "0": return;

                default:
                    System.out.println("Opção inválida."); 
                break;
            }
        }
    }

    private void criarDisciplina() {
        System.out.print("Tipo (1-Obrigatória, 2-Eletiva): ");
        String tipo = in.nextLine().trim();
        System.out.print("Nome: ");
        String nome = in.nextLine().trim();
        System.out.print("Código: ");
        String codigo = in.nextLine().trim();

        int ch;
        while (true) {
            System.out.print("Carga horária (int): ");
            String chInput = in.nextLine().trim();
            try {
                ch = Integer.parseInt(chInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número inteiro.");
            }
        }

        Disciplina d;
        if ("1".equals(tipo)) {
            d = new DisciplinaObrigatoria(nome, codigo, ch, null, "");
        } else {
            d = new DisciplinaEletiva(nome, codigo, ch, null, "");
        }
        controller.cadastrarDisciplina(d);
        System.out.println("Disciplina cadastrada.");
    }

    private void listar() {
        List<Disciplina> list = controller.listarDisciplinas();
        if (list.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }
        for (Disciplina d : list) {
            System.out.printf("- %s (%s) CH:%d%n", d.getNome(), d.getCodigo(), d.getCargaHoraria());
        }
    }

    private void editar() {
        System.out.print("Código da disciplina a editar: ");
        String codigo = in.nextLine().trim();
        Disciplina d = controller.buscarPorCodigo(codigo);
        if (d == null) {
            System.out.println("Disciplina não encontrada.");
            return;
        }

        System.out.print("Novo nome (enter para manter: " + d.getNome() + "): ");
        String nome = in.nextLine().trim();
        if (nome.isEmpty()) nome = d.getNome();

        System.out.print("Nova carga horária (enter para manter: " + d.getCargaHoraria() + "): ");
        String chLine = in.nextLine().trim();
        int ch = chLine.isEmpty() ? d.getCargaHoraria() : Integer.parseInt(chLine);

        d.editarDisciplina(nome, ch, d.getProfessorResponsavel());
        System.out.println("Disciplina atualizada.");
    }

    private void remover() {
        System.out.print("Código da disciplina a remover: ");
        String codigo = in.nextLine().trim();
        boolean ok = controller.removerPorCodigo(codigo);
        System.out.println(ok ? "Removida." : "Não encontrada.");
    }

    private void visualizarAlunos() {
        System.out.print("Código da disciplina: ");
        String codigo = in.nextLine().trim();
        controller.visualizarAlunosMatriculados(codigo);
    }

    private void verificar() {
        System.out.print("Código da disciplina: ");
        String codigo = in.nextLine().trim();
        controller.verificarDisciplina(codigo);
    }

    public static void main(String[] args) {
        new DisciplinaView().menu();
    }
}