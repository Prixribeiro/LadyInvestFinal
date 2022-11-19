package br.com.fiap.ladyinvest.dao;

import java.util.List;
import br.com.fiap.ladyinvest.bean.Despesa;
import br.com.fiap.ladyinvest.exception.DBException; 

public interface DespesaDAO {
	
	void cadastrar(Despesa despesa) throws DBException;
	void atualizar(Despesa despesa) throws DBException;
	void remover(int codigo) throws DBException;
	Despesa buscar(int id);
	List<Despesa> listar();
}