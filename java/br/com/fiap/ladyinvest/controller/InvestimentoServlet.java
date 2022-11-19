package br.com.fiap.ladyinvest.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.ladyinvest.bean.Investimento;
import br.com.fiap.ladyinvest.dao.InvestimentoDAO;
import br.com.fiap.ladyinvest.exception.DBException;
import br.com.fiap.ladyinvest.factory.DAOFactory;

@WebServlet("/investimento")
public class InvestimentoServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    
	   private InvestimentoDAO dao;
	   
	   @Override
	   public void init() throws ServletException{
		   super.init();
		   dao = DAOFactory.getInvestimentoDAO();
	   }


		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String acao = request.getParameter("acao");
			
			switch (acao) {
			case "listar":
				listar(request, response);
				break;
			case "form-editar-investimento":
				abrirFormEdicao(request, response);
				break;	
			case "abrir-form-cadastro":
				abrirFormCadastro(request, response);
				break;
			}	
		}
		
		private void abrirFormCadastro (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Investimento> lista = dao.listar();
			request.setAttribute("investimento", lista);
			request.getRequestDispatcher("cadastra-investimento.jsp").forward(request, response);
		}
			
		private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("codigo"));
			Investimento investimento = dao.buscar(id);
			request.setAttribute("investimento", investimento);
			request.getRequestDispatcher("edicao-investimento.jsp").forward(request, response);
		}
		
		private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Investimento> lista = dao.listar();
			request.setAttribute("investimento", lista);
			request.getRequestDispatcher("lista-investimentos.jsp").forward(request, response);
		}
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String acao = request.getParameter("acao");
			
			switch (acao) {
			case "cadastrar":
				cadastrar(request, response);
				break;
			case "editar":
				editar(request, response);
				break;
			case "excluir":
				excluir(request, response);
				break;
			}
		}
		
		private void excluir(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			try {
				dao.remover(codigo);
				request.setAttribute("msg", "Item removido!");
			} catch (DBException e) {
				e.printStackTrace();
				request.setAttribute("erro", "Erro ao atualizar");
			}
			listar(request,response);
		}
		
		private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				int codigo = Integer.parseInt(request.getParameter("codigo"));
				String descricao = request.getParameter("nomeInvestimento");
				double valor = Double.parseDouble(request.getParameter("valor"));
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Calendar dtAporte = Calendar.getInstance();
				dtAporte.setTime(format.parse(request.getParameter("dataAporte")));
				String tempoInvestimento = request.getParameter("tempo");
				

				Investimento investimento = new Investimento(codigo, descricao, valor, dtAporte, tempoInvestimento);
				
				dao.atualizar(investimento);

				request.setAttribute("msg", "item atualizado!");
			} catch (DBException db) {
				db.printStackTrace();
				request.setAttribute("erro", "Erro ao atualizar");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("erro", "Por favor, valide os dados");
			}
			listar(request,response);
		}
		
		private void cadastrar(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
				
				String descricao = request.getParameter("nomeInvestimento");
				double valor = Double.parseDouble(request.getParameter("valor"));
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Calendar dtAporte = Calendar.getInstance();
				dtAporte.setTime(format.parse(request.getParameter("dataAporte")));
				String tempoInvestimento = request.getParameter("tempo");
				

				Investimento investimento = new Investimento(0, descricao, valor, dtAporte, tempoInvestimento);
								
				dao.cadastrar(investimento);
				
				request.setAttribute("msg", "Investimento Cadastrada");
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
