package model;

public class Aluno extends Pessoa {
    private String instituicaoEnsino;
    private String curso;
    private double notaUltimoEnem;

    public Aluno() {}

    public Aluno(String cpf, String nome, String email, String instituicao, String curso, double notaEnem) {
        super(cpf, nome, email);
        this.instituicaoEnsino = instituicao;
        this.curso = curso;
        this.notaUltimoEnem = notaEnem;
    }

    @Override
    public void exibirDados() {
        System.out.println("Aluno: " + nome + " | Curso: " + curso + " | Nota ENEM: " + notaUltimoEnem);
    }

	public String getInstituicaoEnsino() {
		return instituicaoEnsino;
	}

	public void setInstituicaoEnsino(String instituicaoEnsino) {
		this.instituicaoEnsino = instituicaoEnsino;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public double getNotaUltimoEnem() {
		return notaUltimoEnem;
	}

	public void setNotaUltimoEnem(double notaUltimoEnem) {
		this.notaUltimoEnem = notaUltimoEnem;
	}

    
}