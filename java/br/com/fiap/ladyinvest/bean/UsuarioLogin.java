package br.com.fiap.ladyinvest.bean;

import br.com.fiap.ladyinvest.util.CriptografiaUtils;

public class UsuarioLogin {
	
	private String email;
	private String senha;
	
	public UsuarioLogin(String email, String senha) {
		super();
		this.email= email;
		setSenha(senha);
	}
	
	public UsuarioLogin() {
		super();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		try {
			this.senha = CriptografiaUtils.criptografar(senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	 

}
