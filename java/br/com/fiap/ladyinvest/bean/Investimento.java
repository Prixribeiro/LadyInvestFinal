package br.com.fiap.ladyinvest.bean;

import java.util.Calendar;

public class Investimento {
	
	private int codigo;
	
	private String nomeInvestimento;
	
	private double valor;
	
	private Calendar dataAporte;
	
	private String tempo;

	public Investimento() {
		super();
		
	}

	public Investimento(int codigo, String nomeInvestimento, double valor, Calendar dataAporte, String tempo) {
		super();
		this.codigo = codigo;
		this.nomeInvestimento = nomeInvestimento;
		this.valor = valor;
		this.dataAporte = dataAporte;
		this.tempo = tempo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomeInvestimento() {
		return nomeInvestimento;
	}

	public void setNomeInvestimento(String nomeInvestimento) {
		this.nomeInvestimento = nomeInvestimento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Calendar getDataAporte() {
		return dataAporte;
	}

	public void setDataAporte(Calendar dataAporte) {
		this.dataAporte = dataAporte;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	

}
