package br.com.fiap.ladyinvest.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.com.fiap.ladyinvest.bean.UsuarioLogin;
import br.com.fiap.ladyinvest.dao.UsuarioLoginDAO;
import br.com.fiap.ladyinvest.exception.DBException;
import br.com.fiap.ladyinvest.singleton.ConnectionManager;

public class OracleUsuarioLoginDAO implements UsuarioLoginDAO{
		
		private Connection conexao;

		@Override
		public boolean validarUsuarioLogin(UsuarioLogin usuarioLogin) {
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conexao = ConnectionManager.getInstance().getConnection();
				stmt = conexao.prepareStatement("SELECT * FROM TB_LOGIN WHERE DS_EMAIL = ? AND DS_SENHA = ?");
				stmt.setString(1, usuarioLogin.getEmail());
				stmt.setString(2, usuarioLogin.getSenha());
				rs = stmt.executeQuery();

				if (rs.next()){
					return true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					stmt.close();
					rs.close();
					conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return false;
		}	
		
		@Override
		public void cadastrar(UsuarioLogin usuarioLogin) throws DBException {
			PreparedStatement stmt = null;
			
			try{
				conexao = ConnectionManager.getInstance().getConnection();
				String sql = " INSERT INTO TB_LOGIN (DS_EMAIL, DS_SENHA) VALUES (?, ?) ";
				stmt = conexao.prepareStatement(sql);
				stmt.setString(1, usuarioLogin.getEmail());
				stmt.setString(2, usuarioLogin.getSenha());
				
				
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException ("Erro ao cadastrar nova usuarioLogin.");
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
		public void atualizar(UsuarioLogin usuarioLogin) throws DBException {
			PreparedStatement stmt = null;
			
			try{
				conexao = ConnectionManager.getInstance().getConnection();
				String sql = " UPDATE TB_LOGIN SET DS_SENHA = ? WHERE  DS_EMAIL = ?";
				stmt = conexao.prepareStatement(sql);
				stmt.setString(1, usuarioLogin.getSenha());
				stmt.setString(2, usuarioLogin.getEmail());
					
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException ("Erro ao atualizar usuarioLogin.");
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
		public UsuarioLogin buscar(String id) {
			UsuarioLogin usuarioLogin= null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try{
				conexao = ConnectionManager.getInstance().getConnection();
				stmt = conexao.prepareStatement(" SELECT * FROM TB_LOGIN WHERE DS_EMAIL = ? ");
				
				stmt.setString(1, id);
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					String email = rs.getString("DS_EMAIL");
					String senha = rs.getString("DS_SENHA");
					
					usuarioLogin = new UsuarioLogin (email, senha);
					
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
			return usuarioLogin;
		
	       }
		@Override
		public List<UsuarioLogin> listar() {
			List<UsuarioLogin> lista = new ArrayList<UsuarioLogin>();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				conexao = ConnectionManager.getInstance().getConnection();
				stmt = conexao.prepareStatement(" SELECT * FROM TB_LOGIN ");
				rs = stmt.executeQuery();
				
				//Percorre os registros
				
				while (rs.next()) {
					
					String email = rs.getString("DS_EMAIL");
					String senha = rs.getString("DS_SENHA");
					
					UsuarioLogin usuarioLogin = new UsuarioLogin (email, senha);
					
					lista.add(usuarioLogin);
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
