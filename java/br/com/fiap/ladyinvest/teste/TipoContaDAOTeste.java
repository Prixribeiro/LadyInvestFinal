package br.com.fiap.ladyinvest.teste;

import java.util.List;

import br.com.fiap.ladyinvest.bean.TipoConta;
import br.com.fiap.ladyinvest.dao.TipoContaDAO;
import br.com.fiap.ladyinvest.factory.DAOFactory;

public class TipoContaDAOTeste {

	public static void main(String[] args) {
		TipoContaDAO dao = DAOFactory.getTipoContaDAO();
		
		List<TipoConta> lista = dao.listar();
		for (TipoConta tipoconta : lista) {
			System.out.println(tipoconta.getCodigo() + " " + tipoconta.getNome());
		}
	}
	
}