package br.com.fiap.ladyinvest.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String url = req.getRequestURI();
		
		
		if(url.endsWith("/FintechFinal/")) {
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}else if(session.getAttribute("user") == null && !url.endsWith("login") && !url.endsWith("login.jsp") && !url.endsWith("erro")  
				&& !url.contains("esqueceu") && !url.contains("img") && !url.endsWith("cadastrar-usuario")) {
			request.setAttribute("erro", "Entre com usuário e senha!");
			
			request.getRequestDispatcher("cadastrar-usuario.jsp").forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
		
	}

}

