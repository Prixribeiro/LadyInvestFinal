<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lady Invest</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="Menu.jsp" %>

<div class="container-fluid my-3 py-4">
		 
        <div class="row mt-2 mx-3">
            <div class="col-md-8 float-start">
                <h6 class="text-dark mt-5 ">Acompanhe suas movimentações, relatórios e investimentos em um único lugar</h6>
            </div>

        </div>
      </div>
      
      <div class="row m-3">
        <div class="col-sm-4">
          <div class="card">
            <div class="card-body">
              <h6 class="card-title text-dark">Investimentos</h6>
              <p class="card-text text-dark">Consulte seus investimentos atuais e encontre novas oportunidades de investir.</p>
              <a href="investimento?acao=listar" class="btn btn-sm btn-secondary float-end">Abrir</a>
            </div>
          </div>
        </div>
        <div class="col-sm-4">
          <div class="card">
            <div class="card-body">
              <h6 class="card-title text-dark">Receitas</h6>
              <p class="card-text text-dark">Cadastre seus recebimentos, reembolsos, entradas em cash.</p>
              <a href="receita?acao=listar" class="btn btn-sm btn-secondary float-end">Abrir</a>
            </div>
          </div>
        </div>
        <div class="col-sm-4">
          <div class="card">
            <div class="card-body">
              <h6 class="card-title text-dark">Despesas</h6>
              <p class="card-text text-dark">Cadastre seus gastos para não ter surpresa no final do mês.</p>
              <a href="despesa?acao=listar" class="btn btn-sm btn-secondary float-end">Abrir</a>
            </div>
          </div>
        </div>
      </div>  
      <div id="carouseCaptions" class="carousel slide" data-bs-ride="false">
        <div class="carousel-indicators">
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
        </div>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img src="resources/images/podcast.jpg" class="d-block w-100 h-50" alt="Imagem de chamada do Podcast da semana">
            <div class="carousel-caption d-none d-md-block">
              <h5>Fala Patroa!</h5>
              <p class="text-white">Vem ouvir nosso podcast incrível sobre investimentos com a Nath Arcury, dona do MePoupe!</p>
            </div>
          </div>
          <div class="carousel-item">
            <img src="resources/images/Tesouro-Direto.jpg" class="d-block w-100 h-50" alt="Imagem de chamada do post no blog sobre Tesouro Direto">
            <div class="carousel-caption d-none d-md-block">
              <h5>No Blog: Tesouro Direto</h5>
              <p class="text-white">Vem acompanhar as últimas novidades sobre o Tesouro Direto e as alterações da taxa Selic.</p>
            </div>
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouseCaptions" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouseCaptions" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>   

<%@ include file="footer.jsp" %>
</body>
</html>