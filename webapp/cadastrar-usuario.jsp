<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Cadastro </title>

<%@ include file="header.jsp" %>

</head>

<body class="bg-gradient-primary">
<%@ include file="Menu.jsp" %>
  <div class="container-fluid">

    <div class="card o-hidden border-0 shadow-lg my-5">
      <div class="card-body p-0">

        <div class="row">
          <div class="col-lg-5 d-none d-lg-block bg-cadastro-image"></div>
          <div class="col-lg-7">
            <div class="p-5">
              <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4">Crie sua conta!</h1>
                <c:if test="${not empty msg }">
					<div class="alert alert-success">${msg}</div>
				</c:if>
				<c:if test="${not empty erro }">
					<div class="alert alert-danger">${erro}</div>
				</c:if>
              </div>
              <form class="user" id=cadastro action="cadastrar-usuario" method="POST">
              <input type="hidden" value="cadastrar" name="acao">
                
                <div class="form-group my-4">
                  <input type="text" class="form-control form-control-user" name="email" id="email" placeholder="Email" required>
                </div>
                <div class="form-group row">
                  <div class="col-sm-6 mb-3 mb-sm-0">
                    <input type="password" class="form-control form-control-user" name="senha" id="senha" placeholder="Senha" required>
                    <small
						id="texto ajuda" class="form-text text-muted"> Sua senha
						deve ter no mínimo 6 caractéres combinando letras e números. 
					</small>
                  </div>
                  <div class="col-sm-6">
                    <input type="password" class="form-control form-control-user" name="confirmaSenha" id="confirmaSenha" placeholder="Confirme sua senha" required>
                  </div>
                </div>
             
                <input type="submit" value="Criar conta" class="btn btn-primary btn-user btn-block my-4" />

              </form>
              <hr>
              <div class="text-center">
                <a class="strong" href="esqueceu-senha.jsp">Esqueceu sua senha?</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
  <%@ include file="footer.jsp" %>
</body>
</html>