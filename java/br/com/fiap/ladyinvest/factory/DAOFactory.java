package br.com.fiap.ladyinvest.factory;

import br.com.fiap.ladyinvest.dao.*;
import br.com.fiap.ladyinvest.dao.impl.*;

public class DAOFactory {
	
	public static ReceitaDAO getReceitaDAO(){
		return new OracleReceitaDAO();
		
	}
	
	public static CategoriaDAO getCategoriaDAO(){
		return new OracleCategoriaDAO();
		
	}
	
	public static InvestimentoDAO getInvestimentoDAO(){
		return new OracleInvestimentoDAO();
		
	}
	
	public static DespesaDAO getDespesaDAO(){
		return new OracleDespesaDAO();
		
	}
	
	public static TipoContaDAO getTipoContaDAO(){
		return new OracleTipoContaDAO();
		
	}
	
	public static UsuarioLoginDAO getUsuarioLoginDAO(){
		return new OracleUsuarioLoginDAO();
		
	}

}
