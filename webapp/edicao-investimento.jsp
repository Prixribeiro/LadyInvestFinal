<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atualização de Investimento</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="Menu.jsp" %>
<div class="container">
	<h1>Editar Investimento</h1>
	<form action="investimento" method="post">
		<input type="hidden" value="editar" name="acao">
		<input type="hidden" value="${investimento.codigo}" name="codigo">
		<div class="form-group">
			<label for="id-item">Item</label>
			<input type="text" name="nomeInvestimento" id="id-item" class="form-control" value="${investimento.nomeInvestimento}" >
		</div>
		<div class="form-group">
			<label for="id-valor">Valor</label>
			<input type="text" name="valor" id="id-valor" class="form-control" value="${investimento.valor}">
		</div>
	
		<div class="form-group">
			<label for="id-data">Data</label>
			<input type="text" name="dataAporte" id="id-data" class="form-control" 
				value='<fmt:formatDate value="${investimento.dataAporte.time }" pattern="dd/MM/yyyy"/>'>
		</div>
		
		<div class="form-group ">
			<label for="id-tempo" >Tempo de Investimento</label>
			<input type="text" name="tempo" id="id-tempo" class="form-control" value="${investimento.tempo}">
		</div>
		
		<input type="submit" value="Salvar" class="btn btn-primary mt-3">
		<a href="investimento?acao=listar" class="btn btn-danger mt-3 float-end">Cancelar</a>
	</form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>