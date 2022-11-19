package br.com.fiap.ladyinvest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.ladyinvest.bean.UsuarioLogin;
import br.com.fiap.ladyinvest.dao.UsuarioLoginDAO;
import br.com.fiap.ladyinvest.exception.DBException;
import br.com.fiap.ladyinvest.factory.DAOFactory;

@WebServlet("/cadastrar-usuario")
public class UsuarioCadastroServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	private UsuarioLoginDAO dao;
  
	public UsuarioCadastroServlet() {
        dao = DAOFactory.getUsuarioLoginDAO();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "listar":
			listar(request, response);
		case "abrir-form-cadastro":
			abrirFormCadastro(request, response);
			break;
			
		}	
	}
	
	private void abrirFormCadastro (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UsuarioLogin> lista = dao.listar();
		request.setAttribute("usuarios", lista);
		request.getRequestDispatcher("cadastrar-usuario.jsp").forward(request, response);
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UsuarioLogin> lista = dao.listar();
		request.setAttribute("usuarios", lista);
		request.getRequestDispatcher("cadastrar-usuario.jsp").forward(request, response);
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
    		String acao = request.getParameter("acao");
    		
    		switch (acao) {
    		case "cadastrar-usuario":
    			cadastrar(request, response);
    			break;
    		case "editar":
    			editar(request, response);
    			break;
    		}
    	}
    	
    	
    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		try {
    			
    			String email = request.getParameter("email");
    			String senha = request.getParameter("senha");
    			
    			UsuarioLogin usuarioLogin = new UsuarioLogin(email, senha);
    			dao.atualizar(usuarioLogin);

    			request.setAttribute("msg", "Usuário atualizado!");
    		} catch (DBException db) {
    			db.printStackTrace();
    			request.setAttribute("erro", "Erro ao atualizar");
    		} catch (Exception e) {
    			e.printStackTrace();
    			request.setAttribute("erro", "Por favor, valide os dados");
    		}
    		
    	}
    	
    private void cadastrar(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
    		try {
    			String nome = request.getParameter("nome");
    			String email = request.getParameter("email");
    						
    			UsuarioLogin usuarioLogin = new UsuarioLogin(nome, email);;
    			
    			dao.cadastrar(usuarioLogin);
    			
    			request.setAttribute("msg", "Usuário Cadastrado");
    		}catch(DBException db){
    			db.printStackTrace();
    			request.setAttribute("erro", "Erro ao cadstrar");
    			
    		}catch(Exception e) {
    			e.printStackTrace();
    			request.setAttribute("erro", "Por favor valide os dados.");
    		}
    		abrirFormCadastro(request, response);
    	}
    		
}
