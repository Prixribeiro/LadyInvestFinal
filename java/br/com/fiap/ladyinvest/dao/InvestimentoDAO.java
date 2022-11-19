package br.com.fiap.ladyinvest.dao;

import java.util.List;

import br.com.fiap.ladyinvest.bean.Investimento;
import br.com.fiap.ladyinvest.exception.DBException;

public interface InvestimentoDAO {
	void cadastrar(Investimento investimento) throws DBException;
	void atualizar(Investimento investimento) throws DBException;
	void remover(int codigo) throws DBException;
	Investimento buscar(int id);
	List<Investimento> listar();

}
