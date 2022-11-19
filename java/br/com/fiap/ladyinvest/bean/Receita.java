package br.com.fiap.ladyinvest.bean;

import java.util.Calendar;

public class Receita {
	
	private int codigo;
	
	private String descricao;
	
	private double valor;
	
	private Calendar dataOperacao;
	
	private Categoria categoria;
	
	private TipoConta tipoconta;

	public Receita() {
		super();
		
	}

	public Receita(int codigo, String descricao, double valor, Calendar dataOperacao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
		this.dataOperacao = dataOperacao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Calendar getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(Calendar dataOperacao) {
		this.dataOperacao = dataOperacao;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public TipoConta getTipoConta() {
		return tipoconta;
	}
	
	public void setTipoConta(TipoConta tipoconta) {
		this.tipoconta = tipoconta;
	}
}


