package br.com.fiap.ladyinvest.teste;

import java.util.Calendar;
import java.util.List;

import br.com.fiap.ladyinvest.bean.Receita;
import br.com.fiap.ladyinvest.dao.ReceitaDAO;
import br.com.fiap.ladyinvest.exception.DBException;
import br.com.fiap.ladyinvest.factory.DAOFactory;

public class ReceitaDAOTeste {
	public static void main(String[] args) {
		ReceitaDAO dao = DAOFactory.getReceitaDAO();
		
		//Cadastrar uma Receita
		Receita receita = new Receita(0,"Reembolso",200.00,Calendar.getInstance());
		try {
			dao.cadastrar(receita);
			System.out.println("Receita cadastrado.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		//Buscar uma Receita pelo código e atualizar
		receita = dao.buscar(1);
		receita.setDescricao("Salário");
		receita.setValor(5250.00);
		receita.setDataOperacao(Calendar.getInstance());
		try {
			dao.atualizar(receita);
			System.out.println("Receita atualizado.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		//Listar as Receitas
		List<Receita> lista = dao.listar();
		for (Receita item : lista) {
			System.out.println(item.getDescricao() + " " + item.getDataOperacao().getTime() + " " + item.getValor());
		}
		
		//Remover uma Receita
		try {
			dao.remover(5);
			System.out.println("Receita removido.");
		} catch (DBException e) {
			e.printStackTrace();
		}	
	}	

}






