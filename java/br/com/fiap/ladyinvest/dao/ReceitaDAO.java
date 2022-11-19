package br.com.fiap.ladyinvest.dao;

import java.util.List;
import br.com.fiap.ladyinvest.bean.Receita;
import br.com.fiap.ladyinvest.exception.DBException;

public interface ReceitaDAO {
		
	void cadastrar(Receita receita) throws DBException;
	void atualizar(Receita receita) throws DBException;
	void remover(int codigo) throws DBException;
	Receita buscar(int id);
	List<Receita> listar();
	

}
