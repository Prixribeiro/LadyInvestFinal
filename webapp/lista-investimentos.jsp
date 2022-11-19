<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Investimentos</title>
<%@ include file="header.jsp" %>
</head>
<body>

<%@ include file="Menu.jsp" %>
	<div class="container-fluid mt-5">
		<h2 class="pt-3">Investimentos</h2>
		<c:if test="${not empty msg }">
			<div class="alert alert-success">${msg}</div>
		</c:if>
		<c:if test="${not empty erro }">
			<div class="alert alert-danger">${erro}</div>
		</c:if>
		<table class="table table-striped table-responsive w-100 h-auto text-center shadow p-1 mb-5 rounded">
		<thead class="table-light">
			<tr>
				<th>Data</th>
				<th>Descrição</th>
				<th>Valor</th>
				<th>Tempo do Investimento</th>
				<th></th>
				
			</tr>
		</thead>
			<c:forEach items="${investimento}" var="i">
			<tbody class="text-dark">
				<tr>
					<td>
						<fmt:formatDate value="${i.dataAporte.time }" pattern="dd/MM/yyyy"/>
					</td>
					<td>${i.nomeInvestimento}</td>
					<td><fmt:formatNumber value="${i.valor}" type="currency" /></td>
					<td>${i.tempo}</td>
					<td>
						<c:url value="investimento" var="link">
							<c:param name="acao" value="form-editar-investimento" />
							<c:param name="codigo" value="${i.codigo}" />
						</c:url>
						<a href="${link}" class ="btn btn-xs btn-primary">Editar</a>
					
						<button type="button" class ="btn btn-xs btn-danger float-end" data-bs-toggle="modal" data-bs-target="#excluirModal" onclick="codigoExcluir.value = ${i.codigo}">Excluir</button>
					</td>
				</tr>
				</tbody>
			</c:forEach>
		</table>
		<a href="investimento?acao=abrir-form-cadastro">
			<button type="button" class="btn btn-dark float-end" >
				ADICIONAR NOVO ITEM
			</button>
		</a>
	</div>
<%@ include file="footer.jsp" %>

<!-- Modal -->
<div class="modal fade" id="excluirModal" tabindex="-1"  btn-close aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" >
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Confirmação</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
          <span aria-hidden="true"></span>
        </button>
      </div>
      <div class="modal-body">
        		Deseja realmente excluir este item?
      </div>
      <div class="modal-footer">
      	<form action="investimento" method="post">
      		<input type="hidden" name="acao" value="excluir">
      		<input type="hidden" name="codigo" id="codigoExcluir">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
	        <button type="submit" class="btn btn-danger">Excluir</button>
        </form>
      </div>
    </div>
  </div>
</div>


</body>
</html>