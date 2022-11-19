<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Receita</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="Menu.jsp" %>
<div class="container mt-5">
	<h1 class="mt-5 pt-5">Editar Receita</h1>
	<form action="receita" method="post">
		<input type="hidden" value="editar" name="acao">
		<input type="hidden" value="${receita.codigo}" name="codigo">
		<div class="form-group">
			<label for="id-item">Item</label>
			<input type="text" name="item" id="id-item" class="form-control" value="${receita.descricao}" >
		</div>
		<div class="form-group">
			<label for="id-valor">Valor</label>
			<input type="text" name="valor" id="id-valor" class="form-control" value="${receita.valor}">
		</div>
	
		<div class="form-group">
			<label for="id-data">Data</label>
			<input type="text" name="data" id="id-data" class="form-control" 
				value='<fmt:formatDate value="${receita.dataOperacao.time }" pattern="dd/MM/yyyy"/>'>
		</div>
		
		<div class="form-group">
			<label for="id-categoria">Categoria</label>
			<select name="categoria" id="id-categoria" class="form-control">
				<option value="0"> Selecionar </option>
				<c:forEach items="${categorias}" var="c">
					<c:if test="${c.codigo == receita.categoria.codigo}">
						<option value="${c.codigo}" selected> ${c.nomeCategoria}</option>
					</c:if>
					<c:if test="${c.codigo != receita.categoria.codigo}">
						<option value="${c.codigo}"> ${c.nomeCategoria}</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
		
		<div class="form-group">
			<label for="id-tipoConta">Origem</label>
			<select name="tipoConta" id="id-tipoConta" class="form-control">
				<option value="0"> Selecionar </option>
				<c:forEach items="${tipoConta}" var="tp">
					<c:if test="${tp.codigo == receita.tipoConta.codigo}">
						<option value="${tp.codigo}" selected> ${tp.nome}</option>
					</c:if>
					<c:if test="${tp.codigo != receita.tipoConta.codigo}">
						<option value="${tp.codigo}"> ${tp.nome}</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
		
		<input type="submit" value="Salvar" class="btn btn-primary mt-3">
		<a href="receita?acao=listar" class="btn btn-danger mt-3 float-end">Cancelar</a>
	</form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>