import java.util.Scanner;
import repository.*;
import view.*;

public class Main {
    public static void main(String[] args) {
        ProfessorRepository profRepo = new ProfessorRepository();
        DisciplinaRepository discRepo = new DisciplinaRepository();
        AlunoRepository alunoRepo = new AlunoRepository();
        ProjetoPesquisaRepository projetoRepo = new ProjetoPesquisaRepository();

        ProfessorView profView = new ProfessorView(profRepo, discRepo);
        DisciplinaView discView = new DisciplinaView(discRepo);
        AlunoView alunoView = new AlunoView(alunoRepo, discRepo);

        ProjetoPesquisaView projetoPesquisaView = new ProjetoPesquisaView(profRepo, discRepo, projetoRepo);
        EletivasView eletivasView = new EletivasView(discRepo, alunoRepo);
        RelatorioView relatorioView = new RelatorioView(profRepo, discRepo);
        AjudaSobreView ajudaSobreView = new AjudaSobreView();

        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== SISTEMA ACADÊMICO UNIVERSIDADE AURORA ===");
                System.out.println("1. Professores");
                System.out.println("2. Disciplinas");
                System.out.println("3. Alunos");
                System.out.println("4. Vínculos e Projetos");
                System.out.println("5. Eletivas");
                System.out.println("6. Relatórios");
                System.out.println("7. Ajuda / Sobre");
                System.out.println("8. Sair");
                System.out.print("Escolha uma opção: ");

                String opcao = in.nextLine().trim();

                switch (opcao) {
                    case "1": 
                        profView.menu(); 
                    break;

                    case "2": 
                        discView.menu();
                    break;

                    case "3": 
                        alunoView.menu(); 
                    break;

                    case "4":
                        projetoPesquisaView.menu();
                    break;

                    case "5":
                        eletivasView.menu();
                    break;

                    case "6":
                        relatorioView.menu();
                    break;

                    case "7":
                        ajudaSobreView.menu();
                    break;
                    case "8": 
                        System.out.println("Encerrando sistema...");
                    return;

                    default:
                        System.out.println("Opção inválida!");
                }
            }
        }
    }
}