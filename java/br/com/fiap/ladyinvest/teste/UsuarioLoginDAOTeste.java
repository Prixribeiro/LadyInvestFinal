package br.com.fiap.ladyinvest.teste;


import br.com.fiap.ladyinvest.bean.UsuarioLogin;
import br.com.fiap.ladyinvest.dao.UsuarioLoginDAO;

import br.com.fiap.ladyinvest.exception.DBException;
import br.com.fiap.ladyinvest.factory.DAOFactory;

public class UsuarioLoginDAOTeste {

	public static void main(String[] args) {
	UsuarioLoginDAO dao = DAOFactory.getUsuarioLoginDAO();
		
		//Cadastrar uma UsuarioLogin
		UsuarioLogin usuarioLogin = new UsuarioLogin("beatriz.bazevedo@gmail.com", "Lara123");
		try {
			dao.cadastrar(usuarioLogin);
			System.out.println("Usuario cadastrado.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		//Buscar uma UsuarioLogin pelo código e atualizar
		usuarioLogin = dao.buscar("priscila_teste@fiap.com.br");
		usuarioLogin.setSenha("Fiap2022");

		try {
			dao.atualizar(usuarioLogin);
			System.out.println("Usuario atualizado.");
		} catch (DBException e) {
			e.printStackTrace();
		}

	}

}
