package br.com.fiap.ladyinvest.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.ladyinvest.bean.TipoConta;
import br.com.fiap.ladyinvest.dao.TipoContaDAO;
import br.com.fiap.ladyinvest.singleton.ConnectionManager;

public class OracleTipoContaDAO implements TipoContaDAO{
	
	private Connection conexao;

	@Override
	public List<TipoConta> listar() {
		List<TipoConta> lista = new ArrayList<TipoConta>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_TIPO_CONTA");
			rs = stmt.executeQuery();

			//Percorre todos os registros encontrados
			while (rs.next()) {
				int codigo = rs.getInt("CD_TIPO_CONTA");
				String nome = rs.getString("NM_TIPO_CONTA");
				TipoConta tipoConta = new TipoConta(codigo,nome);
				lista.add(tipoConta);
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
		return lista;
	}
}