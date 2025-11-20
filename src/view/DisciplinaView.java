package view;

import controller.DisciplinaController;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import model.Aluno;
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
            System.out.println("\n--- Menu Disciplinas ---");
            System.out.println("1. Cadastrar disciplina");
            System.out.println("2. Listar disciplinas");
            System.out.println("3. Editar disciplina");
            System.out.println("4. Remover disciplina");
            System.out.println("5. Registrar aluno em disciplina");
            System.out.println("6. Visualizar alunos matriculados");
            System.out.println("7. Verificar disciplina");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            String op = in.nextLine().trim();
            switch (op) {
                case "1": criarDisciplina(); 
                break;

                case "2": listar(); 
                break;

                case "3": editar(); 
                break;

                case "4": remover(); 
                break;

                case "5": registrarAluno(); 
                break;

                case "6": visualizarAlunos(); 
                break;

                case "7": verificar(); 
                break;

                case "0": 
                return;

                default: System.out.println("Opção inválida."); 
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
        System.out.print("Carga horária (int): ");
        int ch = Integer.parseInt(in.nextLine().trim());

        Disciplina d;
        // os construtores em model esperam (String nome, String codigo, int cargaHoraria, Professor professorResponsavel, String alunosMatriculados)
        // passamos "" para alunosMatriculados porque a implementação atual ignora esse parâmetro e inicializa a lista internamente
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
        Optional<Disciplina> opt = controller.buscarPorCodigo(codigo);
        if (!opt.isPresent()) {
            System.out.println("Disciplina não encontrada.");
            return;
        }

        Disciplina d = opt.get();
        System.out.print("Novo nome (enter para manter: " + d.getNome() + "): ");
        String nome = in.nextLine().trim();
        if (nome.isEmpty()) nome = d.getNome();

        System.out.print("Nova carga horária (enter para manter: " + d.getCargaHoraria() + "): ");
        String chLine = in.nextLine().trim();
        int ch = chLine.isEmpty() ? d.getCargaHoraria() : Integer.parseInt(chLine);

        // preferimos editar o objeto existente (não substituir com um construtor incompatível)
        d.editarDisciplina(nome, ch, d.getProfessorResponsavel());
        System.out.println("Disciplina atualizada.");
    }

    private void remover() {
        System.out.print("Código da disciplina a remover: ");
        String codigo = in.nextLine().trim();
        boolean ok = controller.removerPorCodigo(codigo);
        System.out.println(ok ? "Removida." : "Não encontrada.");
    }

    private void registrarAluno() {
        System.out.print("Código da disciplina: ");
        String codigo = in.nextLine().trim();
        System.out.print("Nome do aluno: ");
        String nome = in.nextLine().trim();
        System.out.print("Matrícula do aluno: ");
        String matricula = in.nextLine().trim();

        // modelo Aluno tem construtor (String nome, String matricula, String disciplina)
        Aluno a = new Aluno(nome, matricula, codigo);

        boolean ok = controller.registrarAluno(codigo, a);
        System.out.println(ok ? "Aluno registrado." : "Disciplina não encontrada.");
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

    // para testar rapidamente
    public static void main(String[] args) {
        new DisciplinaView().menu();
    }
}
