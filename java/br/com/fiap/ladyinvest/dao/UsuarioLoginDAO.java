package br.com.fiap.ladyinvest.dao;


import java.util.List;

import br.com.fiap.ladyinvest.bean.UsuarioLogin;
import br.com.fiap.ladyinvest.exception.DBException;

public interface UsuarioLoginDAO {
	
	boolean validarUsuarioLogin(UsuarioLogin usuario);
	void cadastrar(UsuarioLogin usuario) throws DBException;
	void atualizar(UsuarioLogin usuario) throws DBException;
	UsuarioLogin buscar(String id);
	List<UsuarioLogin> listar();

}
