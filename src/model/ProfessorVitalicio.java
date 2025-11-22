package model;

import java.util.List;

public class ProfessorVitalicio extends Professor {
    private double salarioBase;
    private List<ProjetoPesquisa> projetosOrientados;

    public ProfessorVitalicio(String nome, String matricula, String titulacao, double salarioBase, List<ProjetoPesquisa> projetosOrientados) {
        super(nome, matricula, titulacao);
        this.salarioBase = salarioBase;
        this.projetosOrientados = projetosOrientados;
    }

    @Override
    public double calcularSalario() {
        if (getTitulacao() != null && getTitulacao().equals("Doutor")) {
            return getSalarioBase() * 1.20;
        }

        return getSalarioBase();
    }

    @Override
    public int getLimiteDisciplinas() {
        return 3; // Regra definida no PDF 
    }

    public double getSalarioBase(){
        return salarioBase; 
    }

    public void setSalarioBase(double salarioBase){ 
        this.salarioBase = salarioBase; 
    }

    public List<ProjetoPesquisa> getProjetosOrientados() {
        return projetosOrientados;
    }

    public void setProjetosOrientados(List<ProjetoPesquisa> projetosOrientados) {
        this.projetosOrientados = projetosOrientados;
    }

    public void adicionarprojetoOrientado(ProjetoPesquisa projeto){
        this.projetosOrientados.add(projeto);
    }
}
