package view;

import controller.ProfessorController;
import controller.ProjetoPesquisaController;
import java.util.Scanner;
import repository.DisciplinaRepository;
import repository.ProfessorRepository;
import repository.ProjetoPesquisaRepository;

public class ProjetoPesquisaView {
    private ProfessorController profController;
    private ProjetoPesquisaController projetoController;
    private Scanner in = new Scanner(System.in);
    
    public ProjetoPesquisaView(ProfessorRepository profRepo, DisciplinaRepository discRepo, ProjetoPesquisaRepository projRepo) {
        this.profController = new ProfessorController(profRepo, discRepo);
        this.projetoController = new ProjetoPesquisaController(projRepo, profRepo);
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- 4. Vínculos e Projetos --- ");
            System.out.println("1. Atribuir disciplina a professor");
            System.out.println("2. Remover disciplina de professor");
            System.out.println("3. Cadastrar projeto de pesquisa");
            System.out.println("4. Listar projetos de um professor");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Opção: ");
            String op = in.nextLine().trim().toLowerCase();

            switch (op) {
                case "1": 
                    atribuirDisciplina(); 
                break;

                case "2": 
                    removerDisciplina(); 
                break;

                case "3": 
                    cadastrarProjeto(); 
                break;

                case "4": 
                    listarProjetos();
                break;

                case "0": return;

                default: 
                    System.out.println("Opção inválida.");
                break;
            }
        }
    }

    private void atribuirDisciplina() {
        System.out.print("Matrícula do professor: ");
        String mat = in.nextLine().trim();
        System.out.print("Código da disciplina: ");
        String cod = in.nextLine().trim();
        
        // Chama controller que já valida os limites (Vitalicio=3, Substituto=2)
        String resultado = profController.atribuirDisciplina(mat, cod);
        System.out.println(resultado);
    }

    private void removerDisciplina() {
        System.out.print("Matrícula do professor: ");
        String mat = in.nextLine().trim();
        System.out.print("Código da disciplina a remover: ");
        String cod = in.nextLine().trim();
        
        boolean ok = profController.removerDisciplinaDeProfessor(mat, cod);
        System.out.println(ok ? "Vínculo removido com sucesso." : "Erro: Professor ou disciplina não encontrados/vinculados.");
    }

    private void cadastrarProjeto() {
        System.out.print("Matrícula do professor orientador: ");
        String mat = in.nextLine().trim();
        System.out.print("Nome do projeto de pesquisa: ");
        String nome = in.nextLine().trim();
        
        // Controller valida se é ProfessorVitalicio
        String msg = projetoController.cadastrarProjeto(nome, mat);
        System.out.println(msg);
    }

    private void listarProjetos() {
        System.out.print("Nome do professor: ");
        String nome = in.nextLine().trim();
        projetoController.listarProjetosPorProfessor(nome);
    }
}