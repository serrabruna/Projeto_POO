package view;

import controller.DisciplinaController;
import java.util.Scanner;
import repository.AlunoRepository;
import repository.DisciplinaRepository;

public class EletivasView {
    private final DisciplinaController controller;
    private final AlunoRepository alunoRepo; // Necessário para buscar o aluno
    private final Scanner in = new Scanner(System.in);

    public EletivasView(DisciplinaRepository discRepo, AlunoRepository alunoRepo) {
        this.controller = new DisciplinaController(discRepo);
        this.alunoRepo = alunoRepo;
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- 5. Menu Eletivas ---");
            System.out.println("1. Registrar interesse em disciplina eletiva");
            System.out.println("2. Relatório de popularidade das eletivas");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            String op = in.nextLine().trim().toLowerCase();

            switch (op) {
                case "1": 
                    registrarInteresse(); 
                break;

                case "2": 
                    controller.relatorioPopularidade();
                break;

                case "0": return;
                
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private void registrarInteresse() {
        System.out.print("Matrícula do aluno: ");
        String mat = in.nextLine().trim();
        System.out.print("Código da disciplina eletiva: ");
        String cod = in.nextLine().trim();
        
        String res = controller.registrarInteresse(cod, mat, alunoRepo);
        System.out.println(res);
    }
}