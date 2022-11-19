package br.com.fiap.ladyinvest.teste;

import java.util.Calendar;
import java.util.List;

import br.com.fiap.ladyinvest.bean.Despesa;
import br.com.fiap.ladyinvest.dao.DespesaDAO;
import br.com.fiap.ladyinvest.exception.DBException;
import br.com.fiap.ladyinvest.factory.DAOFactory;

public class DespesaDAOTeste {
	public static void main(String[] args) {
		DespesaDAO dao = DAOFactory.getDespesaDAO();
		
		//Cadastrar uma Receita
		Despesa despesa = new Despesa(0,"Gasolina",200,Calendar.getInstance());
		try {
			dao.cadastrar(despesa);
			System.out.println("Despesa cadastrada.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		//Buscar uma Despesa pelo código e atualizar
		despesa = dao.buscar(10);
		despesa.setDescricao("Teste");
		despesa.setValor(5250.00);
		despesa.setDataOperacao(Calendar.getInstance());
		try {
			dao.atualizar(despesa);
			System.out.println("Despesa atualizado.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		//Listar as Despesa
		List<Despesa> lista = dao.listar();
		for (Despesa item : lista) {
			System.out.println(item.getDescricao() + " " + item.getDataOperacao().getTime() + " " + item.getValor());
		}
		
		//Remover uma Despesa
		try {
			dao.remover(5);
			System.out.println("Despesa removida.");
		} catch (DBException e) {
			e.printStackTrace();
		}	
	}	

}
