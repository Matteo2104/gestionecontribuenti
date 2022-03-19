<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Ricerca</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Ricerca Cartella Esattoriale</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form method="post" action="${pageContext.request.contextPath}/cartellaesattoriale/find" class="row g-3" >
							
								<div class="col-md-6">
									<label for="descrizione" class="form-label">Descrizione <span class="text-danger">*</span></label>
									<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire una descrizione"  >
								</div>
								
								<div class="col-md-6">
									<label for="importo" class="form-label">Importo <span class="text-danger">*</span></label>
									<input type="number" name="importo" id="importo" class="form-control" placeholder="Inserire importo"  >
								</div>
							
								<div class="col-md-3">
									<label for="stato" class="form-label">Stato <span class="text-danger">*</span></label>
								    <select class="form-select" id="stato" name="stato" >
								    	<option value="" selected> - Selezionare - </option>
								      	<option value="CREATA" >Creata</option>
								      	<option value="IN_VERIFICA"  >In Verifica</option>
								      	<option value="CONCLUSA" >Conclusa</option>
								      	<option value="IN_CONTENZIOSO"  >In Contenzioso</option>
								    </select>
								</div>
								
								
								
								
							<div class="col-12">
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
								<a class="btn btn-outline-primary ml-2" href="${pageContext.request.contextPath}/cartellaesattoriale/insert">Add New</a>
								<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
							</div>
		
						</form>
  
				    
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>