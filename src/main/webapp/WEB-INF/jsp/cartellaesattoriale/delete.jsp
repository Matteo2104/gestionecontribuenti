<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Rimuovi Cartella Esattoriale</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Sei sicuro di voler rimuovere questa Cartella Esattoriale?</h5>
					    </div>
					    <form method="post" action="${pageContext.request.contextPath}/cartellaesattoriale/remove" class="row g-3" >
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${delete_cartella_attr.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Descrizione:</dt>
							  <dd class="col-sm-9">${delete_cartella_attr.descrizione}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Importo:</dt>
							  <dd class="col-sm-9">${delete_cartella_attr.importo}</dd>
					    	</dl>
					    	
					    	
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Stato :</dt>
							  <dd class="col-sm-9">${delete_cartella_attr.stato}</dd>
					    	</dl>
					    	
					    	<!-- info contribuente -->	
					    	<p>
							  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
							    Info Regista
							  </a>
							</p>
							<div class="collapse" id="collapseExample">
							  <div class="card card-body">
							  	<dl class="row">
								  <dt class="col-sm-3 text-right">Nome:</dt>
								  <dd class="col-sm-9">${delete_cartella_attr.contribuente.nome}</dd>
							   	</dl>
							   	<dl class="row">
								  <dt class="col-sm-3 text-right">Cognome:</dt>
								  <dd class="col-sm-9">${delete_cartella_attr.contribuente.cognome}</dd>
							   	</dl>
							   	<dl class="row">
							  		<dt class="col-sm-3 text-right">Data di Nascita:</dt>
							  		<dd class="col-sm-9"><fmt:formatDate type="date" value = "${delete_cartella_attr.contribuente.dataDiNascita}" /></dd>
					    		</dl>
							   	<dl class="row">
								  <dt class="col-sm-3 text-right">Codice Fiscale:</dt>
								  <dd class="col-sm-9">${delete_cartella_attr.contribuente.codiceFiscale}</dd>
							   	</dl>
							   	<dl class="row">
								  <dt class="col-sm-3 text-right">Indirizzo:</dt>
								  <dd class="col-sm-9">${delete_cartella_attr.contribuente.indirizzo}</dd>
							   	</dl>
							    
							  </div>
					    	
					    	</div>
					    	
					    </div>
					    <!-- end card body -->
					    
					    <div class='card-footer'>
					        <a href="${pageContext.request.contextPath}/cartellaesattoriale" class='btn btn-outline-secondary' style='width:80px'>
					            <i class='fa fa-chevron-left'></i> Back
					        </a>
					        <button type="submit" name="idCartella" value="${delete_cartella_attr.id}" id="submit" class="btn btn-danger">Rimuovi</button>
					    </div>
					    
					    
					    </form>
					<!-- end card -->
					
					</div>	
			  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>