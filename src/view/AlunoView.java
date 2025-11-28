package view;

import controller.AlunoController;

import java.util.List;
import java.util.Scanner;
import model.Aluno;
import model.Disciplina;
import repository.AlunoRepository;
import repository.DisciplinaRepository;

public class AlunoView {
    private final AlunoController controller;
    private final Scanner in = new Scanner(System.in);

    public AlunoView(AlunoRepository alunoRepo, DisciplinaRepository discRepo) {
        this.controller = new AlunoController(alunoRepo, discRepo);
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- 3. Alunos ---");
            System.out.println("1. Cadastrar aluno");
            System.out.println("2. Listar alunos");
            System.out.println("3. Editar aluno");
            System.out.println("4. Desmatricular aluno de disciplina");
            System.out.println("5. Matricular aluno em uma disciplina");
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
                    desmatricular();
                    break;

                case "5":
                    matricular();
                    break;

                case "0":
                    return;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private void cadastrar() {
        System.out.print("Nome: ");
        String nome = in.nextLine().trim();

        String matricula;
        while (true) {
            System.out.print("Matrícula: ");
            matricula = in.nextLine().trim();

            if (controller.buscarPorMatricula(matricula) != null) {
                System.out.println("Já existe um aluno com a matricula " + matricula);
            } else {
                break;
            }
        }

        Aluno a = new Aluno(nome, matricula);
        controller.cadastrarAluno(a);

        // boolean ok = controller.matricularEmDisciplina(disciplina, a);

        // if (ok){
        // System.out.println("Sucesso: Aluno matriculado na disciplina.");
        // } else{
        // System.out.println("Erro: Disciplina não encontrada.");
        // }

        System.out.println("Aluno cadastrado.");
    }

    private void listar() {
        List<Aluno> lista = controller.listarAlunos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        System.out.println();
        for (Aluno a : lista) {
            System.out.printf("- %s (%s) ", a.getNome(), a.getMatricula());
        }
    }

    private void editar() {
        System.out.print("Matrícula do aluno a editar: ");
        String mat = in.nextLine().trim();
        Aluno a = controller.buscarPorMatricula(mat);

        if (a == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.print("Novo nome (enter para manter " + a.getNome() + "): ");
        String nome = in.nextLine().trim();
        if (!nome.isEmpty()) {
            a.setNome(nome);
        }

        controller.editarAluno(mat, a);
        System.out.println("Aluno atualizado.");
    }

    private void desmatricular() {
        System.out.print("Matrícula do aluno: ");
        String mat = in.nextLine().trim();

        Aluno a = controller.buscarPorMatricula(mat);

        if (a == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.print("Código da disciplina para remover matrícula: ");
        String codigo = in.nextLine().trim();

        boolean ok = controller.desmatricularAluno(codigo, mat);

        if (ok) {
            System.out.println("Sucesso: Aluno desmatriculado da disciplina.");
        } else {
            System.out.println(
                    "Erro: Não foi possível desmatricular (verifique se o aluno realmente estava nesta disciplina).");
        }
    }

    private void matricular() {
        System.out.print("Matricula do aluno: ");
        String mat = in.nextLine().trim();

        Aluno a = controller.buscarPorMatricula(mat);

        if (a == null) {
            System.out.println("Aluno não encontrado");
            return;
        }

        System.out.print("Código da disciplina para matrícula: ");
        String codigo = in.nextLine().trim();

        for (Disciplina d : a.getDisciplina()) {
            if (d.getCodigo().equalsIgnoreCase(codigo)) {
                System.out.println("Erro: Aluno já está matriculado nesta disciplina.");
                return;
            }
        }

        boolean ok = controller.matricularEmDisciplina(codigo, a);

        if (ok) {
            System.out.println("Sucesso: Aluno matriculado na disciplina.");
        } else {
            System.out.println("Erro: Disciplina não encontrada.");
        }
    }
}