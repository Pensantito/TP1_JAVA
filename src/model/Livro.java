package model;

public class Livro {
    private String titulo;
    private String autores;
    private String editora;
    private int anoPublicacao;
    private int numeroPaginas;
    private String isbn;
    private String genero;
    private String sinopse;
    private String idioma;

    public Livro() {}

    public Livro(String titulo, String autores, String editora, int ano, int paginas, 
                 String isbn, String genero, String sinopse, String idioma) {
        this.titulo = titulo;
        this.autores = autores;
        this.editora = editora;
        this.anoPublicacao = ano;
        this.numeroPaginas = paginas;
        this.isbn = isbn;
        this.genero = genero;
        this.sinopse = sinopse;
        this.idioma = idioma;
    }

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public int getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

    
}