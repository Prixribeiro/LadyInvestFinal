package br.com.fiap.ladyinvest.teste;

import java.util.List;

import br.com.fiap.ladyinvest.bean.Categoria;
import br.com.fiap.ladyinvest.dao.CategoriaDAO;
import br.com.fiap.ladyinvest.factory.DAOFactory;

public class CategoriaDAOTeste {

	public static void main(String[] args) {
	CategoriaDAO dao = DAOFactory.getCategoriaDAO();
		
		List<Categoria> lista = dao.listar();
		for (Categoria categoria : lista) {
			System.out.println(categoria.getCodigo() + " " + categoria.getNomeCategoria());
		}
	}

}


