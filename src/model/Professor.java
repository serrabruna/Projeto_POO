public abstract class Professor {
    private String nome;
    private String matricula;
    private float salario-base;

    public Professor(String nome, String matricula, float salarioBase) {
        this.nome = nome;
        this.matricula = matricula;
        this.salario-base = salarioBase;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public float getSalarioBase() {
        return salario-base;
    }

    public void setSalarioBase(float salarioBase) {
        this.salario-base = salarioBase;
    }

    public abstract float calcularSalario();
}