package model;

public class Funcionario extends Pessoa implements IAutenticavel {
    private Cargo cargo;
    private double salario;
    private String senha;

    public Funcionario() {}

    public Funcionario(String cpf, String nome, String email, Cargo cargo, double salario, String senha) {
        super(cpf, nome, email);
        this.cargo = cargo;
        this.salario = salario;
        this.senha = senha;
    }

    @Override
    public void exibirDados() {
        System.out.println("Funcionário: " + nome + " | Cargo: " + cargo.getNome());
    }

    @Override
    public boolean efetuarLogin(String cpfInfo, String senhaInfo) {
        return this.cpf.equals(cpfInfo) && this.senha.equals(senhaInfo);
    }

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}