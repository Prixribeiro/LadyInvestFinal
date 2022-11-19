package br.com.fiap.ladyinvest.bean;

public class Categoria {
	
	private int codigo;
	private String nomeCategoria;
	
	public Categoria() {
		super();
		
	}

	public Categoria(int codigo, String nomeCategoria) {
		super();
		this.codigo = codigo;
		this.nomeCategoria = nomeCategoria;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
	

}
