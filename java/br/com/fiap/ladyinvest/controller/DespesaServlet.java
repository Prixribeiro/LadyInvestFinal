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

import br.com.fiap.ladyinvest.bean.Categoria;
import br.com.fiap.ladyinvest.bean.Despesa;
import br.com.fiap.ladyinvest.dao.CategoriaDAO;
import br.com.fiap.ladyinvest.dao.DespesaDAO;
import br.com.fiap.ladyinvest.dao.TipoContaDAO;
import br.com.fiap.ladyinvest.exception.DBException;
import br.com.fiap.ladyinvest.factory.DAOFactory;
import br.com.fiap.ladyinvest.bean.TipoConta;


@WebServlet("/despesa")
public class DespesaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	   private DespesaDAO dao;
	   private CategoriaDAO categoriaDao;
	   private TipoContaDAO tipoContaDao;
	   
	   @Override
	   public void init() throws ServletException{
		   super.init();
		   dao = DAOFactory.getDespesaDAO();
		   categoriaDao = DAOFactory.getCategoriaDAO();
		   tipoContaDao = DAOFactory.getTipoContaDAO();
	   }


		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String acao = request.getParameter("acao");
			
			switch (acao) {
			case "listar":
				listar(request, response);
				break;
			case "form-editar-despesa":
				abrirFormEdicao(request, response);
				break;
			case "abrir-form-cadastro":
				abrirFormCadastro(request, response);
				break;

				
			}	
		}
		
		private void abrirFormCadastro (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Categoria> lista = categoriaDao.listar();
			request.setAttribute("categorias", lista);
			List<TipoConta> listar = tipoContaDao.listar();
			request.setAttribute("tipoConta", listar);
			request.getRequestDispatcher("cadastra-despesa.jsp").forward(request, response);
		}
		
		private void carregarOpcoesCategoria(HttpServletRequest request) {
			List<Categoria> lista = categoriaDao.listar();
			request.setAttribute("categorias", lista);
		}
		
		private void carregarOpcoesTipoConta(HttpServletRequest request) {
			List<TipoConta> listar = tipoContaDao.listar();
			request.setAttribute("tipoConta", listar);
		}
		
		private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("codigo"));
			Despesa despesa = dao.buscar(id);
			request.setAttribute("despesa", despesa);
			carregarOpcoesCategoria(request);
			carregarOpcoesTipoConta(request);
			request.getRequestDispatcher("edicao-despesa.jsp").forward(request, response);
		}
		
		private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Despesa> lista = dao.listar();
			request.setAttribute("despesas", lista);
			request.getRequestDispatcher("lista-despesas.jsp").forward(request, response);
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
				String descricao = request.getParameter("descricao");
				double preco = Double.parseDouble(request.getParameter("valor"));
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Calendar data = Calendar.getInstance();
				data.setTime(format.parse(request.getParameter("dataOperacao")));
				int codigoCategoria = Integer.parseInt(request.getParameter("categoria"));
				int codigoTipoConta = Integer.parseInt(request.getParameter("tipoConta"));
				
				Categoria categoria = new Categoria();
				categoria.setCodigo(codigoCategoria);
				
				TipoConta tipoconta = new TipoConta();
				tipoconta.setCodigo(codigoTipoConta);

				Despesa despesa = new Despesa(codigo, descricao, preco, data);
				despesa.setCategoria(categoria);
				despesa.setTipoConta(tipoconta);
				dao.atualizar(despesa);

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
				String item = request.getParameter("descricao");
				double valor = Double.parseDouble(request.getParameter("valor"));
				SimpleDateFormat format = new SimpleDateFormat ("dd/MM/yyyy");
				Calendar data = Calendar.getInstance();
				data.setTime(format.parse(request.getParameter("dataOperacao")));
				int codigoCategoria = Integer.parseInt(request.getParameter("categoria"));
				int codigoTipoConta = Integer.parseInt(request.getParameter("tipoConta"));
				
				Categoria categoria = new Categoria();
				categoria.setCodigo(codigoCategoria);
				
				TipoConta tipoConta = new TipoConta();
				tipoConta.setCodigo(codigoTipoConta);
				
				Despesa despesa = new Despesa(0, item, valor, data);
				despesa.setCategoria(categoria);
				despesa.setTipoConta(tipoConta);
				
				dao.cadastrar(despesa);
				
				request.setAttribute("msg", "Despesa Cadastrada");
			}catch(DBException db){
				db.printStackTrace();
				request.setAttribute("erro", "Erro ao cadastrar");
				
			}catch(Exception e) {
				e.printStackTrace();
				request.setAttribute("erro", "Por favor valide os dados.");
			}
			
			abrirFormCadastro(request, response);
			
		}

}