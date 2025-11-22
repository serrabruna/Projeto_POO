package model;

public class ProfessorVitalicio extends Professor {
    private double salarioBase;

    public ProfessorVitalicio(String nome, String matricula, String titulacao, double salarioBase) {
        super(nome, matricula, titulacao);
        this.salarioBase = salarioBase;
    }

    @Override
    public double calcularSalario() {
        return salarioBase;
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
}
