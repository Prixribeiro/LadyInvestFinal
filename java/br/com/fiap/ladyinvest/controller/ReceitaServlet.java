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
import br.com.fiap.ladyinvest.bean.Receita;
import br.com.fiap.ladyinvest.dao.CategoriaDAO;
import br.com.fiap.ladyinvest.dao.ReceitaDAO;
import br.com.fiap.ladyinvest.dao.TipoContaDAO;
import br.com.fiap.ladyinvest.exception.DBException;
import br.com.fiap.ladyinvest.factory.DAOFactory;
import br.com.fiap.ladyinvest.bean.TipoConta;

@WebServlet("/receita")
public class ReceitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   private ReceitaDAO dao;
   private CategoriaDAO categoriaDao;
   private TipoContaDAO tipoContaDao;
   
   @Override
   public void init() throws ServletException{
	   super.init();
	   dao = DAOFactory.getReceitaDAO();
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
		case "form-editar-receita":
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
		request.getRequestDispatcher("cadastra-receita.jsp").forward(request, response);
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
		Receita receita = dao.buscar(id);
		request.setAttribute("receita", receita);
		carregarOpcoesCategoria(request);
		carregarOpcoesTipoConta(request);
		request.getRequestDispatcher("edicao-receita.jsp").forward(request, response);
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Receita> lista = dao.listar();
		request.setAttribute("receitas", lista);
		request.getRequestDispatcher("lista-receitas.jsp").forward(request, response);
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
			String descricao = request.getParameter("item");
			double preco = Double.parseDouble(request.getParameter("valor"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataOperacao = Calendar.getInstance();
			dataOperacao.setTime(format.parse(request.getParameter("data")));
			int codigoCategoria = Integer.parseInt(request.getParameter("categoria"));
			int codigoTipoConta = Integer.parseInt(request.getParameter("tipoConta"));
			
			Categoria categoria = new Categoria();
			categoria.setCodigo(codigoCategoria);
			
			TipoConta tipoconta = new TipoConta();
			tipoconta.setCodigo(codigoTipoConta);

			Receita receita = new Receita(codigo, descricao, preco, dataOperacao);
			receita.setCategoria(categoria);
			receita.setTipoConta(tipoconta);
			dao.atualizar(receita);

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
			String item = request.getParameter("item");
			double valor = Double.parseDouble(request.getParameter("valor"));
			SimpleDateFormat format = new SimpleDateFormat ("dd/MM/yyyy");
			Calendar dataOperacao = Calendar.getInstance();
			dataOperacao.setTime(format.parse(request.getParameter("data")));
			int codigoCategoria = Integer.parseInt(request.getParameter("categoria"));
			int codigoTipoConta = Integer.parseInt(request.getParameter("tipoConta"));
			
			
			Categoria categoria = new Categoria();
			categoria.setCodigo(codigoCategoria);
			
			TipoConta tipoConta = new TipoConta();
			tipoConta.setCodigo(codigoTipoConta);
			
			Receita receita = new Receita(0, item, valor, dataOperacao);
			receita.setCategoria(categoria);
			receita.setTipoConta(tipoConta);
			
			dao.cadastrar(receita);
			
			request.setAttribute("msg", "Receita Cadastrada");
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
