<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp" />
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">CHARGEMENT -> CONSULTER -> ${chargement.id} </h1>
	</div>
	<div class="row">

		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<!-- Card Header - Dropdown -->
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">Consultation de chargement</h5>
				</div>
				<!-- Card Body -->
				<div class="card-body table-responsive">
					<form:form action="${pageContext.request.contextPath}/chargement/consult" method="post"
						modelAttribute="chargement">

						<form:hidden path="id" />
						<div class="row">
							<div class="col-md-6">
								<label for="identifiant">Identifiant :</label> ${chargement.id}
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="mb-3">
									<label for="dateSimuation">Flux :</label> ${chargement.flux }

								</div>
							</div>
							<div class="col-md-6">
								<div class="mb-3">
									<label for="dateSimuation">State :</label> ${chargement.state }

								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="mb-3">
									<label for="dateSimuation">Date debut de chargement :</label>
									${chargement.dateChargement}

								</div>
							</div>
							<div class="col-md-6">
								<div class="mb-3">
									<label for="dateArreter">Date fin de chargement :</label>
									${chargement.dateFinChargement}
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="mb-3">
									<label for="dateGeneration">Nombre de lignes chargées :</label>
									${chargement.totalCharged}
								</div>
							</div>
							<div class="col-md-6">
								<div class="mb-3">
									<label for="dateGeneration">Identifiant du premier
										élément chargée :</label> ${chargement.fisrt }
								</div>
							</div>
						</div>
						<c:if test="${chargement.statut == true}">
							<fieldset>
								<legend>
									<h5>Données chargées</h5>
								</legend>
								<div class="table-responsive">
									<table id="chargement-table"
										class="table  table-bordered table-hover datatable">
										<thead>
											<tr>
												<c:forEach var="th" items="${ chargement.details.header}">
													<th>${th }</th>
												</c:forEach>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="tr" items="${ chargement.details.details}">
												<tr>
													<c:forEach var="td" items="${ tr}">
														<td>${td }</td>
													</c:forEach>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</fieldset>
						</c:if>
						<c:if test="${chargement.statut == false}">
							<h3 style="color: red; font-size: large;">Les données de
								chargement ne sont pas correctes, veuillez consulter la liste
								des erreurs !!</h3>
							<table id="erreur-table"
											class="table  table-bordered table-hover datatable">
											<thead>
												<tr>
													<th style="width: 1%">numéro de ligne</th>
													<th>Description erreur</th>
												</tr>
											</thead>
											<tbody>

												<c:forEach var="erreur" items="${ chargement.errors}">
													<c:forEach var="val" items="${ erreur.value}"
														varStatus="loop">
														<tr>
															<td>${erreur.key}</td>
															<td>${val }</td>
														</tr>
													</c:forEach>
												</c:forEach>
											</tbody>
										</table>
						</c:if>
						<br>

						<div class="text-end" style="padding-top: 2em;">
							<a
								class="btn btn-secondary rounded-pill px-4 waves-effect waves-light"
								href="${pageContext.request.contextPath}/chargement/"> <i class="fa fa-arrow-left bigger-110"></i>
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
			$('#"chargement-table"').DataTable({
				dom : 'frtipB',
				"lengthMenu": [ [10, 25, 50, -1], [10, 25, 50, "Tout"] ],
				"language" : {url : '${pageContext.request.contextPath}/assets1/json/datatable-fr-FR.json'},
				"buttons" : [ 'csv', 'excel', 'pdf' ]
			});
			$('#erreur-table').DataTable({
				dom : 'frtipB',
				rowGroup : {
					dataSrc : 0
				},
				"language" : { url: '${pageContext.request.contextPath}/assets1/json/datatable-fr-FR.json'},
				buttons : [ 'csv', 'excel', 'pdf' ]
			});
		});
	</script>
	<jsp:include page="../footer.jsp" />