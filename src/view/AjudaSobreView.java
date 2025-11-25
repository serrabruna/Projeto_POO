package view;

import java.util.Scanner;

public class AjudaSobreView {
    private final Scanner in = new Scanner(System.in);

    public void menu(){
        System.out.println("\n--- 7. Ajuda / Sobre --- ");
        System.out.println("1. Instruções do Uso do CLI");
        System.out.println("2. Sobre os Autores e o Repositório");
        System.out.println("0. Voltar ao menu principal");
        System.out.print("Opção: ");
        String op = in.nextLine().trim();

        switch (op) {
            case "1":
                exibirInstrucoes();
                break;
            case "2":
                exibirSobre();
                break;
            case "0":
                return;
            default:
                System.out.println("Opção inválida.");
                break;
        }

    }

    private void exibirInstrucoes() {
        System.out.println("\n--- Instruções do Uso do CLI ---");
        System.out.println("1. Este sistema acadêmico permite gerenciar professores, disciplinas, alunos, projetos de pesquisa e eletivas.");
        System.out.println("2. Navegue pelos menus principais digitando o número correspondente à opção desejada.");
        System.out.println("3. Siga as instruções apresentadas em cada submenu para realizar operações específicas.");
        System.out.println("4. Para retornar ao menu principal, selecione a opção '0' em qualquer submenu.");
        System.out.println("----------------------------------");
    }

    private void exibirSobre() {
        System.out.println("\n--- Sobre os Autores e o Repositório ---");
        System.out.println("1. Desenvolvido por Bruna Serra e Vinicius Araujo.");
        System.out.println("2. O código-fonte está disponível no repositório GitHub: https://github.com/serrabruna/Projeto_POO.");
        System.out.println("----------------------------------");
    }
}
