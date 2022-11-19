package br.com.fiap.ladyinvest.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.ladyinvest.bean.Categoria;
import br.com.fiap.ladyinvest.bean.Receita;
import br.com.fiap.ladyinvest.bean.TipoConta;
import br.com.fiap.ladyinvest.dao.ReceitaDAO;
import br.com.fiap.ladyinvest.exception.DBException;
import br.com.fiap.ladyinvest.singleton.ConnectionManager;

public class OracleReceitaDAO implements ReceitaDAO{


		private Connection conexao;
		
		@Override
		public void cadastrar(Receita receita) throws DBException {
			PreparedStatement stmt = null;
			
			try{
				conexao = ConnectionManager.getInstance().getConnection();
				String sql = " INSERT INTO TB_RECEITA ("
						+ "CD_RECEITA, DS_ITEM, VL_RECEITA, DT_OPERACAO, CD_CATEGORIA, CD_TIPO_CONTA) "
						+ "VALUES (SQ_TB_RECEITA.NEXTVAL, ?, ?, ?, ?, ?) ";
				stmt = conexao.prepareStatement(sql);
				stmt.setString(1, receita.getDescricao());
				stmt.setDouble(2, receita.getValor());
				java.sql.Date data = new java.sql.Date(receita.getDataOperacao().getTimeInMillis());
				stmt.setDate(3, data);
				stmt.setInt(4, receita.getCategoria().getCodigo());
				stmt.setInt(5, receita.getTipoConta().getCodigo());
				
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException ("Erro ao cadastrar nova receita.");
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
		public void atualizar(Receita receita) throws DBException {
			PreparedStatement stmt = null;
			
			try{
				conexao = ConnectionManager.getInstance().getConnection();
				String sql = " UPDATE TB_RECEITA SET DS_ITEM = ?, "
						+ "VL_RECEITA = ?, "
						+ "DT_OPERACAO= ?, "
						+ "CD_CATEGORIA = ?, "
						+ "CD_TIPO_CONTA = ? "
						+ "WHERE CD_RECEITA = ? ";
				stmt = conexao.prepareStatement(sql);
				stmt.setString(1, receita.getDescricao());
				stmt.setDouble(2, receita.getValor());
				java.sql.Date data = new java.sql.Date(receita.getDataOperacao().getTimeInMillis());
				stmt.setDate(3, data);
				stmt.setInt(4, receita.getCategoria().getCodigo());
				stmt.setInt(5, receita.getTipoConta().getCodigo());
				stmt.setInt(6, receita.getCodigo());
				
				
				
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException ("Erro ao atualizar receita.");
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
				String sql = " DELETE FROM TB_RECEITA WHERE CD_RECEITA = ? ";
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, codigo);
				
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException ("Erro ao remover receita.");
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
		public Receita buscar(int id) {
			Receita receita= null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try{
				conexao = ConnectionManager.getInstance().getConnection();
				stmt = conexao.prepareStatement(" SELECT * FROM TB_RECEITA "
						+ "INNER JOIN TB_CATEGORIA "
						+ "ON TB_RECEITA.CD_CATEGORIA = TB_CATEGORIA.CD_CATEGORIA "
						+ "INNER JOIN TB_TIPO_CONTA "
						+ "ON TB_RECEITA.CD_TIPO_CONTA = TB_TIPO_CONTA.CD_TIPO_CONTA "
						+ "WHERE TB_RECEITA.CD_RECEITA = ? ");
				
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					int codigo = rs.getInt("CD_RECEITA");
					String descricao = rs.getString("DS_ITEM");
					double valor = rs.getDouble("VL_RECEITA");
					java.sql.Date data = rs.getDate("DT_OPERACAO");
					Calendar dataOperacao = Calendar.getInstance();
					dataOperacao.setTimeInMillis(data.getTime());
					int codigoCategoria = rs.getInt("CD_CATEGORIA");
					String nomeCategoria = rs.getString("NM_CATEGORIA");
					int codigoTipoConta = rs.getInt("CD_TIPO_CONTA");
					String nomeTipoConta = rs.getString("NM_TIPO_CONTA");
					
					receita = new Receita (codigo, descricao, valor, dataOperacao);
					Categoria categoria = new Categoria (codigoCategoria, nomeCategoria);
					receita.setCategoria(categoria);
					
					TipoConta tipoconta = new TipoConta (codigoTipoConta, nomeTipoConta);
					receita.setTipoConta(tipoconta);
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
			return receita;
		
	       }

		@Override
		public List<Receita> listar() {
			List<Receita> lista = new ArrayList<Receita>();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				conexao = ConnectionManager.getInstance().getConnection();
				stmt = conexao.prepareStatement(" SELECT * FROM TB_RECEITA "
						+ "INNER JOIN TB_CATEGORIA "
						+ "ON TB_RECEITA.CD_CATEGORIA = TB_CATEGORIA.CD_CATEGORIA "
						+ "INNER JOIN TB_TIPO_CONTA "
						+ "ON TB_RECEITA.CD_TIPO_CONTA = TB_TIPO_CONTA.CD_TIPO_CONTA "
						+ "ORDER BY DT_OPERACAO DESC");
				rs = stmt.executeQuery();
				
				//Percorre os registros
				
				while (rs.next()) {
					int codigo = rs.getInt("CD_RECEITA");
					String descricao = rs.getString("DS_ITEM");
					double valor = rs.getDouble("VL_RECEITA");
					java.sql.Date data = rs.getDate("DT_OPERACAO");
					Calendar dataOperacao = Calendar.getInstance();
					dataOperacao.setTimeInMillis(data.getTime());
					int codigoCategoria = rs.getInt("CD_CATEGORIA");
					String nomeCategoria = rs.getString("NM_CATEGORIA");
					int codigoTipoConta = rs.getInt("CD_TIPO_CONTA");
					String nomeTipoConta = rs.getString("NM_TIPO_CONTA");
					
					Receita receita = new Receita (codigo, descricao, valor, dataOperacao);
					
					Categoria categoria = new Categoria (codigoCategoria, nomeCategoria);
					receita.setCategoria(categoria);
					
					TipoConta tipoconta = new TipoConta (codigoTipoConta, nomeTipoConta);
					receita.setTipoConta(tipoconta);
					lista.add(receita);
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
