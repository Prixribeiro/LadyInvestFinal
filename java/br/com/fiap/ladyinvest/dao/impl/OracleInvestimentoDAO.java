package br.com.fiap.ladyinvest.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.ladyinvest.bean.Investimento;
import br.com.fiap.ladyinvest.dao.InvestimentoDAO;
import br.com.fiap.ladyinvest.exception.DBException;
import br.com.fiap.ladyinvest.singleton.ConnectionManager;

public class OracleInvestimentoDAO implements InvestimentoDAO{
	
	private Connection conexao;
	
	@Override
	public void cadastrar(Investimento investimento) throws DBException {
		PreparedStatement stmt = null;
		
		try{
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = " INSERT INTO TB_INVESTIMENTO (CD_INVESTIMENTO, NM_INVESTIMENTO, VL_INVESTIMENTO, DT_APORTE, DS_TEMPO_INVESTIMENTO) VALUES (SQ_TB_INVESTIMENTO.NEXTVAL, ?, ?, ?, ?) ";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, investimento.getNomeInvestimento());
			stmt.setDouble(2, investimento.getValor());
			java.sql.Date data = new java.sql.Date(investimento.getDataAporte().getTimeInMillis());
			stmt.setDate(3, data);
			stmt.setString(4, investimento.getTempo());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException ("Erro ao cadastrar nova investimento.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void atualizar(Investimento investimento) throws DBException {
		PreparedStatement stmt = null;
		
		try{
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = " UPDATE TB_INVESTIMENTO SET NM_INVESTIMENTO = ?, VL_INVESTIMENTO = ?, DT_APORTE= ?, DS_TEMPO_INVESTIMENTO = ? WHERE CD_INVESTIMENTO = ? ";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, investimento.getNomeInvestimento());
			stmt.setDouble(2, investimento.getValor());
			java.sql.Date data = new java.sql.Date(investimento.getDataAporte().getTimeInMillis());
			stmt.setDate(3, data);
			stmt.setString(4, investimento.getTempo());
			stmt.setInt(5, investimento.getCodigo());
			
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException ("Erro ao atualizar investimento.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void remover(int codigo) throws DBException {
		PreparedStatement stmt = null;
		
		try{
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = " DELETE FROM TB_INVESTIMENTO WHERE CD_INVESTIMENTO = ? ";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException ("Erro ao remover investimento.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Investimento buscar(int id) {
		Investimento investimento= null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement(" SELECT * FROM TB_INVESTIMENTO WHERE CD_INVESTIMENTO = ? ");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				int codigo = rs.getInt("CD_INVESTIMENTO");
				String descricao = rs.getString("NM_INVESTIMENTO");
				double valor = rs.getDouble("VL_INVESTIMENTO");
				java.sql.Date data = rs.getDate("DT_APORTE");
				Calendar dataOperacao = Calendar.getInstance();
				dataOperacao.setTimeInMillis(data.getTime());
				String tempoInvestimento = rs.getString("DS_TEMPO_INVESTIMENTO");
				
				investimento = new Investimento (codigo, descricao, valor, dataOperacao, tempoInvestimento);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return investimento;
	
       }

	@Override
	public List<Investimento> listar() {
		List<Investimento> lista = new ArrayList<Investimento>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement(" SELECT * FROM TB_INVESTIMENTO ORDER BY DT_APORTE DESC ");
			rs = stmt.executeQuery();
			
			//Percorre os registros
			
			while (rs.next()) {
				int codigo = rs.getInt("CD_INVESTIMENTO");
				String descricao = rs.getString("NM_INVESTIMENTO");
				double valor = rs.getDouble("VL_INVESTIMENTO");
				java.sql.Date data = rs.getDate("DT_APORTE");
				Calendar dataOperacao = Calendar.getInstance();
				dataOperacao.setTimeInMillis(data.getTime());
				String tempoInvestimento = rs.getString("DS_TEMPO_INVESTIMENTO");
				
				Investimento investimento = new Investimento (codigo, descricao, valor, dataOperacao, tempoInvestimento);
				
				lista.add(investimento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}


}
