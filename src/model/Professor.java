package model;

public class Professor extends Pessoa implements IAutenticavel {
    private String titulacao;
    private String senha;

    public Professor() {}

    public Professor(String cpf, String nome, String email, String titulacao, String senha) {
        super(cpf, nome, email);
        this.titulacao = titulacao;
        this.senha = senha;
    }

    @Override
    public void exibirDados() {
        System.out.println("Professor: " + nome + " (" + titulacao + ")");
    }

    @Override
    public boolean efetuarLogin(String cpfInfo, String senhaInfo) {
        return this.cpf.equals(cpfInfo) && this.senha.equals(senhaInfo);
    }

	public String getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}