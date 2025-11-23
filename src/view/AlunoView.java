package view;

import controller.AlunoController;
import java.util.List;
import java.util.Scanner;
import model.Aluno;
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

                case "0": return;

                default: 
                    System.out.println("Opção inválida.");
                break;
            }
        }
    }

    private void cadastrar() {
        System.out.print("Nome: ");
        String nome = in.nextLine().trim();
        System.out.print("Matrícula: ");
        String matricula = in.nextLine().trim();
        System.out.print("Disciplina (código, se houver): ");
        String disciplina = in.nextLine().trim();
        Aluno a = new Aluno(nome, matricula, disciplina);
        controller.cadastrarAluno(a);
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
            System.out.printf("- %s (%s) Disciplina: %s%n", a.getNome(), a.getMatricula(), a.getDisciplina());
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
        if (!nome.isEmpty()){
            a.setNome(nome);
        }

        System.out.print("Nova disciplina (enter para manter " + a.getDisciplina() + "): ");
        String d = in.nextLine().trim();
        if (!d.isEmpty()){
            a.setDisciplina(d);
        }

        controller.editarAluno(mat, a);
        System.out.println("Aluno atualizado.");
    }

    private void desmatricular() {
        System.out.print("Matrícula do aluno: ");
        String mat = in.nextLine().trim();
    
        Aluno a = controller.buscarPorMatricula(mat);

        if (a == null){
            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.print("Código da disciplina para remover matrícula: ");
        String codigo = in.nextLine().trim();

        boolean ok = controller.desmatricularAluno(codigo, mat);
    
        if (ok){
            System.out.println("Sucesso: Aluno desmatriculado da disciplina.");
        } else{
            System.out.println("Erro: Não foi possível desmatricular (verifique se o aluno realmente estava nesta disciplina).");
        }
    }

    private void matricular() {
        System.out.print("Matricula do aluno");
        String mat = in.nextLine().trim();

        Aluno a = controller.buscarPorMatricula(mat);

        if(a == null){
            System.out.println("Aluno não encontrado");
            return;
        }

        System.out.print("Código da disciplina para matrícula: ");
        String codigo = in.nextLine().trim();

        boolean ok = controller.matricularEmDisciplina(codigo, a);
        
        if (ok){
            System.out.println("Sucesso: Aluno matriculado na disciplina.");
        } else{
            System.out.println("Erro: Disciplina não encontrada.");
        }
    }
}