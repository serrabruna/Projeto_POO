# 🏛️ Sistema Acadêmico - Universidade Aurora

> Trabalho Prático I - Programação Orientada a Objetos  
> **Instituto Federal de São Paulo (IFSP) - Câmpus Boituva**

## 📄 Sobre o Projeto

Este projeto consiste no desenvolvimento de um sistema em **Java** (CLI - Command Line Interface) para o gerenciamento acadêmico da Universidade Aurora. 
O objetivo principal foi aplicar os pilares da Programação Orientada a Objetos (POO) para solucionar problemas de modelagem de professores, disciplinas e alunos.

O sistema permite o gerenciamento de vínculos entre docentes e disciplinas, respeitando regras de negócios específicas para diferentes tipos de contratos 
(Vitalício e Substituto), além de gerenciar matrículas de alunos e projetos de pesquisa.

---------------------------------------------------------------------------------------------------------------------------------------------------------------

## ⚙️ Funcionalidades

O sistema conta com um menu interativo via terminal que oferece as seguintes operações:

### 1. Gestão de Professores
- **Cadastro:** Professor Vitalício (com bônus p/ Doutorado) ou Substituto (por horas-aula).
- **Polimorfismo:** Cálculo de salário diferenciado para cada tipo.
- **Regras:** Validação de limite de disciplinas (Vitalício: 3, Substituto: 2).

### 2. Gestão de Disciplinas
- **Cadastro:** Disciplinas Obrigatórias e Eletivas.
- **Vínculos:** Associação de professores responsáveis.
- **Controle:** Monitoramento de carga horária mínima.

### 3. Gestão de Alunos
- **Matrícula:** Inscrição em disciplinas (relação N:N).
- **Interesse:** Registro de interesse em disciplinas Eletivas.
- **Histórico:** Visualização das disciplinas cursadas.

### 4. Projetos e Relatórios
- **Pesquisa:** Cadastro de projetos vinculados exclusivamente a professores Vitalícios.
- **Relatórios:** Resumo de folha de pagamento e popularidade de eletivas.

---------------------------------------------------------------------------------------------------------------------------------------------------------------

## 📊 Diagrama de Classes (UML)

Abaixo está a representação da modelagem do sistema, destacando o uso de herança e associações.

<img width="1511" height="587" alt="diagrama-trabalho-poo drawio (2)" src="https://github.com/user-attachments/assets/687b94f2-76b1-48ac-8d84-b94a2a441232" />

---------------------------------------------------------------------------------------------------------------------------------------------------------------

## 🛠️ Tecnologias e Conceitos Aplicados

- **Linguagem:** Java (JDK 17+)
- **Arquitetura:** MVC (Model - View - Controller) + Repository Pattern
- **Conceitos de POO:**
  - **Herança:** (`Professor` -> `ProfessorVitalicio`, `ProfessorSubstituto`)
  - **Polimorfismo:** (`calcularSalario()`, `verificarDisciplina()`)
  - **Encapsulamento:** Uso de modificadores de acesso e métodos assessores.
  - **Abstração:** Classes abstratas e interfaces.

---------------------------------------------------------------------------------------------------------------------------------------------------------------

## 🚀 Como Executar o Projeto

### Pré-requisitos
- Java JDK instalado.

### Passo a passo
1. Clone o repositório:
   git clone [https://github.com/serrabruna/Projeto_POO.git](https://github.com/serrabruna/Projeto_POO.git)

2.  Acesse a pasta do projeto:
    cd Projeto_POO
    
4.  Compile os arquivos Java:
    javac -d bin src/*.java src/controller/*.java src/model/*.java src/repository/*.java src/view/*.java Main.java
    
6.  Execute a aplicação:
    java -cp bin Main

---------------------------------------------------------------------------------------------------------------------------------------------------------------

## 📂 Estrutura do Projeto

O código foi organizado seguindo o padrão MVC para separar responsabilidades:

src/
├── model/       # Classes de Entidade (Professor, Aluno, Disciplina...)
├── view/        # Classes de Interface (Menus e interação com usuário)
├── controller/  # Regras de negócio e comunicação entre View e Model
└── repository/  # Armazenamento de dados em memória (Listas)

-----

*Projeto desenvolvido para a disciplina do Prof. Dr. Anisio Silva - Novembro/2025.*
Desenvolvido por Bruna Serra Amorim e Vinicius Araújo Domingues.

