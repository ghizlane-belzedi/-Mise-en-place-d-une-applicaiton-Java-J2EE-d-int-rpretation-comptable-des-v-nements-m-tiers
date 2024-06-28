<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../header.jsp" />
<div class="page-wrapper">
	<!-- ============================================================== -->
	<!-- Container fluid  -->
	<!-- ============================================================== -->
	<div class="container-fluid">
		<div class="row">
			<!-- column -->
			<div class="col-sm-12">
				<div class="card">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">Ajouter une simulation</h4>
							<form:form action="${pageContext.request.contextPath}/simulation/simuler" method="get"
								modelAttribute="simulation" id="form-simulation">
								<nav class="nav nav-tabs">
									<a
										class="nav-item nav-link p1 ${resultatSimulation == null ? 'active' : '' }"
										onclick="activer('p1')" data-toggle="tab">Génération </a> <a
										class="nav-item nav-link p2 ${resultatSimulation != null ? 'active' : '' }"
										onclick="activer('p2')" data-toggle="tab">Simulation </a>
								</nav>
								<div class="tab-content">
									<div
										class="tab-pane fade ${resultatSimulation == null ? 'active show' : '' }"
										id="p1" style="border: 1px black">
										<br>
										<div class="row"
											style="border-color: black; border-style: solid; border-width: 0.0em">
											<div class="col-md-6">
												<label>Flux :</label>
												<form:select class="form-control" path="flux">
													<form:option value="">--- Choisir un flux</form:option>
													<c:forEach var="f" items="${ flux}">
														<form:option value="${f.key }">${f.value }</form:option>
													</c:forEach>
												</form:select>
												<span style="color: red; text-align: center;">${FLUX_ERROR }</span>
											</div>
											<div class="col-md-6">
												<label>Date Arrêté à :</label>
												<form:input type="date" class="form-control"
													path="dateArreter" />
												<span class="error">${DATE_ARRETE_ERROR }</span>
											</div>
										</div>
										<br>
										<fieldset ${resultatGeneration != null }>
											<h3>Extraction de donnée</h3>
											<h4>
												<span class="error">${GENERATION_VIDE }</span>
											</h4>
											<table id="simple-table"
												class="table  table-bordered table-hover">
												<thead>
													<tr>
														<c:forEach var="th" items="${ resultatGeneration.header}">
															<th>${th }</th>
														</c:forEach>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="tr" items="${ resultatGeneration.details}">
														<tr>
															<c:forEach var="td" items="${ tr}">
																<td>${td }</td>
															</c:forEach>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</fieldset>

									</div>
									<div
										class="tab-pane fade ${resultatSimulation != null ? 'active show' : '' }"
										id="p2">
										<c:if test="${resultatSimulation.containsKey(true)}">
											<fieldset>
												<h3>Extraction des écritures comptable</h3>
												<table id="ecriture-table"
													class="table  table-bordered table-hover">
													<thead>
														<tr>
															<th>Code Journal</th>
															<th>N° Pièce</th>
															<th>Date</th>
															<th>Description entête Doc</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="piece"
															items="${ resultatSimulation[true]}">
															<tr>
																<td>${piece.codeJournale }</td>
																<td>${piece.numeroPiece }</td>
																<td>${piece.date }</td>
																<td>${piece.description }</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</fieldset>

										</c:if>
										<c:if test="${resultatSimulation.containsKey(false)}">
											<h3 style="color: red; font-size: large;">Les données de
												simulation ne sont pas correctes, veuillez consulter la liste
												des erreurs</h3>
											<table id="erreur-table"
												class="table  table-bordered table-hover">
												<thead>
													<tr>
														<th style="width: 1%">numéro de ligne</th>
														<th>Description erreur</th>
													</tr>
												</thead>
												<tbody>

													<c:forEach var="erreur"
														items="${ resultatSimulation[false]}">
														<tr>
															<td rowspan="${fn:length(erreur.value)}">${erreur.key}</td>
															<c:forEach var="val" items="${ erreur.value}"
																varStatus="loop">
																<c:if test="${loop.index > 0}">
														</tr>
														<tr>
															</c:if>
															<td>${val }</td>
													</c:forEach>
													</tr>
													</c:forEach>
												</tbody>
											</table>
										</c:if>
									</div>
								</div>

								<div class="text-end" style="padding-top: 2em;">
									<a
										class="btn btn-secondary rounded-pill px-4 waves-effect waves-light"
										href="${pageContext.request.contextPath}/simulation/"> <i
										class="fa fa-arrow-left bigger-110"></i> Annuler
									</a>
									<button type="submit"
										class="btn btn-info rounded-pill px-4 waves-effect waves-light">
										<i class="fa fa-bars bigger-110"></i> Simuler
									</button>
									<c:if test="${resultatSimulation.containsKey(true)}">
										<a
											href="${pageContext.request.contextPath}/simulation/generer/${simulation.id}"
											class="btn btn-primary rounded-pill px-4 waves-effect waves-light">
											<i class="fa fa- bigger-110"></i> Génerer
										</a>
									</c:if>
									<!-- -->
								</div>
							</form:form>

							<!-- Form 2 -->


						</div>
					</div>
				</div>
			</div>
			<!-- column -->
		</div>
		<!-- ============================================================== -->
		<!-- Table -->
		<!-- ============================================================== -->
		<div class="row">
			<div class="col-sm-12"></div>
		</div>
		<!-- ============================================================== -->
		<!-- Table -->
		<!-- ============================================================== -->

	</div>
	<!-- ============================================================== -->
	<!-- End Container fluid  -->
	<!-- ============================================================== -->

	<script type="text/javascript">
		function activer(element) {
			$('#form-simulation .tab-content div').removeClass('show active');
			$('#form-simulation nav a').removeClass('active');
			$('#' + element).addClass('show active');
			$('#form-simulation .' + element).addClass('active');
		}
		$(document).ready(function() {
			$('#simple-table').DataTable({
				dom : 'Bfrtip',
				"language" : { url: '${pageContext.request.contextPath}/assets/json/datatable-fr-FR.json'},
				buttons : [ 'csv', 'excel', 'pdf' ]
			});
		});
	</script>

	<jsp:include page="../footer.jsp" />