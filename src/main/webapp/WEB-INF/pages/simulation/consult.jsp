<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp" />
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">SIMULATION -> CONSULTER</h1>
	</div>
	<div class="row">

		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<!-- Card Header - Dropdown -->
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">Consulter une
						simulation</h5>
				</div>
				<!-- Card Body -->
				<div class="card-body table-responsive">
					<form:form
						action="${pageContext.request.contextPath}/simulation/consult"
						method="post" modelAttribute="simulation" id="form-simulation">
						<h4>
							<span style="color: red;">${simulation.comment }</span>
						</h4>
						<nav class="nav nav-tabs">
							<a
								class="nav-item nav-link p1 active"
								onclick="activer('p1')" data-toggle="tab">Consultation </a> <a
								class="nav-item nav-link p2"
								onclick="activer('p2')" data-toggle="tab">Simulation </a>
						</nav>
						<div class="tab-content">
							<div class="tab-pane fade active show" id="p1"
								style="border: 1px black">
								<br>
								<h6 style="text-transform: uppercase; font-size: large; font-weight: bold; color: blue;"><spring:message code="donnees.extraites"/></h6>
								<fieldset ${simulation.resultatGeneration != null }>
									<div class="table-responsive">
										<table id="vehicule-table"
											class="table  table-bordered table-hover datatable">
											<thead>
												<tr>
													<c:forEach var="th" items="${ simulation.resultatGeneration.header}">
														<th>${th }</th>
													</c:forEach>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="tr" items="${ simulation.resultatGeneration.details}">
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
							</div>
							<div class="tab-pane fade" id="p2">
								<br>
								<form:hidden path="id" />
								<div class="row">
									<div class="col-md-6">
										<div class="mb-3">
											<label for="dateSimuation">Flux :</label> ${simulation.flux }
										</div>
									</div>

								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="mb-3">
											<label for="dateSimuation">Date Simulation :</label>
											${simulation.dateSimuation }

										</div>
									</div>
									<div class="col-md-6">
										<div class="mb-3">
									<label for="dateArreter"><spring:message code="date.arrete"/></label>
											${simulation.dateArreter}
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="mb-3">
									<label for="dateGeneration"><spring:message code="date.generation"/></label>
											${simulation.dateGeneration}
										</div>
									</div>
									<div class="col-md-6">
										<div class="mb-3">
											<label for="dateGeneration">Statut :</label>
											${simulation.state }
										</div>
									</div>
								</div>
								<c:if test="${simulation.statut == true}">
									<fieldset>
								<h3><spring:message code="extraction.ecritures.comptables"/></h3>
										<table id="ecriture-table"
											class="table  table-bordered table-hover datatable">
											<thead>
												<tr>
													<th>Code Journal</th>
											<th><spring:message code="numero.piece"/></th>
													<th>Date</th>
											<th><spring:message code="description.entete"/></th>
													<th>Actions</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="piece" items="${ simulation.pieces}">
													<tr>
														<td>${piece.codeJournale }</td>
														<td>${piece.numeroPiece }</td>
														<td>${piece.date }</td>
														<td>${piece.description }</td>
														<td><a onclick="openForm(${piece.id})"
															class="btn btn-success rounded-pill px-4 waves-effect waves-light">
																<i class="fa fa-eye bigger-110"></i>
														</a></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</fieldset>
								</c:if>
								<c:if test="${simulation.statut == false}">
							<h3 style="color: red; font-size: large;">
								<spring:message code="data.invalid"/>
							</h3>
							<table id="erreur-table" class="table table-bordered table-hover datatable">
										<thead>
											<tr>
										<th style="width: 5%;font-size: 12px">Magic</th>
										<th style="width: 95%;font-size: 12px">Description erreur</th>
											</tr>
										</thead>
										<tbody>

											<c:forEach var="erreur" items="${ simulation.errors}">
												<c:forEach var="val" items="${ erreur.value}"
													varStatus="loop">
													<tr>
												<td style="width: 5%;font-size: 12px">${erreur.key}</td>
												<td style="width: 95%;font-size: 12px">${val }</td>
													</tr>
												</c:forEach>
											</c:forEach>
										</tbody>
									</table>
								</c:if>
								<br>
							</div>
						</div>



						<div class="text-end" style="padding-top: 2em;">
							<a
								class="btn btn-secondary rounded-pill px-4 waves-effect waves-light"
								href="${pageContext.request.contextPath}/simulation/"> <i
								class="fa fa-arrow-left bigger-110"></i> Retour
							</a>
							<c:if
								test="${simulation.statut == true && simulation.state == 'EN_INSTANCE'}">
								<a
									href="${pageContext.request.contextPath}/simulation/generer/${simulation.id}"
									class="btn btn-primary rounded-pill px-4 waves-effect waves-light">
									<i class="fa fa-check bigger-110"></i> <spring:message code="generer"/>
								</a>
							</c:if>
							<c:if
								test="${simulation.statut == true &&( simulation.state == 'EN_INSTANCE' || simulation.state == 'VALIDER')}">
								<a
									href="${pageContext.request.contextPath}/simulation/exporter/${simulation.id}"
									class="btn btn-light rounded-pill px-4 waves-effect waves-light">
									<i class="fa fa-print bigger-110"></i> Exporter
								</a>
							</c:if>
							<c:if
								test="${simulation.state == 'EN_INSTANCE' || simulation.state == 'WITH_ERRORS' || simulation.state == EN_COURS}">
								<a
									href="${pageContext.request.contextPath}/simulation/annuler/${simulation.id}"
									class="btn btn-danger rounded-pill px-4 waves-effect waves-light">
									<i class="fa fa-xmark bigger-110"></i> Annuler
								</a>
							</c:if>
						</div>
					</form:form>
				</div>
			</div>
		</div>

	</div>

</div>
<!-- Modal -->
<div id="myModal" class="modal fade" tabindex="-1">
	<div class="modal-dialog  modal-xxl">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Consulter une piece comptable</h5>
			</div>
			<div class="modal-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal"
					onclick="$('#myModal').modal('hide')">Cancel</button>
			</div>
		</div>
	</div>
</div>
<!-- End of Main Content -->
<script type="text/javascript">
function activer(element) {
	$('#form-simulation .tab-content div').removeClass('show active');
	$('#form-simulation nav a').removeClass('active');
	$('#' + element).addClass('show active');
	$('#form-simulation .' + element).addClass('active');
	console.log(element)
}
		$(document).ready(function() {
			
			$('#ecriture-table').DataTable({
				dom : 'frtipB',
				"language" : { url: '${pageContext.request.contextPath}/assets1/json/datatable-fr-FR.json'},
				buttons : [ 'csv', 'excel', 'pdf' ]
			});
			$('#erreur-table').DataTable({
				dom : 'frtipB',
				rowGroup : {
					dataSrc : 0
				},
				"language" : { url: '${pageContext.request.contextPath}/assets1/json/datatable-fr-FR.json'},
				buttons : [ 'csv', 'excel', 'pdf' ],
				
			});
			$('#vehicule-table')
			.DataTable(
					{
						dom : 'frtipB',
						responsive: true,
						"language" : {
							url : '${pageContext.request.contextPath}/assets1/json/datatable-fr-FR.json'
						},
						buttons : [ 'csv', 'excel',
								'pdf' ]
					});
		});
		function openForm(element) {
			$('#myModal .modal-body').html('');
			$.ajax({url: "${pageContext.request.contextPath}/simulation/piece/"+element, success: function(result){
				$('#myModal .modal-body').html(result);
				$("#myModal").modal('show')
			  }});
		}
	</script>
<jsp:include page="../footer.jsp" />