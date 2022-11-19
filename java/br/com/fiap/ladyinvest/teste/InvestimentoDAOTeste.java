package br.com.fiap.ladyinvest.teste;

import java.util.Calendar;
import java.util.List;

import br.com.fiap.ladyinvest.bean.Investimento;
import br.com.fiap.ladyinvest.dao.InvestimentoDAO;
import br.com.fiap.ladyinvest.exception.DBException;
import br.com.fiap.ladyinvest.factory.DAOFactory;

public class InvestimentoDAOTeste {
	public static void main(String[] args) {
		InvestimentoDAO dao = DAOFactory.getInvestimentoDAO();
		
		//Cadastrar uma Investimento
		Investimento investimento = new Investimento(0,"Compra de CDB",1000.00,Calendar.getInstance(), "3 anos");
		try {
			dao.cadastrar(investimento);
			System.out.println("Investimento cadastrado.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		//Buscar uma Investimento pelo código e atualizar
		investimento = dao.buscar(1);
		investimento.setNomeInvestimento("Tesouro Direto");
		investimento.setValor(5250.00);
		investimento.setDataAporte(Calendar.getInstance());
		investimento.setTempo("2 anos");
		try {
			dao.atualizar(investimento);
			System.out.println("Investimento atualizado.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		//Listar as Investimentos
		List<Investimento> lista = dao.listar();
		for (Investimento item : lista) {
			System.out.println(item.getNomeInvestimento() + " " + item.getDataAporte().getTime()+ " " + item.getValor() + " " + item.getTempo());
		}
		
		//Remover uma Investimento
		try {
			dao.remover(5);
			System.out.println("Investimento removido.");
		} catch (DBException e) {
			e.printStackTrace();
		}	
	}	
}
