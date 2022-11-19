package br.com.fiap.ladyinvest.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.ladyinvest.bean.Categoria;
import br.com.fiap.ladyinvest.bean.Despesa;
import br.com.fiap.ladyinvest.bean.TipoConta;
import br.com.fiap.ladyinvest.dao.DespesaDAO;
import br.com.fiap.ladyinvest.exception.DBException;
import br.com.fiap.ladyinvest.singleton.ConnectionManager;

public class OracleDespesaDAO implements DespesaDAO{


		private Connection conexao;
		
		
		//Cadastrar 
		@Override
		public void cadastrar(Despesa despesa) throws DBException {
			PreparedStatement stmt = null;
			
			try{
				conexao = ConnectionManager.getInstance().getConnection();
				String sql = " INSERT INTO TB_DESPESA (CD_DESPESA, DS_ITEM, VL_DESPESA, DT_OPERACAO, CD_CATEGORIA, CD_TIPO_CONTA) VALUES (SQ_TB_DESPESA.NEXTVAL, ?, ?, ?, ?, ?) ";
				stmt = conexao.prepareStatement(sql);
				stmt.setString(1, despesa.getDescricao());
				stmt.setDouble(2, despesa.getValor());
				java.sql.Date data = new java.sql.Date(despesa.getDataOperacao().getTimeInMillis());
				stmt.setDate(3, data);
				stmt.setInt(4, despesa.getCategoria().getCodigo());
				stmt.setInt(5, despesa.getTipoConta().getCodigo());
				
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException ("Erro ao cadastrar nova despesa.");
			} finally {
				try {
					stmt.close();
					conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}

		
		//Atualizar
		@Override
		public void atualizar(Despesa despesa) throws DBException {
			PreparedStatement stmt = null;
			
			try{
				conexao = ConnectionManager.getInstance().getConnection();
				String sql = " UPDATE TB_DESPESA SET DS_ITEM = ?, VL_DESPESA = ?, DT_OPERACAO= ?, CD_CATEGORIA = ?, CD_TIPO_CONTA = ? WHERE CD_DESPESA = ? ";
				stmt = conexao.prepareStatement(sql);
				stmt.setString(1, despesa.getDescricao());
				stmt.setDouble(2, despesa.getValor());
				java.sql.Date data = new java.sql.Date(despesa.getDataOperacao().getTimeInMillis());
				stmt.setDate(3, data);
				stmt.setInt(4, despesa.getCategoria().getCodigo());
				stmt.setInt(5, despesa.getTipoConta().getCodigo());
				stmt.setInt(6, despesa.getCodigo());
				
				
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException ("Erro ao atualizar despesa.");
			} finally {
				try {
					stmt.close();
					conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		//Remover
		@Override
		public void remover(int codigo) throws DBException {
			PreparedStatement stmt = null;
			
			try{
				conexao = ConnectionManager.getInstance().getConnection();
				String sql = " DELETE FROM TB_DESPESA WHERE CD_DESPESA = ? ";
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, codigo);
				
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException ("Erro ao remover despesa.");
			} finally {
				try {
					stmt.close();
					conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}

		//Buscar
		@Override
		public Despesa buscar(int id) {
			Despesa despesa= null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try{
				conexao = ConnectionManager.getInstance().getConnection();
				stmt = conexao.prepareStatement(" SELECT * FROM TB_DESPESA "
						+ "INNER JOIN TB_CATEGORIA ON TB_DESPESA.CD_CATEGORIA = TB_CATEGORIA.CD_CATEGORIA "
						+ "INNER JOIN TB_TIPO_CONTA ON TB_DESPESA.CD_TIPO_CONTA = TB_TIPO_CONTA.CD_TIPO_CONTA WHERE CD_DESPESA = ? ");
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					int codigo = rs.getInt("CD_DESPESA");
					String descricao = rs.getString("DS_ITEM");
					double valor = rs.getDouble("VL_DESPESA");
					java.sql.Date data = rs.getDate("DT_OPERACAO");
					Calendar dataOperacao = Calendar.getInstance();
					dataOperacao.setTimeInMillis(data.getTime());
					int codigoCategoria = rs.getInt("CD_CATEGORIA");
					String nomeCategoria = rs.getString("NM_CATEGORIA");
					int codigoTipoConta = rs.getInt("CD_TIPO_CONTA");
					String nomeTipoConta = rs.getString("NM_TIPO_CONTA");
					
					despesa = new Despesa (codigo, descricao, valor, dataOperacao);
					Categoria categoria = new Categoria (codigoCategoria, nomeCategoria);
					despesa.setCategoria(categoria);
					
					TipoConta tipoConta = new TipoConta (codigoTipoConta, nomeTipoConta);
					despesa.setTipoConta(tipoConta);
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
			return despesa;
		
	       }

		@Override
		public List<Despesa> listar() {
			List<Despesa> lista = new ArrayList<Despesa>();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				conexao = ConnectionManager.getInstance().getConnection();
				stmt = conexao.prepareStatement(" SELECT * FROM TB_DESPESA "
						+ "INNER JOIN TB_CATEGORIA ON TB_DESPESA.CD_CATEGORIA = TB_CATEGORIA.CD_CATEGORIA "
						+ "INNER JOIN TB_TIPO_CONTA ON TB_DESPESA.CD_TIPO_CONTA = TB_TIPO_CONTA.CD_TIPO_CONTA "
						+ "ORDER BY DT_OPERACAO DESC");
				rs = stmt.executeQuery();
				
				//Percorre os registros
				
				while (rs.next()) {
					int codigo = rs.getInt("CD_DESPESA");
					String descricao = rs.getString("DS_ITEM");
					double valor = rs.getDouble("VL_DESPESA");
					java.sql.Date data = rs.getDate("DT_OPERACAO");
					Calendar dataOperacao = Calendar.getInstance();
					dataOperacao.setTimeInMillis(data.getTime());
					int codigoCategoria = rs.getInt("CD_CATEGORIA");
					String nomeCategoria = rs.getString("NM_CATEGORIA");
					int codigoTipoConta = rs.getInt("CD_TIPO_CONTA");
					String nomeTipoConta = rs.getString("NM_TIPO_CONTA");
					
					Despesa despesa = new Despesa (codigo, descricao, valor, dataOperacao);
					
					Categoria categoria = new Categoria (codigoCategoria, nomeCategoria);
					despesa.setCategoria(categoria);
					
					TipoConta tipoConta = new TipoConta (codigoTipoConta, nomeTipoConta);
					despesa.setTipoConta(tipoConta);
					lista.add(despesa);
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
