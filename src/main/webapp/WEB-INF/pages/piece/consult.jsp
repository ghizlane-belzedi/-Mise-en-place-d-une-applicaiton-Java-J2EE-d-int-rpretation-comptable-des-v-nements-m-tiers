<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp" />
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">PIECE COMPTABLE -> CONSULTER -> ${piece.id} </h1>
	</div>
	<div class="row">

		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<!-- Card Header - Dropdown -->
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary"><spring:message code="consultation.piece.comptable"/></h5>
				</div>
				<!-- Card Body -->
				<div class="card-body table-responsive">
					<form:form action="${pageContext.request.contextPath}/piece-comptable/consult" method="post" modelAttribute="piece">
								<fieldset class="border p-3">
									<legend> Piece Comptable </legend>
									<form:hidden path="id"/>
									<div class="row">
										<div class="col-md-6">
											<div class="mb-3">
												<label for="dateSimuation">Flux :</label> ${piece.flux }
												
											</div>
										</div>
										<div class="col-md-6">
											<div class="mb-3">
												<label for="dateSimuation">Statut :</label> ${piece.statut ? 'Envoyé' : 'Non envoyé'}
												
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="mb-3">
												<label for="dateSimuation">Code journale :</label> ${piece.codeJournale}
												
											</div>
										</div>
										<div class="col-md-6">
											<div class="mb-3">
												<label for="dateArreter">Numéro de piece :</label> ${chargement.numeroPiece}
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="mb-3">
												<label for="dateGeneration">Date Piece :</label> ${piece.date}
											</div>
										</div>
										<div class="col-md-6">
											<div class="mb-3">
												<label for="dateGeneration">Description :</label>
												${piece.description }
											</div>
										</div>
									</div>
							<div class="table-responsive">
							
							<table id="simple-table"
								class="table  table-bordered table-hover">
								<thead>
									<tr>
										<th>Account number</th>
										<th>Cost center</th>
										<th>Account description</th>
										<th>Ref 1</th>
										<th>Ref 2</th>
										<th>Ref 3</th>
										<th>Ref 4</th>
										<th>Ref 5</th>
										<th>Ref 6</th>
										<th>currency amount<br> (debit)</th>
										<th>currency amount <br>(credit)</th>
										<th>DEBIT (MAD)</th>
										<th>CREDIT ( MAD)</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach var="ecriture" items="${ piece.ecritures}">
										<tr>
											<td>${ecriture.ncpt}</td>
											<td>${ecriture.costCenter}</td>
											<td>${ecriture.accountDescription}</td>
											<td>${ecriture.ref1}</td>
											<td>${ecriture.ref2}</td>
											<td>${ecriture.ref3}</td>
											<td>${ecriture.ref4}</td>
											<td>${ecriture.ref5}</td>
											<td>${ecriture.ref6 }</td>
											<c:if test="${ecriture.sens == 'D' }">
												<td>${ecriture.montant}</td>
												<td></td>
												<td>${ecriture.montantMAD }</td>
												<td></td>
											</c:if>
											<c:if test="${ecriture.sens == 'C' }">
												<td></td>
												<td>${ecriture.montant}</td>
												<td></td>
												<td>${ecriture.montantMAD }</td>
											</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							</div>
								</fieldset>
								<br>
								
								<div class="text-end" style="padding-top: 2em;">
									<a
										class="btn btn-secondary rounded-pill px-4 waves-effect waves-light"
										href="${pageContext.request.contextPath}/piece-comptable/"> <i class="fa fa-arrow-left bigger-110"></i>
										Retour
									</a>
									<!--<c:if test="${chargement.statut == false}">
										<a
											href="/chargement/extract/erreur/${simulation.id}"
											class="btn btn-danger rounded-pill px-4 waves-effect waves-light">
											<i class="fa fa-bars bigger-110"></i> Erreurs 
										</a>
									</c:if>
									<c:if test="${chargement.statut == 'TERMINER'}">
										<a
											href="/chargement/extract/details/${simulation.id}"
											class="btn btn-primary rounded-pill px-4 waves-effect waves-light">
											<i class="fa fa-bars bigger-110"></i> Détails
										</a>
									</c:if>-->
								</div>
							</form:form>
				</div>
			</div>
		</div>

	</div>

</div>
<!-- End of Main Content -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('.datatable').DataTable({
				dom : 'frtipB',
				"language" : { url: '${pageContext.request.contextPath}/assets1/json/datatable-fr-FR.json'},
				buttons : [ 'csv', 'excel', 'pdf' ]
			});

		});
	</script>
	<jsp:include page="../footer.jsp" />