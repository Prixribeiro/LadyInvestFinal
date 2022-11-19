package br.com.fiap.ladyinvest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.ladyinvest.bean.UsuarioLogin;
import br.com.fiap.ladyinvest.dao.UsuarioLoginDAO;
import br.com.fiap.ladyinvest.factory.DAOFactory;


@WebServlet("/login")
public class UsuarioLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioLoginDAO dao;
  
	public UsuarioLoginServlet() {
        dao = DAOFactory.getUsuarioLoginDAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		UsuarioLogin usuarioLogin = new UsuarioLogin(email, senha);
		
		if (dao.validarUsuarioLogin(usuarioLogin)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", email);
			
		}else {
			request.setAttribute("erro", "Usuário e/ou senha inválidos");
		}
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
	
	
}