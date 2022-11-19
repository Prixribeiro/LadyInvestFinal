<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar Receitas</title>
<%@ include file="header.jsp" %>
</head>
<body>


<%@ include file="Menu.jsp" %>
<div class="container-fluid mt-5 py-2">
	<h1 class="text-center mt-4">Cadastrar Receitas</h1>
	<c:if test="${not empty msg}" >
		<div class="alert alert-success">${msg}</div>
	</c:if>
	<c:if test="${not empty erro}" >
		<div class="alert alert-danger">${erro}</div>
	</c:if>
	<form action="receita" method="post">
		<input type="hidden" value="cadastrar" name="acao" >
		<div class="form-group m-3">
			<label for="id-tem" class="my-2">Item</label>
			<input type="text" name="item" id="id-item" class="form-control">
		</div>
		<div class="form-group m-3">
			<label for="id-valor" class="my-2">Valor</label>
			<input type="text" name="valor" id="id-valor" class="form-control">
		</div>
		<div class="form-group m-3">
			<label for="id-data" class="my-2">Data</label>
			<input type="text" name="data" id="id-data" class="form-control">
		</div>
		<div class="form-group m-3">
			<label for="id-categoria" class="my-2">Categoria</label>
			<select name="categoria" id="id-categoria" class="form-control">
				<option value="0"> Selecionar </option>
				<c:forEach items="${categorias}" var="c">
					<option value="${c.codigo}"> ${c.nomeCategoria}</option>
				</c:forEach>
				
			</select>
		</div>
		
		<div class="form-group m-3">
			<label for="id-tipoConta" class="my-2">Origem</label>
			<select name="tipoConta" id="id-tipoConta" class="form-control">
				<option value="0"> Selecionar </option>
				<c:forEach items="${tipoConta}" var="tc">
					<option value="${tc.codigo}"> ${tc.nome}</option>
				</c:forEach>
				
			</select>
		</div>
		
		<input type="submit" value="Salvar" class="btn btn-success m-3">
		<a href="receita?acao=listar" class="btn btn-primary m-3 float-end">Voltar</a>
	</form>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>