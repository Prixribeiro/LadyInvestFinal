    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <nav class="navbar fixed-top bg-danger mt-0 p-1">
      <div class="container-fluid">
			<c:if test="${empty user}">
	  		<form action="login" method="post" class="float-start">
    			<input type="text" class="form-control-sm" name="email" placeholder="E-mail">
    			<input type="password" class="form-control-sm" name="senha" placeholder="Senha">
    			<button class="btn btn-sm btn-outline-dark" type="submit">Entrar</button>
			</form>
			
	    	<span class="navbar-text text-dark" style="margin-right:10px">
	        	${erro}
	  		</span>	
    		</c:if>
    		<c:if test="${not empty user}">
    			<span class="navbar-text">
    			<a href="login" class="btn btn-sm btn-outline-dark text-white">Sair</a>
	    			${user}
	    			
	  			</span>	
    		</c:if>

       	<button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar">
         		<span class="navbar-toggler-icon"></span>
       	</button>

       	<div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
         	<div class="offcanvas-header">
           		<h5 class="offcanvas-title" id="offcanvasNavbarLabel">Tá boa patroa?</h5>
           		<button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
         	</div>
         	<div class="offcanvas-body">
           		<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
             		<li class="nav-item">
               			<a class="nav-link active" aria-current="page" href="login">Home</a>
             		</li>
             		<li class="nav-item">
               			<a class="nav-link" href="#">Perfil</a>
             		</li>
             		<li class="nav-item">
               			<a class="nav-link" href="investimento?acao=listar">Investimentos</a>
             		</li>
             		<li class="nav-item">
               			<a class="nav-link" href="receita?acao=listar">Receitas</a>
             		</li>
             		<li class="nav-item">
               			<a class="nav-link" href="despesa?acao=listar">Despesas</a>
             		</li>
           		</ul>
         	</div>
     	</div>
     	

    		
        </div>
        <c:if test="${empty user}">
        <div class="row ">
        	<div class="col-sm-12 ">
                <a href="cadastrar-usuario?acao=abrir-form-cadastro" type="url" class="text-white mx-4">Crie sua conta!</a>
                <button type="button" class="btn btn-sm btn-outline-light mx-5" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo">Esqueci minha senha</button>                     
            </div>
        </div>
        </c:if>
</nav>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content" >
                    <div class="modal-header bg-light">
                      <h5 class="modal-title bg-light" id="exampleModalLabel">Recuperar senha</h5>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body bg-light mt-0">
                      <form class="bg-light">
                        <p class="text-dark text-center bg-light">Informe o email cadastrado na plataforma para envio do link de recuperação de senha.</p>  
                        <div class="mb-3 bg-light">
                          <label for="recipient-name" class="col-form-label mb-2 bg-light">Email:</label>
                          <input type="email" class="form-control bg-light" id="recipient-email">
                        </div>
                      </form>
                    </div>
                    <div class="modal-footer">
                      <button type="submit" class="btn btn-dark text-white" data-bs-dismiss="modal">Enviar</button>
                    </div>
                  </div>
                </div>
              </div>
